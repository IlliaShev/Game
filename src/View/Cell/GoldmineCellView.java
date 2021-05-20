package View.Cell;

import javafx.scene.image.*;
import javafx.scene.paint.*;

/**
 * __
 * __
 *
 * @author Liubcheck
 * @version 1.0.0
 * @see CellView
 */
public class GoldmineCellView extends CellView{

    private static final String imageURL = "file:src\\View\\Cell\\Goldmine.png";//path to image of goldmine

    public GoldmineCellView(int length, double x, double y) {
        super(length,x,y);
        setCellImage();
    }

    @Override
    protected void setCellImage() {
        Image goldmine = new Image(imageURL);
        this.setFill(new ImagePattern(goldmine, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

    @Override
    protected void setCellCoordinates(double x, double y) {
        if (true){
            this.setX(x);
            this.setY(y);
        }
    }
}
