package watch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Watch extends Application {

    private AnchorPane root;
    private ClockFace clockFace;

    @Override
    public void start(Stage primaryStage) throws Exception{
        initMain(primaryStage);
    }

    private void initMain(Stage primaryStage){
        root = new AnchorPane();
        //create clock face
        clockFace = new ClockFace(300, 300);

        //root property
        root.getChildren().add(clockFace);
        root.widthProperty().addListener(observable -> {
            clockFace.setWidth(root.getWidth());
            clockFace.repaint();
        });
        root.heightProperty().addListener(observable -> {
            clockFace.setHeight(root.getHeight());
            clockFace.repaint();
        });

        //setup primary stage
        primaryStage.setTitle("Watch");
        primaryStage.setScene(new Scene(root, 300, 300));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

