package View.Icons;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class Icon extends Rectangle {

    private int x;
    private int y;

    public Icon(int length, int x, int y){
        this.setWidth(length);
        this.setHeight(length);
        this.x = x;
        this.y = y;
    }

    public void highlight(){
        this.setOnMouseEntered(mouseEvent -> {
            this.setStroke(Paint.valueOf("ORANGE"));
        });

        this.setOnMouseExited(mouseEvent -> {
            this.setStroke(null);
        });
    };

    protected abstract void setCellImage();

    protected abstract void click();
}
