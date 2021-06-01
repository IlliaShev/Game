package View.Cell;


import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class MiniMapCell extends Rectangle {
    private int length;

    public MiniMapCell(int length, String color) {
        this.length = length;
        setWidth(length);
        setHeight(length);
        setFill(Paint.valueOf(color));
        setStroke(Paint.valueOf("BLACK"));
    }
}
