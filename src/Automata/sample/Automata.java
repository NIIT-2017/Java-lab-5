package sample;

import javafx.scene.control.TextArea;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class Automata {

Controller controller;

        private static int cash;
        private int price;
        private int change;
        private int savedCash;

        public Automata(int cash, int price, int change, int savedCash) {
            this.cash = cash;
            this.price = price;
            this.change = change;
            this.savedCash=savedCash;
        }


        public  void setSavedCash(int savedCash){
            this.savedCash=savedCash;
        }

        public int getSavedCash() {
            return savedCash;
        }

        public Controller getController() {
            return controller;
        }

        public void setController() {
            this.controller = controller;
        }

        public static int getCash() {
            return cash;
        }

        public void setCash(int cash) {
            this.cash = cash;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getChange() {
            return change;
        }

        public void setChange(int change) {
            this.change = change;
        }

        public Automata() {

        }


        //     public int change;
        public Properties getMenu() {
            return menu;
        }

        enum STATES {
            OFF,
            WAIT,//switch-on state
            ACCEPT,
            CHECK,
            COOK
        }

    public STATES getState() {
        return state;
    }

    public void setState(STATES state) {
        this.state = state;
    }

    private STATES state;
        private Properties menu;

        Automata(Properties menu) {
            STATES state = STATES.OFF;
        }

        public void on() {
            state = STATES.WAIT;
            System.out.println("Automata is switched on. Input money. Then Press ACCEPT. \nChoose your drink. Then press START. ");
            AutomataDemo.logfile.println("Automata is switched on. Input money. Then Press ACCEPT. \nChoose your drink. Then press START.");

        }

        public int cancel(int moneyForReturn){
           controller= new Controller();
            state=STATES.WAIT;
            cash=moneyForReturn;
            setCash(cash);
            System.out.println("Take your cash back: "+getSavedCash());

            return savedCash;
        }

        public void off() { state = STATES.OFF;
            AutomataDemo.logfile.println("Automata is switched off");
            System.out.println("Automata is switched off");
        }

        public int coin(int addMoney) {
            Controller controller=new Controller();
            //передать деньги int coin(...)
                state = STATES.ACCEPT;
                cash=addMoney;
               savedCash+=cash;
                setSavedCash(savedCash);
            System.out.println("");
            System.out.println(" Money accepted. You give: "+cash+" rubles");
            System.out.println("Your credit is: "+getSavedCash());

            AutomataDemo.logfile.println("Please input money. Money accepted. You give: "+cash+" rubles");
            AutomataDemo.logfile.println("Your credit is: "+getSavedCash());
            return savedCash;
        }

        public STATES choice(int priceOfDrink) throws InterruptedException {
            /* call check cook finish */

            price=priceOfDrink;
            if(getSavedCash()>=getPrice()){
                check(getSavedCash(),getPrice());
                return state;
            }else
                check(cash,price);
            return state;
        }

        private void check(int cashInput, int priceOfDrink) throws InterruptedException {
            Controller controller=new Controller();
            state=STATES.CHECK;
            cash=cashInput;
            price=priceOfDrink;
            if (cash == price) {
                state = STATES.COOK;
                cook();
            }
            else if(cash>price) {
                state = STATES.COOK;
                returnChange(cashInput, priceOfDrink);
                cook();


            }
            else if (cash < price) {
                state = STATES.WAIT;
                System.out.println("Not enough money. If you want to continue, input full price and press ACCEPT. Or press CANCEL.");
                AutomataDemo.logfile.println("Not enough money. If you want to continue, input to full price and press ACCEPT");


            } else {
                state = STATES.WAIT;
                AutomataDemo.logfile.println("If you don't want to continue, press CANCEL");
                System.out.println("If you don't want to continue, press CANCEL");
                cancel(cash);
            }
        }

        private void cook() throws InterruptedException {//InterruptedException сигнализирует о том, что поток просят завершить его работу.// При этом вас не просят немедленно завершить свою работу. Вас просят корректно завершить работу. На это может понадобится некоторое время
            Controller controller=new Controller();
            AutomataDemo.logfile.println("Press START for preparing.");
            System.out.println("Press START for preparing.");


            state=STATES.WAIT;
        }
        public int returnChange(int cashInput, int priceOfDrink){
            state=STATES.CHECK;
            setCash(cash);
            cash=cashInput;
            price=priceOfDrink;
            //  setPrice(price);
            change=cash-price;
            setChange(change);

           AutomataDemo.logfile.println("Take your change: "+getChange());
            System.out.println("Take your change: "+getChange());
            return getChange();
        }
        private void finish(){
            state=STATES.WAIT;
            //"Done."


        }

    }

    class AutomataDemo {

        public static PrintStream logfile;

        static {
            try {

                logfile = new PrintStream(new File("log.txt"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        public AutomataDemo(PrintStream logfile) throws FileNotFoundException {
            this.logfile=logfile;
        }


        public static void main(String args[]) throws InterruptedException, FileNotFoundException {

//            PrintStream logfile = new PrintStream(new File("log.txt"));

            File file = new File("src/main/resources/menu.properties");
            Properties properties = new Properties();
            System.setOut(logfile);

            Automata nescafe = new Automata(properties);
            nescafe.on();//включение
            //logfile.println("Automata is switched on. Choose your drink.");
            int value = 0;
            try {

                FileInputStream fileInput = new FileInputStream(file);
                properties.load(fileInput);

                Enumeration names = properties.keys();
                while (names.hasMoreElements()) {
                    String key = (String) names.nextElement();
                    value = Integer.parseInt(properties.getProperty(key));
                    // logfile.println(key + ":" + value);
                }
                String latte = properties.getProperty("latte");
                String capuccino = properties.getProperty("capuccino");
                String coffeBlack = properties.getProperty("coffeBlack");
                String espresso = properties.getProperty("espresso");

                logfile.println("MENU: " + "latte: " + latte +
                        ", capuccino: " + capuccino +
                        " ,coffeBlack: " + coffeBlack +
                        " ,espresso: " + espresso);
                fileInput.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            Properties menu = nescafe.getMenu();
            String ourChoice;
            ourChoice = (properties.getProperty("latte"));
            logfile.println("Price of your drink is: "  + ourChoice);
            nescafe.coin(30);
            nescafe.choice(Integer.parseInt((ourChoice)));

            nescafe.off();
        }

//        public static void setLogfile() {
//        }
    }

