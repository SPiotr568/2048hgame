package game.controllers;

import game.main.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {

    private MainController mainController;
    private FXMLLoader loader;
    private Timer time;
    private Game game;
    private ShowScore scoreThread;
    private SendToDB sendToDBThread;
    private ExecutorService threadPoolGame;
    private ExecutorService threadPoolEnd;
    private String nick;

    @FXML
    private Label scoreLabel;

    @FXML
    public Label timerLabel;

    @FXML
    private GridPane gridPane;

    @FXML
    private void initialize() {
        scoreLabel.setText("0");
        timerLabel.setText("0:00");
        startGame();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @FXML
    public void endGame() {
        threadPoolGame.shutdownNow();
        mainController.loadMenuScreen();
    }

    public void setTimerLabel(String text) {
        timerLabel.setText(text);
    }

    public void setScoreLabel(String text) {
        scoreLabel.setText(text);
    }

    public void startGame() {
        threadPoolGame = Executors.newCachedThreadPool();
        time = new Timer(this);
        time.setTimerLabel(timerLabel);
        game = new Game(gridPane);
        threadPoolGame.submit(time);
        threadPoolGame.submit(game);
    }

    public void gameOver() {
        threadPoolGame.shutdownNow();
        gridPane.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, game.getControls());

        threadPoolEnd = Executors.newCachedThreadPool();
        //sent score to db
        sendToDBThread = new SendToDB();
        threadPoolEnd.submit(sendToDBThread);

        //show ScoreScreen
        scoreThread = new ShowScore(scoreLabel.getText(), nick);
        scoreThread.setMainController(mainController);

        threadPoolEnd.submit(scoreThread);
        threadPoolEnd.shutdown();
    }
}
