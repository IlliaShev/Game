package View;

import View.Cell.*;

public class Army {

    private int health;
    private ArmyCellView armyCell;
    private City city;

    public Army(){
        health = 100;
    }

    public ArmyCellView getArmyCell(){
        return armyCell;
    }

    public void setArmyCell(ArmyCellView armyCell) {
        this.armyCell = armyCell;
    }

    public void getDamage(int damage){
        this.health -= damage;
        if(this.health<=0){
            this.health = 0;
        }
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}