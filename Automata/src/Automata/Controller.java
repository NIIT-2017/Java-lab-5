package Automata;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane View;
    @FXML
    private Button OnOff;
    @FXML
    private Button Buy1;
    @FXML
    private Button Buy2;
    @FXML
    private Button Buy3;
    @FXML
    private Button Buy4;
    @FXML
    private Button Buy5;
    @FXML
    private Button Buy6;
    @FXML
    private Button Enter;
    @FXML
    private Button Get;
    @FXML
    private Button ReturnCash;
    @FXML
    private TextField tfCash;
    @FXML
    private Label Bever1;
    @FXML
    private Label Bever2;
    @FXML
    private Label Bever3;
    @FXML
    private Label Bever4;
    @FXML
    private Label Bever5;
    @FXML
    private Label Bever6;
    @FXML
    private Label Price1;
    @FXML
    private Label Price2;
    @FXML
    private Label Price3;
    @FXML
    private Label Price4;
    @FXML
    private Label Price5;
    @FXML
    private Label Price6;
    @FXML
    private Label State;
    @FXML
    private Label Balance;
    @FXML
    private Label Prompt;

    Automata auto = new Automata();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Bever1.setText(auto.getterMenu().get(0));
        Bever2.setText(auto.getterMenu().get(1));
        Bever3.setText(auto.getterMenu().get(2));
        Bever4.setText(auto.getterMenu().get(3));
        Bever5.setText(auto.getterMenu().get(4));
        Bever6.setText(auto.getterMenu().get(5));

        Price1.setText(auto.getterPrice().get(0).toString());
        Price2.setText(auto.getterPrice().get(1).toString());
        Price3.setText(auto.getterPrice().get(2).toString());
        Price4.setText(auto.getterPrice().get(3).toString());
        Price5.setText(auto.getterPrice().get(4).toString());
        Price6.setText(auto.getterPrice().get(5).toString());

        auto.setCondition(State);
        auto.setBalance(Balance);
        auto.setPrompt(Prompt);
    }

    @FXML
    public void onOff() {
        if (auto.state == Automata.States.OFF) {
            auto.on();
        } else if (auto.state == Automata.States.WAIT) {
            auto.off();
        }
    }

    @FXML
    public void enter() {
        if (auto.state != Automata.States.OFF) {
            auto.coin(Integer.parseInt(tfCash.getText().trim()));
            System.out.println(Integer.parseInt(tfCash.getText().trim()));
            Balance.setText(auto.getterCash().toString());
            tfCash.clear();
        }
    }

    @FXML
    public void buy1() {
        auto.choice(1);
    }
    @FXML
    public void buy2() {
        auto.choice(2);
        Balance.setText(auto.getterCash().toString());}
    @FXML
    public void buy3() {
        auto.choice(3);
        Balance.setText(auto.getterCash().toString());
    }
    @FXML
    public void buy4() {
        auto.choice(4);
        Balance.setText(auto.getterCash().toString());}
    @FXML
    public void buy5() {
        auto.choice(5);
        Balance.setText(auto.getterCash().toString());}
    @FXML
    public void buy6() {
        auto.choice(6);
        Balance.setText(auto.getterCash().toString());}

    public void cancel(){
        auto.cancel();
    }





}


