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
    private boolean dbSend;

    public ShowScore(String score, String nick, boolean dbSend) {
        this.nick = nick;
        this.score = score;
        this.dbSend = dbSend;
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
        scoreController.setDbsend(dbSend);
        Pane finalPane = pane;
        Platform.runLater(() -> mainController.setScreen(finalPane));
    }

}
