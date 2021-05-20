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
public class FieldCellView extends CellView{

    private static final String imageURL = "file:src\\View\\Cell\\Field.png";//path to image of field

    public FieldCellView(int length, double x, double y) {
        super(length,x,y);
        setCellImage();
    }

    @Override
    protected void setCellImage() {
        Image field = new Image(imageURL);
        this.setFill(new ImagePattern(field, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }


    @Override
    protected void setCellCoordinates(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
}
