package game.controllers;

import javafx.fxml.FXML;

public class GameController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void endGame(){
        mainController.loadMenuScreen();
    }



}
