package game.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MenuController {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void openResults(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/ResultsScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResultsController resultsController = loader.getController();
        resultsController.setMainController(mainController);
        mainController.setScreen(pane);
    }

    @FXML
    public void newGame(){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/StartingGameScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StartingGameController startingGameController = loader.getController();
        startingGameController.setMainController(mainController);
        mainController.setScreen(pane);
    }

}
