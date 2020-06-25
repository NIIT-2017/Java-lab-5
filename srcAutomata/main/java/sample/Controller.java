package sample;

import Automata.Automata;
import Automata.Drink;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
     private Automata automata;
     @FXML
     private Button buttonOn;
     @FXML
     private Button buttonOff;
     @FXML
     private Label labelState;
     @FXML
     private Label labelCash;
     @FXML
     private TextField textFieldMoney;
     @FXML
     private Button buttonPutMoney;
     @FXML
     private Button buttonBuy;
     @FXML
     private Button buttonCancel;
     @FXML
     private Button buttonReturnMoney;
     @FXML
     private ComboBox<String> comboBoxMenu;
     @FXML
     private ProgressBar progressBar;
     SimpleStringProperty stringPropertyState;
     private boolean isBusy=false;
     @Override
     public void initialize(URL location, ResourceBundle resources)
     {
         stringPropertyState=new SimpleStringProperty();
         stringPropertyState.setValue("State : Off");
         labelState.textProperty().bind(stringPropertyState);
     }
     @FXML
     public void clickOn()
     {
         if(automata==null)
         {
             automata = new Automata("Menu.json");
             automata.on();
             comboBoxMenu.getItems().addAll(automata.getMenu());
         }
         else
            automata.on();
         printState();
     }
     @FXML
     public void clickOff()
     {
         if(automata!=null) {
             automata.off();
             printState();
         }
     }
     @FXML
     public void clickPutMoney()
     {
        try
        {
            automata.coin(Integer.parseInt(textFieldMoney.getText()));
            printState();
        }
        catch (Exception ex)
        {
            textFieldMoney.setText("");
        }
     }
     @FXML
     public void clickBuy() throws Exception
     {
         if(automata!=null) {
             automata.choice(comboBoxMenu.getSelectionModel().getSelectedIndex());
             printState();
             Task<Void> task = new Task<Void>() {
                 @Override
                 protected Void call() {
                     try {
                         Platform.runLater(()->isBusy=true);
                         waitSeconds(2000);
                         Platform.runLater(() -> automata.cook());
                         waitSeconds(6000);
                         Platform.runLater(() -> automata.finish());
                         printStats(1);
                         Platform.runLater(()->isBusy=false);
                     } catch (Exception ex) {

                     }
                     return null;
                 }

                 private void printStats(float progress) {
                     Platform.runLater(() -> printState());
                     Platform.runLater(() -> progressBar.setProgress(progress));
                 }

                 private void waitSeconds(int seconds) throws Exception {
                     for (int i = 1; i <= seconds; i++) {
                         Thread.sleep(1);
                         printStats((float) i / seconds);
                     }
                 }
             };

             Thread th = new Thread(task);
             th.setDaemon(true);
             th.start();
         }
     }
     @FXML
     public void clickCancel()
     {
         if(automata!=null && !isBusy)
            automata.cancel();
         printState();
     }
     @FXML
     public void clickReturnMoney()
     {
         if(automata!=null) {
             automata.returnMoney();
             printState();
         }
     }
     private void printState()
     {
         if(automata!=null)
         {
             stringPropertyState.setValue("State : "+automata.getState());
             labelCash.setText("Cash : "+automata.getCash());
         }
     }
}
