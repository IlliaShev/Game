package View.Icons;

import View.Cell.*;
import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class GoldmineIcon extends Icon {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Goldmine.png";//path to image of goldmine

    public GoldmineIcon(int length, int x, int y){
        super(length,x,y);
    }

    @Override
    public void clickResponse() {

    }



    @Override
    protected void setCellImage() {
        Image goldmine = new Image(imageURL);
        this.setFill(new ImagePattern(goldmine, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }
}
