package View.Icons;

import View.Cell.*;
import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class GoldmineIcon extends Icon {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Goldmine.png";//path to image of goldmine

    public GoldmineIcon(int length, int x, int y){
        super(length,x,y);
        highlight();
        click();
        setCellImage();
    }

    /**
    @Override
    protected void click() {
        CellView[][] cell = MapArrView.getMapArrView().getMap();
        int indX = this.takeX();
        int indY = this.takeY();
    }**/

    @Override
    protected void setCellImage() {
        Image goldmine = new Image(imageURL);
        this.setFill(new ImagePattern(goldmine, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

    @Override
    protected void click() {

    }
}
