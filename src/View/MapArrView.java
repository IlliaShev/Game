package View;

import View.Cell.*;

import java.util.Random;

public class MapArrView {
    private static MapArrView mapArrView;
    private final int NUM_OF_ROWS = 50;
    private final int NUM_OF_COLUMNS = 50;
    private final CellView[][] map = new CellView[NUM_OF_ROWS][NUM_OF_COLUMNS];
    private final int CELL_WIDTH;
    private CityHandler cityHandler;

    private MapArrView(int CELL_WIDTH){
        this.CELL_WIDTH = CELL_WIDTH;
        cityHandler = CityHandler.getCityHandler();
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
                map[i][j] = generateCellFill(i,j);
            }
        }
    }

    private CellView generateCellFill(int i, int j) {
        Random random = new Random();
        if(random.nextInt(100)>98){
            City city = new City("SMT"+i+""+j);
            //Army army = new Army();
            //city.setArmy(army);
            //army.setCity(city);
            CityCellView cityCellView = new CityCellView(CELL_WIDTH, i, j);
            cityCellView.setCity(city);
            city.setCityCell(cityCellView);
            cityHandler.addCity(city);
            return cityCellView;
        }
        return switch (random.nextInt(3)) {
            case 0 -> new MountainCellView(CELL_WIDTH, i, j);
            case 1 -> new ForestCellView(CELL_WIDTH, i, j);
            case 2 -> new GrassCellView(CELL_WIDTH, i, j);
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        };
    }

    /*
    public void changeCell(int i, int j, int type, City cityWhereBuild){
        Random random = new Random();
        type = random.nextInt(3);
        switch (type) {
            case 0 -> map[i][j] = new MineralCellView(CELL_WIDTH, i,j);
            case 1 -> map[i][j] = new FieldCellView(CELL_WIDTH, i ,j);
            case 2 -> map[i][j] = new GoldmineCellView(CELL_WIDTH, i, j);
        }
        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
    }*/

    public void changeCell(int i, int j, int type, City cityWhereBuild){
        map[i][j] = new ArmyCellView(CELL_WIDTH, i,j);
        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
    }

    public CellView[][] getMap() {
        return map;
    }
}
