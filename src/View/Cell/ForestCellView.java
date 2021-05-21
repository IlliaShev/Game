package View.Cell;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This is one of three types of cells on the board
 * Forest cell with it's own image ready to be painted
 *
 * @author Vasia_Pupkin
 * @version 1.0.0
 * @see CellView
 */
public class ForestCellView extends CellView {

    private static final String imageURL = "file:resources\\images\\cells\\Forest.jpg";//path to image of meadow

    public ForestCellView(int length, int x, int y) {
        super(length,x,y,true);
        setCellImage();
    }

    public ForestCellView(int length) {
        super(length);
        setCellImage();
    }

    @Override
    protected void setCellImage() {
        Image mountain = new Image(imageURL);
        this.setFill(new ImagePattern(mountain, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}