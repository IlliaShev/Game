package View.Cell;

import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class GrassCellView extends CellView{

    private static final String imageURL = "file:resources\\images\\cells\\Grass.png";//path to image of grass

    public GrassCellView(int length, int x, int y) {
        super(length, x, y,true);
        setCellImage();
        listener();
    }


    private void listener(){
        this.setOnMouseClicked(mouseEvent -> {
            if(isReadyToBuild()) {
                if(getCityWhereBuild().getBuildings().size() < City.maxNumberOfBuildings) {
                    MapView.getMapView().changeCell(takeX(), takeY(), 1, getCityWhereBuild());
                    setReadyToBuild(false);
                }
            }
            else if(isReadyToMove()){
                ArmyCellView army = getArmyCellView();
                getArmyCellView().fillFields();
                this.setArmyCellView(army);
                MapView.getMapView().moveArmy(takeX(), takeY(), getArmyCellView());
            }
        });
    }


    @Override
    protected void setCellImage() {
        Image grass = new Image(imageURL);
        this.setFill(new ImagePattern(grass, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
