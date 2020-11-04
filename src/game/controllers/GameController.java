package game.controllers;

import game.main.Game;
import game.main.Timer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {

    private MainController mainController;
    private FXMLLoader loader;
    private Timer time;
    private Game game;
    private ExecutorService threadPoolGame;
    private ExecutorService threadPoolEnd;

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
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
        startGame();
    }

    @FXML
    public void endGame(){
        threadPoolGame.shutdown();
        mainController.loadMenuScreen();
    }


    public void setTimerLabel(String text){
        timerLabel.setText(text);
    }

    public void setScoreLabel(String text){
        scoreLabel.setText(text);
    }

    public void startGame(){
        threadPoolGame = Executors.newCachedThreadPool();
        time = new Timer();
        time.setTimerLabel(timerLabel);
        game = new Game(gridPane);
        threadPoolGame.submit(time);
        threadPoolGame.submit(game);
    }

    public void gameOver(){
        threadPoolGame.shutdown();
        threadPoolEnd = Executors.newCachedThreadPool();
        threadPoolEnd.shutdown();
        //sent score to db
        //show ScoreScreen
    }
}
