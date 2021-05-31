import Scenes.*;
import View.Player;
import View.PlayersHandler;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.awt.*;

public class Main extends Application {

    private final int MAP_LENGTH = 840;
    private final int FRAME_LENGTH = 630;

    @Override
    public void start(Stage stage) throws Exception {

        StartMenuScene startScene = new StartMenuScene(new Pane(),MAP_LENGTH,FRAME_LENGTH);

        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setTitle("TCU");
        stage.setScene(startScene);
        stage.show();

        startScene.getStartButton().setOnMouseClicked(mouseEvent -> {
            startScene.getClip().stop();
            GameScene gameScene = new GameScene(new Group(),MAP_LENGTH+6,FRAME_LENGTH);
            stage.setScene(gameScene);
        });
        startScene.getExitButton().setOnMouseClicked(mouseEvent -> stage.close());
    }

    public static void main(String[] args) {
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        launch(args);
    }

}
