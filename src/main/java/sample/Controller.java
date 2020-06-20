package sample;

import automata.Automata;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TimerTask;
import java.util.Timer;

public class Controller implements Initializable {
    Automata auto = new Automata();

    @FXML
    private Button espresso;
    @FXML
    private Button espressoLarge;
    @FXML
    private Button americano;
    @FXML
    private Button americanoBig;
    @FXML
    private Button cappuccino;
    @FXML
    private Button cappuccinoLarge;
    @FXML
    private TextField actualState;
    @FXML
    private TextField buyersCoin;
    @FXML
    private TextField message;
    @FXML
    private Button putMoney;
    @FXML
    private Button cancel;
    @FXML
    private Button buy;
    @FXML
    private Button on;
    @FXML
    private Button off;
    @FXML
    private Button latte;
    @FXML
    private Button latteLarge;
    @FXML
    private Button hotChocolate;
    @FXML
    private Button hotChocolateBig;
    @FXML
    private Button blackTeaStandard;
    @FXML
    private Button greenTeaStandard;
    @FXML
    private ImageView picture;
    private String drink;
    private String cash;
    private int price;
    private int coins;
    private int change;
    private HashMap<Automata.STATES, String> state = new HashMap<>();

    {
        state.put(Automata.STATES.ON, "ON");
        state.put(Automata.STATES.OFF, "OFF");
        state.put(Automata.STATES.WAIT, "WAIT");
        state.put(Automata.STATES.ACCEPT, "ACCEPT");
        state.put(Automata.STATES.CHECK, "CHECK");
        state.put(Automata.STATES.COOK, "COOK");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Controller initialize");
    }

    @FXML
    public void clickOn() {
        auto.on();
        actualState.setText(state.get(auto.getState()));
        message.setText("Hello! Please, put your money and press \"Put money!\"");
    }

    @FXML
    public void clickOff() {
        auto.off();
        actualState.setText(state.get(auto.getState()));
        message.clear();
    }

    @FXML
    public void clickCancel() {
        auto.cancel();
        message.setText("Please, take your " + cash + " rubles back!");
        actualState.setText(state.get(auto.getState()));
        buyersCoin.clear();
        coins = 0;
    }

    @FXML
    public void clickPutMoney() {
        cash = buyersCoin.getText();
        coins = Integer.parseInt(cash);
        auto.coin(coins);
        message.setText("You have put " + cash + " rubles. Please, choose your drink and press Buy!");
    }

    @FXML
    public void clickEspresso() {
        auto.readMenu();
        drink = espresso.getText();
        price = auto.choice(drink);
        System.out.println(auto.getState());
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickEspressoLarge() {
        auto.readMenu();
        drink = espressoLarge.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickAmericano() {
        auto.readMenu();
        drink = americano.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickAmericanoBig() {
        auto.readMenu();
        drink = americanoBig.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickCappuccino() {
        auto.readMenu();
        drink = cappuccino.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickCappuccinoLarge() {
        auto.readMenu();
        drink = cappuccinoLarge.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickLatte() {
        auto.readMenu();
        drink = latte.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickLatteLarge() {
        auto.readMenu();
        drink = latteLarge.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickHotChocolate() {
        auto.readMenu();
        drink = hotChocolate.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickHotChocolateBig() {
        auto.readMenu();
        drink = hotChocolateBig.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickBlackTeaStandard() {
        auto.readMenu();
        drink = blackTeaStandard.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickGreenTeaStandard() {
        auto.readMenu();
        drink = greenTeaStandard.getText();
        price = auto.choice(drink);
        actualState.setText(state.get(auto.getState()));
        message.setText("The price of your drink is - " + price + " rubles. Please, press buy!");
    }

    @FXML
    public void clickBuy()  {
        if ((state.get(auto.getState())).equals("CHECK")) {
                Timer t1 = new Timer();
                t1.schedule(new AutoTimerTask() {
                    @Override
                    public void run() {
                        System.out.println("AutoTimertask started" + new Date());
                        try {
                            if (price <= coins) {
                                change = coins - price;
                                if (change > 0) {
                                    message.setText("Please, take your change " + change + " rubles.");
                                }
                                auto.check();
                                Thread.sleep(5000);
                                message.setText("Your drink is cooking, wait please...");
                                auto.cook();
                                actualState.setText(state.get(auto.getState()));
                                Thread.sleep(5000);
                                message.setText("Please, take your " + drink + " and enjoy it! I hope to see you again!");
                            } else {
                                auto.check();
                                message.setText("Sorry, this amount isn`t enought, take your coins back: " + coins + " rubles");
                            }
                            Thread.sleep(7000);
                            buyersCoin.clear();
                            message.clear();
                            coins = 0;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }, 500);
            actualState.setText(state.get(auto.getState()));
        }
    }

        class AutoTimerTask extends TimerTask {
            @Override
            public void run() {
                System.out.println("AutoTimerTask started" + new Date());
                completeTask();
                System.out.println("AutoTimerTask finished" + new Date());

            }

            private void completeTask() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


