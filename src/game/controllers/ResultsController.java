package game.controllers;

import game.main.GetFromDB;
import game.main.ResultData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.LoadException;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

public class ResultsController {

    private MainController mainController;
    private CountDownLatch startCdl;
    private CountDownLatch stopCdl;
    private int numberOfResults;
    private String [][] resultTab = new String[10][3];


    @FXML
    private TableView resultsTable;

    @FXML
    private Label unavailable;

    private TableColumn nick;
    private TableColumn score;
    private  TableColumn date;

    @FXML
    private void initialize(){
        getData();
    }

    public void getData(){
        String hostname = "localhost";
        int port = 2761;

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text;

            numberOfResults = 10;
            resultTab = new String[numberOfResults][3];
            startCdl = new CountDownLatch(1);
            stopCdl = new CountDownLatch(numberOfResults);
            for(int i = 0; i<numberOfResults; i++){
                new Thread(new GetFromDB(startCdl, stopCdl, i, resultTab, socket, writer)).start();
                Thread.sleep(5);
            }
            startCdl.countDown();

            try {
                stopCdl.await();
            } catch (InterruptedException ex) {
                System.out.println("Error with countdownlatch: " + ex);
            }
            writer.println("STOP");
            socket.close();
        } catch (UnknownHostException unknowHostEx) {
            System.out.println("Server not found... " + unknowHostEx.getMessage());
        } catch (IOException IOex) {
            System.out.println("I/O error... " + IOex.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(resultTab[0][0] == null){
            System.out.println("Server not available");
            unavailable.setText("Server not available!");
        }
        else{
            showTable();
        }
    }

    public void showTable(){
        nick = new TableColumn("NICK");
        nick.setPrefWidth(150);
        nick.setResizable(false);
        score = new TableColumn("SCORE");
        score.setPrefWidth(200);
        nick.setResizable(false);
        date = new TableColumn("DATE");
        date.setPrefWidth(200);
        nick.setResizable(false);


        resultsTable.getColumns().addAll(nick, score, date);

        nick.setCellValueFactory(
                new PropertyValueFactory<ResultData, String>("nickData")
        );
        score.setCellValueFactory(
                new PropertyValueFactory<ResultData, String>("scoreData")
        );
        date.setCellValueFactory(
                new PropertyValueFactory<ResultData, String>("dateData")
        );

        final ObservableList<ResultData> results = FXCollections.observableArrayList(
                //new ResultData("Piotr1999", "2048", "21/11/2020"),
                //new ResultData("Piotr12", "1024", "22/11/2020"),
                new ResultData(resultTab[0][0], resultTab[0][1], resultTab[0][2]),
                new ResultData(resultTab[1][0], resultTab[1][1], resultTab[1][2]),
                new ResultData(resultTab[2][0], resultTab[2][1], resultTab[2][2]),
                new ResultData(resultTab[3][0], resultTab[3][1], resultTab[3][2]),
                new ResultData(resultTab[4][0], resultTab[4][1], resultTab[4][2]),
                new ResultData(resultTab[5][0], resultTab[5][1], resultTab[5][2]),
                new ResultData(resultTab[6][0], resultTab[6][1], resultTab[6][2]),
                new ResultData(resultTab[7][0], resultTab[7][1], resultTab[7][2]),
                new ResultData(resultTab[8][0], resultTab[8][1], resultTab[8][2]),
                new ResultData(resultTab[9][0], resultTab[9][1], resultTab[9][2])
        );

        resultsTable.setEditable(true);
        resultsTable.setItems(results);
        resultsTable.setEditable(false);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //in future: add requests to db and show table (different threads)

    @FXML
    public void backMenu(){
        mainController.loadMenuScreen();
    }

}
