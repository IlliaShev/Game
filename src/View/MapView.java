package View;

import View.Cell.*;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

import java.awt.*;

public class MapView{
    private final int CELL_WIDTH;
    private Point mapLU = new Point(10,10);
    private Point mapRB = new Point(25,25);
    private MapArrView mapArrView;
    private CellView[][] map;
    private int length;
    private GridPane gridPane;

    public MapView(int length, GridPane gridPane){
        this.length = length;
        this.gridPane = gridPane;
        CELL_WIDTH = length / 15;
        initGridPane();
        mapArrView = MapArrView.getMapArr(CELL_WIDTH);
        map = mapArrView.getMap();
        drawMap();
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
                CellView cell = map[i][j];
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
}