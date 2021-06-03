package Scenes;

import View.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javax.sound.sampled.*;
import java.io.*;

public class SecondLevelScene extends Scene implements LevelScene{

    private LevelButton levelButton;
    private Clip clip;
    private MapView secondLevel;
    private int level;

    public SecondLevelScene(Group group, int FRAME_WIDTH, int FRAME_HEIGHT, LevelButton levelButton) {

        super(group, FRAME_WIDTH, FRAME_HEIGHT);
        this.levelButton = levelButton;

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        PlayersHandler.getPlayersHandler().clearPlayers();
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        secondLevel = MapView.getMapView(FRAME_WIDTH, 472, gridPane,21,21, this);
        borderPane.setBottom(ToolPanel.getInstance());
        group = new Group();
        group.getChildren().add(borderPane);

        this.setRoot(group);

        this.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP -> {
                    if (secondLevel.getMapLU().y > 0) {
                        secondLevel.getMapLU().y--;
                        secondLevel.getMapRB().y--;
                    }
                }
                case DOWN -> {
                    if (secondLevel.getMapRB().y < MapArrView.getMapArrView().getRowsNumber()) {
                        secondLevel.getMapLU().y++;
                        secondLevel.getMapRB().y++;
                    }
                }
                case LEFT -> {
                    if (secondLevel.getMapLU().x > 0) {
                        secondLevel.getMapLU().x--;
                        secondLevel.getMapRB().x--;
                    }
                }
                case RIGHT -> {
                    if (secondLevel.getMapRB().x < MapArrView.getMapArrView().getColumnsNumber()) {
                        secondLevel.getMapLU().x++;
                        secondLevel.getMapRB().x++;
                    }
                }
                case SPACE -> {
                    this.getLevelButton().setPassed(true);
                    for(LevelButton button : ChooseLevelScene.getLevelButtons()){

                    }
                }
            }
            MiniMap.getMiniMap().drawMiniMap();
            secondLevel.drawMap();
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
        return secondLevel;
    }
}
