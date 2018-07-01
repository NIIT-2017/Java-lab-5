package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Controller
{
    @FXML
    private Button btnPower;

    @FXML
    private Button btnR10;
    @FXML
    private Button btnR5;
    @FXML
    private Button btnR2;
    @FXML
    private Button btnR1;
    @FXML
    private Button btnK50;
    @FXML
    private Button btnK10;
    @FXML
    private Button btnK5;
    @FXML
    private Button btnK1;

    @FXML
    private Button btnDrink0;
    @FXML
    private Button btnDrink1;
    @FXML
    private Button btnDrink2;
    @FXML
    private Button btnDrink3;

    @FXML
    private Button btnChange;

    private ArrayList<Button> btnCoins;

    private ArrayList<Button> btnDrinks;

    private ArrayList<Button> buttons;

    @FXML
    private TextField tfStat;
    @FXML
    private TextField tfCash;

    @FXML
    private TextField tfDTitle0;
    @FXML
    private TextField tfDTitle1;
    @FXML
    private TextField tfDTitle2;
    @FXML
    private TextField tfDTitle3;

    @FXML
    private TextField tfDPrice0;
    @FXML
    private TextField tfDPrice1;
    @FXML
    private TextField tfDPrice2;
    @FXML
    private TextField tfDPrice3;

    private ArrayList<TextField> tfDTitles;

    private ArrayList<TextField> tfDPrices;

    private ArrayList<TextField> textFields;

    @FXML
    public void initialize() {
        Menu.load();
        Logger.init();
        tfDTitles = new ArrayList<TextField>
                (Arrays.asList(
                        tfDTitle0,
                        tfDTitle1,
                        tfDTitle2,
                        tfDTitle3)
                );
        tfDPrices = new ArrayList<TextField>
                (Arrays.asList(
                        tfDPrice0,
                        tfDPrice1,
                        tfDPrice2,
                        tfDPrice3)
                );
        textFields = new ArrayList<TextField>
                (Arrays.asList(
                        tfStat,
                        tfCash)
                );
        textFields.addAll(tfDTitles);
        textFields.addAll(tfDPrices);
        textFields.forEach(textField ->
                textField.setStyle(
                        "-fx-background-color: black;" +
                        "-fx-text-fill: white;"
                ));
        btnCoins = new ArrayList<Button>
                (Arrays.asList(
                        btnR10,
                        btnR5,
                        btnR2,
                        btnR1,
                        btnK50,
                        btnK10,
                        btnK5,
                        btnK1)
                );
        btnDrinks = new ArrayList<Button>
                (Arrays.asList(
                        btnDrink0,
                        btnDrink1,
                        btnDrink2,
                        btnDrink3)
                );
        buttons = new ArrayList<Button>
                (Arrays.asList(
                        btnPower,
                        btnChange)
                );
        buttons.addAll(btnCoins);
        buttons.addAll(btnDrinks);
        init();
    }

    public void init() {
        AStream.init();
        startStat(false);
        disableOnState("OFF");
        btnPower.setText("Включить");
    }

    @FXML
    public void onClick_btnPower() {
        if (!startStat) {
            startStat(true);
            pThread = new PanelThread();
            pThread.start();
            while(!pThread.isTWorkStat()) {
                delay(1);
            }
            automata = new Automata();
            automata.start();
            delay(1);
            btnPower.setText("Выключить");
        }
        else {
            sendForAutoInput("EXIT", AStream.CType.STR, "EXIT");
            while(pThread.isTWorkStat()) {
                delay(1);
            }
            threadStop(pThread);
            threadStop(automata);
            init();
        }
    }

    private void threadStop(Thread thread) {
        thread.interrupt();
        try {
            thread.join();
        } catch(InterruptedException ie) {
        }
        thread = null;
    }

    private void delay(int q) {
        try {
            Thread.sleep(q);
        } catch (InterruptedException e) {
        }
    }

    @FXML
    public void onClick_btnR10() {
        onClick_btnCoin("10.00");
    }
    @FXML
    public void onClick_btnR5() {
        onClick_btnCoin("5.00");
    }
    @FXML
    public void onClick_btnR2() {
        onClick_btnCoin("2.00");
    }
    @FXML
    public void onClick_btnR1() {
        onClick_btnCoin("1.00");
    }
    @FXML
    public void onClick_btnK50() {
        onClick_btnCoin("0.50");
    }
    @FXML
    public void onClick_btnK10() {
        onClick_btnCoin("0.10");
    }
    @FXML
    public void onClick_btnK5() {
        onClick_btnCoin("0.05");
    }
    @FXML
    public void onClick_btnK1() {
        onClick_btnCoin("0.01");
    }

    private void onClick_btnCoin(String strVal) {
        sendForAutoInput("COIN", AStream.CType.DOUB, strVal);
    }

    @FXML
    public void onClick_btnDrink0() {
        onClick_btnDrinkNum("0");
    }
    @FXML
    public void onClick_btnDrink1() {
        onClick_btnDrinkNum("1");
    }
    @FXML
    public void onClick_btnDrink2() {
        onClick_btnDrinkNum("2");
    }
    @FXML
    public void onClick_btnDrink3() {
        onClick_btnDrinkNum("3");
    }

    private void onClick_btnDrinkNum(String strNum) {
        sendForAutoInput("DRINK", AStream.CType.INT, strNum);
    }

    @FXML
    public void onClick_btnChange() {
        sendForAutoInput("CHANGE", AStream.CType.STR, "CHANGE");
    }

    private void sendForAutoInput(String name, AStream.CType type, String value) {
        AStream.sendForAutoInput(new AStream.CParam(name, type, value));
    }
    private void disableAll() {
        buttons.forEach(button -> setDisable(button, true));
    }
    private void disablePower() {
        setDisablePower(true);
    }
    private void disableCoins() {
        setDisableCoins(true);
    }
    private void disableDrinks() {
        setDisableDrinks(true);
    }
    private void disableChange() {
        setDisableChange(true);
    }
    private void enablePower() {
        setDisablePower(false);
    }
    private void enableCoins() {
        setDisableCoins(false);
    }
    private void enableDrinks() {
        setDisableDrinks(false);
    }
    private void enableChange() {
        setDisableChange(false);
    }
    private void setDisablePower(boolean b) {
        setDisable(btnPower, b);
    }
    private void setDisableCoins(boolean b) {
        btnCoins.forEach(btnCoin -> setDisable(btnCoin, b));
    }
    private void setDisableDrinks(boolean b) {
        btnDrinks.forEach(btnDrink -> setDisable(btnDrink, b));
    }
    private void setDisableChange(boolean b) {
        setDisable(btnChange, b);
    }
    private void setDisable(Button button, boolean b) {
        button.setDisable(b);
    }
    private void setTextTFStatCash(String stat, String cash) {
        setTextTFStat(stat);
        setTextTFCash(cash);
    }
    private void setTextTFStat(String stat) {
        setTextTF(tfStat, stat);
    }
    private void setTextTFCash(String cash) {
        setTextTF(tfCash, cash);
    }
    private void setTextTF(TextField textField, String text) {
        textField.setText(text);
    }
    private String doubStrForm(double val) {
        return String.format("%(.2f", val);
    }
    private void startStat(boolean startStat) {
        this.startStat = startStat;
    }
    private volatile boolean startStat;
    private PanelThread pThread;
    private Automata automata;
    private void disableOnState(String stat) {
        Automata.State state = Automata.State.valueOf(stat);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                switch(state) {
                    case OFF:
                        enablePower();
                        disableCoins();
                        disableDrinks();
                        disableChange();
                        break;
                    case WAIT:
                        enablePower();
                        enableCoins();
                        disableDrinks();
                        disableChange();
                        break;
                    case ACCEPT:
                        disablePower();
                        enableCoins();
                        enableDrinks();
                        enableChange();
                        break;
                    case CHECK:
                        disableAll();
                        break;
                    case COOK:
                        disableAll();
                        break;
                }
            }
        });
    }
    private void setTextStatCash(AStream.CParam cParam) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String[] mesArr = cParam.value.split("><");
                String stat = mesArr[0];
                String cash = doubStrForm(Double.parseDouble(mesArr[1])) + " руб.";
                setTextTFStatCash(stat, cash);
                if (cParam.type != AStream.CType.TMP) {
                    toLogger(stat, cash);
                }
            }
        });
    }
    private DateTimeFormatter dateTimeForm = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    private void toLogger(String stat, String cash) {
        String message = "'" + LocalDateTime.now().format(dateTimeForm) + "' '" + stat + "' '" + cash + "'\n";
        Logger.show(message);
    }
    private void setTextRunLater(boolean filled) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (filled) {
                    setTextTFStatCash("Поехали!", "0.00");
                    for(int i=0; i < Menu.getSize(); i++) {
                        setTextTF(tfDTitles.get(i), Menu.getTitle(i));
                        setTextTF(tfDPrices.get(i), doubStrForm(Menu.getPrice(i)));
                    }
                }
                else {
                    textFields.forEach(textField -> setTextTF(textField, ""));
                }
            }
        });
    }
    private class PanelThread extends Thread
    {
        private AStream.CParam cParam;
        public void run() {
            setTWorkStat(true);
            setTextRunLater(true);
            while(!Main.isExitStat()) {
                if ((cParam = AStream.getFromAutoOutput()) != null) {
                    if (cParam.name.equals("ONSTATE")) {
                        disableOnState(cParam.value);
                    }
                    if (cParam.name.equals("MESSAGE")) {
                        setTextStatCash(cParam);
                    }
                    if (cParam.value.contains("Автомат выключен.")) {
                        break;
                    }
                }
            }
            setTextRunLater(false);
            setTWorkStat(false);
        }
        private boolean tWorkStat = false;
        private void setTWorkStat(boolean tWorkStat) {
            this.tWorkStat = tWorkStat;
        }
        public boolean isTWorkStat() {
            return tWorkStat;
        }
    }
}
