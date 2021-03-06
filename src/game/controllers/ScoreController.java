package game.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class ScoreController {
    private MainController mainController;

    private String score;
    private String nick;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label nickLabel;

    @FXML
    private Label dbsendLabel;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


    public void setScore(String score) {
        this.score = score;
        setScoreLabel();
    }

    public void setScoreLabel() {
        scoreLabel.setText(this.score);
    }

    public void setNick(String nick) {
        this.nick = nick;
        setNickLabel();
    }

    public void setNickLabel() {
        nickLabel.setText(this.nick);
    }

    @FXML
    public void backMenu(){
        mainController.loadMenuScreen();
    }

    @FXML
    public void newGame(){
        final Semaphore mutex = new Semaphore(1);
        try {
            mutex.acquire();
            runGame();
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
        gameController.setNick(nick);
        Pane finalPane = pane;
        Platform.runLater( () -> mainController.setScreen(finalPane));
    }

    public void setDbsend(boolean dbSend) {
        if(dbSend == false){
            dbsendLabel.setText("Server with database is not available");
        }
    }
}
