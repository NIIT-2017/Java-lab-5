package sample;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.net.URL;

public class MyClockDemo extends Application{
    final String NAME   = "My Green Clock";
    final double CLOCK_RADIUS = 200;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(final Stage stage) throws Exception {
        // backdrop-фон сцены
        final Pane backdrop = makeBackdrop("backdrop", stage);//фон сцены

        // располагаем сцену
        final Scene scene = createClockScene(
                StackPaneBuilder.create()
                        .id("layout")
                        .children(
                                backdrop,
                                VBoxBuilder.create()
                                        .id("clocks")
                                        .pickOnBounds(false)
                                        .children(
                                                makeAnalogClock(stage)
                                        )
                                        .alignment(Pos.CENTER)
                                        .build()
                        )
                        .build()
        );

        sizeToScene(backdrop, scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        DoubleProperty opacity=stage.opacityProperty();
        Timeline fadeIn=new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(opacity,0.0)),
                new KeyFrame(new Duration(4000), new KeyValue(opacity, 1.0))
                );
                fadeIn.play();


    }

    private Node makeAnalogClock(Stage stage) {
        final MyClock myClock = new MyClock(NAME, CLOCK_RADIUS);
        Utilities.addGlowOnHover(myClock);
        Utilities.fadeOnClick(myClock, closeStageEventHandler(stage));
        return myClock;
    }

    private void sizeToScene(Pane pane, Scene scene) {
        pane.prefWidthProperty().bind(scene.widthProperty());
        pane.prefHeightProperty().bind(scene.heightProperty());
    }

    private Scene createClockScene(StackPane layout) throws MalformedURLException {
        final Scene scene = new Scene(layout, Color.TRANSPARENT);
        ResourseLoader resourseLoader=new ResourseLoader();
        scene.getStylesheets().add(
                      resourseLoader.getResourceFor(
                              MyClockDemo.class, "myClock.css"
                      ));
        return scene;
    }

    private EventHandler<ActionEvent> closeStageEventHandler(final Stage stage) {
        return new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        };
    }

    private Pane makeBackdrop(String id, Stage stage) {
        Pane backdrop = new Pane();
        backdrop.setId(id);
        Utilities.makeDraggable(stage, backdrop);
        return backdrop;
    }
}
