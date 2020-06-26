package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root, 800, 600, Color.TRANSPARENT);
        scene.getStylesheets().add(getClass().getResource("radBut.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setMaxHeight(600);
        primaryStage.setMinHeight(600);
        primaryStage.setMaxWidth(800);
        primaryStage.setMinWidth(800);
        primaryStage.setAlwaysOnTop(true);
        com.sun.glass.ui.Window.getWindows().get(0).setUndecoratedMoveRectangle(10);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
