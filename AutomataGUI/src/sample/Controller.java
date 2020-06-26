package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;

public class Controller implements Initializable {
    @FXML
    private RadioButton rbtClose;

    @FXML
    private ToggleButton tbOnOff;

    @FXML
    private Button btPicUpChange;

    @FXML
    private Button btTakeCoffee;

    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;

    @FXML
    private Button btCooking;

    @FXML
    private Button btPutMoney;

    @FXML
    private ImageView ivOnOff;

    @FXML
    private ImageView ivOn;

    @FXML
    private ImageView ivOff;

    @FXML
    private ImageView ivCoffee;

    @FXML
    private ComboBox<Integer> cbCash;

    @FXML
    private TextField tfCurrentCash;

    @FXML
    private TextField tfStatus;

    @FXML
    private ProgressBar pbCooking;

    private AutomataDemo automata;

    Tooltip on = new Tooltip("Сlick to turn off the coffee machine");
    Tooltip off = new Tooltip("Сlick to turn on the coffee machine");

    int inWork = 0;

    private int selectedBeverage = 99;
    private int cashChoice = 0;
    private int bufer = 0;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        automata = new AutomataDemo();
        setComboBox();
        setMenu();
        bt1.setTooltip(new Tooltip("Espresso is made by forcing very\n" +
                "hot water under high pressure through\n" +
                "finely ground compacted coffee."));
        bt2.setTooltip(new Tooltip("Americano is a classic espresso\nwith hot water added."));
        bt3.setTooltip(new Tooltip("Latte is made from a portion of espresso, \nwhich is filled with hot foamed milk."));
        bt4.setTooltip(new Tooltip("To make a cappuccino, foamed milk is poured\n" +
                                        "into the espresso. The milk foam on top of the\n" +
                                        "drink acts as an insulator \n" +
                                        "and helps keep the temperature high."));
        bt5.setTooltip(new Tooltip("Mocachino is a coffee drink with a pronounced\n" +
                "chocolate taste. Mocachino is two-thirds espresso,\n" +
                "the rest is milk and chocolate, cocoa, cream."));
        bt6.setTooltip(new Tooltip("RAF-coffee is a dessert drink based on coffee \n(a cocktail of espresso, sugar and cream).\n" +
                "A special feature of the recipe is\nthe vanilla flavor and uniform structure."));
        btPutMoney.setTooltip(new Tooltip("Click to select a deposit"));
        btPicUpChange.setTooltip(new Tooltip("Сlick to withdraw your Deposit"));
        btCooking.setTooltip(new Tooltip("Click to prepare the selected drink"));
        tbOnOff.setTooltip(off);
        rbtClose.setTooltip(new Tooltip("Close the program"));
    }
    public void choice(int value){
        if (automata.getState()== "TAKE")
            tfStatus.setText("First of all, take your already prepared coffee");
        else {
            tfStatus.setText(automata.getMenu(value));
            selectedBeverage = value;
        }
    }
    public void cookClick(){
        tfStatus.setText(automata.choice(selectedBeverage));
        bufer = automata.getPrices(selectedBeverage);
        if (automata.getState()=="COOK" && inWork ==0) {
            inWork = 1;
            moneyDown moneyDown = new moneyDown();
            moneyDown.start();
            progress progress = new progress();
            progress.start();
        }
        selectedBeverage = 99;
    }
    public void tgOnOffOnClick(){
        if (automata.getState() == "OFF"){
            ivOnOff.setImage(ivOn.getImage());
            tbOnOff.setText("ON");
            tfStatus.setText(automata.on());
            tfCurrentCash.setText(automata.getCoin()+" ₽");
            launch(true);
            tbOnOff.setTooltip(on);
        }
        else {
            tfStatus.setText(automata.off());
        }
        if (automata.getState() == "OFF"){
            launch(false);
            tfStatus.setText(null);
            tfCurrentCash.setText(null);
            ivOnOff.setImage(ivOff.getImage());
            tbOnOff.setText("OFF");
            tbOnOff.setTooltip(off);
        }
    }
    public void btPutMoney(){
        cbCash.setVisible(true);
        btPutMoney.setVisible(false);
        cbCash.setDisable(false);
    }
    public void cbCashChoice(){
        int i = 0;
        cashChoice++;
        try {i = cbCash.getValue();}catch (RuntimeException ignored){}
        automata.coin(i);
        bufer = i;
        if (cashChoice%2 == 1)
            tfStatus.setText("You made a deposit "+i+" ₽");
        cbCash.setDisable(true);
        moneyUp moneyUp = new moneyUp();
        moneyUp.start();
        cbCash.setVisible(false);
        btPutMoney.setVisible(true);
        pbCooking.requestFocus();
        javafx.application.Platform.runLater(() ->
                cbCash.getSelectionModel().clearSelection());
    }
    public void btPicUpChangeOnClick(){
        tfStatus.setText(automata.moneyBack());
        moneyUp.interrupted();
        moneyDown.interrupted();
        tfCurrentCash.setText(automata.getCoin()+" ₽");

    }
    public void bt1Click(){
        choice(0);
    }
    public void bt2Click(){
        choice(1);
    }
    public void bt3Click(){
        choice(2);
    }
    public void bt4Click(){
        choice(3);
    }
    public void bt5Click(){
        choice(4);
    }
    public void bt6Click(){
        choice(5);
    }
    public void setMenu(){
        bt1.setText(automata.getPrices(0)+"₽ "+automata.getDrink(0));
        bt2.setText(automata.getPrices(1)+"₽ "+automata.getDrink(1));
        bt3.setText(automata.getPrices(2)+"₽ "+automata.getDrink(2));
        bt4.setText(automata.getPrices(3)+"₽ "+automata.getDrink(3));
        bt5.setText(automata.getPrices(4)+"₽ "+automata.getDrink(4));
        bt6.setText(automata.getPrices(5)+"₽ "+automata.getDrink(5));
    }
    public void rbtCloseOnClick(){
        exit();
    }
    public void setComboBox(){
        ObservableList<Integer> cashAvailable = FXCollections.observableArrayList(5,10,50,100);
        cbCash.setItems(cashAvailable);
    }
    public void launch(boolean status){
        if (status){
            bt1.setDisable(false);
            bt2.setDisable(false);
            bt3.setDisable(false);
            bt4.setDisable(false);
            bt5.setDisable(false);
            bt6.setDisable(false);
            cbCash.setDisable(false);
            tfStatus.setDisable(false);
            btCooking.setDisable(false);
            pbCooking.setDisable(false);
            btPutMoney.setDisable(false);
            btPicUpChange.setDisable(false);
            tfCurrentCash.setDisable(false);

        }
        else{
            bt1.setDisable(true);
            bt2.setDisable(true);
            bt3.setDisable(true);
            bt4.setDisable(true);
            bt5.setDisable(true);
            bt6.setDisable(true);
            cbCash.setDisable(true);
            tfStatus.setDisable(true);
            btCooking.setDisable(true);
            pbCooking.setDisable(true);
            btPutMoney.setDisable(true);
            btPicUpChange.setDisable(true);
            tfCurrentCash.setDisable(true);
        }
    }
    public void takeCoffee(){
        ivCoffee.setVisible(false);
        btTakeCoffee.setVisible(false);
        pbCooking.setProgress(0);
        tfStatus.setText(automata.seeYou());

    }

    class moneyUp extends Thread{
        @Override
        public void run() {
            int i = automata.getCoin()-bufer;
            try{
                if (!interrupted()) {
                    for (; i <= automata.getCoin(); i++) {
                        try {
                            Thread.sleep(5);
                            int finalI = i;
                            javafx.application.Platform.runLater( () -> tfCurrentCash.setText(finalI + " ₽"));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e){
                            tfCurrentCash.setText(automata.getCoin()+" ₽");
                        }
                    }
                }
                else{
                    throw new InterruptedException();
                }
            }catch (InterruptedException e){
                javafx.application.Platform.runLater( () -> tfCurrentCash.setText(automata.getCoin()+" ₽"));
            }
        }
    }
    class moneyDown extends Thread{
        @Override
        public void run() {
            int i = automata.getCoin()+bufer;
            try{
                if (!interrupted()) {
                    for (; i >= automata.getCoin(); i--) {
                        try {
                            Thread.sleep(5);
                            int finalI = i;
                            javafx.application.Platform.runLater( () -> tfCurrentCash.setText(finalI + " ₽"));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (NullPointerException e){
                            tfCurrentCash.setText(automata.getCoin()+" ₽");
                        }
                    }
                }
                else{
                    throw new InterruptedException();
                }
            }catch (InterruptedException e){
                javafx.application.Platform.runLater( () -> tfCurrentCash.setText(automata.getCoin()+" ₽"));
            }
            selectedBeverage = 99;
        }
    }
    class progress extends Thread {
        @Override
        public void run() {
            double i = 0;
            while (true){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                double finalI = i*0.001;
                javafx.application.Platform.runLater(()-> pbCooking.setProgress(finalI));
                i++;
                if(i>1000) break;
            }
            tfStatus.setText(automata.finish());
            btTakeCoffee.setVisible(true);
            ivCoffee.setVisible(true);
            inWork = 0;
        }
    }
}