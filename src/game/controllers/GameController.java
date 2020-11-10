package game.controllers;

import game.main.Game;
import game.main.ShowScore;
import game.main.Timer;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {

    private MainController mainController;
    private FXMLLoader loader;
    private Timer time;
    private Game game;
    private ShowScore scoreThread;
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
        startGame();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void endGame(){
        threadPoolGame.shutdownNow();
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
        time = new Timer(this);
        time.setTimerLabel(timerLabel);
        game = new Game(gridPane);
        threadPoolGame.submit(time);
        threadPoolGame.submit(game);
        //gameOver();
    }

    @FXML
    public void gameOver(){
        threadPoolGame.shutdownNow();
        System.out.println("a");
        //Platform.runLater( () -> {mainController.loadMenuScreen();});
        System.out.println("b");
        threadPoolEnd = Executors.newCachedThreadPool();
        //show ScoreScreen
        scoreThread = new ShowScore();
        scoreThread.setMainController(mainController);
        threadPoolEnd.submit(scoreThread);
        //sent score to db
        threadPoolEnd.shutdown();
    }


}
