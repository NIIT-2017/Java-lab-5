package sample;

import java.util.ArrayList;
enum States //текущее состояние автомата;
{OFF, WAIT, ACCEPT, CHECK, COOK}

public class Automata {
    private float cash; //текущея сумма
    private States state;//начальное состояние

    public Automata() {
        this.cash = 0;
        this.state = States.OFF;//начальное состояние
    }

    public States getState() {
        return state;
    }

    public void on() { // включение автомата;
        if (state == States.OFF ) {
            state = States.WAIT;
        }
    }

    public void off() { //выключение автомата;
        if (state == States.WAIT ) {
            state = States.OFF;
        }
    }

    public ArrayList[] getMenu() { // отображение меню с напитками и ценами для пользователя;
        ArrayList<String> menuItem = new ArrayList();
        ArrayList<Float> menuItemPrice = new ArrayList();
        menuItem.add("Espresso");
        menuItemPrice.add(35f);
        menuItem.add("Cappuccino");
        menuItemPrice.add(45f);
        menuItem.add("Green Tea");
        menuItemPrice.add(15f);
        menuItem.add("Black Tea");
        menuItemPrice.add(15f);
        menuItem.add("Hot Chocolate");
        menuItemPrice.add(30f);
        menuItem.add("Orange juice");
        menuItemPrice.add(60f);
        ArrayList[] fullMenu = {menuItem, menuItemPrice};
        return fullMenu;
    }

    public void  coin( float money ) { //занесение денег на счёт пользователем;
        if(state == States.WAIT || state == States.ACCEPT){
            state = States.ACCEPT;
            cash += money;
        }
    }

    public float getCash() { // геттер к закрытой переменной cash
        return cash;
    }

    private boolean check(float price) { // проверка наличия необходимой суммы;
        return (state == States.CHECK) && (cash >= price);
    }

    public float cancel() { // отмена сеанса обслуживания пользователем;
        float  change = 0;
        if (state == States.CHECK || state == States.ACCEPT){
            state = States.WAIT;
            change = cash;
            cash = 0;
        }
        return change;
    }
    private void cook(int itemNumber) { // имитация процесса приготовления напитка;
        if ( state == States.CHECK){
            state = States.COOK;
        }
    }

    private void finish() { // завершение обслуживания пользователя.
        if (state == States.COOK) {
            state = States.WAIT;
            System.out.println("Take your drink, man");
        }
    }

    public float choice (int itemNumber) { // выбор напитка пользователем;
        float  change = 0;
        try {
            if (state == States.ACCEPT) {
                state = States.CHECK;
                ArrayList[] menu = getMenu();
                float price = (Float) menu[1].get(itemNumber);
                if (check(price)) {
                    change = cash -= price;
                    cash = 0;
                    cook(itemNumber);
                    finish();
                }
                else {
                    change = cancel();
                }
            }
        }
        catch (IndexOutOfBoundsException e1){
            System.out.println("I don't cook this, try again! " + e1);
            change = cancel();
            System.out.println("Take the change: " + change);
        }
        return change;
    }
}