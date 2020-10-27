package game.main;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import static java.lang.StrictMath.floor;

public class Timer extends Thread{

    private int time;
    private Label timer;
    private int min;
    private int sec;

    public Timer() {
        time = 0;
        min = 0;
        sec = 0;
    }

    @Override
    public void run() {
        try {
            while(time<100000){
                Thread.sleep(1000);
                time += 1;
                setTimer(time);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
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
}
