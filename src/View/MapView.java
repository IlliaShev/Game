package View;

import View.Cell.*;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class MapView {
    public static int CELL_WIDTH;
    private Point mapLU = new Point(0, 0);
    private Point mapRB;
    private MapArrView mapArrView;
    private Cell[][] map;
    private int width;
    private int height;
    private GridPane gridPane;
    private static MapView mapView;
    private int levelNumber;

    private MapView(int width, int height, GridPane gridPane, int x, int y, int levelNumber) {
        this.width = width;
        this.height = height;
        this.gridPane = gridPane;
        mapRB = new Point(21,12);
        this.levelNumber = levelNumber;
        CELL_WIDTH = Math.min(width / (mapRB.x - mapLU.x), height / (mapRB.y - mapLU.y));
        initGridPane();
        mapArrView = MapArrView.getMapArr(CELL_WIDTH,y,x,levelNumber);
        map = mapArrView.getMap();
        drawMap();
    }

    public static MapView getMapView(int length, GridPane gridPane, int x, int y, int levelNumber) {
        if (mapView == null)
            mapView = new MapView(length, length, gridPane, x, y, levelNumber);
        return mapView;
    }

    public static MapView getMapView() {
        return mapView;
    }

    public static MapView getMapView(int frame_width, int frame_height, GridPane gridPane, int x, int y, int levelNumber) {
        if (mapView == null)
            mapView = new MapView(frame_width, frame_height, gridPane, x, y, levelNumber);
        return mapView;
    }

    private void initGridPane() {
        gridPane.setMinWidth(width);
        gridPane.setMinHeight(height);
        gridPane.setPadding(new Insets(0, 0, 0, 0));
    }


    public void drawMap() {
        gridPane.getChildren().clear();
        for (int i = mapLU.x; i < mapRB.x; i++) {
            for (int j = mapLU.y; j < mapRB.y; j++) {
                Cell cell = map[i][j];
                gridPane.add(cell, (i - mapLU.x), (j - mapLU.y));
            }
        }
    }

    public Point getMapLU() {
        return mapLU;
    }

    public void setMapLU(Point mapLU) {
        this.mapLU = mapLU;
    }

    public Point getMapRB() {
        return mapRB;
    }

    public void setMapRB(Point mapRB) {
        this.mapRB = mapRB;
    }

    public void changeCell(int i, int j, City cityWhereBuild, Cell prevCell) {
        mapArrView.changeCell(i, j, cityWhereBuild, prevCell);
        drawMap();
        MiniMap.getMiniMap().drawMiniMap();
    }

    public void buildResources(int i, int j, City cityWhereBuild, Class<BuildingCell> type){
        mapArrView.buildResources(i,j,cityWhereBuild,type);
        drawMap();
        MiniMap.getMiniMap().drawMiniMap();
    }

    public void changeOnGrass(int i, int j) {
        mapArrView.changeCellOnGrass(i, j);
        drawMap();
        MiniMap.getMiniMap().drawMiniMap();
    }

    public void moveArmy(int i, int j, ArmyCell armyCellView) {
        mapArrView.moveArmy(i, j, armyCellView);
        drawMap();
        MiniMap.getMiniMap().drawMiniMap();
    }

}