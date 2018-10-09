package gui;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import javax.script.Bindings;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private StackPane pane;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private Line secArrow;

    @FXML
    private Line minArrow;

    @FXML
    private Line hourArrow;

    LocalTime lt = LocalTime.now();
    long minute=lt.getMinute();
    long sec = lt.getSecond();
    long hour = lt.getHour();

    double sec_angle = sec*(360/60);
    double min_angle = (minute+sec_angle/360)*(360/60);
    double hour_angle = (hour+min_angle/360)*(360/12);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rotate rotation = new Rotate();
        rotation.setPivotX(250);
        rotation.setPivotY(250);
        backgroundImage.getTransforms().add(rotation);
        Timeline tl = new Timeline(
                new KeyFrame(Duration.seconds(180),
                        new KeyValue(rotation.angleProperty(),-360, Interpolator.LINEAR)));
        tl.setCycleCount(Animation.INDEFINITE);
        tl.play();

        Rotate sec_rotation = new Rotate(sec_angle);
        sec_rotation.setPivotY(0);
        sec_rotation.setPivotY(0);
        secArrow.getTransforms().add(sec_rotation);
        final Timeline secTime = new Timeline(
                new KeyFrame(Duration.seconds(60),
                        new KeyValue(sec_rotation.angleProperty(),360+sec_angle, Interpolator.LINEAR)));
        secTime.setCycleCount(Animation.INDEFINITE);
        secTime.setCycleCount(Animation.INDEFINITE);
        secTime.play();

        Rotate min_rotation = new Rotate(min_angle);
        min_rotation.setPivotY(0);
        min_rotation.setPivotY(0);
        minArrow.getTransforms().add(min_rotation);
        final Timeline minTime = new Timeline(
                new KeyFrame(Duration.minutes(60),
                        new KeyValue(min_rotation.angleProperty(),360+min_angle, Interpolator.LINEAR)));
        minTime.setCycleCount(Animation.INDEFINITE);
        minTime.setCycleCount(Animation.INDEFINITE);
        minTime.play();

        Rotate hour_rotation = new Rotate(hour_angle);
        hour_rotation.setPivotY(0);
        hour_rotation.setPivotY(0);
        hourArrow.getTransforms().add(hour_rotation);
        final Timeline hourTime = new Timeline(
                new KeyFrame(Duration.hours(12),
                        new KeyValue(hour_rotation.angleProperty(),360+hour_angle, Interpolator.LINEAR)));
        hourTime.setCycleCount(Animation.INDEFINITE);
        hourTime.setCycleCount(Animation.INDEFINITE);
        hourTime.play();

    }
}
