package View.Cell;

import View.*;
import com.sun.tools.javac.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

import java.util.*;

/**
 * This is one of three types of cells on the board
 * City cell with it's own image ready to be painted
 *
 * @author Liubcheck
 * @version 1.0.0
 * @see CellView
 */
public class CityCellView extends CellView{

    private static final String imageURL = "file:resources\\images\\city\\City.png";//path to image of city

    public CityCellView(int length, int x, int y) {
        super(length,x,y,false);
        setCellImage();
        this.setOnMouseClicked(mouseEvent -> {

            CellView[][] cell = MapArrView.getMapArrView().getMap();
            int indX = this.takeX();
            int indY = this.takeY();

            for(int i=indX-2; i<=indX+2; i++){
                for(int j=indY-2; j<=indY+2; j++){
                    if(cell[i][j].isEmpty()){
                        cell[i][j].setFill(Paint.valueOf("ORANGE"));
                    }
                }
            }
        });
    }

    @Override
    protected void setCellImage() {
        Image city = new Image(imageURL);
        this.setFill(new ImagePattern(city, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

}
