package gui;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalTime;


public class Main extends Application{
    private Pane root = new Pane();
    private Circle circle = new Circle();
    private Circle spindle = new Circle();
    private Line line1 = new Line();
    private Line line2 = new Line();
    private Line line3 = new Line();
    private Line line4 = new Line();
    private Line line5 = new Line();
    private Arc arc1 = new Arc();
    private Arc arc2 = new Arc();
    private Arc arc3 = new Arc();
    private Arc stone = new Arc();
    private Arc stone2 = new Arc();
    private Text text = new Text("TIME");

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Analog Clock");

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(600);

        circle.radiusProperty().bind(Bindings.divide(scene.heightProperty(),3));
        circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(),2.5));
        circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(),2));

        circle.setFill(null);
        circle.setStrokeWidth(4);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);

        spindle.radiusProperty().bind(Bindings.divide(circle.radiusProperty(),40));
        spindle.centerXProperty().bind(circle.centerXProperty());
        spindle.centerYProperty().bind(circle.centerYProperty());
        spindle.setFill(Color.BLACK);

        line1.setStrokeWidth(6);
        line2.setStrokeWidth(6);
        line3.setStrokeWidth(6);
        line4.setStrokeWidth(6);
        line5.setStrokeWidth(6);

        arc1.centerXProperty().bind(line1.endXProperty());
        arc1.centerYProperty().bind(Bindings.add(line1.endYProperty(),5));
        arc1.setLength(180);
        arc1.radiusXProperty().bind(Bindings.divide(scene.widthProperty(),23));
        arc1.radiusYProperty().bind(Bindings.divide(scene.heightProperty(),23));
        arc1.setFill(Color.BLACK);
        arc1.setType(ArcType.OPEN);


        arc2.centerXProperty().bind(line2.endXProperty());
        arc2.centerYProperty().bind(Bindings.add(line2.endYProperty(),5));
        arc2.setLength(180);
        arc2.radiusXProperty().bind(Bindings.divide(scene.widthProperty(),23));
        arc2.radiusYProperty().bind(Bindings.divide(scene.heightProperty(),23));
        arc2.setFill(Color.BLACK);
        arc2.setType(ArcType.OPEN);

        arc3.centerXProperty().bind(line2.endXProperty());
        arc3.centerYProperty().bind(Bindings.add(line3.endYProperty(),3));
        arc3.setLength(180);
        arc3.radiusXProperty().bind(Bindings.divide(scene.widthProperty(),27));
        arc3.radiusYProperty().bind(Bindings.divide(scene.heightProperty(),30));
        arc3.setFill(Color.BLACK);
        arc3.setType(ArcType.OPEN);

        stone.centerXProperty().bind(circle.centerXProperty());
        stone.centerYProperty().bind(circle.centerYProperty());
        stone.setTranslateY(200);
        stone.setTranslateX(-600);
        stone.setLength(180);
        stone.radiusXProperty().bind(Bindings.divide(scene.widthProperty(),60));
        stone.radiusYProperty().bind(Bindings.divide(scene.heightProperty(),60));
        stone.setFill(Color.WHITE);
        stone.strokeProperty().set(Color.BLACK);
        stone.setType(ArcType.CHORD);

        stone2.centerXProperty().bind(circle.centerXProperty());
        stone2.centerYProperty().bind(circle.centerYProperty());
        stone2.setTranslateY(300);
        stone2.setTranslateX(-800);
        stone2.setLength(180);
        stone2.radiusXProperty().bind(Bindings.divide(scene.widthProperty(),40));
        stone2.radiusYProperty().bind(Bindings.divide(scene.heightProperty(),40));
        stone2.setFill(Color.WHITE);
        stone2.strokeProperty().set(Color.BLACK);
        stone2.setType(ArcType.CHORD);

        line1.startXProperty().bind(circle.centerXProperty());
        line1.startYProperty().bind(circle.centerYProperty());
        line1.endXProperty().bind(circle.centerXProperty());
        line1.endYProperty().bind(Bindings.divide(scene.heightProperty(),1.13));

        line2.startXProperty().bind(circle.centerXProperty());
        line2.startYProperty().bind(circle.centerYProperty());
        line2.endXProperty().bind(circle.centerXProperty());
        line2.endYProperty().bind(Bindings.divide(scene.heightProperty(),1.13));

        line3.startXProperty().bind(circle.centerXProperty());
        line3.startYProperty().bind(circle.centerYProperty());
        line3.endXProperty().bind(circle.centerXProperty());
        line3.endYProperty().bind(Bindings.divide(scene.heightProperty(),25));

        text.xProperty().bind(circle.centerXProperty());
        text.setTranslateX(-160);
        text.setTranslateY(50);
        text.yProperty().bind(circle.centerYProperty());
        text.setFont(new Font(150));
        text.fillProperty().set(Color.WHITE);
        text.setStroke(Color.BLACK);

        FadeTransition ft = new FadeTransition(Duration.millis(1500),text);
        ft.setFromValue(1.0);
        ft.setToValue(0.15);
        ft.setCycleCount(Animation.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();

        Rotate rotation1 = new Rotate();
        rotation1.pivotXProperty().bind(line1.startXProperty());
        rotation1.pivotYProperty().bind(line1.startYProperty());
        rotation1.setAngle(35);

        line1.getTransforms().add(rotation1);
        arc1.getTransforms().add(rotation1);

        Rotate rotation2 = new Rotate();
        rotation2.pivotXProperty().bind(line2.startXProperty());
        rotation2.pivotYProperty().bind(line2.startYProperty());
        rotation2.setAngle(-35);

        line2.getTransforms().add(rotation2);
        arc2.getTransforms().add(rotation2);

        Rotate rotation3 = new Rotate();
        rotation3.pivotXProperty().bind(line1.startXProperty());
        rotation3.pivotYProperty().bind(line1.startYProperty());
        line1.getTransforms().add(rotation3);
        arc1.getTransforms().add(rotation3);

        Rotate rotation4 = new Rotate();
        rotation4.pivotXProperty().bind(line2.startXProperty());
        rotation4.pivotYProperty().bind(line2.startYProperty());
        line2.getTransforms().add(rotation4);
        arc2.getTransforms().add(rotation4);

        Rotate rotation5 = new Rotate();
        rotation5.pivotXProperty().bind(circle.centerXProperty());
        rotation5.pivotYProperty().bind(circle.centerYProperty());
        line3.getTransforms().add(rotation5);

        arc3.getTransforms().add(rotation5);
        rotation5.setAngle(5);

        KeyValue keyValue1 = new KeyValue(rotation3.angleProperty(),-70);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), keyValue1);

        KeyValue keyValue2 = new KeyValue(rotation4.angleProperty(),70);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(1), keyValue2);

        KeyValue keyValue3 = new KeyValue(rotation5.angleProperty(),-5);
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(1), keyValue3);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2, keyFrame3);
        timeline.play();

        LocalTime lt = LocalTime.now();
        long minute=lt.getMinute();
        long sec = lt.getSecond();
        long hour = lt.getHour();

        double sec_angle = sec*(360/60);
        double min_angle = (minute+sec_angle/360)*(360/60);
        double hour_angle = (hour+min_angle/360)*(360/12);

        Line secArrow = new Line();
        secArrow.startXProperty().bind(circle.centerXProperty());
        secArrow.startYProperty().bind(circle.centerYProperty());
        secArrow.endXProperty().bind(circle.centerXProperty());
        secArrow.endYProperty().bind(Bindings.divide(circle.radiusProperty(),2.5));

        Rotate sec_rotation = new Rotate(sec_angle);
        sec_rotation.pivotYProperty().bind(circle.centerYProperty());
        sec_rotation.pivotXProperty().bind(circle.centerXProperty());
        secArrow.getTransforms().add(sec_rotation);

        Line minArrow = new Line();
        minArrow.setStrokeWidth(3);
        minArrow.startXProperty().bind(circle.centerXProperty());
        minArrow.startYProperty().bind(circle.centerYProperty());
        minArrow.endXProperty().bind(circle.centerXProperty());
        minArrow.endYProperty().bind(Bindings.divide(circle.radiusProperty(),2.5));

        Rotate min_rotation = new Rotate(min_angle);
        min_rotation.pivotXProperty().bind(circle.centerXProperty());
        min_rotation.pivotYProperty().bind(circle.centerYProperty());
        minArrow.getTransforms().add(min_rotation);

        Line hourArrow = new Line();
        hourArrow.setStrokeWidth(4);
        hourArrow.startXProperty().bind(circle.centerXProperty());
        hourArrow.startYProperty().bind(circle.centerYProperty());
        hourArrow.endXProperty().bind(circle.centerXProperty());
        hourArrow.endYProperty().bind(Bindings.divide(circle.radiusProperty(),1.5));

        Rotate hour_rotation = new Rotate(hour_angle);
        hour_rotation.pivotXProperty().bind(circle.centerXProperty());
        hour_rotation.pivotYProperty().bind(circle.centerYProperty());
        hourArrow.getTransforms().add(hour_rotation);

        final Timeline secTime = new Timeline(
                new KeyFrame(Duration.seconds(60),
                        new KeyValue(sec_rotation.angleProperty(),360+sec_angle, Interpolator.LINEAR)));
        secTime.setCycleCount(Animation.INDEFINITE);

        final Timeline minTime = new Timeline(
                new KeyFrame(Duration.minutes(60),
                        new KeyValue(min_rotation.angleProperty(),360+min_angle, Interpolator.LINEAR)));
        minTime.setCycleCount(Animation.INDEFINITE);

        final Timeline hourTime = new Timeline(
                new KeyFrame(Duration.hours(12),
                        new KeyValue(hour_rotation.angleProperty(),360+hour_angle, Interpolator.LINEAR)));
        hourTime.setCycleCount(Animation.INDEFINITE);

        secTime.play();
        minTime.play();
        hourTime.play();

        TranslateTransition tt = new TranslateTransition(Duration.seconds(7),stone);
        tt.setToX(scene.getWidth());
        tt.setCycleCount(Animation.INDEFINITE);
        tt.play();

        TranslateTransition tt2 = new TranslateTransition(Duration.seconds(5),stone2);
        tt2.setToX(scene.getWidth());
        tt2.setCycleCount(Animation.INDEFINITE);
        tt2.play();

        Group ticks = new Group();

        for (int i=0; i<12; i++){
            Line tick = new Line();
            tick.startXProperty().bind(circle.centerXProperty());
            tick.startYProperty().bind(Bindings.divide(circle.radiusProperty(),3));
            tick.endXProperty().bind(circle.centerXProperty());
            tick.endYProperty().bind(Bindings.divide(circle.radiusProperty(),4));
            Rotate rotation = new Rotate(i*360/12);
            rotation.pivotXProperty().bind(circle.centerXProperty());
            rotation.pivotYProperty().bind(circle.centerYProperty());
            tick.getTransforms().add(rotation);
            ticks.getChildren().add(tick);
        }

        root.getChildren().addAll(stone, stone2, line1, line2, line3, circle, text, spindle, arc1, arc2, arc3,  secArrow, minArrow, hourArrow, ticks);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}

