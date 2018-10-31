package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Controller {
    private Automata a=new Automata(new String[]{"Caffè Americano","Espresso","Caffè Mocha"},new int[]{10,12,15});

    @FXML
    private Button bt_cash;
    @FXML
    private Button bt_americano;
    @FXML
    private Button bt_espresso;
    @FXML
    private Button bt_mocha;
    @FXML
    private TextField txt_moneyback;
    @FXML
    private TextField txt_cashDisplay;
    @FXML
    private Label txtDisplay;
    @FXML
    private Button bt_on;
    @FXML
    private Button bt_printMenu;
    @FXML
    private Button bt_printState;

    public void onPrintMenu(){
        txtDisplay.setText(a.printMenu());
    }

    public void onPrintState(){
        txtDisplay.setText(String.valueOf(a.printState()));
    }



}
