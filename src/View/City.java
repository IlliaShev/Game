package View;

import View.Cell.*;

import java.util.ArrayList;

public class City {
    private String name;
    private int resMineral;
    private int resGold;
    private int resField;
    public static final int maxNumberOfBuildings = 8;
    private CityCell cityCell;
    private int numberOfArmy;
    private Player player;
    private ArrayList<Army> armies;

    private ArrayList<BuildingCell> buildings;

    public City(String name) {
        this.name = name;
        resMineral = 4;
        resGold = 4;
        resField = 4;
        buildings = new ArrayList<>();
        armies = new ArrayList<>();
        Thread collectorRes = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            while (!currentThread.isInterrupted()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collectResources();
                spendResources();
            }
        });
        collectorRes.start();
    }


    public void addBuilding(BuildingCell buildingCell){
        buildings.add(buildingCell);
        if(buildingCell.getClass().equals(ArmyCell.class)){
            Army army = new Army();
            ((ArmyCell) buildingCell).setArmy(army);
            army.setArmyCell((ArmyCell) buildingCell);
            army.setCity(this);
            if(armies.size()<3){
                numberOfArmy++;
            }
            armies.add(army);
        }
    }

    public void deleteBuilding(BuildingCell buildingCell){
        buildings.remove(buildingCell);
    }

    public boolean hasBuilding(BuildingCell buildingCell){
        return buildings.contains(buildingCell);
    }

    public ArrayList<Army> getArmies() {
        return armies;
    }

    public int getNumberOfArmy() {
        return numberOfArmy;
    }

    public String getName() {
        return name;
    }

    public CityCell getCityCell() {
        return cityCell;
    }

    public void setCityCell(CityCell cityCell) {
        this.cityCell = cityCell;
    }

    public ArrayList<BuildingCell> getBuildings() {
        return buildings;
    }

    public void collectResources(){
        for(BuildingCell buildingCell: buildings){
            if(buildingCell.getClass().equals(FieldCell.class))
                resField++;
            else if(buildingCell.getClass().equals(MineralCell.class))
                resMineral++;
            else if(buildingCell.getClass().equals(GoldmineCell.class))
                resGold++;
        }
    }

    public void spendResources(){
        if(resField > 0)
            resField--;
        if(resGold > 0)
            resGold--;
        if(resMineral > 0)
            resMineral--;
    }



    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", resMineral=" + resMineral +
                ", resGold=" + resGold +
                ", resField=" + resField +
                '}';
    }
}
