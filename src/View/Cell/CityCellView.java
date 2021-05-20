package View.Cell;

import javafx.scene.image.*;
import javafx.scene.paint.*;

/**
 * This is one of three types of cells on the board
 * City cell with it's own image ready to be painted
 *
 * @author Liubcheck
 * @version 1.0.0
 * @see CellView
 */
public class CityCellView extends CellView{

    private static final String imageURL = "file:src\\View\\Cell\\City.png";//path to image of city

    public CityCellView(int length, double x, double y) {
        super(length,x,y);
        setCellImage();
    }

    @Override
    protected void setCellImage() {
        Image city = new Image(imageURL);
        this.setFill(new ImagePattern(city, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

    @Override
    protected void setCellCoordinates(double x, double y) {
        this.setX(x);
        this.setY(y);
    }
}
