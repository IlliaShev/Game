package View.Cell;

import View.MapView;
import javafx.scene.image.*;
import javafx.scene.paint.*;

import javax.sound.sampled.*;
import java.io.*;

/**
 * __
 * __
 *
 * @author Liubcheck
 * @version 1.0.0
 * @see Cell
 */
public class GoldmineCell extends Cell implements BuildingCell {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Goldmine.png";//path to image of goldmine
    private Clip clip;
    private String sound = "resources\\music\\Goldmine.wav";

    public GoldmineCell(int length, int x, int y) {
        super(length,x,y,false, true, false);
    }

    @Override
    protected void clickResponse() {
        super.clickResponse();
        if(isReadyToMove()){
            getCityWhereBuild().deleteBuilding(this);
            ArmyCell army = getArmyCell();
            getArmyCell().fillFields();
            this.setArmyCellView(army);
            MapView.getMapView().moveArmy(takeX(), takeY(), getArmyCell());
            getArmyCell().setPrevCell(this);
        }
        playSound();
    }

    @Override
    protected void setCellImage() {
        Image goldmine = new Image(imageURL);
        this.setFill(new ImagePattern(goldmine, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
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
