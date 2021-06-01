package View;

import View.Cell.Cell;
import View.Cell.CityCell;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * <p>Tool panel below map</p>
 *
 * @author Vasia_Pupkin
 * @version 0.0.0
 */
public final class ToolPanel extends Group {
    private static final String backgroundImagePath = "file:resources\\images\\toolbar\\Cobblestone.png";
    private static ToolPanel instance;
    private static int PANEL_WIDTH = 840;
    private static int PANEL_HEIGHT = 630 / 3 - 15;

    private GridPane miniMap = new GridPane();//maybe not map, just place for some shit
    private GridPane mainPanel = new GridPane();
    private GridPane actions = new GridPane();

    //getInstance

    private ToolPanel() {
        super();
        defaultPanelView();
    }

    public static ToolPanel getInstance() {
        if (instance == null)
            instance = new ToolPanel();
        return instance;
    }

    //getters & setters

    public static int getPanelWidth() {
        return PANEL_WIDTH;
    }

    public static void setPanelWidth(int panelWidth) {
        PANEL_WIDTH = panelWidth;
    }

    public static int getPanelHeight() {
        return PANEL_HEIGHT;
    }

    public static void setPanelHeight(int panelHeight) {
        PANEL_HEIGHT = panelHeight;
    }

    //methods

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
        System.out.println("City refresh");
    }

    private void notCityCellRefresh() {
        System.out.println("not city cell with shit on it(buildings, generals, etc.) refresh");
    }

    public void defaultPanelView() {
        this.getChildren().clear();
        GridPane cobblestoneCellsBackground = new GridPane();
        //background
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 4; x++) {
                Rectangle background = new Rectangle();
                background.setFill(new ImagePattern(new Image(backgroundImagePath)));
                background.setHeight(PANEL_HEIGHT / 3);
                background.setWidth(PANEL_WIDTH / 4);
                cobblestoneCellsBackground.add(background, x, y);
            }
        }
        this.getChildren().add(cobblestoneCellsBackground);
        //other shit
        GridPane tools = new GridPane();
        tools.setHgap(10);
        tools.setVgap(10);
        tools.add(miniMap, 0, 0);
        tools.add(mainPanel, 1, 0);
        tools.add(actions, 2, 0);
        initMiniMap();
        initMainPanel();
        initActions();
        this.getChildren().add(tools);
    }


    private void initMiniMap() {
        //TODO detalize this part of toolbar
        Rectangle miniMapBackground = new Rectangle();
        miniMapBackground.setFill(Paint.valueOf("GRAY"));
        miniMapBackground.setWidth(PANEL_WIDTH / 4 - 18);
        miniMapBackground.setHeight(PANEL_HEIGHT);
        MiniMap.getMiniMap(miniMap, PANEL_WIDTH / 4 - 18, PANEL_HEIGHT);
        //miniMap.setBackground(miniMapBackground, 0, 0);
    }

    private void initMainPanel() {
        //TODO detalize this part of toolbar
        Rectangle miniMapBackground = new Rectangle();
        miniMapBackground.setFill(Paint.valueOf("GRAY"));
        miniMapBackground.setWidth(PANEL_WIDTH /2);
        miniMapBackground.setHeight(PANEL_HEIGHT);
        mainPanel.add(miniMapBackground, 0, 0);
    }

    private void initActions() {
        //TODO detalize this part of toolbar
        Rectangle miniMapBackground = new Rectangle();
        miniMapBackground.setFill(Paint.valueOf("GRAY"));
        miniMapBackground.setWidth(PANEL_WIDTH / 4);
        miniMapBackground.setHeight(PANEL_HEIGHT);
        actions.add(miniMapBackground, 0, 0);
    }

}
