import Scenes.*;
import View.Player;
import View.PlayersHandler;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.*;

public class Main extends Application {

    private final int FRAME_HEIGHT = 660;
    private final int FRAME_WIDTH = 840;
    private static StartMenuScene startMenuScene;
    private static ChooseLevelScene chooseLevelScene;
    private GameScene gameScene;

    @Override
    public void start(Stage stage) throws Exception {

        startMenuScene = new StartMenuScene(new Pane(), FRAME_WIDTH, FRAME_HEIGHT,stage);
        chooseLevelScene = new ChooseLevelScene(new Pane(),FRAME_WIDTH, FRAME_HEIGHT,stage);

        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setTitle("TCU");
        stage.setScene(startMenuScene);
        startMenuScene.playBackMusic();
        stage.setResizable(false);
        stage.show();
        stage.setMinWidth(FRAME_WIDTH);
        stage.setMinHeight(FRAME_HEIGHT);
        System.out.println(stage.getWidth());

        startMenuScene.getStartButton().setOnMouseClicked(mouseEvent -> {
            startMenuScene.getClip().stop();
            stage.setScene(chooseLevelScene);
            chooseLevelScene.playBackMusic();
            chooseLevelScene.getBackButton().setOnMouseClicked(newMouseEvent -> {
                chooseLevelScene.getClip().stop();
                stage.setScene(startMenuScene);
                //startMenuScene.getClip().setMicrosecondPosition(0);
                startMenuScene.getClip().start();
            });
            chooseLevelScene.getFirstLevelButton().setOnMouseClicked(newMouseEvent -> chooseLevelScene.getFirstLevelButton().runLevel());
            chooseLevelScene.getSecondLevelButton().setOnMouseClicked(newMouseEvent -> chooseLevelScene.getSecondLevelButton().runLevel());
            chooseLevelScene.getThirdLevelButton().setOnMouseClicked(newMouseEvent -> chooseLevelScene.getThirdLevelButton().runLevel());
            chooseLevelScene.getFourthLevelButton().setOnMouseClicked(newMouseEvent -> chooseLevelScene.getFourthLevelButton().runLevel());
            chooseLevelScene.getFifthLevelButton().setOnMouseClicked(newMouseEvent -> chooseLevelScene.getFifthLevelButton().runLevel());
        });
//        startMenuScene.getRandomButton().setOnMouseClicked(mouseEvent -> {
//            startMenuScene.getClip().stop();
//            startMenuScene.getClip().setMicrosecondPosition(0);
//            gameScene = new GameScene(new Group(),FRAME_WIDTH,FRAME_HEIGHT);
//            stage.setScene(gameScene);
//        });
        startMenuScene.getExitButton().setOnMouseClicked(mouseEvent -> {
            stage.close();
            startMenuScene.getClip().stop();
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static StartMenuScene getStartMenuScene(){
        return startMenuScene;
    }

}
