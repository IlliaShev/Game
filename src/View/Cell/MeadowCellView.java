package View.Cell;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This is one of three types of cells on the board
 * Meadow cell with it's own image ready to be painted
 *
 * @author Vasia_Pupkin
 * @version 1.0.0
 * @see CellView
 */
public class MeadowCellView extends CellView {

    private static final String imageURL = "file:resources\\images\\cells\\Meadow.png";//path to image of meadow

    public MeadowCellView(int length) {
        super(length);
        setCellImage();
    }

    @Override
    protected void setCellImage() {
        Image mountain = new Image(imageURL);
        this.setFill(new ImagePattern(mountain, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }
}
