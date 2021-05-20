package View.Cell;

import View.MapView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * A single cell on world map
 * Used to draw a single sell in {@link MapView}
 *
 * @author Liubciek
 * @version 1.1.0
 * @see MapView
 */
public abstract class CellView extends Rectangle {

    public CellView(int length) {
        this.setWidth(length);
        this.setHeight(length);
        highlight();
        setCellImage();
        this.setStroke(Paint.valueOf("BLACK"));
    }

    /**
     * Method highlights the cell when mouse is pointed on it
     *
     * @since 1.1.0
     */
    private void highlight() {

        this.setOnMouseEntered(mouseEvent -> {
            this.setStroke(Paint.valueOf("ORANGE"));
            toFront();
        });

        this.setOnMouseExited(mouseEvent -> {
            this.setStroke(Paint.valueOf("BLACK"));
            toBack();
        });
    }

    /**
     * Sets image of particular cell
     *
     * @see MountainCellView
     * @since 1.1.0
     */
    protected abstract void setCellImage();

}