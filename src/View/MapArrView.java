package View;

import View.Cell.*;

import java.awt.*;
import java.util.Random;

public class MapArrView {
    private static MapArrView mapArrView;
    private int rowsNumber;
    private int columnsNumber;
    private Cell[][] map;
    private final int CELL_WIDTH;
    private int levelNumber;

    private MapArrView(int CELL_WIDTH, int rowsNumber, int columnsNumber, int levelNumber) {
        this.CELL_WIDTH = CELL_WIDTH;
        this.rowsNumber = rowsNumber;
        this.columnsNumber = columnsNumber;
        map = new Cell[columnsNumber][rowsNumber];
        this.levelNumber = levelNumber;
        switch (levelNumber) {
            case 1:
                initFirstLevelMap();
                break;
            default:
                initMap();
                break;
        }
    }

    public static MapArrView getMapArr(int CELL_WIDTH, int rowsNumber, int columnsNumber, int levelNumber) {
        mapArrView = new MapArrView(CELL_WIDTH, rowsNumber, columnsNumber, levelNumber);
        return mapArrView;
    }

    public static MapArrView getMapArrView() {
        return mapArrView;
    }

    private void initMap() {
        for (int i = 0; i < columnsNumber; i++) {
            for (int j = 0; j < rowsNumber; j++) {
                if (map[i][j] == null) {
                    map[i][j] = generateCellFill(i, j);
                }
            }
        }
    }

    public void initFirstLevelMap() {
        Random rand = new Random();
        for (int i = 0; i < columnsNumber; i++) {
            for (int j = 0; j < 5; j++) {
                switch (rand.nextInt(3)) {
                    case 0 -> map[i][j] = new MountainCell(CELL_WIDTH, i, j);
                    case 1 -> map[i][j] = new ForestCell(CELL_WIDTH, i, j);
                    case 2 -> map[i][j] = new GrassCell(CELL_WIDTH, i, j);
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            switch (rand.nextInt(3)) {
                case 0 -> map[i][5] = new MountainCell(CELL_WIDTH, i, 5);
                case 1 -> map[i][5] = new ForestCell(CELL_WIDTH, i, 5);
                case 2 -> map[i][5] = new GrassCell(CELL_WIDTH, i, 5);
            }
        }
        CityCell cityCell = new CityCell(CELL_WIDTH, 4, 5);
        map[4][5] = cityCell;
        PlayersHandler.getPlayersHandler().getPlayer(0).addCityCell(cityCell);
        cityCell.setDefaultFill();
        for (int i = 5; i < 16; i++) {
            switch (rand.nextInt(3)) {
                case 0 -> map[i][5] = new MountainCell(CELL_WIDTH, i, 5);
                case 1 -> map[i][5] = new ForestCell(CELL_WIDTH, i, 5);
                case 2 -> map[i][5] = new GrassCell(CELL_WIDTH, i, 5);
            }
        }
        CityCell enemyCityCell = new CityCell(CELL_WIDTH, 16, 5);
        map[16][5] = enemyCityCell;
        PlayersHandler.getPlayersHandler().getPlayer(1).addCityCell(enemyCityCell);
        cityCell.setDefaultFill();
        for (int i = 17; i < columnsNumber; i++) {
            switch (rand.nextInt(3)) {
                case 0 -> map[i][5] = new MountainCell(CELL_WIDTH, i, 5);
                case 1 -> map[i][5] = new ForestCell(CELL_WIDTH, i, 5);
                case 2 -> map[i][5] = new GrassCell(CELL_WIDTH, i, 5);
            }
        }
        for (int i = 0; i < columnsNumber; i++) {
            for (int j = 6; j < rowsNumber; j++) {
                switch (rand.nextInt(3)) {
                    case 0 -> map[i][j] = new MountainCell(CELL_WIDTH, i, j);
                    case 1 -> map[i][j] = new ForestCell(CELL_WIDTH, i, j);
                    case 2 -> map[i][j] = new GrassCell(CELL_WIDTH, i, j);
                }
            }
        }
        generateBuildings(16, 5, enemyCityCell.getCity());
    }

    private Cell generateCellFill(int i, int j) {
        Random random = new Random();
        if (random.nextInt(100) > 98) {
            CityCell cityCell = new CityCell(CELL_WIDTH, i, j);
            if (PlayersHandler.getPlayersHandler().getPlayer(0).getCities().size() < 2) {
                System.out.println(i + " " + j);
                PlayersHandler.getPlayersHandler().getPlayer(0).addCityCell(cityCell);
                cityCell.setDefaultFill();

            } else {
                PlayersHandler.getPlayersHandler().getPlayer(1).addCityCell(cityCell);
                cityCell.setDefaultFill();
                generateBuildings(i, j, cityCell.getCity());
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
        for (int i = Math.max(x - 2, 0); i <= Math.min(x + 2, columnsNumber - 1); i++) {
            for (int j = Math.max(y - 2, 0); j <= Math.min(y + 2, rowsNumber - 1); j++) {
                if ((map[i][j] == null || map[i][j].isEmpty()) && i != x && j != y) {
                    Random random = new Random();
                    if (random.nextInt(3) >= 1 && city.getBuildings().size() < 8) {
                        changeCell(i, j, city, new GrassCell(CELL_WIDTH, i, j));
                    }
                }
            }
        }
    }


    public void changeCell(int i, int j, City cityWhereBuild, Cell prevCell) {
        Random random = new Random();
        int type = random.nextInt(4);
        switch (type) {
            case 0 -> {
                map[i][j] = new MineralCell(CELL_WIDTH, i, j);
                cityWhereBuild.decrementResMineral();
            }
            case 1 -> {
                map[i][j] = new FieldCell(CELL_WIDTH, i, j);
                cityWhereBuild.decrementResField();
            }
            case 2 -> {
                map[i][j] = new GoldmineCell(CELL_WIDTH, i, j);
                cityWhereBuild.decrementResGold();
            }
            case 3 -> {
                if (cityWhereBuild.getNumberOfArmy() < 3) {
                    map[i][j] = new ArmyCell(CELL_WIDTH, i, j);
                    ((ArmyCell) map[i][j]).setPrevCell(prevCell);
                    cityWhereBuild.decrementResGold();
                    cityWhereBuild.decrementResField();
                    cityWhereBuild.decrementResMineral();
                } else
                    return;
            }
        }
        map[i][j].setCityWhereBuild(cityWhereBuild);
        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
        map[i][j].setDefaultFill();
    }

    public void buildResources(int i, int j, City cityWhereBuild, Class<BuildingCell> type) {
        if (type.equals(MineralCell.class)) {
            map[i][j] = new MineralCell(CELL_WIDTH, i, j);
        } else if (type.equals(FieldCell.class)) {
            map[i][j] = new FieldCell(CELL_WIDTH, i, j);
        } else if (type.equals(GoldmineCell.class)) {
            map[i][j] = new GoldmineCell(CELL_WIDTH, i, j);
        } else {
            map[i][j] = new ArmyCell(CELL_WIDTH, i, j);
        }
        map[i][j].setCityWhereBuild(cityWhereBuild);
        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
        map[i][j].setDefaultFill();
    }

    public void changeCellOnGrass(int i, int j) {
        map[i][j] = new GrassCell(CELL_WIDTH, i, j);
    }

    public void changeCellOnForest(int i, int j) {
        map[i][j] = new ForestCell(CELL_WIDTH, i, j);
    }

    public void changeCellOnMountain(int i, int j) {
        map[i][j] = new MountainCell(CELL_WIDTH, i, j);
    }

//    public void changeCell(int i, int j, int type, City cityWhereBuild){
//        map[i][j] = new ArmyCell(CELL_WIDTH, i,j);
//        cityWhereBuild.addBuilding((BuildingCell) map[i][j]);
//    }

    public void moveArmy(int i, int j, ArmyCell armyCell) {
        Point armyCellP = new Point(armyCell.takeX(), armyCell.takeY());
        map[i][j] = armyCell;
        armyCell.setX(i);
        armyCell.setY(j);
        if (armyCell.getPrevCell() == null) {
            changeCellOnGrass(armyCellP.x, armyCellP.y);
            return;
        }
        if (armyCell.getPrevCell().getClass().equals(ForestCell.class)) {
            changeCellOnForest(armyCellP.x, armyCellP.y);
        } else if (armyCell.getPrevCell().getClass().equals(MountainCell.class)) {
            changeCellOnMountain(armyCellP.x, armyCellP.y);
        } else {
            changeCellOnGrass(armyCellP.x, armyCellP.y);
        }
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getRowsNumber() {
        return rowsNumber;
    }

    public int getColumnsNumber() {
        return columnsNumber;
    }
}
