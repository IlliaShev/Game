package View.Icons;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class Icon extends Rectangle {

    public Icon(int length, int x, int y){
        this.setWidth(length);
        this.setHeight(length);
        this.setX(x);
        this.setY(y);
        highlight();
        initClick();
        setCellImage();

    }

    private void highlight(){
        this.setOnMouseEntered(mouseEvent -> {
            mouseEnteredResponse();
        });

        this.setOnMouseExited(mouseEvent -> {
            mouseExitedResponse();
        });
    };

    public void setClickResponse(Runnable runnable){
        this.setOnMouseClicked(e -> runnable.run());
    }

    private void initClick() {
        this.setOnMouseClicked(mouseEvent -> clickResponse());
    }

    protected void mouseEnteredResponse(){
        this.setStroke(Paint.valueOf("ORANGE"));
    }

    protected void mouseExitedResponse(){
        this.setStroke(null);
    }

    protected abstract void clickResponse();

    protected abstract void setCellImage();

}
