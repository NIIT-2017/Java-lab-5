package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Button btnOn;
    @FXML
    private Button btnOff;

    @FXML
    private Button btnAccept;
    @FXML
    private Button btnGetStage;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnGetCash;
    @FXML
    private Button btnChoice;

    @FXML
    private ImageView ivYourCoffee;

    @FXML
    private TextField tfFirst;
    @FXML
    private TextField tfAccept;
    @FXML
    private TextField tfSurrender;

    @FXML
    private ComboBox<String> cbGetMenu;

    private Automat automate;
    private ArrayList<String> forMenu;
    Image image;
    String[] coffee = new String[]{"Expresso.jpg", "Americano.png","Cappuccino.jpg","Mokachino.jpg","Dalgona.jpg"};
    Media media;
    MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        automate = new Automat();
        image=null;
        ivYourCoffee.setImage(null);
        tfFirst.setText("Click 'on' to start!");
    }

    @FXML
    private void onAction(){
        if(automate.on()== Automat.STAGES.WAIT){
            tfFirst.setText("Ready for work!");
            if(cbGetMenu.getItems().size()==0) {
                forMenu = automate.getMenu();
                for (String st : forMenu)
                    cbGetMenu.getItems().add(st);
            }
        }
    }

    @FXML
    private void getStageAction(){
        tfSurrender.setText("");
        ivYourCoffee.setImage(null);
        tfFirst.setText("This stage is "+automate.getStage()+".");
    }

    @FXML
    private void acceptAction() {
        tfSurrender.setText("");
        ivYourCoffee.setImage(null);
        if(automate.getStage()==Automat.STAGES.WAIT || automate.getStage()==Automat.STAGES.ACCEPT) {
            if (tfAccept.getText().equals("") || !(tfAccept.getText().matches("[-+]?\\d+"))) {
                tfFirst.setText("Deposit coins! Your cash = " + automate.getCash() + " rub");
                tfAccept.setText("");
            } else {
                automate.coin(Integer.parseInt(tfAccept.getText()));
                tfFirst.setText("Your cash = " + automate.getCash() + " rub");
                tfAccept.setText("");
                URL coins = Controller.class.getResource("/coins.mp3");
                media = new Media(coins.toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            }
        }
    }

    @FXML
    private void getCashAction(){
        tfSurrender.setText("");
        ivYourCoffee.setImage(null);
        tfFirst.setText("Your cash = " + automate.getCash() + " rub");
    }

    @FXML
    private void choiceAction() {

        tfSurrender.setText("");
        ivYourCoffee.setImage(null);
        if(automate.getStage()==Automat.STAGES.ACCEPT || automate.getStage()==Automat.STAGES.WAIT) {
            int cashBefore = automate.getCash();
            int i=0;
            String choice = cbGetMenu.getValue();
            for(String st : forMenu){
                if(st.equals(choice))
                    break;
                i++;
            }

            if(automate.choice(i)==true){

                URL toPourCoffee = Controller.class.getResource("/to pour coffee.mp3");
                media = new Media(toPourCoffee.toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();

                image=new Image(coffee[i]);
                ivYourCoffee.setImage(image);
                ivYourCoffee.setFitHeight(150);
                ivYourCoffee.setFitWidth(175);

                if((cashBefore-automate.prices[i])>0) {
                    tfFirst.setText("Done! Take your coffee and a surrender!");
                    tfSurrender.setText(((Integer) (cashBefore - automate.prices[i])).toString());
                }
                else
                    tfFirst.setText("Done!!!");
            }
            else //if automate.choice(i)==false
                tfFirst.setText("Your cash < price of the coffee!");
        }
        else
            tfFirst.setText("Invalid command for Choice!");
    }

    @FXML
    private void cancelAction(){
        ivYourCoffee.setImage(null);
        if(automate.getCash()!=0)
            tfFirst.setText("Take the money!");
        else
            tfFirst.setText("");
        tfSurrender.setText(((Integer)automate.getCash()).toString());
        automate.cancel(false);
    }

    @FXML
    private void offAction(){
        if(automate.off()== Automat.STAGES.OFF){
            tfFirst.setText("Click 'on' to start!");
            cbGetMenu.getItems().clear();
            tfSurrender.setText("");
            ivYourCoffee.setImage(null);
        }
        else
            tfFirst.setText("Invalid command for Off!");
    }
}