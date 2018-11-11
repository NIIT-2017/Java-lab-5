package sample;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Button btn_Power;

    @FXML
    private Button btn_PowerOff;

    @FXML
    private Button btn_Menu;

    @FXML
    private Button btn_Cancel;

    @FXML
    private Button btn_Add;

    @FXML
    private Button btn_Choice;

    @FXML
    private Button btn_Cook;


    @FXML
    private ProgressBar prb_Cook;

    @FXML
    private TextField txf_State;

    @FXML
    private Label lbl_Cash;

    @FXML
    private TextField txF_Money;

    @FXML
    private ListView lv_Menu;



    ObservableList<String> list = FXCollections.observableArrayList();
    private int p=0;
    public void initialize(URL location, ResourceBundle resources) {
           Automat a = new Automat();
btn_Choice.setDisable(true);
btn_Power.setDisable(false);
btn_Add.setDisable(true);
btn_PowerOff.setDisable(true);
btn_Cancel.setDisable(true);
btn_Cook.setDisable(true);
btn_Menu.setDisable(true);

         btn_Power.setOnAction(e -> {
            DemoAutomat.s = new String[]{"ON"};
            DemoAutomat.main(DemoAutomat.s);
           btn_Power.setDisable(true);
            btn_PowerOff.setDisable(false);
            btn_Add.setDisable(false);
            txF_Money.setText("0");
            runn();
        });

        btn_PowerOff.setOnAction(e -> {
            DemoAutomat.s = new String[]{"OFF"};
            DemoAutomat.main(DemoAutomat.s);
            btn_Choice.setDisable(true);
            btn_Power.setDisable(false);
            btn_Add.setDisable(true);
            btn_PowerOff.setDisable(true);
            btn_Cancel.setDisable(true);
            btn_Cook.setDisable(true);
            btn_Menu.setDisable(true);
            list.clear();
            txF_Money.clear();
runn();


        });

        btn_Choice.setOnAction(e -> {
            DemoAutomat.s = new String[]{"CHOICE"};
            int k = lv_Menu.getSelectionModel().getSelectedIndex();
            a.setIndex(k);
            DemoAutomat.main(DemoAutomat.s);
            btn_Cook.setDisable(false);
            runn();

        });

       btn_Cook.setOnAction(e -> {
                if (a.getEnCook()==0){
                a.printState("Внесите деньги или повторите выбор");
                runn();
                return;
                }
            else {
                DemoAutomat.s = new String[]{"COOK"};
                p = 0;
                DemoAutomat.main(DemoAutomat.s);
               DownloadTask downloadTask = new DownloadTask();
                prb_Cook.progressProperty().bind(downloadTask.progressProperty());
                txf_State.textProperty().bind(downloadTask.titleProperty());
                Thread th = new Thread(downloadTask);
                th.start();
                list.clear();
                btn_Cook.setDisable(true);
                btn_Cancel.setDisable(true);
                btn_Menu.setDisable(true);
                btn_Choice.setDisable(true);
                txF_Money.setText("0");
            }
        });




        btn_Cancel.setOnAction(e -> {
            DemoAutomat.s = new String[]{"CANCEL"};
            DemoAutomat.main(DemoAutomat.s);
            list.clear();
            btn_Cancel.setDisable(true);
            btn_Menu.setDisable(true);
            btn_Choice.setDisable(true);
            btn_Cook.setDisable(true);
             txF_Money.clear();
            txF_Money.setText("0");
             runn();

        });

        btn_Add.setOnAction(e -> {

            if (txF_Money.getText().isEmpty()|| checkString(txF_Money.getText())==false|| Double.parseDouble(txF_Money.getText())% 1 != 0 || Integer.parseInt(txF_Money.getText())==0){
                a.printState("Внесите деньги");
                runn();
                return;
            }
            else {

               DemoAutomat.s = new String[]{txF_Money.getText()};
               DemoAutomat.main(DemoAutomat.s);
               btn_Cancel.setDisable(false);
               btn_Menu.setDisable(false);
               runn();
            }
        });

        btn_Menu.setOnAction(e -> {
            list.clear();
            lv_Menu.setItems(list);
            DemoAutomat.s = new String[]{"PRINTMENU"};
            DemoAutomat.main(DemoAutomat.s);

            for (String k : a.getResult()) {
                int i = a.getResult().size();
                if (i > 1) {
                    k = a.getResult().pop();
                    list.add(k);
                }
                lv_Menu.setItems(list);
                lv_Menu.getSelectionModel().selectFirst();
            }
                    btn_Choice.setDisable(false);
                    runn();



        }

        );

    }



  public void runn(){
       Taskk taskk = new Taskk();
        Thread th1 = new Thread(taskk);
        txf_State.textProperty().bind(taskk.titleProperty());
        th1.start();
}

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

 }

class DownloadTask extends Task<Void> {
int count=0;
    @Override
    protected Void call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            updateTitle("Приготовление напитка ");
count++;
            updateProgress(i, 100);

            Thread.sleep(100);
        }

        updateProgress(0, 100);

        Automat a1 = new Automat();
        for (String k : a1.getResult()) {
            k = a1.getResult().pop();
            updateTitle(String.valueOf(k));
            Thread.sleep(1500);
        }

        return null;
    }
}
    class Taskk extends Task<Void>  {

        @Override
        protected Void call() throws Exception {
            Automat a1 = new Automat();
            for (String k : a1.getResult()) {
                k = a1.getResult().pop();
                updateTitle(String.valueOf(k));
                Thread.sleep(1500);
            }
                  return null;
        }
}




