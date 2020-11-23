package game.controllers;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class StartingGameController {

    private MainController mainController;

    @FXML
    private TextField nick;

    @FXML
    private Label errorLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void backMenu(){
        mainController.loadMenuScreen();
    }

    @FXML
    public void startGame(){
        final Semaphore mutex = new Semaphore(1);
        try {
            if(nick.getText().isEmpty()){
                errorLabel.setText("Error!! Please enter your nick again!");
            }
            else{
                mutex.acquire();
                runGame();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mutex.release();
        }
    }


    public void runGame(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/GameScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        GameController gameController = loader.getController();
        gameController.setMainController(mainController);
        gameController.setNick(nick.getText());
        mainController.setScreen(pane);
    }
}
