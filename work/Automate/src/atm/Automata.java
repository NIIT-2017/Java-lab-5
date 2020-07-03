package atm;

import java.util.*;

public class Automata {
    public float cash;
    private ArrayList<Beverage> menu;
    public HashMap<String, Boolean> additions;
    private float addPrice = 15f;
    private STATES state;

    public Beverage order;
    public String prompt = "The Automata is ready to work";
    public String answer = "";

    public List<Beverage> getMenu() {
        return Collections.unmodifiableList(menu);
    }

    public Automata() {
        Init();
    }
    //Initialization of fields
    private void Init(){
        cash = 0f;
        menu = new ArrayList<>();
        additions = new HashMap<>();
        menu.add(new Beverage("Coffee", 150));
        menu.add(new Beverage("Tea", 80));
        menu.add(new Beverage("Juice", 200));
        additions.put("Sugar", false);
        additions.put("Milk", false);
        additions.put("Ice", false);
        additions.put("Syrup", false);
        setState(STATES.OFF);
    }

    public void on() {
        setState(STATES.WAIT);
        answer = "Waiting for your order";
    }

    public void off() {
        setState(STATES.OFF);
        cash = 0f;
        answer = "";
    }

    public void coin(float money) {
        if (state != STATES.WAIT && state != STATES.ACCEPT) {
            return;
        }
        if (money <= 0) {
            answer = "Invalid value of money!";
        }
        cash += money;
        if (state == STATES.WAIT)
            setState(STATES.ACCEPT);
        answer = "cash : " + cash;
    }

    public void getState() {
        answer = "The Automate is " + state + ". Cash : " + cash;
    }

    public void check() {
        if (findOut()) {
            answer =  "Cash = " + cash +
                      "Price of " + order + " = " + order.getName() +
                      "You have to add " + (order.getPrice() - cash);
        } else
            answer = "Cash is enough!";
    }

    //Check, is there cash enough for the order
    private boolean findOut() {
        return cash < fullOrderPrice();
    }
    private float fullOrderPrice() {
        float orderPrice = order.getPrice();
        for (Map.Entry<String, Boolean> add : additions.entrySet()) {
            if (add.getValue()){
                orderPrice += addPrice;
            }
        }
        return orderPrice;
    }

    public void cancel() {
        if (state != STATES.OFF && state != STATES.WAIT) {
            return;
        }
        setState(STATES.WAIT);
        answer = "Your order is canceled";
    }

    public void cook() {
        if (!findOut()) {
            cash -= fullOrderPrice();
            setState(STATES.COOK);
            finish();
            answer = "Please, your " + order;
        } else
            answer = "Cash isn't enough!";
    }

    private void finish() {
        on();
    }

    //Change the automate state and give a describe of the state
    private void setState(STATES state){
        switch (state) {
            case OFF:
                this.state = STATES.OFF;
                prompt = "The automate is disable.\nPossible actions : ON";
                break;
            case WAIT:
                this.state = STATES.WAIT;
                prompt = "The automate is waiting for your command.\nPossible action : OFF, COIN";
                break;
            case ACCEPT:
                this.state = STATES.ACCEPT;
                prompt = "You money is accepted.\nPossible actions : COIN, CHOICE, CANCEL";
                break;
            case CHECK:
                this.state = STATES.CHECK;
                prompt = "You made a choice.\nPossible actions : CANCEL, CHECK, COOK";
                break;
            case COOK:
                this.state = STATES.COOK;
                prompt = "The cooking has started.\nPossible actions : just relax and wait";
                break;
            default:
                prompt = "Unknown state!";
                break;
        }
    }
}

//types of automate state
enum STATES {
    OFF, WAIT, ACCEPT, CHECK, COOK
}
