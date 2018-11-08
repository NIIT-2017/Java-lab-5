package sample;

public class Automate {
    private int cash;
    private String[] menu;
    private int[] prices;
    private States state;

    private Automate(String[] menu, int[] prices){
        this.menu = menu;
        this.prices = prices;
        cash = 0;
        state = States.OFF;
    }

    public static Automate getAutomate(String[] menu, int[] prices){
        if (!(menu.length > 0) || (menu.length != prices.length))
            return null;                                              // Отказ создать новый автомат

        int minPrice = prices[0];
        for (int price:prices
             ) {
            if (price < minPrice)
                minPrice = price;
        }

        if (minPrice <= 0)
            return null;                                                    // Отказ создать новый автомат

        return new Automate(menu, prices);

    }

    public boolean on(){
        if (state == States.OFF){
            state = States.WAIT;
            return true;
        }
        return false;       //Отказ включения
    }
    public boolean off(){
        if (state == States.WAIT) {
            state = States.OFF;
            return true;
        }
        return false;       //Отказ выключения
    }
    public int coin(int money){
        if ((state == States.WAIT || state == States.ACCEPT) && money > 0){
            cash += money;
            state = States.ACCEPT;
            return cash;
        }
        return -1;      // Отказ принять деньги
    }
    public String[] printMenu(){
        if (state == States.WAIT || state == States.ACCEPT){
            String[] result = new String[menu.length];
            for (int i = 0; i < menu.length; i++) {
                int number = i + 1;
                result[i] = number + ". " + menu[i] + " " + prices[i] + " руб.";
            }
            return result;
        }
        return null;    //  Отказ вывести меню
    }
    public String printState(){
        if (state != States.OFF)
            return state.toString();

        return null;    // Отказ отобразить состояние
    }
    public Integer choice(int number){
        if (state != States.ACCEPT || number <= 0 || number > menu.length)
            return null;                                                         // Отказ принять выбор

        int numberPrice = number - 1;
        int rest = check(numberPrice);
        if (rest < 0){
            state = States.ACCEPT;
            return Integer.valueOf(rest);
        }
        cook(numberPrice);
        int result = finish();
        return Integer.valueOf(result);
    }
    private int check(int numberMenu){
        state = States.CHECK;
        return cash - prices[numberMenu];
    }
    public int cancel(){
        if (state != States.ACCEPT)
            return -1;

        return finish();
    }
    private void cook(int numberPrice){
        state = States.COOK;
        cash -= prices[numberPrice];
    }
    private int finish(){
        int rest = cash;
        cash = 0;
        state = States.WAIT;
        return rest;
    }
}
enum States{
    OFF, WAIT, ACCEPT, CHECK, COOK
}
