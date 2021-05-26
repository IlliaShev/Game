package View.Icons;

import View.Cell.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

import java.lang.reflect.*;

public class FieldIcon extends Icon {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Field.png";//path to image of field

    public FieldIcon(int length, int x, int y) {
        super(length,x,y);
    }

    @Override
    public void clickResponse() {

    }

    @Override
    protected void setCellImage() {
        Image field = new Image(imageURL);
        this.setFill(new ImagePattern(field, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }
}
