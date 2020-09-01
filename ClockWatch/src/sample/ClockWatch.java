package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClockWatch extends Application{
    @Override
    public void start(Stage stage) throws Exception {

        Image image = new Image("/567.png", 580, 580, false, false);
        ImageView imageView = new ImageView(image);
        Calendar calendar = GregorianCalendar.getInstance();

        URL resource = ClockWatch.class.getResource("/341.mp3");
        Media media = new Media(resource.toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        Circle face = new Circle(295,295,8);
        face.setFill(Color.BLACK);

        Line secondHand = new Line(0,35,0,-190);
        secondHand.setTranslateX(295);
        secondHand.setTranslateY(295);
        secondHand.setStroke(Color.PURPLE);
        secondHand.setStrokeWidth(5);
        secondHand.setStrokeLineCap(StrokeLineCap.ROUND);

        Line minuteHand = new Line(0,0,0,-175);
        minuteHand.setTranslateX(295);
        minuteHand.setTranslateY(295);
        minuteHand.setStroke(Color.PURPLE);
        minuteHand.setStrokeWidth(8);
        minuteHand.setStrokeLineCap(StrokeLineCap.ROUND);

        Line hourHand = new Line(0,0,0,-150);
        hourHand.setTranslateX(295);
        hourHand.setTranslateY(295);
        hourHand.setStroke(Color.PURPLE);
        hourHand.setStrokeWidth(11);
        hourHand.setStrokeLineCap(StrokeLineCap.ROUND);

        double seedSecondDegrees = calendar.get(Calendar.SECOND)*(360/60);
        double seedMinuteDegrees = (calendar.get(Calendar.MINUTE)+seedSecondDegrees/360.0)*(360/60);
        double seedHourDegrees = (calendar.get(Calendar.HOUR)+seedMinuteDegrees/360.0)*(360/12);

        Rotate secondRotate = new Rotate(seedSecondDegrees);
        Rotate minuteRotate = new Rotate(seedMinuteDegrees);
        Rotate hourRotate = new Rotate(seedHourDegrees);

        secondHand.getTransforms().add(secondRotate);
        minuteHand.getTransforms().add(minuteRotate);
        hourHand.getTransforms().add(hourRotate);

        final Timeline secondTime = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate.angleProperty(),
                                360 + seedSecondDegrees,
                                Interpolator.LINEAR
                        )
                )
        );

        final Timeline minuteTime = new Timeline(
                new KeyFrame(
                        Duration.minutes(60),
                        new KeyValue(
                                minuteRotate.angleProperty(),
                                360 + seedMinuteDegrees,
                                Interpolator.LINEAR
                        )
                )
        );

        Timeline hourTime = new Timeline(
                new KeyFrame(
                        Duration.hours(12),
                        new KeyValue(
                                hourRotate.angleProperty(),
                                360 + seedHourDegrees,
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

        Group root = new Group(imageView, hourHand, minuteHand, secondHand, face);
        Scene scene = new Scene(root, 580,580);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}