package View;

import View.Cell.*;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import java.awt.*;

public class MapView {
    public static int CELL_WIDTH;
    private Point mapLU = new Point(10,10);
    private Point mapRB = new Point(25,25);
    private MapArrView mapArrView;
    private Cell[][] map;
    private int length;
    private GridPane gridPane;
    private static MapView mapView;

    private MapView(int length, GridPane gridPane){
        this.length = length;
        this.gridPane = gridPane;
        CELL_WIDTH = length / 15;
        initGridPane();
        mapArrView = MapArrView.getMapArr(CELL_WIDTH);
        map = mapArrView.getMap();
        drawMap();
    }

    public static MapView getMapView(int length, GridPane gridPane){
        if(mapView == null)
            mapView = new MapView(length, gridPane);
        return mapView;
    }

    public static MapView getMapView(){
        return mapView;
    }

    private void initGridPane() {
        gridPane.setMinWidth(length);
        gridPane.setMinHeight(length);
        gridPane.setPadding(new Insets(0,0,0,0));
    }


    public void drawMap(){
        gridPane.getChildren().clear();
        for (int i = mapLU.x; i < mapRB.x; i++) {
            for (int j = mapLU.y; j <mapRB.y ; j++) {
                Cell cell = map[i][j];
                gridPane.add(cell, (i - mapLU.x),(j - mapLU.y));
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

    public void changeCell(int i, int j, int type, City cityWhereBuild){
        mapArrView.changeCell(i,j,type, cityWhereBuild);
        drawMap();
    }

    public void moveArmy(int i, int j, ArmyCell armyCellView){
        mapArrView.moveArmy(i,j,armyCellView);
        drawMap();
    }

}