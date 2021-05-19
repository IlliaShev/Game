import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class Map extends Application {

    private static final int LENGTH = 510;
    private static final int CELL_WIDTH = LENGTH/15;

    @Override
    public void start(Stage stage) throws Exception {

        Group group = new Group();
        Scene scene = new Scene(group,LENGTH,LENGTH);

        for(int i=0; i<LENGTH; i+=CELL_WIDTH){
            for(int j=0; j<=LENGTH; j+=CELL_WIDTH){
                Cell cell = new Cell(CELL_WIDTH);
                cell.setX(i);
                cell.setY(j);
                group.getChildren().add(cell);
            }
        }

        stage.setTitle("The Game");
        stage.setScene(scene);
        stage.setX(200);
        stage.setY(100);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
