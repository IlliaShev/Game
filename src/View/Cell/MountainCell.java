package View.Cell;

import View.MapView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * This is one of three types of cells on the board
 * Mountain cell with it's own image ready to be painted
 *
 * @author Vasia_Pupkin
 * @version 1.0.0
 * @see Cell
 */
public class MountainCell extends Cell {

    private static final String imageURL = "file:resources\\images\\cells\\Mountain.png";//path to image of mountain

    public MountainCell(int length, int x, int y) {
        super(length,x,y,false, true, false);
    }

    public MountainCell(int length) {
        super(length);
    }

    @Override
    protected void clickResponse() {
        super.clickResponse();
        System.out.println("xyi mountain debug");
        /*if(isReadyToBuild()) {
            if(getCityWhereBuild().getBuildings().size() < 8) {
                MapView.getMapView().changeCell(takeX(), takeY(), 1, getCityWhereBuild());
                setReadyToBuild(false);
            }
        }*/
        if(isReadyToMove()){
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
