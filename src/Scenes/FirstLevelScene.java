package Scenes;

import View.*;
import javafx.scene.*;
import javafx.scene.layout.*;

import javax.sound.sampled.*;
import java.io.*;

public class FirstLevelScene extends Scene implements LevelScene{

    private LevelButton levelButton;
    private Clip clip;
    private MapView firstLevel;

    public FirstLevelScene(Group group, int FRAME_WIDTH, int FRAME_HEIGHT, LevelButton levelButton) {

        super(group, FRAME_WIDTH, FRAME_HEIGHT);
        this.levelButton = levelButton;

        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);
        PlayersHandler.getPlayersHandler().clearPlayers();
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        firstLevel = MapView.getMapView(FRAME_WIDTH, 472, gridPane,21,12, this);
        borderPane.setBottom(ToolPanel.getInstance());
        ToolPanel.getInstance().initMapPanel();
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
                case ESCAPE -> {
                    try {
                        StartMenuScene.getStage().setScene(new PauseScene(new GridPane(),FRAME_WIDTH,FRAME_HEIGHT,this));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                case SPACE -> {
                    this.getLevelButton().setPassed(true);
                    if(this.getLevelButton().isPassed()){
                        this.getLevelButton().changeBackground();
                        this.getMapView().getLevelScene().getClip().stop();
                        this.getMapView().getLevelScene().getClip().setMicrosecondPosition(0);
                        WinScene winScene = null;
                        try {
                            winScene = new WinScene(new GridPane(), StartMenuScene.takeWidth(), StartMenuScene.takeHeight(),this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        StartMenuScene.getStage().setScene(winScene);
                    }
                }
                case SHIFT -> {
                    this.getMapView().getLevelScene().getClip().stop();
                    this.getMapView().getLevelScene().getClip().setMicrosecondPosition(0);
                    LoseScene loseScene = null;
                    try {
                        loseScene = new LoseScene(new GridPane(), StartMenuScene.takeWidth(), StartMenuScene.takeHeight(),this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StartMenuScene.getStage().setScene(loseScene);
                }
                case BACK_SPACE -> {
                    StartMenuScene.getStage().setScene(StartMenuScene.getLoadingScene());
                }
            }
            MiniMap.getMiniMap().drawMiniMap();
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
        return firstLevel;
    }
}
