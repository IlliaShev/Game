package View;

import View.Cell.CityCell;

import java.util.*;

public class Player {

    private ArrayList<City> cities;

    public Player(){
        cities = new ArrayList<>();
    }

    public Player(ArrayList<City> cities){
        this.cities = cities;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public void addCityCell(CityCell cityCell){
        City city = new City("SMT");
        cityCell.setCity(city); // set city to cell
        city.setCityCell(cityCell); // set cell to city
        CityHandler.getCityHandler().addCity(city); // add city to all cities handler
        cities.add(city); // add city to player`s cities
        city.setPlayer(this); // set player to city
    }

    public void addCity(City city){
        cities.add(city);
    }

    public boolean hasCity(City city){
        return cities.contains(city);
    }

    public void attack(Army firstArmy, Army secondArmy){
        while(firstArmy.getHealth()>0 && secondArmy.getHealth()>0){
            Random rand = new Random();
            int firstArmyDamage = rand.nextInt(firstArmy.getAttackDamage()*2);
            int secondArmyDamage = rand.nextInt(secondArmy.getDefenceDamage()*2);
            firstArmy.receiveDamage(secondArmyDamage);
            secondArmy.receiveDamage(firstArmyDamage);
        }
    }

    public void attackCity(Army army, City city){
        while(army.getHealth()>0 && city.getHealth()>0){
            Random rand = new Random();
            int armyDamage = rand.nextInt(army.getAttackDamage()*2);
            int cityDamage = rand.nextInt(city.getDefenceDamage()*2);
            army.receiveDamage(cityDamage);
            city.receiveDamage(armyDamage);
        }
    }

    public boolean hasArmy(Army army) {
        for(City city: cities){
            if(city.getArmies().contains(army))
                return true;
        }
        return false;
    }
}
