package View.Cell;

import View.MapView;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * A single cell on world map
 * Used to draw a single sell in {@link MapView}
 *
 * @author Liubcheck
 * @version 1.1.0
 * @see MapView
 */
public abstract class CellView extends Rectangle {

    private double x;
    private double y;

    public CellView(int length, double x, double y) {
        this.x = x;
        this.y = y;
        this.setWidth(length);
        this.setHeight(length);
        highlight();
        setCellImage();
        setCellCoordinates(x,y);
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

    /**
     * Sets x and y coordinates of particular cell
     *
     * @see MountainCellView
     * @since 1.1.0
     */
    protected abstract void setCellCoordinates(double x, double y);

}