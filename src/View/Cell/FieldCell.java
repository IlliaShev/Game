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
public class FieldCell extends Cell implements BuildingCell {

    private static final String imageURL = "file:resources\\images\\city\\buildings\\Field.png";//path to image of field
    private Clip clip;
    private String sound = "C:\\Users\\lyubo\\Desktop\\JAVA - Останнє\\Game\\resources\\music\\Field.wav";

    public FieldCell(int length, int x, int y) {
        super(length,x,y,false);
        playSound();
    }

    public FieldCell(int length){
        super(length);
    }

    @Override
    protected void setCellImage() {
        Image field = new Image(imageURL);
        this.setFill(new ImagePattern(field, super.getX(), super.getY(), super.getWidth(), super.getHeight(), false));
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
