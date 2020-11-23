package game.main;

import game.controllers.MainController;
import game.controllers.ScoreController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ShowScore extends Thread {

    private MainController mainController;
    private String nick;
    private String score;

    public ShowScore(String score, String nick) {
        this.nick = nick;
        this.score = score;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void run() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/ScoreScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ScoreController scoreController = loader.getController();
        scoreController.setMainController(mainController);
        scoreController.setNick(nick);
        scoreController.setScore(score);
        Pane finalPane = pane;
        Platform.runLater(() -> mainController.setScreen(finalPane));
    }

}
