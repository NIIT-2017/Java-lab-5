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

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Clock extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Clock");
        URL background = getClass().getResource("/image.png");
        Image cl = new Image(background.toString(), 600, 600, false, false);
        ImageView imageView = new ImageView(cl);
        Calendar calendar = GregorianCalendar.getInstance();
        Circle face = new Circle(300,300,10);
        Circle face2 = new Circle(300,300,280);
        face.setFill(Color.WHITE);
        face2.setFill(null);
        face2.setStroke(Color.WHITE);
        face2.setStrokeWidth(6);

        Line secondArrow = new Line(0,60,0,-290);
        secondArrow.setTranslateX(300);
        secondArrow.setTranslateY(300);
        secondArrow.setStroke(Color.WHITE);
        secondArrow.setStrokeWidth(2);
        Line minuteArrow = new Line(0,60,0,-250);
        minuteArrow.setTranslateX(300);
        minuteArrow.setTranslateY(300);
        minuteArrow.setStroke(Color.WHITE);
        minuteArrow.setStrokeWidth(6);
        Line hourArrow = new Line(0,60,0,-150);
        hourArrow.setTranslateX(300);
        hourArrow.setTranslateY(300);
        hourArrow.setStroke(Color.WHITE);
        hourArrow.setStrokeWidth(8);

        double secDegree = calendar.get(Calendar.SECOND) * (360 / 60);
        double minDegree = (calendar.get(Calendar.MINUTE) + secDegree / 360.0) * (360 / 60);
        double hourDegree = (calendar.get(Calendar.HOUR) + minDegree / 360) * (360 / 12);

        Rotate rotateSec = new Rotate(secDegree);
        Rotate rotateMin = new Rotate(minDegree);
        Rotate rotateHour = new Rotate(hourDegree);

        secondArrow.getTransforms().add(rotateSec);
        minuteArrow.getTransforms().add(rotateMin);
        hourArrow.getTransforms().add(rotateHour);

        final Timeline secTime = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                rotateSec.angleProperty(),
                                360 + secDegree,
                                Interpolator.LINEAR
                        )
                )
        );

        final Timeline minTime = new Timeline(
                new KeyFrame(
                        Duration.minutes(60),
                        new KeyValue(
                                rotateMin.angleProperty(),
                                360 + minDegree,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline hourTime = new Timeline(
                new KeyFrame(
                        Duration.hours(12),
                        new KeyValue(
                                rotateHour.angleProperty(),
                                360 + hourDegree,
                                Interpolator.LINEAR
                        )
                )
        );
        secTime.setCycleCount(Animation.INDEFINITE);
        minTime.setCycleCount(Animation.INDEFINITE);
        hourTime.setCycleCount(Animation.INDEFINITE);

        secTime.play();
        minTime.play();
        hourTime.play();

        Group root = new Group(imageView, face,face2,secondArrow,minuteArrow,hourArrow);
        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();

    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
