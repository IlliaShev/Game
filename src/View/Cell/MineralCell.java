package View.Cell;

import javafx.scene.image.*;
import javafx.scene.paint.*;

/**
 * __
 * __
 *
 * @author Liubcheck
 * @version 1.0.0
 * @see Cell
 */
public class MineralCell extends Cell implements BuildingCell{

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Mineral.png";//path to image of mineral

    public MineralCell(int length, int x, int y) {
        super(length,x,y,false);
        setCellImage();
    }

    @Override
    protected void setCellImage() {
        Image mineral = new Image(imageURL);
        this.setFill(new ImagePattern(mineral, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
