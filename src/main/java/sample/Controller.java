package sample;

import Methods.Automata;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;


public class Controller implements Initializable{
    @FXML
    private Button btnTeaBlackLemon;
    @FXML
    private Button btnTeaBlackBerg;
    @FXML
    private Button btnTeaGreen;
    @FXML
    private Button btnTeaGreenLemon;
    @FXML
    private Button btnCapuchino;
    @FXML
    private Button btnCoffeBlended;
    @FXML
    private Button btnLatte;
    @FXML
    private Button btnCoffeBlendedMilk;


    @FXML
    private TextField tfDrink;
    @FXML
    private TextField tfPrice;

    @FXML
    private TextField tfScore;
    @FXML
    private TextField tfState;
    @FXML
    private Button btBuy;

    @FXML
    private RadioButton rbOn;
    @FXML
    private RadioButton rbOff;

    private ToggleGroup tgONOFF = new ToggleGroup();

    @FXML
    private TextField tfEntScore;
    @FXML
    private Button btnEnter;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnFinish;
    @FXML
    private CheckBox  cbSurrender;

    private Automata automata;
    private Timer t,t2;
    private TimerTask task,task2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        automata = new Automata("/menu.json");
        rbOff.fire();
        tfState.setText(automata.off());
        btnTeaBlackLemon.setDisable(true);
        btnTeaBlackBerg.setDisable(true);
        btnTeaGreen.setDisable(true);
        btnTeaGreenLemon.setDisable(true);
        btnCapuchino.setDisable(true);
        btnCoffeBlended.setDisable(true);
        btnLatte.setDisable(true);
        btnCoffeBlendedMilk.setDisable(true);
        tfDrink.setDisable(true);
        tfPrice.setDisable(true);
        tfScore.setDisable(true);
        tfEntScore.setDisable(true);
        btnEnter.setDisable(true);
        btnCancel.setDisable(true);
        btnFinish.setDisable(true);
        cbSurrender.setDisable(true);
        btBuy.setDisable(true);

        rbOn.setToggleGroup(tgONOFF);
        rbOff.setToggleGroup(tgONOFF);

        tgONOFF.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> changed, Toggle oldValue, Toggle newValue) {
                RadioButton selectedBtn = (RadioButton) newValue;
                String exptype = selectedBtn.getText();
                if (exptype.equals("on")){
                    btnTeaBlackLemon.setDisable(false);
                    btnTeaBlackBerg.setDisable(false);
                    btnTeaGreen.setDisable(false);
                    btnTeaGreenLemon.setDisable(false);
                    btnCapuchino.setDisable(false);
                    btnCoffeBlended.setDisable(false);
                    btnLatte.setDisable(false);
                    btnCoffeBlendedMilk.setDisable(false);
                    tfDrink.setDisable(false);
                    tfPrice.setDisable(false);
                    tfScore.setDisable(false);
                    tfEntScore.setDisable(false);
                    btnEnter.setDisable(false);
                    btnCancel.setDisable(false);
                    btnFinish.setDisable(false);
                    cbSurrender.setDisable(false);
                    btBuy.setDisable(false);
                    tfState.setText(automata.on());
                    tfScore.setText(automata.checkcash().toString());
                }
                else if (exptype.equals("off")){
                    tfState.setText(automata.off());
                    tfDrink.clear();
                    tfPrice.clear();
                    tfScore.clear();
                    btnTeaBlackLemon.setDisable(true);
                    btnTeaBlackBerg.setDisable(true);
                    btnTeaGreen.setDisable(true);
                    btnTeaGreenLemon.setDisable(true);
                    btnCapuchino.setDisable(true);
                    btnCoffeBlended.setDisable(true);
                    btnLatte.setDisable(true);
                    btnCoffeBlendedMilk.setDisable(true);
                    tfDrink.setDisable(true);
                    tfPrice.setDisable(true);
                    tfScore.setDisable(true);
                    tfEntScore.setDisable(true);
                    btnEnter.setDisable(true);
                    btnCancel.setDisable(true);
                    btnFinish.setDisable(true);
                    cbSurrender.setDisable(false);
                    btBuy.setDisable(true);
                }
            }
        });
    }
    @FXML
    public void TeaBlackLemonClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnTeaBlackLemon.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void TeaBlackBergClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnTeaBlackBerg.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void TeaGreenClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnTeaGreen.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void TeaGreenLemonClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnTeaGreenLemon.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void CapuchinoClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnCapuchino.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void CoffeBlendedClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnCoffeBlended.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void LatteClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnLatte.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void CoffeBlendedMilkClick (){
        tfDrink.clear();
        tfPrice.clear();
        String text = btnCoffeBlendedMilk.getText();
        tfDrink.setText(text);
        tfPrice.setText(automata.getPrice(text).toString());
    }
    @FXML
    public void EnterClick(){
        tfScore.setText(automata.coin(Integer.parseInt(tfEntScore.getText())));
        tfEntScore.clear();
        tfState.setText(automata.getState());
    }
    @FXML
    public void BuyClick() {
        btBuy.setDisable(true);
        t=new Timer();
        task = new TimerTask() {
            @Override public void run() {
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                            tfState.setText(automata.choise(tfDrink.getText()));
                            tfScore.setText(automata.checkcash().toString());
                        }
                    });
                }
            };
        t.schedule(task,2000);
        t2=new Timer();
        task2 = new TimerTask() {
            @Override public void run() {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        if(automata.getState().equals("COOK"))
                            tfState.setText(automata.cancel());
                        else
                            tfState.setText(automata.getState());
                        btBuy.setDisable(false);
                    }
                });
            }
        };
        t2.schedule(task2,7000);
        new Thread(new Runnable() {
            @Override public void run() {
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        tfState.setText("CHECK");
                    }
                });
            }
        }).start();
    }
    @FXML
    public void CancelClick(){
        tfState.setText(automata.cancel());
    }
    @FXML
    public void FinishClick(){
        if (cbSurrender.isSelected()){
            tfState.setText(automata.finish("YES"));
            tfScore.setText(automata.checkcash().toString());
        }
        else
            tfScore.setText(automata.checkcash().toString());
    }
}

