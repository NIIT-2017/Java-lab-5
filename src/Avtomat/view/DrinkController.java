package Avtomat.view;

import java.net.URL;
import java.util.ResourceBundle;

import Avtomat.controller.Automat;
import Avtomat.model.Drink;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DrinkController {
    private Drink drink;
    private Automat automat;

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

    public void setupDrink(Drink drink, Automat automat){
        this.drink = drink;
        drinkLabel.setText(drink.getDrink());
        priceLabel.setText(String.valueOf(drink.getCost()));
        this.automat = automat;
    }


    @FXML
    void initialize() {
        changeButton.setOnAction(event ->
            automat.choiceDrink(automat.getDrinks().indexOf(drink))
        );
    }
}
