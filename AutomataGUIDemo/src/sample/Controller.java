package sample;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.ResourceBundle;

import sample.Automate;

public class Controller implements Initializable {

    private Automate automate;
    private String choiceDrink = "";
    private Image background = new Image(getClass().getResourceAsStream("background.jpg"));
    private Image drink = new Image(getClass().getResourceAsStream("drink.jpg"));
    private Image money = new Image(getClass().getResourceAsStream("money.jpg"));

    @FXML
    private Button btnON;

    @FXML
    private Button btnOFF;

    @FXML
    private Button btnDeposit;

    @FXML
    private Button btnNumber1;

    @FXML
    private Button btnNumber2;

    @FXML
    private Button btnNumber3;

    @FXML
    private Button btnNumber4;

    @FXML
    private Button btnNumber5;

    @FXML
    private Button btnNumber6;

    @FXML
    private Button btnNumber7;

    @FXML
    private Button btnNumber8;

    @FXML
    private Button btnNumber9;

    @FXML
    private Button btnNumber0;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnTake;

    @FXML
    private TextField tfBalance;

    @FXML
    private RadioButton rb100rub;

    @FXML
    private RadioButton rb50rub;

    @FXML
    private RadioButton rb10rub;

    @FXML
    private RadioButton rb5rub;

    @FXML
    private TextArea taPrice;

    @FXML
    private TextArea taInfo;

    @FXML
    private ImageView iwDrink;

    @FXML
    private ImageView iwMoney;

    public void initialize(URL location, ResourceBundle resources) {
        iwDrink.setImage(background);
        iwMoney.setImage(background);
        tfBalance.setEditable(false);
        taInfo.setEditable(false);
        taPrice.setEditable(false);

        btnON.setOnAction(event -> {
            if (automate == null || automate.printState() == null) {
                createAutomate();
                newSession();
            }
        });

        btnOFF.setOnAction(event -> {
            if (automate != null) {
                boolean result = automate.off();
                if (result) {
                    tfBalance.deleteText(0, tfBalance.getText().length());
                    taInfo.deleteText(0, taInfo.getText().length());
                    taPrice.deleteText(0, taPrice.getText().length());
                }
            }
        });

        btnTake.setOnAction(event -> {
            iwDrink.setImage(background);
            iwMoney.setImage(background);
            if (automate != null && automate.printState() != null && automate.printState().compareTo("WAIT") == 0){
                taInfo.deleteText(0, taInfo.getText().length());
                newSession();
            }
            if (automate != null && automate.printState() != null && automate.printState().compareTo("ACCEPT") == 0){
                int startDel = taInfo.getText().indexOf("Возьмите вашу сдачу:");
                int endDel = taInfo.getText().indexOf(".", startDel);
                taInfo.deleteText(startDel, endDel+2);
            }
        });

        btnDeposit.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && (automate.printState().compareTo("WAIT") == 0 || automate.printState().compareTo("ACCEPT") == 0)){
                int balance = Integer.parseInt(tfBalance.getText());
                if(rb100rub.isSelected()){
                    balance += 100;
                    automate.coin(100);
                }else if(rb50rub.isSelected()){
                    balance += 50;
                    automate.coin(50);
                }else if(rb10rub.isSelected()){
                    balance += 10;
                    automate.coin(10);
                }else if(rb5rub.isSelected()){
                    balance += 5;
                    automate.coin(5);
                }
                tfBalance.setText(String.valueOf(balance));
            }
        });

        btnCancel.setOnAction(event -> {
            if(choiceDrink.length() > 0){
                taInfo.deleteText(0, taInfo.getText().length());
                newSession();
                choiceDrink = "";
            }else {
                if (automate != null) {
                    int result = automate.cancel();
                    if (result > 0){
                        taInfo.deleteText(0, taInfo.getText().length());
                        cashBack(result);
                    }
                }
            }
        });

        //region Numbers
        btnNumber0.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "0";
                taInfo.appendText("0");
            }
        });

        btnNumber1.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "1";
                taInfo.appendText("1");
            }
        });

        btnNumber2.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "2";
                taInfo.appendText("2");
            }
        });

        btnNumber3.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "3";
                taInfo.appendText("3");
            }
        });

        btnNumber4.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "4";
                taInfo.appendText("4");
            }
        });

        btnNumber5.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "5";
                taInfo.appendText("5");
            }
        });

        btnNumber6.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "6";
                taInfo.appendText("6");
            }
        });

        btnNumber7.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "7";
                taInfo.appendText("7");
            }
        });

        btnNumber8.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "8";
                taInfo.appendText("8");
            }
        });

        btnNumber9.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() < 3){
                choiceDrink += "9";
                taInfo.appendText("9");
            }
        });
        //endregion

        btnEnter.setOnAction(event -> {
            if (automate != null && automate.printState() != null
                    && automate.printState().compareTo("ACCEPT") == 0 && choiceDrink.length() > 0){
                Integer result = automate.choice(Integer.parseInt(choiceDrink));
                if(result != null){
                    if (result >= 0){
                        cookDrink(result);
                        choiceDrink = "";
                    }else {
                        taInfo.deleteText(0, taInfo.getText().length());
                        taInfo.appendText("Для выбранного напитка не хватает " + String.valueOf(~(result)+1) + " руб.\n");
                        newSession();
                        taInfo.appendText(String.valueOf(choiceDrink));
                    }
                }
            }
        });
    }

    private void cookDrink(int result) {
        taInfo.deleteText(0, taInfo.getText().length());
        taInfo.appendText("Ваш напиток готов.\n");
        iwDrink.setImage(drink);
        cashBack(result);
    }

    private void cashBack(int result) {
        if (result > 0) {
            taInfo.appendText("Возьмите вашу сдачу: " + String.valueOf(result) + " руб.\n");
            iwMoney.setImage(money);
        }
        newSession();
        tfBalance.setText("0");
    }

    private void createAutomate() {
        ArrayList<String> menu;
        ArrayList<Integer> prices;

        try {
            InputStream is = getClass().getResourceAsStream("price.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            JSONParser parser=new JSONParser();
            JSONObject js=(JSONObject)parser.parse(reader);
            JSONArray items=(JSONArray)js.get("drinks");

            menu = new ArrayList<>();
            prices = new ArrayList<>();

            for(Object i : items) {
                int price = Math.toIntExact((Long) ((JSONObject)i).get("price"));
                prices.add(price);
                String drink = (String) ((JSONObject)i).get("name");
                menu.add(drink);
            }

        }catch (Exception e){
            taInfo.setText("Error: Price file is missing");
            return;
        }

        int[] pricesArray = new int[prices.size()];
        for (int i = 0; i < prices.size(); i++) {
            pricesArray[i] = prices.get(i);
        }

        automate = Automate.getAutomate(menu.toArray(new String[0]), pricesArray);
        if (automate != null) {
            automate.on();
            String[] result = automate.printMenu();
            for (String line:result
            ) {
                taPrice.appendText(line);
                taPrice.appendText("\n");
            }
        }
        tfBalance.setText("0");
    }

    private void newSession() {
        taInfo.appendText("Автомат готов принять ваш заказ!\n");
        taInfo.appendText("Внесите деньги, выбирите напиток и\n");
        taInfo.appendText("введите его номер:  ");
    }
}
