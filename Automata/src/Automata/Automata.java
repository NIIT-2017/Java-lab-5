package Automata;

import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.util.ArrayList;

public class Automata {

    private int cash;
    private ArrayList<String> menu = new ArrayList<String>();
    private ArrayList<Integer> prices = new ArrayList<Integer>();

    private Label condition;
    private Label balance;
    private Label prompt;

    public void setCondition(Label lab){ condition = lab;}
    public void setBalance(Label lab){ balance = lab;}
    public void setPrompt(Label lab){ prompt = lab;}

    public enum States {
        OFF,
        WAIT,
        ACCEPT,
        CHECK,
        COOK
    }

    States state;

    public Automata() {
        this.cash = 0;
        this.menu.add("Coffee");
        this.menu.add("Latte");
        this.menu.add("Cappuccino");
        this.menu.add("Mocaccino");
        this.menu.add("Chocolate");
        this.menu.add("Tea");
        this.prices.add(75);
        this.prices.add(75);
        this.prices.add(75);
        this.prices.add(75);
        this.prices.add(75);
        this.prices.add(50);
        this.state = States.OFF;
    }

    public Integer getterCash() { return cash;}
    public ArrayList<String> getterMenu() { return menu;}
    public ArrayList<Integer> getterPrice() { return prices;}

    public void on() {                                         //включение автомата;
        if (state == States.OFF) {
            state = States.WAIT;
            condition.setText(state + "...");
        }
        else
            condition.setText(state + "...");
    }

    public void off() {                                       //выключение автомата;
        if (state == States.WAIT && cash == 0) {
            state = States.OFF;
            condition.setText(state + "...");
        }
    }

    public void coin(int EntCash) {                             //занесение денег на счёт пользователем;
        if (state == States.WAIT || state == States.ACCEPT) {
            cash = cash + EntCash;
            state = States.ACCEPT;
            condition.setText(state + "...");
            prompt.setText("Choose a beverage!");
        }
    }

    public boolean choice(int i) {                              //выбор напитка пользователем;
        if (state == States.ACCEPT || state == States.WAIT) {
            prompt.setText("Your choice is a " + menu.get(i - 1));
            state = States.CHECK;
            condition.setText(state + "...");

        }
        else {
            prompt.setText("Error!");
            return false;
        }

        if (check(i-1) == true) {
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                cash = cash - prices.get(i - 1);
                balance.setText(Integer.toString(cash));
                cook(i - 1);
            });
            pause.play();

            return true;
        }
        else
            return false;
    }

    private boolean check(int i) {                              //проверка наличия необходимой суммы;
        if (state == States.CHECK && (cash - prices.get(i)) >= 0) {
            prompt.setText("Check is over!");
            state = States.COOK;
            return true;
        }
        else {
            state = States.ACCEPT;
            prompt.setText("Not enough money!");

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                prompt.setText("Add cash or push Return money!");
            });
            pause.play();
            return false;
        }
    }

    public void cancel() {                                        //отмена сеанса обслуживания пользователем;
        if (state == States.ACCEPT && cash != 0) {
            prompt.setText("Return " + cash + "!");
            cash = 0;
            balance.setText(Integer.toString(cash));
            state = States.WAIT;
            condition.setText(state + "...");
        }
    }

    private void cook(int choice) {                                //имитация процесса приготовления напитка;
        if (state == States.COOK) {
            prompt.setText("Cooking a " + menu.get(choice));
            state = States.CHECK;

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                finish();
            });
            pause.play();
        }
    }

    private void finish() {                                        // завершение обслуживания пользователя.
        if (cash != 0) {
            prompt.setText("Return money " + cash + "!");
            cash = 0;
        }
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            prompt.setText("Your order is over!\nTake a drink!\nThank you!");
            state = States.WAIT;
        });
        pause.play();

        balance.setText(Integer.toString(cash));
    }
}


