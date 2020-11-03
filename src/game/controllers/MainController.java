package game.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {

    @FXML
    private StackPane mainStackPane;

    @FXML
    public void initialize(){
        loadMenuScreen();
    }

    void loadMenuScreen() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/game/fxml/MenuScreen.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MenuController menuController = loader.getController();
        menuController.setMainController(this);
        setScreen(pane);
        stage.setOnCloseRequest(e -> Platform.exit());
    }

    void setScreen(Pane pane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(pane);
    }
}
