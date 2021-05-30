package Scenes;

import View.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javax.sound.sampled.*;
import java.io.*;

public class GameScene extends Scene {

    private MapView mapView;
    private Clip clip;

    public GameScene(Group group, int MAP_LENGTH, int FRAME_LENGTH) {

        super(group,MAP_LENGTH,FRAME_LENGTH);

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);

        group = new Group();
        group.getChildren().add(borderPane);
        mapView = MapView.getMapView(MAP_LENGTH, gridPane);

        this.setRoot(group);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case UP ->{
                    if(mapView.getMapLU().y > 0) {
                        mapView.getMapLU().y--;
                        mapView.getMapRB().y--;
                    }
                }
                case DOWN -> {
                    if(mapView.getMapRB().y < 50) {
                        mapView.getMapLU().y++;
                        mapView.getMapRB().y++;
                    }
                }
                case LEFT -> {
                    if(mapView.getMapLU().x > 0) {
                        mapView.getMapLU().x--;
                        mapView.getMapRB().x--;
                    }
                }
                case RIGHT -> {
                    if(mapView.getMapRB().x < 50) {
                        mapView.getMapLU().x++;
                        mapView.getMapRB().x++;
                    }
                }
            }
            mapView.drawMap();
        });

        playBackMusic();
    }

    private void playBackMusic(){
        try {
            File soundFile = new File("resources/music/Game.wav");;
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(ais);

            clip.setFramePosition(0);
            clip.start();

            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException  exc) {
            exc.printStackTrace();
        } catch (UnsupportedAudioFileException exc) {
            exc.printStackTrace();
        } catch (LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }

}
