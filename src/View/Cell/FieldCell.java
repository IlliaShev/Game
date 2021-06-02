package View.Cell;

import View.MapView;
import View.PlayersHandler;
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
public class FieldCell extends Cell implements BuildingCell {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Field.png";//path to image of field
    private static final String imageEnemyURL = "file:resources\\images\\city\\buildings\\EnemyField.png";
    private Clip clip;
    private String sound = "resources\\music\\Field.wav";

    public FieldCell(int length, int x, int y) {
        super(length,x,y,false, true, false);
    }

    @Override
    protected void clickResponse() {
        super.clickResponse();
        if (isReadyToMove()) {
            getCityWhereBuild().deleteBuilding(this);
            ArmyCell army = getArmyCell();
            getArmyCell().fillFields();
            this.setArmyCellView(army);
            MapView.getMapView().moveArmy(takeX(), takeY(), getArmyCell());
            getArmyCell().setPrevCell(this);
            checkIfCanGotAttack();
            PlayersHandler.getPlayersHandler().getPlayer(1).moveArmy();
        }
        playSound();
    }



    @Override
    protected void setCellImage() {
        if(PlayersHandler.getPlayersHandler().getPlayer(0).hasCity(getCityWhereBuild())){
            fillCell(imageURL);
        } else{
            fillCell(imageEnemyURL);
        }
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
