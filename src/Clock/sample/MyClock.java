package sample;

import javafx.animation.*;
import javafx.beans.property.DoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Calendar;

public class MyClock extends Group {
    static final int HOUR_HAND_LENGTH = 50;
    static final int MINUTE_HAND_LENGTH = 80;
    static final int SECOND_HAND_LENGTH = 90;
    static final int SECOND_HAND_OFFSET = 20;


    MyClock(String name, double clockRadius) {
        setId("MyClock");
        ResourseLoader resourseLoader=new ResourseLoader();
        getStylesheets().add(resourseLoader.getResourceFor(getClass(), "myClock.css"));

    final Circle face = createClockFace(clockRadius);
    final Label label = createName(face, name);
    final Line hourHand = createHand(
            "hourHand",
            clockRadius,
            0,
            percentOf(HOUR_HAND_LENGTH, clockRadius)
    );
    final Line minuteHand = createHand(
            "minuteHand",
            clockRadius,
            0,
            percentOf(MINUTE_HAND_LENGTH, clockRadius)
    );
    final Line secondHand = createHand(
            "secondHand",
            clockRadius,
            percentOf(SECOND_HAND_OFFSET, clockRadius),
            percentOf(SECOND_HAND_LENGTH, clockRadius)
    );

    // анимируем стрелки
    bindClockHandsToTime(hourHand, minuteHand, secondHand);

    getChildren().

    addAll(
            face,
            label,
            createTicks(clockRadius),

    createSpindle(clockRadius),

    hourHand,
    minuteHand,
    secondHand
        );
}

    /** круговые отметки по окружности циферблата */
    private Group createTicks(double clockRadius) {
        final double TICK_START_OFFSET = percentOf(83, clockRadius);//отступ нач
        final double TICK_END_OFFSET   = percentOf(93, clockRadius);//отступ конечный

        final Group  ticks = new Group();
        for (int i = 0; i < 12; i++) {
            Line tick = new Line(0, -TICK_START_OFFSET, 0, -TICK_END_OFFSET);
            tick.getStyleClass().add("tick");
            tick.setLayoutX(clockRadius);
            tick.setLayoutY(clockRadius);
            tick.getTransforms().add(new Rotate(i * (360 / 12)));
            ticks.getChildren().add(tick);
        }
        return ticks;
    }

    /** создается шпиндель, вокруг которого работает часовой механизм  */
    private Circle createSpindle(double clockRadius) {
        final Circle spindle = new Circle(clockRadius,  clockRadius, 5);
        spindle.setId("spindle");
        return spindle;
    }

    private Circle createClockFace(double clockRadius) {
        final Circle face = new Circle(clockRadius, clockRadius, clockRadius);
        face.setId("face");
        return face;
    }

    private Line createHand(String handId, double clockRadius, double handOffsetLength, double handLength) {
        final Line secondHand = new Line(0, handOffsetLength, 0, -handLength);
        secondHand.setLayoutX(clockRadius);
        secondHand.setLayoutY(clockRadius);
        secondHand.setId(handId);
        return secondHand;
    }

    private Label createName(Circle face, String clockName) {
        final Label label = new Label(clockName);
        label.setId("label");
        label.layoutXProperty().bind(face.centerXProperty().subtract(label.widthProperty().divide(2)));
        label.layoutYProperty().bind(face.centerYProperty().add(face.radiusProperty().divide(2)));
        return label;
    }

    private void bindClockHandsToTime(final Line hourHand, final Line minuteHand, final Line secondHand) {
        // устанавливается начальное положение стрелок.
        Calendar time = Calendar.getInstance();
        final double initialHourhandDegrees   = calculateHourHandDegrees(time);
        final double initialMinuteHandDegrees = calculateMinuteHandDegrees(time);
        final double initialSecondHandDegrees = calculateSecondHandDegrees(time);

        // добавляем анимацию стрелкам используя  timelines.
        createRotationTimeline(              // полный оборот дважды в сутки
                createRotate(hourHand, initialHourhandDegrees).angleProperty(),
                Duration.hours(12),
                initialHourhandDegrees
        );
        createRotationTimeline(              // оборот за час
                createRotate(minuteHand, initialMinuteHandDegrees).angleProperty(),
                Duration.minutes(60),
                initialMinuteHandDegrees
        );
        createRotationTimeline(              // оборот за минуту
                createRotate(secondHand, initialSecondHandDegrees).angleProperty(),
                Duration.seconds(60),
                initialSecondHandDegrees
        );
    }

    private Rotate createRotate(Line hand, double initialHandDegrees) {
        final Rotate hourRotate = new Rotate(initialHandDegrees);
        hand.getTransforms().add(hourRotate);
        return hourRotate;
    }

    /**
     * добавляем вращение 360 гр за период
     * вращение начинается с initialperiod
     */
    private void createRotationTimeline(DoubleProperty angleProperty, Duration duration, double initialRotation) {
        Timeline timeline = new Timeline(
                new KeyFrame(
                        duration,
                        new KeyValue(
                                angleProperty,
                                360 + initialRotation,
                                Interpolator.LINEAR
                        )
                )
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private int calculateSecondHandDegrees(Calendar time) {
        return time.get(Calendar.SECOND) * (360 / 60);
    }

    private double calculateMinuteHandDegrees(Calendar time) {
        return (time.get(Calendar.MINUTE) + calculateSecondHandDegrees(time) / 360.0) * (360 / 60);
    }

    private double calculateHourHandDegrees(Calendar time) {
        return (time.get(Calendar.HOUR)   + calculateMinuteHandDegrees(time) / 360.0) * (360 / 12);
    }

    private double percentOf(double percent, double clockRadius) {
        return percent / 100 * clockRadius;
    }
}
