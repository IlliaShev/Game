package View.Cell;

import View.MapView;
import View.PlayersHandler;
import View.ToolPanel;
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
public class MineralCell extends Cell implements BuildingCell {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Mineral.png";//path to image of mineral
    private static final String imageEnemyURL = "file:resources\\images\\city\\buildings\\EnemyMineral.png";
    private Clip clip;
    private String sound = "resources\\music\\Mineral.wav";

    public MineralCell(int length, int x, int y) {
        super(length,x,y,false, true, false);
    }

    @Override
    protected void clickResponse() throws IOException, InterruptedException {
        super.clickResponse();
        if(isReadyToMove()){
            getCityWhereBuild().deleteBuilding(this);
            ArmyCell army = getArmyCell();
            getArmyCell().fillFields();
            this.setArmyCellView(army);
            MapView.getMapView().moveArmy(takeX(), takeY(), getArmyCell());
            getArmyCell().setPrevCell(null);
            if(army.getArmy().getHealth() > 0) {
                checkIfCanGotAttack();
            }
            PlayersHandler.getPlayersHandler().getPlayer(1).moveArmy();
        }  else if (ToolPanel.getInstance().getActionsPanel().isReadyToDelete()) {
            getCityWhereBuild().deleteBuilding(this);
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
