package game.main;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Random;

public class Game extends Thread{

    private Board borad;
    private GridPane gridPane;
    private Label[][] labels;
    public Game(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    @Override
    public void run() {
        borad = new Board();
        labels = new Label[4][4];
        for(int i=0;i<4;i++){
            for(int j=0; j<4;j++) {
                if (new Random().nextFloat() <= 0.2){
                    labels[i][j] = new Label("2");
                    setBackground(labels[i][j]);
                    int finalI = i;
                    int finalJ = j;
                    Platform.runLater(() -> gridPane.add(labels[finalI][finalJ], finalI, finalJ));
                }
                else if(new Random().nextFloat() <= 0.15){
                    labels[i][j] = new Label("4");
                    setBackground(labels[i][j]);
                    int finalI = i;
                    int finalJ = j;
                    Platform.runLater(() -> gridPane.add(labels[finalI][finalJ], finalI, finalJ));
                }
            }
        }
    }


    void setBackground(Label label){
        switch (label.getText()) {
            case "2":
                label.setStyle("-fx-background-color: #80FF00;");
                break;
            case "4":
                label.setStyle("-fx-background-color: #F0591E;");
                break;
            case "8":
                label.setStyle("-fx-background-color: #FFFF00;");
                break;
            case "16":
                label.setStyle("-fx-background-color: #994C00;");
                break;
            case "32":
                label.setStyle("-fx-background-color: #F05900;");
                break;
            case "64":
                label.setStyle("-fx-background-color: #990000;");
                break;
            case "128":
                label.setStyle("-fx-background-color: #F05900;");
                break;
            case "256":
                label.setStyle("-fx-background-color: #F0591E;");
                break;
            case "512":
                label.setStyle("-fx-background-color: #F05900;");
                break;
            case "1024":
                label.setStyle("-fx-background-color: #F0591E;");
                break;
            case "2048":
                label.setStyle("-fx-background-color: #F05900;");
                break;
            case "4096":
                label.setStyle("-fx-background-color: #F0591E;");
                break;
        }
    }

}
