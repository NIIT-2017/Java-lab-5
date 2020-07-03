package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.ResourceBundle;
import atm.*;

public class Controller implements Initializable {

    ToggleGroup tgFST = new ToggleGroup();
    Automata automata = new Automata();

    @FXML
    private TextArea taPrompt;
    @FXML
    private TextArea taAnswer;
    @FXML
    private TextField tfYourCash;
    @FXML
    private TextField tfAddCash;
    @FXML
    private Button btnCoin;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCook;
    @FXML
    private ToggleButton btnOnOff;
    @FXML
    public RadioButton rbCoffee;
    @FXML
    public RadioButton rbTea;
    @FXML
    public RadioButton rbJuice;
    @FXML
    public ImageView ivIcon = new ImageView();
    @FXML
    private CheckBox cbSugar;
    @FXML
    private CheckBox cbMilk;
    @FXML
    private CheckBox cbIce;
    @FXML
    private CheckBox cbSyrup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        automata = new Automata();
        rbCoffee.setToggleGroup(tgFST);
        rbCoffee.setSelected(true);
        rbCoffee.setUserData("coffee.png");
        rbTea.setToggleGroup(tgFST);
        rbTea.setSelected(false);
        rbTea.setUserData("tea-cup.png");
        rbJuice.setToggleGroup(tgFST);
        rbJuice.setSelected(false);
        rbJuice.setUserData("orange-juice.png");
        ivIcon.setImage(new Image(getClass().getResourceAsStream(rbCoffee.getUserData().toString())));
        tfYourCash.setText(String.valueOf(automata.cash));
        taAnswer.setText(automata.answer);
        taPrompt.setText(automata.prompt);
        automata.order = automata.getMenu().get(0);
        cbSugar.setUserData("Sugar");
        cbMilk.setUserData("Milk");
        cbIce.setUserData("Ice");
        cbSyrup.setUserData("Syrup");
        setListener(cbSugar);
        setListener(cbMilk);
        setListener(cbIce);
        setListener(cbSyrup);
        tgFST.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1) {
                Toggle item = tgFST.getSelectedToggle();
                if (item != null) {
                    if (item == rbCoffee) {
                        automata.order = automata.getMenu().get(0);
                    } else if (item == rbTea) {
                        automata.order = automata.getMenu().get(1);
                    } else if (item == rbJuice) {
                        automata.order = automata.getMenu().get(2);
                    }
                    final Image image = new Image(
                            getClass().getResourceAsStream(tgFST.getSelectedToggle().getUserData().toString())
                    );
                    ivIcon.setImage(image);
                }
            }
        });
    }
    private void refresh() {
        taPrompt.setText(automata.prompt);
        taAnswer.setText(automata.answer);
        tfYourCash.setText(String.valueOf(automata.cash));
    }

    @FXML
    public void onClickCoinBtn() {
        try {
            automata.coin(Float.parseFloat(tfAddCash.getText()));
            tfAddCash.setText("");
            refresh();
        } catch (NumberFormatException e) {
            taAnswer.setText("Invalid input data for cash!");
        }
    }
    @FXML
    public void onClickOnOffBtn() {
        if (btnOnOff.isSelected()) {
            automata.on();
        } else {
            automata.off();
        }
        refresh();
    }
    @FXML
    public void onClickCookBtn() throws InterruptedException {
        automata.cook();
        refresh();
    }
    @FXML
    public void onClickCancelBtn() {
        tgFST.selectToggle(rbCoffee);
        cbSugar.setSelected(false);
        cbMilk.setSelected(false);
        cbIce.setSelected(false);
        cbSyrup.setSelected(false);
    }

    private void setListener(CheckBox cb) {
        cb.selectedProperty().addListener((observableValue, old_val, new_val) ->
                automata.additions.replace(cb.getUserData().toString(), new_val)
        );
    }
}
