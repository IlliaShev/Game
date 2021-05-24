package View;

import View.Cell.BuildingCell;
import View.Cell.CityCellView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class City {
    private String name;
    private int resMineral;
    private int resGold;
    private int resField;
    private static final int maxNumberOfBuildings = 8;
    private CityCellView cityCell;

    private ArrayList<BuildingCell> buildings;

    public City(String name) {
        this.name = name;
        buildings = new ArrayList<>();
        Thread collectorRes = new Thread(() -> {
            Thread currentThread = Thread.currentThread();
            while (!currentThread.isInterrupted()) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                collectResources();
            }
        });
        collectorRes.start();
    }


    public void addBuilding(BuildingCell buildingCell){
        buildings.add(buildingCell);
    }

    public String getName() {
        return name;
    }

    public CityCellView getCityCell() {
        return cityCell;
    }

    public void setCityCell(CityCellView cityCell) {
        this.cityCell = cityCell;
    }

    public ArrayList<BuildingCell> getBuildings() {
        return buildings;
    }

    public void collectResources(){
        for(BuildingCell buildingCell: buildings){
            if(buildingCell.getClass().getName().equals("View.Cell.FieldCellView"))
                resField++;
            else if(buildingCell.getClass().getName().equals("View.Cell.MineralCellView"))
                resMineral++;
            else
                resGold++;
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
}