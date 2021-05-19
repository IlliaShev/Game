import javafx.scene.shape.*;

import java.io.*;

public class Cell extends Rectangle {

    public Cell(int length) throws FileNotFoundException {

        this.setWidth(length);
        this.setHeight(length);
        this.setStyle("-fx-fill: green; -fx-stroke: black");

        this.setOnMouseEntered(mouseEvent -> {
            setStyle("-fx-fill: green; -fx-stroke: red");
            toFront();
        });
        this.setOnMouseExited(mouseEvent -> {
            setStyle("-fx-fill: green; -fx-stroke: black");
            toBack();
        });
    }

}