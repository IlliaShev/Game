package View.Cell;

import View.*;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This is one of three types of cells on the board
 * Forest cell with it's own image ready to be painted
 *
 * @author Vasia_Pupkin
 * @version 1.0.0
 * @see Cell
 */
public class ForestCell extends Cell implements EmptyCell {

    private static final String imageURL = "file:resources\\images\\cells\\Forest.jpg";//path to image of forest

    public ForestCell(int length, int x, int y) {
        super(length,x,y,true, true, false);
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
        Image mountain = new Image(imageURL);
        this.setFill(new ImagePattern(mountain, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}