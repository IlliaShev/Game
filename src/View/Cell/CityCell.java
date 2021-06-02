package View.Cell;

import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

/**
 * This is one of three types of cells on the board
 * City cell with it's own image ready to be painted
 *
 * @author Liubcheck
 * @version 1.0.0
 * @see Cell
 */
public class CityCell extends Cell implements Attackable {

    private static final String imageURL = "file:resources\\images\\city\\City.png";//path to image of city
    private static final String imageEnemyURL = "file:resources\\images\\city\\EnemyCity.png";//path to image of city
    private City city;

    public CityCell(int length, int x, int y) {
        super(length, x, y, false, false, true);
    }

    // private methods

    @Override
    protected void clickResponse() {
        super.clickResponse();
        if ((!cellIsChosen() || isChosen()) && PlayersHandler.getPlayersHandler().getPlayer(0).hasCity(city)) {
            buildBuildings();
        }
        if (isReadyToGotAttack()) {
            ArmyCell army = getArmyCell();
            getArmyCell().fillFields();
            Player player = PlayersHandler.getPlayersHandler().getPlayer(0);
            Player bot = PlayersHandler.getPlayersHandler().getPlayer(1);
            System.out.println(army.getArmy().getHealth() + " " + city.getHealth());
            player.attackCity(army.getArmy(), city);
            System.out.println(army.getArmy().getHealth() + " " + city.getHealth());
            if (army.getArmy().getHealth() > 0) {
                System.out.println("We won");
                player.addCity(city);
                bot.deleteCity(city);
                for (BuildingCell buildingCell : city.getBuildings()) {
                    ((Cell) buildingCell).setDefaultFill();
                }
                setDefaultFill();
                getCity().setHealth(100);
                MiniMap.getMiniMap().drawMiniMap();
            } else {
                System.out.println("We lose");
                MapView.getMapView().changeOnGrass(army.takeX(), army.takeY());
                army.getCityWhereBuild().deleteBuilding(army);
            }
        }
    }

    /**
     * private boolean cellIsChosen() {
     * Cell[][] cell = MapArrView.getMapArrView().getMap();
     * for(int i=0; i<50; i++){
     * for(int j=0; j<50; j++){
     * if(cell[i][j].isChosen()){
     * return true;
     * }
     * }
     * }
     * return false;
     * }
     **/

    public void buildBuildings() {
        this.setChosen(!this.isChosen());
        Cell[][] cell = MapArrView.getMapArrView().getMap();
        int indX = this.takeX();
        int indY = this.takeY();
        if (isChosen()) {
            System.out.println(city);
        }
        if (this.isChosen()) {
            for (int i = Math.max(indX - 2, 0); i <= Math.min(indX + 2, MapArrView.getMapArrView().getColumnsNumber()); i++) {
                for (int j = Math.max(indY - 2, 0); j <= Math.min(indY + 2, MapArrView.getMapArrView().getRowsNumber()); j++) {
                    if (cell[i][j].isEmpty()) {
                        cell[i][j].setFill(Paint.valueOf("ORANGE"));
                        cell[i][j].setReadyToBuild(true);
                        cell[i][j].setCityWhereBuild(city);
                    }
                }
            }
        } else {
            for (int i = Math.max(indX - 2, 0); i <= Math.min(indX + 2, MapArrView.getMapArrView().getColumnsNumber()); i++) {
                for (int j = Math.max(indY - 2, 0); j <= Math.min(indY + 2, MapArrView.getMapArrView().getRowsNumber()); j++) {
                    if (cell[i][j].isEmpty()) {
                        cell[i][j].setDefaultFill();
                        cell[i][j].setReadyToBuild(false);
                        cell[i][j].setCityWhereBuild(null);
                    }
                }
            }
        }
    }

    @Override
    protected void setCellImage() {
        if (PlayersHandler.getPlayersHandler().getPlayer(0).hasCity(getCity())) {
            fillCell(imageURL);
        } else {
            fillCell(imageEnemyURL);
        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
