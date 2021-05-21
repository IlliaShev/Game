package View.Cell;

import javafx.scene.image.*;
import javafx.scene.paint.*;

public class GrassCellView extends CellView{

    private static final String imageURL = "file:resources\\images\\cells\\Grass.png";//path to image of grass

    public GrassCellView(int length, int x, int y) {
        super(length, x, y,true);
    }



    @Override
    protected void setCellImage() {
        Image grass = new Image(imageURL);
        this.setFill(new ImagePattern(grass, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
