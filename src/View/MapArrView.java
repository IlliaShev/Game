package View;

import View.Cell.*;
import java.awt.*;
import java.util.Random;

public class MapArrView {
    private static MapArrView mapArrView;
    private final int NUM_OF_ROWS = 50;
    private final int NUM_OF_COLUMNS = 50;
    private final Cell[][] map = new Cell[NUM_OF_ROWS][NUM_OF_COLUMNS];
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

    private Cell generateCellFill(int i, int j) {
        Random random = new Random();
        if(random.nextInt(100)>98){
            City city = new City("SMT"+i+""+j);
            //Army army = new Army();
            //city.setArmy(army);
            //army.setCity(city);
            CityCell cityCellView = new CityCell(CELL_WIDTH, i, j);
            cityCellView.setCity(city);
            city.setCityCell(cityCellView);
            cityHandler.addCity(city);
            return cityCellView;
        }
        return switch (random.nextInt(3)) {
            case 0 -> new MountainCell(CELL_WIDTH, i, j);
            case 1 -> new ForestCell(CELL_WIDTH, i, j);
            case 2 -> new GrassCell(CELL_WIDTH, i, j);
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        };
    }


    public void changeCell(int i, int j, int type, City cityWhereBuild){
        Random random = new Random();
        type = random.nextInt(4);
        switch (type) {
            case 0 -> map[i][j] = new MineralCell(CELL_WIDTH, i,j);
            case 1 -> map[i][j] = new FieldCell(CELL_WIDTH, i ,j);
            case 2 -> map[i][j] = new GoldmineCell(CELL_WIDTH, i, j);
            case 3 -> {
                if(cityWhereBuild.getNumberOfArmy() < 3)
                    map[i][j] = new ArmyCell(CELL_WIDTH, i, j);
                else
                    return;
            }
        }
        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
    }

//    public void changeCell(int i, int j, int type, City cityWhereBuild){
//        map[i][j] = new ArmyCell(CELL_WIDTH, i,j);
//        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
//    }

    public void moveArmy(int i, int j, ArmyCell armyCellView){
        Point armyCell = new Point(armyCellView.takeX(), armyCellView.takeY());
        map[i][j] = armyCellView;
        armyCellView.setX(i);
        armyCellView.setY(j);
        map[armyCell.x][armyCell.y] = new GrassCell(CELL_WIDTH, armyCell.x, armyCell.y);
    }

    public Cell[][] getMap() {
        return map;
    }
}
