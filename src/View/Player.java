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

    public void addCity(CityCell cityCell){
        City city = new City("SMT");
        cityCell.setCity(city);
        city.setCityCell(cityCell);
        CityHandler.getCityHandler().addCity(city);
        cities.add(city);
    }

    public boolean hasCity(City city){
        return cities.contains(city);
    }

    public boolean canAttack(Army firstArmy, Army secondArmy){

        boolean firstArmyValid = false;
        boolean secondArmyValid = true;

        for(City city : cities){
            if(city.getArmies().contains(firstArmy)){
                firstArmyValid = true;
            }
            if (city.getArmies().contains(secondArmy)){
                secondArmyValid = false;
            }
        }
        return firstArmyValid && secondArmyValid;
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

    public boolean hasArmy(Army army) {
        for(City city: cities){
            if(city.getArmies().contains(army))
                return true;
        }
        return false;
    }
}
