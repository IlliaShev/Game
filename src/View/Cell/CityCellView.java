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
public class CityCellView extends CellView {

    private static final String imageURL = "file:resources\\images\\city\\City.png";//path to image of city
    private boolean isChosen = false;

    public CityCellView(int length, int x, int y) {
        super(length, x, y, false);
        setCellImage();
        initCityClick();
    }

    // private methods

    private void initCityClick() {
        this.setOnMouseClicked(mouseEvent -> {
            this.isChosen = !isChosen;
            CellView[][] cell = MapArrView.getMapArrView().getMap();
            int indX = this.takeX();
            int indY = this.takeY();
            if (this.isChosen) {
                for (int i = Math.max(indX - 2,0); i <= Math.min(indX + 2,49); i++) {
                    for (int j = Math.max(indY - 2,0); j <= Math.min(indY + 2,49); j++) {
                        if (cell[i][j].isEmpty()) {
                            cell[i][j].setFill(Paint.valueOf("ORANGE"));
                            cell[i][j].setReadyToBuild(true);
                        }
                    }
                }
            } else {
                for (int i = Math.max(indX - 2,0); i <= Math.min(indX + 2,49); i++) {
                    for (int j = Math.max(indY - 2,0); j <= Math.min(indY + 2,49); j++) {
                        if (cell[i][j].isEmpty()) {
                            cell[i][j].setDefaultFill();
                        }
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
