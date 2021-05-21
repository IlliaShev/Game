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

    private int length;
    private int x;
    private int y;
    private boolean isEmpty;

    public CellView(int length, int x, int y, boolean isEmpty) {
        this.x = x;
        this.y = y;
        this.isEmpty = isEmpty;
        this.setWidth(length);
        this.setHeight(length);
        highlight();
        setCellImage();
        this.setStroke(Paint.valueOf("BLACK"));
    }

    public CellView(int length) {
        this.x = 0;
        this.y = 0;
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

    public int getLength(){
        return length;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int takeX(){
        return x;
    }

    public int takeY(){
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}