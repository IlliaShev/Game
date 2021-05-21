import View.MapView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {
    private final int MAP_LENGTH = 510;
    private final int FRAME_LENGTH = 610;
    private MapView mapView;


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        borderPane.setCenter(gridPane);

        Group group = new Group();
        group.getChildren().add(borderPane);
        mapView = new MapView(MAP_LENGTH, gridPane);
        Scene scene = new Scene(group, MAP_LENGTH+6, FRAME_LENGTH);
        stage.setTitle("The The Game");
        stage.setScene(scene);
        stage.setX(200);
        stage.setY(100);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
