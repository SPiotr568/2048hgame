package game.controllers;

import game.main.GetFromDB;
import game.main.ResultData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.concurrent.CountDownLatch;

public class ResultsController {

    private MainController mainController;
    private CountDownLatch startCdl;
    private CountDownLatch stopCdl;
    private int numberOfResults;
    private String resultTab [][];


    @FXML
    private TableView resultsTable;

    private TableColumn nick;
    private TableColumn score;
    private  TableColumn date;

    @FXML
    private void initialize(){
        numberOfResults = 10;
        resultTab = new String[numberOfResults][3];
        startCdl = new CountDownLatch(1);
        stopCdl = new CountDownLatch(numberOfResults);
        for(int i = 0; i<numberOfResults; i++){
            new Thread(new GetFromDB(startCdl, stopCdl, i, resultTab)).start();
        }
        startCdl.countDown();

        try {
            stopCdl.await();
        } catch (InterruptedException e) {
            System.out.println("Error with countdownlatch");
        }
        showTable();
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
                new ResultData("Piotr1999", "2048", "21/11/2020"),
                new ResultData("Piotr12", "1024", "22/11/2020"),
                new ResultData(resultTab[0][0], resultTab[0][1], resultTab[0][2]),
                new ResultData(resultTab[1][0], resultTab[1][1], resultTab[1][2]),
                new ResultData(resultTab[2][0], resultTab[2][1], resultTab[2][2]),
                new ResultData(resultTab[3][0], resultTab[3][1], resultTab[3][2])
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
