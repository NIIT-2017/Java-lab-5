package Avtomat;

import Avtomat.controller.Automat;
import Avtomat.model.Drink;
import Avtomat.view.AvtomatGUIController;
import Avtomat.view.DrinkController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class Main extends Application {
    private Stage primaryStage;
    private Automat automat;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        InputStream io = Main.class.getResourceAsStream("data/Drinks.xml");
        //create automat and read list of drinks
        automat = new Automat(io);
        automat.start();
        //main scene
        initMainScene();
    }

    private void initMainScene() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource( "view/avtomatGUI.fxml"));
        try {
            //load main pane
            AnchorPane rootLayOut = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayOut);
            AvtomatGUIController controller = loader.getController();
            controller.setAutomat(automat);
            //get list of Drinks
            ArrayList<Drink> drinksList = automat.getDrinks();
            //paste frame of Drinks into main pane
            for (int i = 0; i < drinksList.size(); i++) {
                initDrinks(controller,drinksList.get(i),i%2,i/2);
            }
            primaryStage.setScene(scene);
            //apply css for main scene
            scene.getStylesheets().add("Avtomat/css/Avtomat.css");
            //set background for main pane
            Image background = new Image("Avtomat/img/background.jpg");
            rootLayOut.setBackground(new Background(new BackgroundImage(background,BackgroundRepeat.ROUND,
                    BackgroundRepeat.ROUND,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDrinks(AvtomatGUIController controller,Drink drink, int col,int row) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/Drinks.fxml"));
        try {
            //load frame for drink
            AnchorPane drinkPane = (AnchorPane) loader.load();
            DrinkController drinkController = loader.getController();
            drinkController.setupDrink(drink,automat);
            //set css for frame
            drinkPane.getStylesheets().add("Avtomat/css/Drinks.css");
            //add frame into grid pane
            controller.addRowsMenuGridPane(drinkPane,col,row);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
