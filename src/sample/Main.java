package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
    public static void main(String[] args) {
        launch(args);
        AStream.exit();
        setExitStat(true);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Automata");
        primaryStage.setScene(new Scene(root, 700, 630));
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(630);
        primaryStage.setMaxWidth(700);
        primaryStage.setMaxHeight(630);
        primaryStage.show();
    }
    public static boolean isExitStat() {
        return exitStat;
    }
    private static void setExitStat(boolean exitStat) {
        Main.exitStat = exitStat;
    }
    private static volatile boolean exitStat = false;
}
