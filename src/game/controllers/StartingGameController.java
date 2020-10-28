package game.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

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
        if(nick.getText().isEmpty()){
            errorLabel.setText("Error, please enter your nick again!");
        }
        else {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/GameScreen.fxml"));
            Pane pane = null;
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            GameController gameController = loader.getController();
            gameController.setMainController(mainController);
            mainController.setScreen(pane);
        }
    }

}
