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
public class GoldmineCell extends Cell implements BuildingCell {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Goldmine.png";//path to image of goldmine

    public GoldmineCell(int length, int x, int y) {
        super(length,x,y,false);
    }

    @Override
    protected void setCellImage() {
        Image goldmine = new Image(imageURL);
        this.setFill(new ImagePattern(goldmine, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
