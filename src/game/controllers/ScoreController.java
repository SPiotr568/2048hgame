package game.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ScoreController {
    private MainController mainController;
    private int score;

    @FXML
    private Label scoreLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void backMenu(){
        mainController.loadMenuScreen();
    }

    @FXML
    public void newGame(){

    }

}
