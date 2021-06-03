package Scenes;

import View.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javax.sound.sampled.*;
import java.io.*;

public class FifthLevelScene extends Scene implements LevelScene{

    private LevelButton levelButton;
    private Clip clip;
    private MapView fifthLevel;

    public FifthLevelScene(Group group, int FRAME_WIDTH, int FRAME_HEIGHT, LevelButton levelButton) {

        super(group, FRAME_WIDTH, FRAME_HEIGHT);
        this.levelButton = levelButton;

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        PlayersHandler.getPlayersHandler().clearPlayers();
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        fifthLevel = MapView.getMapView(FRAME_WIDTH, 472, gridPane,25,25, this);
        borderPane.setBottom(ToolPanel.getInstance());
        ToolPanel.getInstance().initMapPanel();
        group = new Group();
        group.getChildren().add(borderPane);


        this.setRoot(group);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    if (fifthLevel.getMapLU().y > 0) {
                        fifthLevel.getMapLU().y--;
                        fifthLevel.getMapRB().y--;
                    }
                }
                case DOWN -> {
                    if (fifthLevel.getMapRB().y < MapArrView.getMapArrView().getRowsNumber()) {
                        fifthLevel.getMapLU().y++;
                        fifthLevel.getMapRB().y++;
                    }
                }
                case LEFT -> {
                    if (fifthLevel.getMapLU().x > 0) {
                        fifthLevel.getMapLU().x--;
                        fifthLevel.getMapRB().x--;
                    }
                }
                case RIGHT -> {
                    if (fifthLevel.getMapRB().x < MapArrView.getMapArrView().getColumnsNumber()) {
                        fifthLevel.getMapLU().x++;
                        fifthLevel.getMapRB().x++;
                    }
                }
                case SPACE -> {
                    this.getLevelButton().setPassed(true);
                    if(this.getLevelButton().isPassed()){
                        this.getLevelButton().changeBackground();
                        this.getMapView().getLevelScene().getClip().stop();
                        this.getMapView().getLevelScene().getClip().setMicrosecondPosition(0);
                        StartMenuScene.getStage().setScene(StartMenuScene.getStartMenuScene());
                        StartMenuScene.getStartMenuScene().playBackMusic();
                    }
                }
            }
            MiniMap.getMiniMap().drawMiniMap();
            fifthLevel.drawMap();
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

        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public LevelButton getLevelButton() {
        return levelButton;
    }

    @Override
    public Clip getClip() {
        return clip;
    }

    public MapView getMapView() {
        return fifthLevel;
    }

}
