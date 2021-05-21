package View;

import View.Cell.*;

import java.awt.*;

public class MapArrView {
    private static MapArrView mapArrView;
    private final int NUM_OF_ROWS = 50;
    private final int NUM_OF_COLUMNS = 50;
    private final CellView[][] map = new CellView[NUM_OF_ROWS][NUM_OF_COLUMNS];
    private final int CELL_WIDTH;

    private MapArrView(int CELL_WIDTH){
        this.CELL_WIDTH = CELL_WIDTH;
        initMap();
    }

    public static MapArrView getMapArr(int CELL_WIDTH){
        if(mapArrView == null)
            mapArrView = new MapArrView(CELL_WIDTH);
        return mapArrView;
    }

    public static MapArrView getMapArrView(){
        return mapArrView;
    }

    private void initMap() {
        for(int i = 0; i < NUM_OF_ROWS; i++){
            for(int j = 0; j < NUM_OF_COLUMNS; j++){
                if((i+j)%2==0)
                    map[i][j] = new MountainCellView(CELL_WIDTH,i,j);
                else if(i==13 && j==14)
                    map[i][j] = new CityCellView(CELL_WIDTH,i,j);
                else
                    map[i][j] = new GrassCellView(CELL_WIDTH,i,j);
            }
        }
    }

    public CellView[][] getMap() {
        return map;
    }
}
