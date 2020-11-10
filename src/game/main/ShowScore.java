package game.main;

import game.controllers.GameController;
import game.controllers.MainController;
import game.controllers.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ShowScore extends Thread {

    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void run() {
        System.out.println("startSS");
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/ScoreScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoreController scoreController = loader.getController();
        scoreController.setMainController(mainController);
        Pane finalPane = pane;
        Platform.runLater( () -> {
            mainController.setScreen(finalPane);
        });

        System.out.println("stopSS");
    }

}
