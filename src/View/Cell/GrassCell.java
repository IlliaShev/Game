package View.Cell;

import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class GrassCell extends Cell {

    private static final String imageURL = "file:resources\\images\\cells\\Grass.png";//path to image of grass

    public GrassCell(int length, int x, int y) {
        super(length, x, y,true, true, false);
    }


    @Override
    protected void clickResponse() {
        super.clickResponse();
        if(isReadyToBuild()) {
            if(getCityWhereBuild().getBuildings().size() < 8) {
                MapView.getMapView().changeCell(takeX(), takeY(), getCityWhereBuild(), this);
                setReadyToBuild(false);
            }
        }
        else if(isReadyToMove()){
            ArmyCell army = getArmyCell();
            getArmyCell().fillFields();
            this.setArmyCellView(army);
            MapView.getMapView().moveArmy(takeX(), takeY(), getArmyCell());
            getArmyCell().setPrevCell(this);
        }
    }


    @Override
    protected void setCellImage() {
        Image grass = new Image(imageURL);
        this.setFill(new ImagePattern(grass, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
