package View.Cell;

import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class GrassCell extends Cell {

    private static final String imageURL = "file:resources\\images\\cells\\Grass.png";//path to image of grass

    public GrassCell(int length, int x, int y) {
        super(length, x, y,true);
    }


    @Override
    protected void clickResponse() {
        super.clickResponse();
        if(isReadyToBuild()) {
            if(getCityWhereBuild().getBuildings().size() < 8) {
                MapView.getMapView().changeCell(takeX(), takeY(), 1, getCityWhereBuild());
                setReadyToBuild(false);
            }
        }
        else if(isReadyToMove()){
            ArmyCell army = getArmyCellView();
            getArmyCellView().fillFields();
            this.setArmyCellView(army);
            MapView.getMapView().moveArmy(takeX(), takeY(), getArmyCellView());
        }
    }


    @Override
    protected void setCellImage() {
        Image grass = new Image(imageURL);
        this.setFill(new ImagePattern(grass, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
