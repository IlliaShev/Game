package View;

import View.Cell.CellView;
import View.Cell.MountainCellView;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;

public class MapView extends Application {

    private static final int LENGTH = 510;
    private static final int CELL_WIDTH = LENGTH / 15;

    @Override
    public void start(Stage stage) {

        Group group = new Group();
        Scene scene = new Scene(group, LENGTH, LENGTH);

        for (int i = 0; i < LENGTH; i += CELL_WIDTH) {
            for (int j = 0; j <= LENGTH; j += CELL_WIDTH) {
                CellView cell = new MountainCellView(CELL_WIDTH);
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
