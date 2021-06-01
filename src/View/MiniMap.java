package View;

import View.Cell.*;
import javafx.scene.layout.GridPane;

public class MiniMap {

    private static MiniMap miniMap;

    private MapArrView mapArrView;
    private GridPane gridPane;
    private int miniMapCellLength;
    private int gridWidth;
    private int gridHeight;

    private MiniMap(GridPane gridPane, int width, int height) {
        this.gridPane = gridPane;
        gridWidth = width;
        gridHeight = height;
        mapArrView = MapArrView.getMapArrView();
        miniMapCellLength = Math.min(gridWidth, gridHeight)/Math.max(mapArrView.getColumnsNumber(), mapArrView.getRowsNumber());
        System.out.println(miniMapCellLength);
        drawMiniMap();
    }

    public static MiniMap getMiniMap(GridPane gridPane, int width, int height){
        if(miniMap == null)
            miniMap = new MiniMap(gridPane, width, height);
        return miniMap;
    }

    public static MiniMap getMiniMap(){
        return miniMap;
    }

    public void drawMiniMap() {
        Cell[][] map = MapArrView.getMapArrView().getMap();
        Player player = PlayersHandler.getPlayersHandler().getPlayer(0);
        for(int i = 0; i < mapArrView.getColumnsNumber(); i++){
            for (int j = 0; j < mapArrView.getRowsNumber(); j++){
                MiniMapCell miniMapCell = null;
                if(map[i][j] instanceof CityCell){
                    if(player.hasCity(((CityCell) map[i][j]).getCity())){
                        miniMapCell = new MiniMapCell(miniMapCellLength, "BLUE");
                    } else{
                        miniMapCell = new MiniMapCell(miniMapCellLength, "RED");
                    }
                } else if(map[i][j] instanceof ArmyCell){
                    if(player.hasArmy(((ArmyCell) map[i][j]).getArmy())){
                        miniMapCell = new MiniMapCell(miniMapCellLength, "BLUE");
                    } else{
                        miniMapCell = new MiniMapCell(miniMapCellLength, "RED");
                    }

                } else if(map[i][j] instanceof MountainCell){
                    miniMapCell = new MiniMapCell(miniMapCellLength, "GRAY");
                } else if(map[i][j] instanceof ForestCell){
                    miniMapCell = new MiniMapCell(miniMapCellLength, "DARKGREEN");
                } else{
                    miniMapCell = new MiniMapCell(miniMapCellLength, "GREEN");
                }
                gridPane.add(miniMapCell, i,j);
            }
        }
    }
}
