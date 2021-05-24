package View.Cell;

import View.MapView;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
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
                if(getCityWhereBuild().getBuildings().size() < 8) {
                    MapView.getMapView().changeCell(takeX(), takeY(), 1, getCityWhereBuild());
                    setReadyToBuild(false);
                }
            }
        });
    }


    @Override
    protected void setCellImage() {
        Image grass = new Image(imageURL);
        this.setFill(new ImagePattern(grass, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
