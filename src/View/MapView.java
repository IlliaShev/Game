package View;

import View.Cell.CellView;
import View.Cell.MountainCellView;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

import java.awt.*;

public class MapView{
    private final int CELL_WIDTH;
    private final int NUM_OF_ROWS = 50;
    private final int NUM_OF_COLUMNS = 50;
    private final CellView[][] map = new CellView[NUM_OF_ROWS][NUM_OF_COLUMNS];
    private Point mapLU;
    private Point mapRB;
    private int length;
    private Group group;

    public MapView(int length, Group group){
        this.length = length;
        this.group = group;
        CELL_WIDTH = length / 15;
        initMap();
        drawMap();
    }

    private void initMap() {
        mapLU = new Point(13,13);
        mapRB = new Point(14,15);
        for(int i = 0; i < NUM_OF_ROWS; i++){
            for(int j = 0; j < NUM_OF_COLUMNS; j++){
                map[i][j] = new MountainCellView(CELL_WIDTH);
            }
        }
    }

    public void drawMap(){
        for (int i = mapLU.x; i < mapRB.x; i++) {
            for (int j = mapLU.y; j <mapRB.y ; j++) {
                CellView cell = new MountainCellView(CELL_WIDTH);
                cell.setX((i - mapLU.y)*CELL_WIDTH);
                cell.setY((j - mapLU.x)*CELL_WIDTH);
                group.getChildren().add(cell);
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
