import javafx.animation.*;
import javafx.application.Application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.net.URL;



public class Clock extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Clock");
        URL background = getClass().getResource("Clock.jpg");
        Image im = new Image(background.toString(), 600, 600, false, false);
        ImageView ivBack = new ImageView(im);

        Calendar myCalendar = new GregorianCalendar();
        double secDegree = myCalendar.get(Calendar.SECOND) * (360 / 60);
        double minDegree = (myCalendar.get(Calendar.MINUTE) + secDegree / 360.0) * (360 / 60);
        double hourDegree = (myCalendar.get(Calendar.HOUR) + minDegree / 360) * (360 / 12);

        Line hour, min, sec;
        hour = new Line(0, 0, 0, -150);
        hour.setStroke(Color.BLACK);
        hour.setStrokeWidth(10);
        hour.setTranslateX(300);
        hour.setTranslateY(330);
        Rotate rotateHour = new Rotate();
        rotateHour.setAngle(hourDegree);
        rotateHour.setPivotX(0);
        rotateHour.setPivotY(-30);
        hour.getTransforms().addAll(rotateHour);

        min = new Line(0, 0, -0, -180);
        min.setStroke(Color.BLACK);
        min.setStrokeWidth(5);
        min.setTranslateX(300);
        min.setTranslateY(330);
        Rotate rotateMin = new Rotate();
        rotateMin.setAngle(minDegree);
        rotateMin.setPivotX(0);
        rotateMin.setPivotY(-30);
        min.getTransforms().addAll(rotateMin);

        sec = new Line(0, 0, -0, -210);
        sec.setStroke(Color.BLACK);
        sec.setStrokeWidth(3);
        sec.setTranslateX(300);
        sec.setTranslateY(330);
        Rotate rotateSec = new Rotate();
        rotateSec.setAngle(secDegree);
        rotateSec.setPivotX(0);
        rotateSec.setPivotY(-30);
        sec.getTransforms().addAll(rotateSec);

        Circle circle = new Circle();
        circle.setCenterX(300);
        circle.setCenterY(300);
        circle.setRadius(10);

        final Timeline secTimeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                rotateSec.angleProperty(),
                                360 + secDegree,
                                Interpolator.LINEAR
                        )
                )
        );

        final Timeline minTimeline = new Timeline(
                new KeyFrame(
                        Duration.minutes(60),
                        new KeyValue(
                                rotateMin.angleProperty(),
                                360 + minDegree,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline hourTimeline = new Timeline(
                new KeyFrame(
                        Duration.hours(12),
                        new KeyValue(
                                rotateHour.angleProperty(),
                                360 + hourDegree,
                                Interpolator.LINEAR
                        )
                )
        );
        secTimeline.setCycleCount(Animation.INDEFINITE);
        minTimeline.setCycleCount(Animation.INDEFINITE);
        hourTimeline.setCycleCount(Animation.INDEFINITE);

        secTimeline.play();
        minTimeline.play();
        hourTimeline.play();


        Group root = new Group(ivBack, sec, min, hour, circle);
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();


    }
}