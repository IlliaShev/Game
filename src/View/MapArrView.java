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
                if(map[i][j]==null) {
                    map[i][j] = generateCellFill(i, j);
                }
            }
        }
    }

    private Cell generateCellFill(int i, int j) {
        Random random = new Random();
        if(random.nextInt(100)>98){
            CityCell cityCell = new CityCell(CELL_WIDTH, i, j);
            if(PlayersHandler.getPlayersHandler().getPlayer(0).getCities().size() < 2) {
                System.out.println(i + " " + j);
                PlayersHandler.getPlayersHandler().getPlayer(0).addCityCell(cityCell);

            } else{
                City city = new City("Enemy");
                cityCell.setCity(city);
                city.setCityCell(cityCell);
                CityHandler.getCityHandler().addCity(city);
                generateBuildings(i,j,city);
            }
            return cityCell;
        }
        return switch (random.nextInt(3)) {
            case 0 -> new MountainCell(CELL_WIDTH, i, j);
            case 1 -> new ForestCell(CELL_WIDTH, i, j);
            case 2 -> new GrassCell(CELL_WIDTH, i, j);
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(4));
        };
    }

    private void generateBuildings(int x, int y, City city) {
        for (int i = Math.max(x - 2, 0); i <= Math.min(x + 2, 49); i++) {
            for (int j = Math.max(y - 2, 0); j <= Math.min(y + 2, 49); j++) {
                if (map[i][j] == null||map[i][j].isEmpty() && i!=x&&j!=y) {
                    Random random = new Random();
                    if(random.nextInt(2) == 1 && city.getBuildings().size() < 8)
                        changeCell(i,j,city, new GrassCell(CELL_WIDTH, i, j));
                }
            }
        }
    }


    public void changeCell(int i, int j, City cityWhereBuild, Cell prevCell){
        Random random = new Random();
        int type = random.nextInt(4);
        switch (type) {
            case 0 -> map[i][j] = new MineralCell(CELL_WIDTH, i,j);
            case 1 -> map[i][j] = new FieldCell(CELL_WIDTH, i ,j);
            case 2 -> map[i][j] = new GoldmineCell(CELL_WIDTH, i, j);
            case 3 -> {
                if(cityWhereBuild.getNumberOfArmy() < 3) {
                    map[i][j] = new ArmyCell(CELL_WIDTH, i, j);
                    ((ArmyCell)map[i][j]).setPrevCell(prevCell);
                }
                else
                    return;
            }
        }
        map[i][j].setCityWhereBuild(cityWhereBuild);
        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
    }

    public void changeCellOnGrass(int i, int j){
        map[i][j] = new GrassCell(CELL_WIDTH, i, j);
    }

    public void changeCellOnForest(int i, int j) {
        map[i][j] = new ForestCell(CELL_WIDTH,i,j);
    }

    public void changeCellOnMountain(int i, int j){
        map[i][j] = new MountainCell(CELL_WIDTH,i,j);
    }

//    public void changeCell(int i, int j, int type, City cityWhereBuild){
//        map[i][j] = new ArmyCell(CELL_WIDTH, i,j);
//        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
//    }

    public void moveArmy(int i, int j, ArmyCell armyCell){
        Point armyCellP = new Point(armyCell.takeX(), armyCell.takeY());
        map[i][j] = armyCell;
        armyCell.setX(i);
        armyCell.setY(j);
        if(armyCell.getPrevCell() == null){
            changeCellOnGrass(armyCellP.x, armyCellP.y);
            return;
        }
        if(armyCell.getPrevCell().getClass().equals(ForestCell.class)) {
            changeCellOnForest(armyCellP.x, armyCellP.y);
        } else if(armyCell.getPrevCell().getClass().equals(MountainCell.class)){
            changeCellOnMountain(armyCellP.x, armyCellP.y);
        } else{
            changeCellOnGrass(armyCellP.x, armyCellP.y);
        }
    }

    public Cell[][] getMap() {
        return map;
    }
}
