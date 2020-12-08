package game.main;

import game.controllers.GameController;
import javafx.application.Platform;
import javafx.scene.control.Label;

import static java.lang.StrictMath.floor;

public class Timer extends Thread{

    private int time;
    private Label timer;
    private int min;
    private int sec;
    private boolean exit;
    private GameController gameController;

    public Timer(GameController gameController) {
        stopTimer();
        time = 0;
        min = 0;
        sec = 0;
        exit = false;
        this.gameController=gameController;
    }

    @Override
    public void run() {
        try {
            while(time<10000 && !exit){
                time += 1;
                Thread.sleep(1000);
                if(time>2){
                    gameController.gameOver();
                }
                setTimer(time);
            }
        } catch (InterruptedException e) {
            System.out.println("Timer stopped");
        }
    }

    public void setTimer(int time){
        String timeText;
        sec = time % 60;
        min = (int) floor(time / 60);
        if(sec < 10){
            timeText = min + ":0" + sec;
        }
        else timeText = min + ":" + sec;
        Platform.runLater(() -> timer.setText(timeText));
    }

    public void setTimerLabel(Label timerLabel) {
        this.timer = timerLabel;
    }

    public void stopTimer()
    {
        exit = true;
    }
}
