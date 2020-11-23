package game.main;

import javafx.beans.property.SimpleStringProperty;

public class ResultData {
    private SimpleStringProperty nickData;
    private SimpleStringProperty scoreData;
    private SimpleStringProperty dateData;

    public ResultData(String nickData, String scoreData, String dateData) {
        this.nickData = new SimpleStringProperty(nickData);
        this.scoreData = new SimpleStringProperty(scoreData);
        this.dateData = new SimpleStringProperty(dateData);
    }

    public void setNickData(String nickData) {
        this.nickData.set(nickData);
    }

    public void setScoreData(String scoreData) {
        this.scoreData.set(scoreData);
    }

    public void setDateData(String dateData) {
        this.dateData.set(dateData);
    }

    public String getNickData() {
        return nickData.get();
    }

    public SimpleStringProperty nickDataProperty() {
        return nickData;
    }

    public String getScoreData() {
        return scoreData.get();
    }

    public SimpleStringProperty scoreDataProperty() {
        return scoreData;
    }

    public String getDateData() {
        return dateData.get();
    }

    public SimpleStringProperty dateDataProperty() {
        return dateData;
    }
}
