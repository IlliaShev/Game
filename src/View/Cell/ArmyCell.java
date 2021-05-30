package View.Cell;

import View.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;

import javax.sound.sampled.*;
import java.io.*;

public class ArmyCell extends Cell implements BuildingCell{

    private static final String imageURL = "file:resources\\images\\city\\Knight.png";//path to image of city
    private Army army;
    private Cell prevCell;
    private Clip clip;
    private String sound = "resources\\music\\Army.wav";

    public ArmyCell(int length, int x, int y) {
        super(length, x, y, false, false);
        playSound();
    }

    @Override
    protected void clickResponse() {
        super.clickResponse();
        if((!cellIsChosen() || isChosen()) && Player.getPlayer().hasArmy(army)){
            fillFields();
        }
    }

    public void fillFields() {
        this.setChosen(!this.isChosen());
        Cell[][] cell = MapArrView.getMapArrView().getMap();
        int indX = this.takeX();
        int indY = this.takeY();
        if (isChosen()) {
            for (int i = Math.max(indX - 1, 0); i <= Math.min(indX + 1, 49); i++) {
                for (int j = Math.max(indY - 1, 0); j <= Math.min(indY + 1, 49); j++) {
                    if (cell[i][j].isArmyCanMove() && isProperCell(cell[i][j])) {
                        cell[i][j].setFill(Paint.valueOf("RED"));
                        cell[i][j].setReadyToBuild(false);
                        cell[i][j].setReadyToMove(true);
                        cell[i][j].setArmyCellView(this);
                    }
                }
            }
        } else {
            for (int i = Math.max(indX - 1, 0); i <= Math.min(indX + 1, 49); i++) {
                for (int j = Math.max(indY - 1, 0); j <= Math.min(indY + 1, 49); j++) {
                    if (cell[i][j].isArmyCanMove()) {
                        cell[i][j].setDefaultFill();
                        cell[i][j].setReadyToBuild(false);
                        cell[i][j].setReadyToMove(false);
                        cell[i][j].setArmyCellView(null);
                    }
                }
            }
        }
    }

    private boolean isProperCell(Cell cell) {
        if(!(cell instanceof BuildingCell))
            return true;
        for(City city: Player.getPlayer().getCities()){
            if(city.hasBuilding((BuildingCell) cell))
                return false;
        }
        return true;
    }

    public Army getArmy() {
        return army;
    }

    public void setArmy(Army army) {
        this.army = army;
    }

    public Cell getPrevCell() {
        return prevCell;
    }

    public void setPrevCell(Cell prevCell) {
        this.prevCell = prevCell;
    }

    @Override
    protected void setCellImage() {
        Image knight = new Image(imageURL);
        this.setFill(new ImagePattern(knight, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
    }


    private void playSound(){
        try {
            File soundFile = new File(sound);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();

        } catch (IOException  exc) {
            exc.printStackTrace();
        } catch (UnsupportedAudioFileException exc) {
            exc.printStackTrace();
        } catch (LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }
}
