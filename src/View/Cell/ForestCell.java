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
public class ForestCell extends Cell {

    private static final String imageURL = "file:resources\\images\\cells\\Forest.jpg";//path to image of forest

    public ForestCell(int length, int x, int y) {
        super(length,x,y,true);
        setCellImage();
        listener();
    }


    private void listener(){
        this.setOnMouseClicked(mouseEvent -> {
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
        });
    }

    @Override
    protected void setCellImage() {
        Image mountain = new Image(imageURL);
        this.setFill(new ImagePattern(mountain, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}