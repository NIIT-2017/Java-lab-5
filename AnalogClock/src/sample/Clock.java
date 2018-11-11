package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.image.Image;
import java.awt.Rectangle;
import java.io.InputStream;
import java.util.*;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class Clock extends Application {
    public static void main(String[] args) throws Exception { launch(args); }

    public void start(final Stage stage) throws Exception {
        Class<?> clazz = this.getClass();
        double rad=0;
        final Circle face     = new Circle(100, 100, 200);
        Rectangle rect = new Rectangle();
        face.setId("face");

        InputStream input = clazz.getResourceAsStream("/sample/javafx.png");
        InputStream input1 = clazz.getResourceAsStream("/sample/date.png");

        Image image = new Image(input,100,100,false,false);
        Image image1 = new Image(input1,60,60,false,false);
        ImageView imageView = new ImageView(image);
        ImageView imageView1 = new ImageView(image1);
        imageView.setY(115);
        imageView.setX(50);
        imageView1.setY(65);
        imageView1.setX(191);

        final Label brand     = new Label("Created B10");

      brand.setId("brand");
      brand.layoutXProperty().bind(face.centerXProperty().subtract(brand.widthProperty().divide(2)));
        brand.layoutYProperty().bind(face.centerYProperty().add(face.radiusProperty().divide(-3)));
        final Line hourHand   = new Line(0, 0, 0, -110);
        hourHand.setTranslateX(100);   hourHand.setTranslateY(100);
        hourHand.setId("hourHand");
        final Line minuteHand = new Line(0, 0, 0, -165);
        minuteHand.setTranslateX(100); minuteHand.setTranslateY(100);
        minuteHand.setId("minuteHand");
        final Line secondHand = new Line(0, 25, 0, -185);
        secondHand.setTranslateX(100); secondHand.setTranslateY(100);
        secondHand.setId("secondHand");
        final Circle spindle = new Circle(100, 100, 10);
        spindle.setId("spindle");

        Group ticks = new Group();

        for (int i=6;i<13;i+=3) {
            Text g = new Text(String.valueOf(i));
            g.setId("text");
            g.setFill(Color.FIREBRICK);
            rad = i* 2*Math.PI/12-Math.PI/2;
            g.setX(92 + 140*cos(rad));
            g.setY(104 + 140*sin(rad));
            ticks.getChildren().add(g);
        }
        for (int i = 0; i < 12; i++) {
            Line tick = new Line(0, -183, 0, -163);

            tick.setTranslateX(100); tick.setTranslateY(100);
                tick.getStyleClass().add("tick");
             tick.getTransforms().add(new Rotate(i * (360 / 12)));
           ticks.getChildren().add(tick);
        }

         final Group analogueClock = new Group(face, imageView, imageView1,brand, ticks, spindle, hourHand, minuteHand, secondHand);

        final Label digitalClock = new Label();
        digitalClock.setTranslateX(122);
        digitalClock.setTranslateY(-227);
        digitalClock.setTextFill(Color.BROWN);
        digitalClock.setStyle("-fx-font-weight: bold;");
        digitalClock.setId("digitalClock");


        Calendar calendar            = GregorianCalendar.getInstance();
        final double seedSecondDegrees  = calendar.get(Calendar.SECOND) * (360 / 60);
        final double seedMinuteDegrees  = (calendar.get(Calendar.MINUTE) + seedSecondDegrees / 360.0) * (360 / 60);
        final double seedHourDegrees    = (calendar.get(Calendar.HOUR)   + seedMinuteDegrees / 360.0) * (360 / 12) ;

       final Rotate hourRotate      = new Rotate(seedHourDegrees);
       final Rotate minuteRotate    = new Rotate(seedMinuteDegrees);
        final Rotate secondRotate    = new Rotate(seedSecondDegrees);
        hourHand.getTransforms().add(hourRotate);
        minuteHand.getTransforms().add(minuteRotate);
        secondHand.getTransforms().add(secondRotate);


      final Timeline hourTime = new Timeline(
                new KeyFrame(
                        Duration.hours(12),
                        new KeyValue(
                                hourRotate.angleProperty(),
                               360 + seedHourDegrees,
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

        final Timeline digitalTime = new Timeline(
                new KeyFrame(Duration.seconds(0),
                        new EventHandler<ActionEvent>() {
                            @Override public void handle(ActionEvent actionEvent) {
                                Calendar calendar            = GregorianCalendar.getInstance();
                                String Day = pad(2, '0', calendar.get(Calendar.DAY_OF_MONTH) + "");
                                digitalClock.setText(Day);
                            }
                        }
                ),
                new KeyFrame(Duration.seconds(1))
        );

        hourTime.setCycleCount(Animation.INDEFINITE);
        minuteTime.setCycleCount(Animation.INDEFINITE);
        secondTime.setCycleCount(Animation.INDEFINITE);
        digitalTime.setCycleCount(Animation.INDEFINITE);

        secondTime.play();
        minuteTime.play();
        hourTime.play();
        digitalTime.play();

        analogueClock.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                analogueClock.setMouseTransparent(true);
                FadeTransition fade = new FadeTransition(Duration.seconds(1.2), analogueClock);
                fade.setOnFinished(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent actionEvent) {
                        stage.close();
                    }
                });
                fade.setFromValue(1);
                fade.setToValue(0);
                fade.play();
            }
        });

        stage.initStyle(StageStyle.TRANSPARENT);

       final VBox layout = new VBox();
        layout.getChildren().addAll(analogueClock,digitalClock);
       layout.setAlignment(Pos.CENTER);
       final Scene scene = new Scene(layout, Color.TRANSPARENT);
        scene.getStylesheets().add(getResource("clock.css"));
        stage.setScene(scene);

        stage.show();
    }
    private String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);
        return sb.toString();
    }

       static String getResource(String path) {
        return Clock.class.getResource(path).toExternalForm();
    }

    }
