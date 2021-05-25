package View.Cell;

import View.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * A single cell on world map
 * Used to draw a single sell in {@link MapView}
 *
 * @author Liubcheck
 * @version 1.1.1
 * @see MapView
 */
public abstract class CellView extends Rectangle {

    private int length;
    private int x;
    private int y;
    private boolean isEmpty; //cell don't have any structures
    private boolean readyToBuild;
    private boolean readyToMove;
    private boolean isChosen;
    private City cityWhereBuild;
    private Army army;

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

    // private methods

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

    // public methods

    public int getLength() {
        return length;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public int takeX() {
        return x;
    }

    public int takeY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isReadyToBuild() {
        return readyToBuild;
    }

    public void setReadyToBuild(boolean readyToBuild) {
        this.readyToBuild = readyToBuild;
    }

    public boolean isReadyToMove() {
        return readyToMove;
    }

    public void setReadyToMove(boolean readyToMove) {
        this.readyToMove = readyToMove;
    }

    public void setCityWhereBuild(City cityWhereBuild) {
        this.cityWhereBuild = cityWhereBuild;
    }

    public City getCityWhereBuild() {
        return cityWhereBuild;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    protected boolean cellIsChosen() {
        CellView[][] cell = MapArrView.getMapArrView().getMap();
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(cell[i][j].isChosen()){
                    return true;
                }
            }
        }
        return false;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    /**
     * Restores default fill of empty
     *
     * @since 1.1.1
     */
    public void setDefaultFill() {
        if (this.isEmpty)
            setCellImage();
    }


    //abstract methods

    /**
     * Sets image of particular cell
     *
     * @see MountainCellView
     * @since 1.1.0
     */
    protected abstract void setCellImage();
}