package game.controllers;

import javafx.fxml.FXML;

public class ResultsController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //in future: add requests to db and show table (different threads)

    @FXML
    public void backMenu(){
        mainController.loadMenuScreen();
    }

}
