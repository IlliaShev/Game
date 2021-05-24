package View.Icons;

import View.Cell.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class MineralIcon extends Icon {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Mineral.png";//path to image of mineral

    public MineralIcon(int length, int x, int y) {
        super(length,x,y);
        highlight();
        click();
        setCellImage();
    }

    @Override
    protected void click(){
        this.setOnMouseClicked(mouseEvent -> {

        });
    }

    @Override
    protected void setCellImage() {
        Image mineral = new Image(imageURL);
        this.setFill(new ImagePattern(mineral, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }
}
