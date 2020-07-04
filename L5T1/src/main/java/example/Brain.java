package example;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
public class Brain implements Initializable
{
    Image image = new Image("/Automata.jpg", 500, 400, false, false);
    ImageView imageView = new ImageView(image);
    @FXML
    private Button btnOn;
    @FXML
    private Button btnOff;
    @FXML
    private Button btnLeft1;
    @FXML
    private Button btnLeft2;
    @FXML
    private Button btnLeft3;
    @FXML
    private Button btnLeft4;
    @FXML
    private Button btnLeft5;
    @FXML
    private Button btnLeft6;
    @FXML
    private Button btnRight1;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblNameLeft1;
    @FXML
    private Label lblNameLeft2;
    @FXML
    private Label lblNameLeft3;
    @FXML
    private Label lblNameLeft4;
    @FXML
    private Label lblNameLeft5;
    @FXML
    private Label lblNameLeft6;
    @FXML
    private Label lblNameRight1;
    @FXML
    private Label lblPriceLeft1;
    @FXML
    private Label lblPriceLeft2;
    @FXML
    private Label lblPriceLeft3;
    @FXML
    private Label lblPriceLeft4;
    @FXML
    private Label lblPriceLeft5;
    @FXML
    private Label lblPriceLeft6;
    @FXML
    private Label lblPriceRight1;
    @FXML
    private Label lblChange;
    @FXML
    private Label lblMessage;
    @FXML
    private ComboBox<Integer> cbCash;
    @FXML
    private ProgressIndicator piProgress;
    @FXML
    private ImageView ivCup;
    List <String> menu;
    List <Integer> price;
    AutomataGUIDemo automata;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        automata = new AutomataGUIDemo();
        automata.setMenu();
        automata.setPrices();
        menu = automata.getMenu();
        price = automata.getPrices();
        setComboBox();
        setLblNames();
        setlblPrices();
        ivCup.setVisible(false);
    }
    public void setLblNames()
    {
        String nameLeft1 = menu.get(0);
        lblNameLeft1.setText(nameLeft1);
        String nameLeft2 = menu.get(1);
        lblNameLeft2.setText(nameLeft2);
        String nameLeft3 = menu.get(2);
        lblNameLeft3.setText(nameLeft3);
        String nameLeft4 = menu.get(3);
        lblNameLeft4.setText(nameLeft4);
        String nameLeft5 = menu.get(4);
        lblNameLeft5.setText(nameLeft5);
        String nameLeft6 = menu.get(5);
        lblNameLeft6.setText(nameLeft6);
    }
    public void setlblPrices()
    {
        String priceLeft1 = price.get(0).toString();
        lblPriceLeft1.setText(priceLeft1);
        String priceLeft2 = price.get(1).toString();
        lblPriceLeft2.setText(priceLeft2);
        String priceLeft3 = price.get(2).toString();
        lblPriceLeft3.setText(priceLeft3);
        String priceLeft4 = price.get(3).toString();
        lblPriceLeft4.setText(priceLeft4);
        String priceLeft5 = price.get(4).toString();
        lblPriceLeft5.setText(priceLeft5);
        String priceLeft6 = price.get(5).toString();
        lblPriceLeft6.setText(priceLeft6);
        String priceRight1 = price.get(6).toString();
        lblPriceRight1.setText(priceRight1);
    }
    public void switchOn()
    {
        automata.on();
        lblMessage.setText(automata.getMessage());
    }
    public void switchOff()
    {
        automata.off();
        lblMessage.setText(automata.getMessage());
        piProgress.setProgress(0);
    }
    public void setComboBox()
    {
        ObservableList<Integer> cashAvailable = FXCollections.observableArrayList(5,10,50,100);
        cbCash.setItems(cashAvailable);
    }
    public void getCash()
    {
        try
        {
            int cash = cbCash.getValue();
            automata.coin(cash);
            lblMessage.setText(automata.getMessage());
        }
        catch (java.lang.NullPointerException ignored){};
    }
    public void btnLeft1Pressed()
    {
        choiceAndCook(0);
    }
    public void btnLeft2Pressed()
    {
        choiceAndCook(1);
    }
    public void btnLeft3Pressed()
    {
        choiceAndCook(2);
    }
    public void btnLeft4Pressed()
    {
        choiceAndCook(3);
    }
    public void btnLeft5Pressed()
    {
        choiceAndCook(4);
    }
    public void btnLeft6Pressed()
    {
        choiceAndCook(5);
    }
    public void btnRight1Pressed()
    {
        choiceAndCook(6);
    }
    public void choiceAndCook (int number)
    {
        int price = automata.choice(number);
        lblMessage.setText(automata.getMessage());
        boolean check = automata.check(price);
        if(check)
        {
            int change = automata.getChange();
            try {
                cbCash.setValue(null);
            }
            catch (java.lang.NullPointerException ignored){};
            lblChange.setText(Integer.toString(change));
            new Thread(() ->
            {
                cook(number, piProgress, lblMessage, ivCup);
            }
            ).start();
        }
        else
            lblMessage.setText("Недостаточно средств!");
    }
    public void cook(int number, ProgressIndicator piProgress, Label lblMessage, ImageView ivCup){
        automata.cook();
        try
        {
            for(int i=0; i<=100; i++)
            {
                Thread.sleep(50);
                double progress=(double)i/100;
                Platform.runLater(() ->
                {
                    piProgress.setProgress(progress);
                    if (piProgress.getProgress()<1)
                        lblMessage.setText(menu.get(number) + " готовится");
                    else
                        {
                        lblMessage.setText(menu.get(number) + " готов!");
                        ivCup.setVisible(true);
                    }
                });
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
    public void cancel()
    {
        automata.cancel();
        int change =  automata.getChange();
        lblChange.setText(Integer.toString(change));
        try
        {
            cbCash.setValue(null);
        }
        catch (java.lang.NullPointerException ignored){};
        lblMessage.setText(automata.getMessage());
    }
}