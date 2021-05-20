import View.MapView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final int LENGTH = 510;
    private MapView mapView;


    @Override
    public void start(Stage stage) throws Exception {
        Group group = new Group();
        mapView = new MapView(LENGTH, group);


        Scene scene = new Scene(group, LENGTH, LENGTH);
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
