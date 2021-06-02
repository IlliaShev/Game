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
    private int health;
    private int defenceDamage;
    private ArrayList<Army> armies;

    private ArrayList<BuildingCell> buildings;

    public City(String name) {
        this.name = name;
        resMineral = 6;
        resGold = 6;
        resField = 6;
        health = 100;
        defenceDamage = 20;
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDefenceDamage() {
        return defenceDamage;
    }

    public void setDefenceDamage() {
        this.defenceDamage = defenceDamage;
    }


    public void decrementResMineral() {
        resMineral--;
    }

    public void decrementResGold() {
        resGold--;
    }

    public void decrementResField() {
        resField--;
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


    public void receiveDamage(int damage){
        this.health -= damage;
        if(this.health<=0){
            this.health = 0;
        }
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

    public void deleteArmy(ArmyCell armyCell) {
        armies.remove(armyCell.getArmy());
        buildings.remove(armyCell);
    }
}
