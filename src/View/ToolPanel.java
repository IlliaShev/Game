package View;

import View.Cell.Cell;
import View.Cell.CityCell;
import View.Icons.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * <p>Tool panel below map</p>
 *
 * @author Vasia_Pupkin
 * @version 0.0.0
 */
public final class ToolPanel extends GridPane {
    private static final String imageURL = "file:resources\\images\\toolbar\\Toolbar.jpg";
    private static ToolPanel instance;

    private ToolPanel() {
        super();
        super.setAlignment(Pos.TOP_CENTER);
        defaultPanelView();
    }

    public static ToolPanel getInstance() {
        if (instance == null)
            instance = new ToolPanel();
        return instance;
    }

    public void refresh(Cell cell) {
        if ((cell.getClass()).equals(CityCell.class)) {
            if (!((CityCell) cell).isChosen()) {
                cityCellRefresh();
            } else {
                defaultPanelView();
            }
        } else {
            notCityCellRefresh();
        }
    }

    private void cityCellRefresh() {
        MineralIcon mineralIcon = new MineralIcon(5,5,5);
        mineralIcon.setClickResponse(this::MineralIcon);
        System.out.println("City refresh");
    }

    private void MineralIcon() {
        System.out.println("xyi");
    }

    private void notCityCellRefresh() {
        System.out.println("not city cell with shit on it(buildings, generals, etc.) refresh");
    }

    private void defaultPanelView() {
        System.out.println("xyi");
        this.getChildren().clear();
        this.setPrefSize(75, 75);
        Rectangle background = new Rectangle();
        this.add(background, 0,0);
    }



}
