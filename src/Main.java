import Scenes.*;
import View.Player;
import View.PlayersHandler;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {

    private final int FRAME_HEIGHT = 630;
    private final int FRAME_WIDTH = 840;

    @Override
    public void start(Stage stage) throws Exception {

        StartMenuScene startScene = new StartMenuScene(new Pane(), FRAME_WIDTH, FRAME_HEIGHT);

        stage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setTitle("TCU");
        stage.setScene(startScene);
        stage.show();
        stage.setMinWidth(FRAME_WIDTH);
        stage.setMinHeight(FRAME_HEIGHT);
        System.out.println(stage.getWidth());

        startScene.getStartButton().setOnMouseClicked(mouseEvent -> {
            startScene.getClip().stop();
            GameScene gameScene = new GameScene(new Group(),FRAME_WIDTH, FRAME_HEIGHT);
            stage.setScene(gameScene);
        });
        startScene.getExitButton().setOnMouseClicked(mouseEvent -> stage.close());
    }

    public static void main(String[] args) {
        PlayersHandler.getPlayersHandler().addPlayer(new Player());
        launch(args);
    }

}
