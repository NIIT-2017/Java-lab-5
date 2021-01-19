package sample;

public class AutomataDemo {
    private enum States {OFF, AWAIT, COOK, ACCEPT, CHECK, TAKE};
    private States state = States.OFF;
    private int cash = 0;
    private final String[] drinks = {"Espresso", "Americano", "Latte", "Cappuccino", "Mochaccino", "RAF"};
    private final int[] prices = {40, 45, 55, 65, 80, 110};

    public String on() { //- включение автомата;
        if (state != States.COOK) {
            state = States.AWAIT;
        }
        return "The coffee machine is on";
    }

    public String off() { //- выключение автомата;
        if (state == States.AWAIT && cash == 0){
            state = States.OFF;
            return "The device is turned off";
        }
        else if (cash > 0){
            return "Before you turn it off take your coins!";
        }
        else if (state == States.COOK){
            return "Wait, we haven't cooked yet";
        }
        else if (state == States.ACCEPT){
            return "Дождитесь окончания операции! ACCEPT";
        }
        else if (state == States.CHECK){
            return "Дождитесь окончания операции! CHECK";
        }
        else if (state == States.TAKE){
            return "First of all, take your already prepared coffee";
        }
        else
            return "Unexpected error, please inform the developer";
    }

    public String coin(int money) { // - занесение денег на счёт пользователем;
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        if (money <= 0){
            return "Купюра не распознана, повторите попытку";
        }
        cash = cash + money;
        return "На счету "+cash+" рублей.";
    }

    public void cancel(){
        state = States.AWAIT;
    }

    public int getCoin(){
        return cash;
    }

    public String moneyBack(){ //- возврат денег
        if (state == States.OFF){
            return "Machine is off";
        }
        if (cash==0){
            return "And there was no money";
        }
        else {
            String text = "Take your coins - " + cash + " ₽";
            cash = 0;
            return text;
        }
    }

    public String getMenu(int choice) { // - отображение меню с напитками и ценами для пользователя;
        return drinks[choice]+" - great choice, it will cost "+prices[choice]+"₽, cooking?  ------> ";
    }

    public int getPrices(int choice) {
        if (0<=choice && choice < 6) {
            return prices[choice];
        }
        else
            return 0;
    }

    public String getDrink(int choice){
        return drinks[choice];
    }

    public String getState() { // - отображение текущего состояния для пользователя;
        return String.valueOf(state);
    }

    public String choice(int choice) { // - выбор напитка пользователем;
        if (state == States.OFF){
            return "Аппарат выключен";
        }
        if (choice == 99){
            return "Please choose a drink";
        }
        if (state == States.COOK){
            return "Wait, we haven't cooked yet";
        }
        else{
            state = States.CHECK;
            if (!check(choice)){
                state=States.AWAIT;
                return "Not enough money, please make an additional deposit: " + (prices[choice] - cash) + " ₽";
            }
            else{
                cash = cash - prices[choice];
                cook(choice);
                return "Well, we are preparing your "+ drinks[choice];
            }
        }
    }

    public boolean check (int choice) { // - проверка наличия необходимой суммы;
        if (prices[choice] > cash){
            return false;
        }
        else{
            return true;
        }
    }

    private void cook (int choice) { // - имитация процесса приготовления напитка;
        state = States.COOK;
    }

    public String finish () { // - завершение обслуживания пользователя.
        state = States.TAKE;
        return "Your drink is ready, Thank you for your purchase!";
    }

    public String seeYou(){
        state = States.AWAIT;
        return "Bon appetit!";
    }
}
