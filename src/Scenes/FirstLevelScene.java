package Scenes;

import View.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javax.sound.sampled.*;
import java.io.*;

public class FirstLevelScene extends Scene {

    private Clip clip;
    private MapView firstLevel;

    public FirstLevelScene(Group group, int FRAME_WIDTH, int FRAME_HEIGHT) {

        super(group, FRAME_WIDTH, FRAME_HEIGHT);

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        firstLevel = MapView.getMapView(FRAME_WIDTH, FRAME_HEIGHT / 4 * 3, gridPane,21,12, 1);
        borderPane.setBottom(ToolPanel.getInstance());
        group = new Group();
        group.getChildren().add(borderPane);


        this.setRoot(group);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    if (firstLevel.getMapLU().y > 0) {
                        firstLevel.getMapLU().y--;
                        firstLevel.getMapRB().y--;
                    }
                }
                case DOWN -> {
                    if (firstLevel.getMapRB().y < MapArrView.getMapArrView().getRowsNumber()) {
                        firstLevel.getMapLU().y++;
                        firstLevel.getMapRB().y++;
                    }
                }
                case LEFT -> {
                    if (firstLevel.getMapLU().x > 0) {
                        firstLevel.getMapLU().x--;
                        firstLevel.getMapRB().x--;
                    }
                }
                case RIGHT -> {
                    if (firstLevel.getMapRB().x < MapArrView.getMapArrView().getColumnsNumber()) {
                        firstLevel.getMapLU().x++;
                        firstLevel.getMapRB().x++;
                    }
                }
            }
            firstLevel.drawMap();
        });

        playBackMusic();
    }

    private void playBackMusic() {
        try {
            File soundFile = new File("resources/music/Game.wav");
            ;
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(ais);

            clip.setFramePosition(0);
            clip.start();

            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (IOException exc) {
            exc.printStackTrace();
        } catch (UnsupportedAudioFileException exc) {
            exc.printStackTrace();
        } catch (LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }
}
