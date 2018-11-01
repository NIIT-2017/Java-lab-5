package sample;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    private Task copyWorker;
    private Automata a = new Automata(new String[]{"Caffè Americano", "Espresso", "Caffè Mocha"},
            new int[]{10, 12, 15});
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
    @FXML
    private Button bt_takeMoney;
    @FXML
    private Button bt_cook;
    @FXML
    private Circle shape_pwr;
    @FXML
    private ProgressIndicator shape_progress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtDisplay.setText(String.valueOf(a.printState()));
        txt_cashDisplay.setText("0");
        txt_moneyback.setText("0");
        shape_progress.setProgress(0.0);
    }

    public void onPrintMenu() {
        txtDisplay.setText(a.printState() != STATES.OFF ? a.printMenu() : String.valueOf(a.off()));
    }

    public void onPrintState() {
        txtDisplay.setText(String.valueOf(a.printState()));
    }

    public void onPower() {
        txtDisplay.setText(a.printState() == STATES.OFF ? String.valueOf(a.on()) : String.valueOf(a.off()));
        shape_pwr.setFill((a.printState() == STATES.OFF) ? Paint.valueOf("red") : Paint.valueOf("green"));
    }

    public void onCash() {
        try {
            a.coin(Integer.parseInt(txt_cashDisplay.getText()));
            txtDisplay.setText(String.valueOf(
                    a.getCash() == 0 ? txtDisplay.getText() : a.getCash()));
            txt_cashDisplay.setText("0");
            shape_progress.setVisible(false);
        } catch (NumberFormatException e) {
            txt_cashDisplay.setText("Wrong!!!");
        }
    }

    public void onAmericano() {
        txtDisplay.setText(String.valueOf(a.choice(0)));
    }

    public void onEspresso() {
        txtDisplay.setText(String.valueOf(a.choice(1)));
    }

    public void onMocha() {
        txtDisplay.setText(String.valueOf(a.choice(2)));
    }

    public void onCook() {

        copyWorker = createWorker();
        //shape_progress.setProgress(0.0);
        shape_progress.progressProperty().unbind();
        shape_progress.progressProperty().bind(copyWorker.progressProperty());
        shape_progress.setVisible(true);
        Thread t = new Thread(copyWorker);
        t.start();
        txtDisplay.setText(String.valueOf(a.cook()));
        txt_moneyback.setText(String.valueOf(a.finish()));
    }

    public void onCancel() {
        txt_moneyback.setText(String.valueOf(a.cancel()));
        txtDisplay.setText(String.valueOf(a.printState()));
    }

    public void onTakeMoney() {
        txt_moneyback.setText("0");
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(25);
                    updateProgress(i + 1, 100);
                    //System.out.println(shape_progress.getProgress());
                }
                return true;
            }
        };
    }
}
