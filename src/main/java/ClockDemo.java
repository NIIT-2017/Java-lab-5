import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class ClockDemo extends Application {
    @Override
    public void start(Stage stage) {
        Image clockFace = new Image("file:clock.jpg", 500, 500, false, false);
        ImageView imageView = new ImageView(clockFace);
        Circle centrPoint = new Circle (250,250,6);
        Calendar calendar = GregorianCalendar.getInstance();
        Line second = new Line(0, 30, 0, -200);
        second.setTranslateX(250);
        second.setTranslateY(250);
        second.setStroke(Color.RED);
        second.setStrokeWidth(2);
        Line minute = new Line(0, 25, 0, -170);
        minute.setTranslateX(250);
        minute.setTranslateY(250);
        minute.setStroke(Color.BLACK);
        minute.setStrokeWidth(4);
        Line hour = new Line(0, 15, 0, -100);
        hour.setTranslateX(250);
        hour.setTranslateY(250);
        hour.setStroke(Color.BLACK);
        hour.setStrokeWidth(8);

        double secDeg = calendar.get(Calendar.SECOND) * 6;
        double minDeg = (calendar.get(Calendar.MINUTE) + secDeg / 360.0) * 6;
        double hourDeg = (calendar.get(Calendar.HOUR)   + minDeg / 360.0) * 30;

        Rotate secRotate = new Rotate(secDeg);
        Rotate minRotate = new Rotate(minDeg);
        Rotate hourRotate = new Rotate(hourDeg);
        second.getTransforms().add(secRotate);
        minute.getTransforms().add(minRotate);
        hour.getTransforms().add(hourRotate);

        final Timeline secondTime = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secRotate.angleProperty(),
                                360 + secDeg,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline minuteTime = new Timeline(
                new KeyFrame(
                        Duration.minutes(60),
                        new KeyValue(
                                minRotate.angleProperty(),
                                360 + minDeg,
                                Interpolator.LINEAR
                        )
                )
        );
        Timeline hourTime = new Timeline(
                new KeyFrame(
                        Duration.hours(12),
                        new KeyValue(
                                hourRotate.angleProperty(),
                                360 + hourDeg,
                                Interpolator.LINEAR
                        )
                )
        );
        secondTime.setCycleCount(Animation.INDEFINITE);
        minuteTime.setCycleCount(Animation.INDEFINITE);
        hourTime.setCycleCount(Animation.INDEFINITE);
        secondTime.play();
        minuteTime.play();
        hourTime.play();
        Group root = new Group(imageView, hour, minute, second, centrPoint);
        Scene scene = new Scene(root, 500,500);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]) {
        launch(args);
    }
}