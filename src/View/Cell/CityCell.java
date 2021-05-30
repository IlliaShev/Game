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
public class CityCell extends Cell {

    private static final String imageURL = "file:resources\\images\\city\\City.png";//path to image of city
    private City city;

    public CityCell(int length, int x, int y) {
        super(length, x, y, false, false);
    }

    // private methods

    @Override
    protected void clickResponse() {
        super.clickResponse();
        if((!cellIsChosen() || isChosen()) &&Player.getPlayer().hasCity(city)) {
            this.setChosen(!this.isChosen());
            Cell[][] cell = MapArrView.getMapArrView().getMap();
            int indX = this.takeX();
            int indY = this.takeY();
            if(isChosen()) {
                System.out.println(city);
            }
            if (this.isChosen()) {
                for (int i = Math.max(indX - 2, 0); i <= Math.min(indX + 2, 49); i++) {
                    for (int j = Math.max(indY - 2, 0); j <= Math.min(indY + 2, 49); j++) {
                        if (cell[i][j].isEmpty()) {
                            cell[i][j].setFill(Paint.valueOf("ORANGE"));
                            cell[i][j].setReadyToBuild(true);
                            cell[i][j].setCityWhereBuild(city);
                        }
                    }
                }
            } else {
                for (int i = Math.max(indX - 2, 0); i <= Math.min(indX + 2, 49); i++) {
                    for (int j = Math.max(indY - 2, 0); j <= Math.min(indY + 2, 49); j++) {
                        if (cell[i][j].isEmpty()) {
                            cell[i][j].setDefaultFill();
                            cell[i][j].setReadyToBuild(false);
                            cell[i][j].setCityWhereBuild(null);
                        }
                    }
                }
            }
        }
    }

    /**
    private boolean cellIsChosen() {
        Cell[][] cell = MapArrView.getMapArrView().getMap();
        for(int i=0; i<50; i++){
            for(int j=0; j<50; j++){
                if(cell[i][j].isChosen()){
                    return true;
                }
            }
        }
        return false;
    }**/

    @Override
    protected void setCellImage() {
        Image city = new Image(imageURL);
        this.setFill(new ImagePattern(city, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
