package Avtomat.view;

import Avtomat.controller.Automat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class AvtomatGUIController {
    private Automat automat;

    @FXML
    private Button returnMoneyButton;

    @FXML
    private ToggleButton onOffToggleButton;

    @FXML
    private ProgressBar percentProgressBar;

    @FXML
    private Button coin1Button;

    @FXML
    private Button coin2Button;

    @FXML
    private Button coin5Button;

    @FXML
    private Button coin10Button;

    @FXML
    private Button coin50Button;

    @FXML
    private Label cashLabel;

    @FXML
    private Label moneyReturnLabel;

    @FXML
    private Label displayLabel;

    @FXML
    private GridPane menuGridPane;
    public void setAutomat(Automat automat) {
        this.automat = automat;
        //bind label and progressBar
        displayLabel.textProperty().bindBidirectional(automat.stringProperty());
        percentProgressBar.progressProperty().bindBidirectional(automat.percentsProperty());
    }

    @FXML
    void initialize() {
        //imitation putting money
        coin1Button.setOnAction(event  -> coin(coin1Button));
        coin2Button.setOnAction(event  -> coin(coin2Button));
        coin5Button.setOnAction(event  -> coin(coin5Button));
        coin10Button.setOnAction(event -> coin(coin10Button));
        coin50Button.setOnAction(event -> coin(coin50Button));

        //button for return money and refresh label cash
        returnMoneyButton.setOnAction(event -> {
            moneyReturnLabel.setText(String.valueOf(automat.returnMoney()));
            cashLabel.setText(String.valueOf(automat.getCash()));

        });
        //imitation switchON/OFF button
        onOffToggleButton.setOnAction(event -> switchAutomat());
        //initialisation switchON/OFF button
        onOffToggleButton.setStyle("-fx-background-color: red");
        onOffToggleButton.setText("OFF");
        onOffToggleButton.setSelected(false);
    }

    private void switchAutomat() {
        if (onOffToggleButton.isSelected()) {
            //switch on
            automat.on();
            onOffToggleButton.setStyle("-fx-background-color: green");
            onOffToggleButton.setText("ON");
        }
        else{
            //switch off
            automat.off();
            onOffToggleButton.setStyle("-fx-background-color: red");
            onOffToggleButton.setText("OFF");
        }
    }

    private void coin(Button btn) {
        automat.coin(Double.parseDouble(btn.getText()));
        cashLabel.setText(String.valueOf(automat.getCash()));
    }

    public void addRowsMenuGridPane(AnchorPane pane,int col,int row) {
        menuGridPane.add(pane,col,row);
        menuGridPane.getRowConstraints().add(new RowConstraints());
    }
}


