package sample;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;


public class Controller implements Initializable {

    Calendar calendar = GregorianCalendar.getInstance();
    double seedSecondDegrees;
    double seedMinuteDegrees;
    double seedHourDegrees;

    Rotate secondRotate;
    Rotate minuteRotate;
    Rotate hourRotate;

    Timeline secondTime;
    Timeline minuteTime;
    Timeline hourTime;

    Thread thread;
    Text[] numbers;

    @FXML
    Line secondHand   = new Line();
    @FXML
    Line minuteHand   = new Line();
    @FXML
    Line hourHand   = new Line();
    @FXML
    Circle spot;
    @FXML
    Text num1;
    @FXML
    Text num2;
    @FXML
    Text num3;
    @FXML
    Text num4;
    @FXML
    Text num5;
    @FXML
    Text num6;
    @FXML
    Text num7;
    @FXML
    Text num8;
    @FXML
    Text num9;
    @FXML
    Text num10;
    @FXML
    Text num11;
    @FXML
    Text num12;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        secondHand.setStroke(Color.RED);
        secondHand.setStrokeWidth(2);
        minuteHand.setStroke(Color.RED);
        minuteHand.setStrokeWidth(7);
        hourHand.setStroke(Color.RED);
        hourHand.setStrokeWidth(12);
        seedSecondDegrees  = calendar.get(Calendar.SECOND) * (360 / 60);
        seedMinuteDegrees  = (calendar.get(Calendar.MINUTE) + seedSecondDegrees / 360.0) * (360 / 60);
        seedHourDegrees    = (calendar.get(Calendar.HOUR)   + seedMinuteDegrees / 360.0) * (360 / 12);

        secondRotate = new Rotate(seedSecondDegrees);
        minuteRotate = new Rotate(seedMinuteDegrees);
        hourRotate = new Rotate(seedHourDegrees);

        secondHand.getTransforms().add(secondRotate);
        minuteHand.getTransforms().add(minuteRotate);
        hourHand.getTransforms().add(hourRotate);

        numbers = new Text[] {num12, num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11};

        secondTime = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate.angleProperty(),
                                360 + seedSecondDegrees,
                                Interpolator.LINEAR
                        )
                )
        );
        minuteTime = new Timeline(
                new KeyFrame(
                        Duration.minutes(60),
                        new KeyValue(
                                minuteRotate.angleProperty(),
                                360 + seedMinuteDegrees,
                                Interpolator.LINEAR
                        )
                )
        );
        hourTime = new Timeline(
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

        thread = new Thread(() -> {
            int sec = (GregorianCalendar.getInstance().get(Calendar.SECOND) / 5 + 1) * 5;
            if (sec == 60)
                sec = 0;
            while (true){
                if (GregorianCalendar.getInstance().get(Calendar.SECOND) == sec) {
                    rotateNumber(sec / 5);
                    if (sec >= 55) {
                        sec = 0;
                    } else {
                        sec += 5;
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    public void rotateNumber(int index) {
        RotateTransition rt = new RotateTransition(Duration.millis(1000), numbers[index]);
        rt.setByAngle(360f);
        rt.play();
    }
}
