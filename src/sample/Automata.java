package sample;

/**
 * Created with IntelliJ IDEA.
 * User: Konstantin
 * Date: 29.09.18
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class Automata {
    private int cash;
    private int payment;
    private int moneyback;
    private int choice;
    private int[] prices;
    private String[] menu;
    private STATES state = STATES.OFF;
    private final String TOO_LITTLE_MONEY = "Too little money for your choice...";
    private final String ENJOY = "Enjoy your drink...";

    public Automata(String[] menu, int[] prices) {
        this.menu = menu;
        this.prices = prices;
        this.state = STATES.OFF;
    }

    public STATES on() {
        switch (state) {
            case OFF:
                state = STATES.WAIT;
                break;
            case WAIT:
                break;
            case ACCEPT:
                break;
            case CHECK:
                break;
            case COOK:
                break;
        }

        return state;
    }

    public STATES off() {
        switch (state) {
            case OFF:
                break;
            case WAIT:
                state = STATES.OFF;
                break;
            case ACCEPT:
                break;
            case CHECK:
                break;
            case COOK:
                break;
        }
        return state;
    }

    public int coin(int payment) {
        switch (state) {
            case OFF:
                break;
            case WAIT:
                this.payment += payment;
                cash += payment;
                state = STATES.ACCEPT;
                break;
            case ACCEPT:
                this.payment += payment;
                cash += payment;
                break;
            case CHECK:
                break;
            case COOK:
                break;
        }
        return payment;
    }

    public String printMenu() {
        String printmenu = "";
        switch (state) {
            case OFF:
                return "";
            case WAIT:
                ;
            case ACCEPT:
                ;
            case CHECK:
                ;
            case COOK:
                ;
            default: for (int i = 0; i < menu.length; i++) {
                printmenu+=String.format("%-25s %d\n",menu[i],prices[i]);
                            }
        }
        System.out.println(printmenu);
        return printmenu;
    }

    public STATES printState() {
        return state;
    }

    public String choice(int choice) {
        this.choice = choice;
        String result = TOO_LITTLE_MONEY;
        switch (state) {
            case OFF:
                break;
            case WAIT:
                break;
            case ACCEPT:
                if (check()) {
                    state = STATES.CHECK;
                    result = (menu[choice]);
                }
                break;
            case CHECK:
                break;
            case COOK:
                break;
        }
        return result;
    }

    public boolean check() {
        return (payment >= prices[choice]);
    }

    public int cancel() {
        switch (state) {
            case OFF:
                break;
            case WAIT:
                break;
            case ACCEPT:
                state = STATES.WAIT;
                moneyback = payment;
                cash -= payment;
                payment = 0;
                break;
            case CHECK:
                state = STATES.WAIT;
                moneyback = payment;
                cash -= payment;
                payment = 0;
                break;
            case COOK:
                break;
        }
        return moneyback;
    }

    public int getCash() {
        return cash;
    }

    public String cook() {
        String result = TOO_LITTLE_MONEY;
        switch (state) {
            case OFF:
                break;
            case WAIT:
                break;
            case ACCEPT:
                break;
            case CHECK:
                state = STATES.COOK;
                result = ENJOY;
                break;
            case COOK:
                break;
        }
        return result;
    }

    public int finish() {
        int result = -1;
        switch (state) {
            case OFF:
                break;
            case WAIT:
                break;
            case ACCEPT:
                break;
            case CHECK:
                break;
            case COOK:
                state = STATES.WAIT;
                cash += prices[choice] - payment;
                result = payment - prices[choice];
                payment = 0;
                break;
        }
        return result;
    }


}
