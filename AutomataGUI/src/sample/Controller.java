package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {
    Automata CoffeeMashine = new Automata();

    //Button
    @FXML
    private Button btnOnOff;
    @FXML
    private Button btnOkMoney;
    @FXML
    private Button btnChange;
    @FXML
    private Button btnMenu1;
    @FXML
    private Button btnMenu2;
    @FXML
    private Button btnMenu3;
    @FXML
    private Button btnMenu4;
    @FXML
    private Button btnMenu5;
    @FXML
    private Button btnMenu6;

    //Label
    @FXML
    private Label lbMenu1;
    @FXML
    private Label lbMenu2;
    @FXML
    private Label lbMenu3;
    @FXML
    private Label lbMenu4;
    @FXML
    private Label lbMenu5;
    @FXML
    private Label lbMenu6;

    //Text Field
    @FXML
    private TextField tfDisplay;
    @FXML
    private TextField tfMoney;
    @FXML
    private TextField tfChange;

    private ArrayList[] menu;
    public void onAction_btnOnOff (ActionEvent actionEvent) {
        if (CoffeeMashine.getState() == States.OFF ) {
            CoffeeMashine.on();
            tfDisplay.setText("CoffeeMaker ON! Make your choice, man");
            tfMoney.editableProperty().setValue(true);
            tfChange.editableProperty().setValue(true);
            btnMenu1.setDisable(false);
            btnMenu2.setDisable(false);
            btnMenu3.setDisable(false);
            btnMenu4.setDisable(false);
            btnMenu5.setDisable(false);
            btnMenu6.setDisable(false);
            btnChange.setDisable(false);
            btnOkMoney.setDisable(false);
            menu = CoffeeMashine.getMenu();
            btnMenu1.setText(menu[0].get(0).toString());
            btnMenu2.setText(menu[0].get(1).toString());
            btnMenu3.setText(menu[0].get(2).toString());
            btnMenu4.setText(menu[0].get(3).toString());
            btnMenu5.setText(menu[0].get(4).toString());
            btnMenu6.setText(menu[0].get(5).toString());
            lbMenu1.setText(menu[1].get(0).toString());
            lbMenu2.setText(menu[1].get(1).toString());
            lbMenu3.setText(menu[1].get(2).toString());
            lbMenu4.setText(menu[1].get(3).toString());
            lbMenu5.setText(menu[1].get(4).toString());
            lbMenu6.setText(menu[1].get(5).toString());
        }
        else if (CoffeeMashine.getState() == States.WAIT ) {
            tfDisplay.setText("Goodbye");
            tfMoney.editableProperty().setValue(false);
            tfChange.editableProperty().setValue(false);
            btnMenu1.setDisable(true);
            btnMenu2.setDisable(true);
            btnMenu3.setDisable(true);
            btnMenu4.setDisable(true);
            btnMenu5.setDisable(true);
            btnMenu6.setDisable(true);
            btnChange.setDisable(true);
            btnOkMoney.setDisable(true);
            btnMenu1.setText("");
            btnMenu2.setText("");
            btnMenu3.setText("");
            btnMenu4.setText("");
            btnMenu5.setText("");
            btnMenu6.setText("");
            lbMenu1.setText("");
            lbMenu2.setText("");
            lbMenu3.setText("");
            lbMenu4.setText("");
            lbMenu5.setText("");
            lbMenu6.setText("");
            CoffeeMashine.off();
        }
    }

    public void onAction_btnOkMoney (ActionEvent actionEvent) {
        if (CoffeeMashine.getState() != States.OFF ) {
            float temp = Float.parseFloat(tfMoney.getText());
            tfMoney.setText("");
            CoffeeMashine.coin(temp);
            tfDisplay.setText("Credit: " + CoffeeMashine.getCash());
        }
    }

    public void onAction_btnChange (ActionEvent actionEvent){
        Float temp = CoffeeMashine.cancel();
        if (temp != 0) {
            tfDisplay.setText("Choose!");
            tfChange.setText(temp.toString() );
        }
        else {
            tfChange.setText("");
            tfDisplay.setText("Your coins 0");
        }
    }

    public void onAction_btnMenu (ActionEvent actionEvent){
        int temp = 0;
        if(actionEvent.getSource() == btnMenu1){temp = 0;}
        if(actionEvent.getSource() == btnMenu2){temp = 1;}
        if(actionEvent.getSource() == btnMenu3){temp = 2;}
        if(actionEvent.getSource() == btnMenu4){temp = 3;}
        if(actionEvent.getSource() == btnMenu5){temp = 4;}
        if(actionEvent.getSource() == btnMenu5){temp = 5;}
        float cash = CoffeeMashine.getCash();
        float change = CoffeeMashine.choice(temp);
        if (change != cash){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tfDisplay.setText("Take your "+menu[0].get(temp));
        }
        else {tfDisplay.setText("Take your coins and try again" );}
        if (change != 0) {tfChange.setText("" + change);}
        else {tfChange.setText("");}

    }
}
