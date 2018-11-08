package sample;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Image dialImage = new Image(getClass().getResourceAsStream("dial.png"));
    private Image hourHandImage = new Image(getClass().getResourceAsStream("hourHand.png"));
    private Image minuteHandImage = new Image(getClass().getResourceAsStream("minuteHand.png"));
    private Image secondHandImage = new Image(getClass().getResourceAsStream("secondHand.png"));
    private Image centreImage = new Image(getClass().getResourceAsStream("centre.png"));

    @FXML
    private ImageView dial;

    @FXML
    private ImageView hourHand;

    @FXML
    private ImageView minuteHand;

    @FXML
    private ImageView secondHand;

    @FXML
    private ImageView centre;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dial.setImage(dialImage);
        hourHand.setImage(hourHandImage);
        minuteHand.setImage(minuteHandImage);
        secondHand.setImage(secondHandImage);
        centre.setImage(centreImage);

        RotateTransition secondsHandTransition = createRotateTransition(Duration.seconds(60), secondHand, getAngleOfSeconds(LocalTime.now()));
        secondsHandTransition.play();

        RotateTransition minuteTransition = createRotateTransition(Duration.minutes(60), minuteHand, getAngleOfMinute(LocalTime.now()));
        minuteTransition.play();

        RotateTransition hourTranslation = createRotateTransition(Duration.hours(12), hourHand, getAngleOfHour(LocalTime.now()));
        hourTranslation.play();

    }

    private RotateTransition createRotateTransition(Duration duration, Node node, double startAngle) {
        RotateTransition rt = new RotateTransition(duration, node);
        rt.setFromAngle(startAngle);
        rt.setByAngle(360);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        return rt;
    }

    private static double getAngleOfHour(LocalTime time) {
        return (time.getHour() % 12 + time.getMinute() / 60d + time.getSecond() / (60d * 60d)) * 360 / 12;
    }

    private static double getAngleOfMinute(LocalTime time) {
        return (time.getMinute() + time.getSecond() / 60d) * 360 / 60;
    }

    private static double getAngleOfSeconds(LocalTime time) {
        return time.getSecond() * 360 / 60;
    }
}
