package Avtomat.model;

public class Drink {
    private String drink;
    private double cost;

    public Drink(String drink, double cost) {
        this.drink = drink;
        this.cost = cost;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
