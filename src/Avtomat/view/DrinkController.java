package Avtomat.view;

import java.net.URL;
import java.util.ResourceBundle;

import Avtomat.model.Drink;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DrinkController {
    private Drink drink;


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label drinkLabel;

    @FXML
    private Button changeButton;

    @FXML
    private Label priceLabel;

    public void setDrink(Drink drink){
        this.drink = drink;
        drinkLabel.setText(drink.getDrink());
        priceLabel.setText(String.valueOf(drink.getCost()));
    }

    @FXML
    void initialize() {

    }
}
