package analogClock;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.util.Duration;

import java.net.*;

public class Clock extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Analog analogClock.Clock");
        Image image = new Image("WH2.jpg", 650, 650, false, false);
        ImageView backGround = new ImageView(image);

        Circle circle = new Circle();
        circle.setCenterX(333.9);
        circle.setCenterY(333.9);
        circle.setRadius(7.5);
        circle.setStrokeWidth(1.5);
        circle.setStroke(Color.DARKORCHID);

        //Set time
        Calendar calendar = new GregorianCalendar();
        double SecDegree = calendar.get(Calendar.SECOND) * (360 / 60);
        double MinDegree = (calendar.get(Calendar.MINUTE) + SecDegree / 360) * (360 / 60);
        double HrDegree = (calendar.get(Calendar.HOUR) + MinDegree / 360) * (360 / 12);

        //Create hour clockwise
        Line clockwiseHr = new Line(0, 0, 0, -157);
        clockwiseHr.setStroke(Color.DARKVIOLET);
        clockwiseHr.setStrokeWidth(4);
        clockwiseHr.smoothProperty();
        clockwiseHr.getStrokeLineJoin();
        clockwiseHr.setTranslateX(333.9);
        clockwiseHr.setTranslateY(333.9);

        //Create second clockwise
        Line clockwiseMin = new Line(0, 0, 0, -217);
        clockwiseMin.setStroke(Color.DARKORCHID);
        clockwiseMin.setStrokeWidth(3);
        clockwiseMin.smoothProperty();
        clockwiseMin.getStrokeLineJoin();
        clockwiseMin.setTranslateX(333.9);
        clockwiseMin.setTranslateY(333.9);

        //Create second clockwise
        Line clockwiseSec = new Line(0, 0, -0, -287);
        clockwiseSec.setStroke(Color.DARKORCHID);
        clockwiseSec.setStrokeWidth(2);
        clockwiseSec.smoothProperty();
        clockwiseSec.getStrokeLineJoin();
        clockwiseSec.setTranslateX(333.9);
        clockwiseSec.setTranslateY(333.9);

        //Create the order of rotation
        Rotate rotateHr = new Rotate();
        rotateHr.setAngle(HrDegree);
        rotateHr.setPivotX(0);
        rotateHr.setPivotY(0);
        clockwiseHr.getTransforms().addAll(rotateHr);

        Rotate rotateMin = new Rotate(MinDegree);
        rotateMin.setAngle(MinDegree);
        rotateMin.setPivotX(0);
        rotateMin.setPivotY(0);
        clockwiseMin.getTransforms().addAll(rotateMin);

        Rotate rotateSec = new Rotate(SecDegree);
        rotateSec.setAngle(SecDegree);
        rotateSec.setPivotX(0);
        rotateSec.setPivotY(0);
        clockwiseSec.getTransforms().addAll(rotateSec);

        Timeline timelineH = new Timeline(new KeyFrame(Duration.hours(12),
                new KeyValue(rotateHr.angleProperty(), 325 + HrDegree, Interpolator.LINEAR)));
        Timeline timelineM = new Timeline(new KeyFrame(Duration.minutes(60),
                new KeyValue(rotateSec.angleProperty(), 325 + MinDegree, Interpolator.LINEAR)));
        Timeline timelineS = new Timeline(new KeyFrame(Duration.seconds(60),
                new KeyValue(rotateSec.angleProperty(), 325 + SecDegree, Interpolator.LINEAR)));


        timelineH.setCycleCount(Animation.INDEFINITE);
        timelineM.setCycleCount(Animation.INDEFINITE);
        timelineS.setCycleCount(Animation.INDEFINITE);

        timelineH.play();
        timelineM.play(); 
        timelineS.play();

        Group root = new Group(backGround, clockwiseHr, clockwiseMin, clockwiseSec, circle);
        Scene scene = new Scene(root, 650, 650);
        stage.setScene(scene);
        stage.show();
    }

        public static void main(String[] args) {
            launch(args);
        }
    }
