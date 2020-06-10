package sample;
import AutomataDemo.Automata;
import AutomataDemo.STATES;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import org.json.JSONException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

class CookTask extends Task<Boolean> {
    private GridPane gridPane;

    public CookTask(GridPane gridPane){
        this.gridPane = gridPane;
    }
    @Override
    protected Boolean call() throws Exception {
        gridPane.setMouseTransparent(true);

        for(int i=0; i<100; i++){
            this.cook();
            this.updateProgress(i, 100);
            this.updateMessage("Идёт приготовление напитка ... " + i + "%");
        }
        this.updateProgress(0, 0);
        gridPane.setMouseTransparent(false);
        return true;
    }

    private void cook() throws InterruptedException {
        Thread.sleep(100);
    }
}

public class Controller implements Initializable {
    private Automata atmt;
    private HashMap<String, Float> prices;
    private List<String> keys = new ArrayList<>();

    @FXML
    private ProgressBar progressBar;

    @FXML
    private GridPane gridPane;

    @FXML
    private Label lbInstruction;

    @FXML
    private Pane pane;

    @FXML
    private Label lbMessage;

    @FXML
    private Button btnOnOff;


    @FXML
    private Button btnComplete;

    @FXML
    private Button btnPay;

    @FXML
    private TextField tfAmount;

    @FXML
    private Button btn11;
    @FXML
    private Button btn12;
    @FXML
    private Label label111;
    @FXML
    private Label label112;
    @FXML
    private Label label121;
    @FXML
    private Label label122;
    @FXML
    private Button btn21;
    @FXML
    private Button btn22;
    @FXML
    private Label label211;
    @FXML
    private Label label212;
    @FXML
    private Label label221;
    @FXML
    private Label label222;
    @FXML
    private Button btn31;
    @FXML
    private Button btn32;
    @FXML
    private Label label311;
    @FXML
    private Label label312;
    @FXML
    private Label label321;
    @FXML
    private Label label322;
    @FXML
    private Button btn41;
    @FXML
    private Button btn42;
    @FXML
    private Label label411;
    @FXML
    private Label label412;
    @FXML
    private Label label421;
    @FXML
    private Label label422;
    @FXML
    private Button btn51;
    @FXML
    private Button btn52;
    @FXML
    private Label label511;
    @FXML
    private Label label512;
    @FXML
    private Label label521;
    @FXML
    private Label label522;
    @FXML
    private Button btn61;
    @FXML
    private Button btn62;
    @FXML
    private Label label611;
    @FXML
    private Label label612;
    @FXML
    private Label label621;
    @FXML
    private Label label622;

    @FXML
    public void btn11Click() throws InterruptedException {
        String key = label111.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn12Click() throws InterruptedException {
        String key = label121.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn21Click() throws InterruptedException {
        String key = label211.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn22Click() throws InterruptedException {
        String key = label221.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn31Click() throws InterruptedException {
        String key = label311.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn32Click() throws InterruptedException {
        String key = label321.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn41Click() throws InterruptedException {
        String key = label411.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn42Click() throws InterruptedException {
        String key = label421.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn51Click() throws InterruptedException {
        String key = label511.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn52Click() throws InterruptedException {
        String key = label521.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn61Click() throws InterruptedException {
        String key = label611.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btn62Click() throws InterruptedException {
        String key = label621.getText();
        btnClick(key, prices.get(key));
    }

    @FXML
    public void btnOnOffClick(){

        if(atmt.getState() == STATES.OFF){
            gridPane.setVisible(true);
            atmt.on();
            atmt.getMenu();
            lbMessage.setText("");
        } else {
            if(atmt.getCash()>0){
                lbMessage.setText(String.format("Выдана сдача %.2f", atmt.getCash()));
            } else if (atmt.getCash() == 0){
                lbMessage.setText("");
            }
            atmt.off();
            gridPane.setVisible(false);
        }
    }

    @FXML
    public void bntCompleteClick(){
        float temp = atmt.getCash();
        atmt.cancel();
        if (temp > 0){
            lbMessage.setText(String.format("Выдана сдача %.2f.\nНа счете автомата %.2f.", temp, atmt.getCash()));
        } else if (temp == 0){
            lbMessage.setText("");
        }
    }

    @FXML
    public void bntPayClick(){
        try{
            float amount = Float.parseFloat(tfAmount.getText());
            atmt.coin(amount);
            lbMessage.setText(String.format("На счете автомата %.2f", atmt.getCash()));
            tfAmount.clear();

        } catch (Exception e){
            lbMessage.setText("Не удалось преобразовать введённое значение к числу.\nПопробуйте внести сумму ещё раз.");
        }

    }

    private void btnClick(String key, Float price) throws InterruptedException {
        if(atmt.getState() == STATES.ACCEPT){
            if(atmt.getCash() < price){
                lbMessage.setText(String.format("Не хватает средств!\n Стоимость напитка состовляет %.2f. Остаток на счете %.2f.", price, atmt.getCash()));
            } else {
                CookTask cookTask = new CookTask(gridPane);
                progressBar.progressProperty().unbind();
                progressBar.progressProperty().bind(cookTask.progressProperty());
                Thread th = new Thread(cookTask);
                th.start();
                //th.join();

                atmt.choice(key);
                lbMessage.setText(String.format("Остаток на счете %.2f", atmt.getCash()));
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //pane.setStyle("-fx-background-color: LINEN");
        //pane.setStyle("-fx-background-color: PAPAYAWHIP");
        pane.setStyle("-fx-background-color: OLDLACE");

        lbInstruction.setText("Инструкция:\n" +
                " 1. Для того, чтобы включить (выключить) устройство, воспользуйтесь командой 'ON / OFF'.\n" +
                " 2. Оплатите покупку - введите сумму и воспользуйтесь командой 'Внести'.\n" +
                " 3. Для приготовления напитка воспользуйтесь командой расположенной в центре основного меню.\n" +
                " 4. Для того, чтобы снять остоток внесенных денежных средств или отменить покупку воспользуйтесь командой 'Завершить'.\n" +
                " ВНИМАНИЕ: Отмена покупки невозможна на этапе приготовления напитка.");

        lbInstruction.setStyle("-fx-background-color: WHITE");

        btnOnOff.setStyle("-fx-background-image: url('/onoff.png')");
        btn11.setStyle("-fx-background-image: url('/icon8.png')");
        btn12.setStyle("-fx-background-image: url('/icon8.png')");
        btn21.setStyle("-fx-background-image: url('/icon8.png')");
        btn22.setStyle("-fx-background-image: url('/icon8.png')");
        btn31.setStyle("-fx-background-image: url('/icon8.png')");
        btn32.setStyle("-fx-background-image: url('/icon8.png')");
        btn41.setStyle("-fx-background-image: url('/icon8.png')");
        btn42.setStyle("-fx-background-image: url('/icon8.png')");
        btn51.setStyle("-fx-background-image: url('/icon8.png')");
        btn52.setStyle("-fx-background-image: url('/icon8.png')");
        btn61.setStyle("-fx-background-image: url('/icon8.png')");
        btn62.setStyle("-fx-background-image: url('/icon8.png')");


        try {
            atmt = new Automata();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        atmt.on();
        prices = atmt.getMenu();

        for(String key : prices.keySet()){
            keys.add(key);
        }

        label111.setText(keys.get(0));
        label112.setText("Цена: " + prices.get(keys.get(0)));
        label121.setText(keys.get(1));
        label122.setText("Цена: " + prices.get(keys.get(1)));
        label211.setText(keys.get(2));
        label212.setText("Цена: " + prices.get(keys.get(2)));
        label221.setText(keys.get(3));
        label222.setText("Цена: " + prices.get(keys.get(3)));
        label311.setText(keys.get(4));
        label312.setText("Цена: " + prices.get(keys.get(4)));
        label321.setText(keys.get(5));
        label322.setText("Цена: " + prices.get(keys.get(5)));
        label411.setText(keys.get(6));
        label412.setText("Цена: " + prices.get(keys.get(6)));
        label421.setText(keys.get(7));
        label422.setText("Цена: " + prices.get(keys.get(7)));
        label511.setText(keys.get(8));
        label512.setText("Цена: " + prices.get(keys.get(8)));
        label521.setText(keys.get(9));
        label522.setText("Цена: " + prices.get(keys.get(9)));
        label611.setText(keys.get(10));
        label612.setText("Цена: " + prices.get(keys.get(10)));
        label621.setText(keys.get(11));
        label622.setText("Цена: " + prices.get(keys.get(11)));
        label111.setStyle("-fx-background-color: WHITE");
        label112.setStyle("-fx-background-color: WHITE");
        label121.setStyle("-fx-background-color: WHITE");
        label122.setStyle("-fx-background-color: WHITE");
        label211.setStyle("-fx-background-color: WHITE");
        label212.setStyle("-fx-background-color: WHITE");
        label221.setStyle("-fx-background-color: WHITE");
        label222.setStyle("-fx-background-color: WHITE");
        label311.setStyle("-fx-background-color: WHITE");
        label312.setStyle("-fx-background-color: WHITE");
        label321.setStyle("-fx-background-color: WHITE");
        label322.setStyle("-fx-background-color: WHITE");
        label411.setStyle("-fx-background-color: WHITE");
        label412.setStyle("-fx-background-color: WHITE");
        label421.setStyle("-fx-background-color: WHITE");
        label422.setStyle("-fx-background-color: WHITE");
        label511.setStyle("-fx-background-color: WHITE");
        label512.setStyle("-fx-background-color: WHITE");
        label521.setStyle("-fx-background-color: WHITE");
        label522.setStyle("-fx-background-color: WHITE");
        label611.setStyle("-fx-background-color: WHITE");
        label612.setStyle("-fx-background-color: WHITE");
        label621.setStyle("-fx-background-color: WHITE");
        label622.setStyle("-fx-background-color: WHITE");
    }
}
