package View.Cell;

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
public class MineralCell extends Cell implements BuildingCell{

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Mineral.png";//path to image of mineral
    private Clip clip;
    private String sound = "C:\\Users\\lyubo\\Desktop\\JAVA - Останнє\\Game\\resources\\music\\Mineral.wav";

    public MineralCell(int length, int x, int y) {
        super(length,x,y,false);
        playSound();
    }

    @Override
    protected void setCellImage() {
        Image mineral = new Image(imageURL);
        this.setFill(new ImagePattern(mineral, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
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
