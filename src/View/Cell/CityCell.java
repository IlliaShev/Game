package View.Cell;

import Scenes.*;
import View.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import java.io.*;
import java.util.concurrent.*;

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
    private MapArrView mapArrView;

    public CityCell(int length, int x, int y, MapArrView mapArrView) {
        super(length, x, y, false, false, true);
        this.mapArrView = mapArrView;
    }

    // private methods

    @Override
    protected void clickResponse() throws IOException, InterruptedException {
        super.clickResponse();
        if(isReadyToGotAttack()) {
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
                if(player.getCities().size()==player.getLevel()+1){
                    mapArrView.getMapView().getLevelScene().getLevelButton().setPassed(true);
                    if(mapArrView.getMapView().getLevelScene().getLevelButton().isPassed()){
                        mapArrView.getMapView().getLevelScene().getLevelButton().changeBackground();
                        mapArrView.getMapView().getLevelScene().getClip().stop();
                        mapArrView.getMapView().getLevelScene().getClip().setMicrosecondPosition(0);
                        WinScene winScene = new WinScene(new GridPane(), StartMenuScene.takeWidth(),
                                StartMenuScene.takeHeight(),mapArrView.getMapView().getLevelScene());
                        StartMenuScene.getStage().setScene(winScene);
                    }
                }
            } else {
                System.out.println("We lose");
                MapView.getMapView().changeOnGrass(army.takeX(), army.takeY());
                army.getCityWhereBuild().deleteBuilding(army);
                player.deleteCity(city);
                if(player.getCities().size()==0){
                    mapArrView.getMapView().getLevelScene().getClip().stop();
                    mapArrView.getMapView().getLevelScene().getClip().setMicrosecondPosition(0);
                    LoseScene loseScene = new LoseScene(new GridPane(),
                            StartMenuScene.takeWidth(), StartMenuScene.takeHeight(),mapArrView.getMapView().getLevelScene());;
                    StartMenuScene.getStage().setScene(loseScene);
                }
            }
        }
    }

    public void changeTerritoryHighlight() {
        //this.setChosen(!this.isChosen());
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
