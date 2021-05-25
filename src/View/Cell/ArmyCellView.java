package View.Cell;

import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

public class ArmyCellView extends CellView implements BuildingCell{

    private static final String imageURL = "file:resources\\images\\city\\Knight.png";//path to image of city
    private Army army;
    private CityHandler cityHandler;

    public ArmyCellView(int length, int x, int y) {
        super(length, x, y, false);
        cityHandler = CityHandler.getCityHandler();
        setCellImage();
        initArmyClick();
    }

    private void initArmyClick(){
        this.setOnMouseClicked(mouseEvent -> {
            if(!cellIsChosen() || isChosen()){
                this.setChosen(!this.isChosen());
                CellView[][] cell = MapArrView.getMapArrView().getMap();
                int indX = this.takeX();
                int indY = this.takeY();
                if (isChosen()) {
                    for (int i = Math.max(indX - 1, 0); i <= Math.min(indX + 1, 49); i++) {
                        for (int j = Math.max(indY - 1, 0); j <= Math.min(indY + 1, 49); j++) {
                            if (cell[i][j].isEmpty()) {
                                cell[i][j].setFill(Paint.valueOf("RED"));
                                cell[i][j].setReadyToBuild(false);
                                cell[i][j].setReadyToMove(true);
                            }
                        }
                    }
                } else {
                    for (int i = Math.max(indX - 1, 0); i <= Math.min(indX + 1, 49); i++) {
                        for (int j = Math.max(indY - 1, 0); j <= Math.min(indY + 1, 49); j++) {
                            if (cell[i][j].isEmpty()) {
                                cell[i][j].setDefaultFill();
                                cell[i][j].setReadyToBuild(false);
                                cell[i][j].setReadyToMove(false);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void setCellImage() {
        Image knight = new Image(imageURL);
        this.setFill(new ImagePattern(knight, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }

    public Army getArmy(){
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }
}
