package analogclock;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Launcher extends Application {
    private LauncherController launcherController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("launcher.fxml"));
        Parent root = loader.load();
        launcherController = loader.getController();
        primaryStage.setTitle("AnalogClock");
        primaryStage.setMinHeight(380);
        primaryStage.setMinWidth(380);
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("root.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        initClocks();
        primaryStage.show();
    }

    public void initClocks() {
        launcherController.registerClock(new FXMLLoader(getClass().getClassLoader().getResource("analog.fxml")), "Analog Clock").showClock();
    }

    public static void main(String[] args) {
        Launcher.launch();
    }
}
