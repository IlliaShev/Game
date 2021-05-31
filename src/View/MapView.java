package View;

import View.Cell.*;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class MapView {
    public static int CELL_WIDTH;
    private Point mapLU = new Point(0, 0);
    private Point mapRB = new Point(29, 15);//TODO figure out what happens here
    private MapArrView mapArrView;
    private Cell[][] map;
    private int width;
    private int height;
    private GridPane gridPane;
    private static MapView mapView;

    // get instance

    public static MapView getMapView(int width, int height, GridPane gridPane) {
        if (mapView == null)
            mapView = new MapView(width, height, gridPane);
        return mapView;
    }

    private MapView(int width, int height, GridPane gridPane) {
        this.height = height;
        this.width = width;
        this.gridPane = gridPane;
        CELL_WIDTH = Math.min(this.width / (mapRB.x - mapLU.x), this.height / (mapRB.y - mapLU.y));
        initGridPane();
        mapArrView = MapArrView.getMapArr(CELL_WIDTH);
        map = mapArrView.getMap();
        drawMap();
    }

    private void initGridPane() {
        gridPane.setMinWidth(width);
        gridPane.setMinHeight(height);
        gridPane.setPadding(new Insets(0, 0, 0, 0));
    }

    // getters & setters

    public Point getMapLU() {
        return mapLU;
    }

    public void setMapLU(Point mapLU) {
        if (mapLU.getX() > mapRB.getX() || mapLU.getY() > mapRB.getY())
            throw new IllegalArgumentException("");
        this.mapLU = mapLU;
        drawMap();
    }

    public Point getMapRB() {
        return mapRB;
    }

    public void setMapRB(Point mapRB) {
        this.mapRB = mapRB;
        drawMap();
    }

    public static MapView getMapView() {
        return mapView;
    }

    // methods

    public void drawMap() {
        gridPane.getChildren().clear();
        for (int i = mapLU.x; i < mapRB.x; i++) {
            for (int j = mapLU.y; j < mapRB.y; j++) {
                Cell cell = map[i][j];
                gridPane.add(cell, (i - mapLU.x), (j - mapLU.y));
            }
        }
    }

    public void changeCell(int i, int j,City cityWhereBuild, Cell prevCell){
        mapArrView.changeCell(i,j,cityWhereBuild, prevCell);
        drawMap();
    }

    public void moveArmy(int i, int j, ArmyCell armyCellView) {
        mapArrView.moveArmy(i, j, armyCellView);
        drawMap();
    }

}