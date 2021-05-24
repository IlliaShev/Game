package Menus;

import View.Icons.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class ChooseBuildingMenu extends Stage {

    public ChooseBuildingMenu(double x, double y, int x_coordinate, int y_coordinate){

        GoldmineIcon goldmineIcon = new GoldmineIcon(70,x_coordinate,y_coordinate);
        MineralIcon mineralIcon = new MineralIcon(70,x_coordinate,y_coordinate);
        FieldIcon fieldIcon = new FieldIcon(70,x_coordinate,y_coordinate);

        HBox chooseBuildingMenu = new HBox(goldmineIcon,mineralIcon,fieldIcon);
        chooseBuildingMenu.setPadding(new Insets(20));
        chooseBuildingMenu.setSpacing(15);

        Group group = new Group(chooseBuildingMenu);
        this.setTitle("Choose The Building");
        this.setScene(new Scene(group,280,110));
        this.setX(x);
        this.setY(y);
    }
}
