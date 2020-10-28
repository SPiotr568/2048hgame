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
                labels[i][j] = new Label("2");
                System.out.println(i + ":"+j +"\n" );
                labels[i][j].setAlignment(Pos.CENTER);
                labels[i][j].setPrefSize(70, 70);
                labels[i][j].setStyle("-fx-font-size: 30px;-fx-border-radius: 5px; -fx-border-width: 2px; -fx-border-color: black; -fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.8), 10, 0, 0, 0);");
                int finalI = i;
                int finalJ = j;
                Platform.runLater(() -> gridPane.add(labels[finalI][finalJ], finalI, finalJ));
            }
        }
    }
}
