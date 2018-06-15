package watch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Watch extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        initMain(primaryStage);
    }

    private void initMain(Stage firstStage){
        AnchorPane root = new AnchorPane();
        //create clock face
        ClockFace clockFace = new ClockFace(300, 300);
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
        firstStage.setTitle("Watch");
        firstStage.setScene(new Scene(root, 400, 400));
        firstStage.setX(400.0);
        firstStage.show();

        ClockFace2 clockFace2 = new ClockFace2(500, 500);
        AnchorPane root2 = new AnchorPane();
        root2.getChildren().add(clockFace2);

        Stage secondstage = new Stage();
        secondstage.setTitle("Watch");
        secondstage.setScene(new Scene(root2,400,400));
        secondstage.setX(0.0);

        secondstage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

