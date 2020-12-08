package game.main;

import game.controllers.GameController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import java.util.Random;

public class Game extends Thread{

    private final GridPane gridPane;
    private Label[][] labels = {};
    private Controls controls;
    private GameController gameController;

    public Game(GridPane gridPane, GameController gc) {
        this.gridPane = gridPane;
        this.gameController = gc;
    }

    public Controls getControls() {
        return controls;
    }

    @Override
    public void run() {
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
                else{
                    labels[i][j] = new Label("");
                    int finalI = i;
                    int finalJ = j;
                    Platform.runLater(() -> gridPane.add(labels[finalI][finalJ], finalI, finalJ));
                }
            }
        }
        controls = new Controls(this);
        this.gridPane.getScene().addEventFilter(KeyEvent.KEY_PRESSED, controls);
    }

    public void addRandom(){
        for(int i=0;i<4;i++){
            for(int j=0; j<4;j++) {
                if (labels[i][j].getText() == "" && new Random().nextFloat() <= 0.1){
                    labels[i][j].setText("2");
                    setBackground(labels[i][j]);
                }
                else if(labels[i][j].getText() == "" && new Random().nextFloat() <= 0.05){
                    labels[i][j].setText("4");
                    setBackground(labels[i][j]);
                }
            }
        }
    }

    public boolean canMove(){
        int counter = 0;
        for(int i=0;i<4;i++) {
            for (int j = 0; j < 4; j++) {
                if(labels[i][j].getText() != ""){
                    counter++;
                }
                if(i<3 && j<3){
                    if(labels[i][j].getText() == labels[i][j+1].getText()){
                        return true;
                    }
                    else if(labels[i][j].getText() == labels[i+1][j].getText()){
                        return true;
                    }
                }
            }
        }
        if(counter==16){
            return false;
        }
        return true;
    }

    void moveUP(){
        if(canMove()){
            for(int i=0;i<4;i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStart " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }

            for(int i=0;i<4;i++) {
                for (int j = 0; j < 3; j++) {
                    if(labels[i][j].getText() == ""){
                        System.out.println("Pozycjaijwczesniej " + i + ":" + j + "->" + labels[i][j].getText());
                        break;
                    }
                    for (int k = j + 1; k < 4; k++) {
                        System.out.println("Pozycjaij " + i + ":" + j + "->" + labels[i][j].getText());
                        System.out.println("Pozycjaik " + i + ":" + k + "->" + labels[i][k].getText());
                        if(labels[i][k].getText() == ""){
                            System.out.println("if");
                        }
                        else if(Integer.parseInt(labels[i][j].getText()) == Integer.parseInt(labels[i][k].getText())){
                            int newV = Integer.parseInt(labels[i][j].getText()) * 2;
                            labels[i][j].setText(String.valueOf(newV));
                            labels[i][k].setText("");
                            setBackground(labels[i][j]);
                            setBackground(labels[i][k]);
                            int newScore = Integer.parseInt(gameController.getScoreLabel().getText()) + newV;
                            gameController.setScoreLabel(String.valueOf(newScore));
                        }
                        else if(Integer.parseInt(labels[i][j].getText()) != Integer.parseInt(labels[i][k].getText())){
                            System.out.println("elseif");
                            break;
                        }
                    }
                }
                for(int n=0;n<3;n++) {
                    for (int j = 3; j > 0; j--) {
                        if (labels[i][j].getText() != "" && labels[i][j - 1].getText() == "") {
                            labels[i][j - 1].setText(labels[i][j].getText());
                            setBackground(labels[i][j - 1]);
                            labels[i][j].setText("");
                            setBackground(labels[i][j]);
                        }
                    }
                }
            }
            for(int i=0;i<4;i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStop " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }
            addRandom();
        }
        else{
            gameController.gameOver();
        }
    }

    void moveDOWN(){
        if(canMove()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStart " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 3; j > 0; j--) {
                    if (labels[i][j].getText() == "") {
                        System.out.println("Pozycjaijwczesniej " + i + ":" + j + "->" + labels[i][j].getText());
                        break;
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        System.out.println("Pozycjaij " + i + ":" + j + "->" + labels[i][j].getText());
                        System.out.println("Pozycjaik " + i + ":" + k + "->" + labels[i][k].getText());
                        if (labels[i][k].getText() == "") {
                            System.out.println("if");
                        } else if (Integer.parseInt(labels[i][j].getText()) == Integer.parseInt(labels[i][k].getText())) {
                            int newV = Integer.parseInt(labels[i][j].getText()) * 2;
                            labels[i][j].setText(String.valueOf(newV));
                            labels[i][k].setText("");
                            setBackground(labels[i][j]);
                            setBackground(labels[i][k]);
                            int newScore = Integer.parseInt(gameController.getScoreLabel().getText()) + newV;
                            gameController.setScoreLabel(String.valueOf(newScore));
                        } else if (Integer.parseInt(labels[i][j].getText()) != Integer.parseInt(labels[i][k].getText())) {
                            System.out.println("elseif");
                            break;
                        }
                    }
                }
                for (int n = 3; n > 0; n--) {
                    for (int j = 0; j < 3; j++) {
                        if (labels[i][j].getText() != "" && labels[i][j + 1].getText() == "") {
                            labels[i][j + 1].setText(labels[i][j].getText());
                            setBackground(labels[i][j + 1]);
                            labels[i][j].setText("");
                            setBackground(labels[i][j]);
                        }
                    }
                }
            }
            for(int i=0;i<4;i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStop " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }
            addRandom();
        }
        else{
            gameController.gameOver();
        }
    }
    void moveLEFT(){
        if(canMove()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStart " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    if (labels[j][i].getText() == "") {
                        break;
                    }
                    for (int k = j + 1; k < 4; k++) {
                        System.out.println("Pozycjaij " + j + ":" + i + "->" + labels[j][i].getText());
                        System.out.println("Pozycjaik " + k + ":" + i + "->" + labels[k][i].getText());
                        if (labels[k][i].getText() == "") {
                            System.out.println("if");
                        } else if (Integer.parseInt(labels[j][i].getText()) == Integer.parseInt(labels[k][i].getText())) {
                            int newV = Integer.parseInt(labels[j][i].getText()) * 2;
                            labels[j][i].setText(String.valueOf(newV));
                            labels[k][i].setText("");
                            setBackground(labels[j][i]);
                            setBackground(labels[k][i]);
                            int newScore = Integer.parseInt(gameController.getScoreLabel().getText()) + newV;
                            gameController.setScoreLabel(String.valueOf(newScore));
                        } else if (Integer.parseInt(labels[j][i].getText()) != Integer.parseInt(labels[k][i].getText())) {
                            System.out.println("elseif");
                            break;
                        }
                    }
                }
                for (int n = 0; n < 3; n++) {
                    for (int j = 3; j > 0; j--) {
                        if (labels[j][i].getText() != "" && labels[j - 1][i].getText() == "") {
                            labels[j - 1][i].setText(labels[j][i].getText());
                            setBackground(labels[j - 1][i]);
                            labels[j][i].setText("");
                            setBackground(labels[j][i]);
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStop " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }
            addRandom();
        }
        else{
            gameController.gameOver();
        }
    }
    void moveRIGHT(){
        if(canMove()) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStart " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 3; j > 0; j--) {
                    if (labels[j][i].getText() == "") {
                        break;
                    }
                    for (int k = j - 1; k >= 0; k--) {
                        System.out.println("Pozycjaij " + j + ":" + i + "->" + labels[j][i].getText());
                        System.out.println("Pozycjaik " + k + ":" + i + "->" + labels[k][i].getText());
                        if (labels[k][i].getText() == "") {
                            System.out.println("if");
                        } else if (Integer.parseInt(labels[j][i].getText()) == Integer.parseInt(labels[k][i].getText())) {
                            int newV = Integer.parseInt(labels[j][i].getText()) * 2;
                            labels[j][i].setText(String.valueOf(newV));
                            labels[k][i].setText("");
                            setBackground(labels[j][i]);
                            setBackground(labels[k][i]);
                            int newScore = Integer.parseInt(gameController.getScoreLabel().getText()) + newV;
                            gameController.setScoreLabel(String.valueOf(newScore));
                        } else if (Integer.parseInt(labels[j][i].getText()) != Integer.parseInt(labels[k][i].getText())) {
                            System.out.println("elseif");
                            break;
                        }
                    }
                }
                for (int n = 0; n < 3; n++) {
                    for (int j = 0; j < 3; j++) {
                        if (labels[j][i].getText() != "" && labels[j + 1][i].getText() == "") {
                            labels[j + 1][i].setText(labels[j][i].getText());
                            setBackground(labels[j + 1][i]);
                            labels[j][i].setText("");
                            setBackground(labels[j][i]);
                        }
                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println("PozycjaStop " + i + ":" + j + "->" + labels[i][j].getText());
                }
            }
            addRandom();
        }
        else{
            gameController.gameOver();
        }
    }


    void setBackground(Label label){
        switch (label.getText()) {
            case "":
                label.setStyle("-fx-background-color: #FFFFFF;");
                break;
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
                label.setStyle("-fx-background-color: #d0f0c0;");
                break;
            case "256":
                label.setStyle("-fx-background-color: #FFC213;");
                break;
            case "512":
                label.setStyle("-fx-background-color: #e6e6fa;");
                break;
            case "1024":
                label.setStyle("-fx-background-color: #52af52;");
                break;
            case "2048":
                label.setStyle("-fx-background-color: #4b9681;");
                break;
            case "4096":
                label.setStyle("-fx-background-color: #fe2e2e;");
                break;
        }
    }

}
