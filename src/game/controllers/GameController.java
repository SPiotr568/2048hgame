package game.controllers;

import game.main.Timer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import java.awt.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameController {

    private MainController mainController;
    private FXMLLoader loader;
    private Timer time;
    private ExecutorService threadPool;

    @FXML
    private Label scoreLabel;

    @FXML
    public Label timerLabel;

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
        mainController.loadMenuScreen();
        threadPool.shutdown();
    }


    public void setLabelText(String text){
        timerLabel.setText(text);
    }

    public void startGame(){
        threadPool = Executors.newCachedThreadPool();
        time = new Timer();
        time.setTimerLabel(timerLabel);

        threadPool.submit(time);
    }
}
