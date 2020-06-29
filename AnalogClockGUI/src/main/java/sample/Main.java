package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application {

    public void music(){
        URL mp3 = this.getClass().getClassLoader().getResource("backgroundMusic.mp3");
        Media hit = new Media(mp3.toString());
        AudioClip mediaPlayer = new AudioClip(hit.getSource());
        mediaPlayer.setCycleCount(INDEFINITE);
        mediaPlayer.play();
    }

    @Override
    public void start(Stage stage) {

        URL img = this.getClass().getClassLoader().getResource("backgroundBlack.jpg");
        Image image = new Image(String.valueOf(img), 720, 720, false, false);
        ImageView imageView = new ImageView(image);

        URL img2 = this.getClass().getClassLoader().getResource("backHour.jpg");
        Image hour = new Image(String.valueOf(img2), 502, 502, false, false);

        URL img3 = this.getClass().getClassLoader().getResource("backMinuteB.jpg");
        Image minute = new Image(String.valueOf(img3), 402, 402, false, false);

        URL img4 = this.getClass().getClassLoader().getResource("backSecond.jpg");
        Image second = new Image(String.valueOf(img4), 302, 302, false, false);

        Circle hourImage = new Circle (357,357,251);
        hourImage.setFill(new ImagePattern(hour));

        Circle minuteImage = new Circle (357,357,201);
        minuteImage.setFill(new ImagePattern(minute));

        Circle secondImage = new Circle (357,357,151);
        secondImage.setFill(new ImagePattern(second));


        Circle circle1 = new Circle (357,357,252);
        circle1.setFill(new ImagePattern(image));
        Circle circle2 = new Circle (357,357,253);
        circle2.setFill(new ImagePattern(image));
        Circle circle3 = new Circle (357,357,254);
        circle3.setFill(new ImagePattern(image));
        Circle circle4 = new Circle (357,357,255);
        circle4.setFill(new ImagePattern(image));
        Circle circle5 = new Circle (357,357,256);
        circle5.setFill(new ImagePattern(image));
        Circle circle6 = new Circle (357,357,257);
        circle6.setFill(new ImagePattern(image));
        Circle circle7 = new Circle (357,357,258);
        circle7.setFill(new ImagePattern(image));
        Circle circle8 = new Circle (357,357,259);
        circle8.setFill(new ImagePattern(image));
        Circle circle9 = new Circle (357,357,260);
        circle9.setFill(new ImagePattern(image));
        Circle circle10 = new Circle (357,357,261);
        circle10.setFill(new ImagePattern(image));
        Circle circle11 = new Circle (357,357,262);
        circle11.setFill(new ImagePattern(image));
        Circle circle12 = new Circle (357,357,263);
        circle12.setFill(new ImagePattern(image));
        Circle circle13 = new Circle (357,357,264);
        circle13.setFill(new ImagePattern(image));
        Circle circle14 = new Circle (357,357,265);
        circle14.setFill(new ImagePattern(image));
        Circle circle15 = new Circle (357,357,266);
        circle15.setFill(new ImagePattern(image));
        Circle circle16 = new Circle (357,357,267);
        circle16.setFill(new ImagePattern(image));
        Circle circle17 = new Circle (357,357,268);
        circle17.setFill(new ImagePattern(image));
        Circle circle18 = new Circle (357,357,269);
        circle18.setFill(new ImagePattern(image));
        Circle circle19 = new Circle (357,357,270);
        circle19.setFill(new ImagePattern(image));
        Circle circle20 = new Circle (357,357,271);
        circle20.setFill(new ImagePattern(image));
        Circle circle21 = new Circle (357,357,272);
        circle21.setFill(new ImagePattern(image));
        Circle circle22 = new Circle (357,357,273);
        circle22.setFill(new ImagePattern(image));
        Circle circle23 = new Circle (357,357,274);
        circle23.setFill(new ImagePattern(image));
        Circle circle24 = new Circle (357,357,275);
        circle24.setFill(new ImagePattern(image));
        Circle circle25 = new Circle (357,357,276);
        circle25.setFill(new ImagePattern(image));
        Circle circle26 = new Circle (357,357,277);
        circle26.setFill(new ImagePattern(image));
        Circle circle27 = new Circle (357,357,278);
        circle27.setFill(new ImagePattern(image));
        Circle circle28 = new Circle (357,357,279);
        circle28.setFill(new ImagePattern(image));
        Circle circle29 = new Circle (357,357,280);
        circle29.setFill(new ImagePattern(image));
        Circle circle30 = new Circle (357,357,281);
        circle30.setFill(new ImagePattern(image));
        Circle circle31 = new Circle (357,357,282);
        circle31.setFill(new ImagePattern(image));
        Circle circle32 = new Circle (357,357,283);
        circle32.setFill(new ImagePattern(image));
        Circle circle33 = new Circle (357,357,284);
        circle33.setFill(new ImagePattern(image));
        Circle circle34 = new Circle (357,357,285);
        circle34.setFill(new ImagePattern(image));
        Circle circle35 = new Circle (357,357,286);
        circle35.setFill(new ImagePattern(image));
        Circle circle36 = new Circle (357,357,287);
        circle36.setFill(new ImagePattern(image));
        Circle circle37 = new Circle (357,357,288);
        circle37.setFill(new ImagePattern(image));
        Circle circle38 = new Circle (357,357,289);
        circle38.setFill(new ImagePattern(image));
        Circle circle39 = new Circle (357,357,290);
        circle39.setFill(new ImagePattern(image));
        Circle circle40 = new Circle (357,357,291);
        circle40.setFill(new ImagePattern(image));

        Line secondHand   = new Line(0, -151, 0, -251);
        secondHand.setTranslateX(357);   secondHand.setTranslateY(357); secondHand.setStroke(Color.BEIGE); secondHand.setStrokeWidth(2);

        Calendar calendar = GregorianCalendar.getInstance();

        double seedSecondDegrees  = calendar.get(Calendar.SECOND) * (360 / 60);
        double seedSecondDegrees2  = (60-calendar.get(Calendar.SECOND)) * (360 / 60);
        double seedMinuteDegrees  = (calendar.get(Calendar.MINUTE)-60 - seedSecondDegrees / 360.0) * (-360 / 60);
        double seedMinuteDegrees2  = (calendar.get(Calendar.MINUTE) + seedSecondDegrees / 360.0) * (360 / 60);
        double seedHourDegrees    = (calendar.get(Calendar.HOUR) + seedMinuteDegrees2/360) * (-360 / 12);

        Rotate secondRotate    = new Rotate(seedSecondDegrees);
        Rotate minuteRotate    = new Rotate(seedMinuteDegrees);
        Rotate hourRotate      = new Rotate(seedHourDegrees);
        Rotate secondRotate1    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate2    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate3    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate4    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate5    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate6    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate7    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate8    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate9    = new Rotate(seedSecondDegrees2);
        Rotate secondRotate10   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate11   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate12   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate13   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate14   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate15   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate16   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate17   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate18   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate19   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate20   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate21   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate22   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate23   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate24   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate25   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate26   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate27   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate28   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate29   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate30   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate31   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate32   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate33   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate34   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate35   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate36   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate37   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate38   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate39   = new Rotate(seedSecondDegrees2);
        Rotate secondRotate40   = new Rotate(seedSecondDegrees2);

        hourRotate.setPivotX(357);
        hourRotate.setPivotY(357);
        minuteRotate.setPivotX(357);
        minuteRotate.setPivotY(357);
        secondRotate.setPivotX(357);
        secondRotate.setPivotY(357);
        secondRotate1.setPivotX(357);
        secondRotate1.setPivotY(357);
        secondRotate2.setPivotX(357);
        secondRotate2.setPivotY(357);
        secondRotate3.setPivotX(357);
        secondRotate3.setPivotY(357);
        secondRotate4.setPivotX(357);
        secondRotate4.setPivotY(357);
        secondRotate5.setPivotX(357);
        secondRotate5.setPivotY(357);
        secondRotate6.setPivotX(357);
        secondRotate6.setPivotY(357);
        secondRotate7.setPivotX(357);
        secondRotate7.setPivotY(357);
        secondRotate8.setPivotX(357);
        secondRotate8.setPivotY(357);
        secondRotate9.setPivotX(357);
        secondRotate9.setPivotY(357);
        secondRotate10.setPivotX(357);
        secondRotate10.setPivotY(357);
        secondRotate11.setPivotX(357);
        secondRotate11.setPivotY(357);
        secondRotate12.setPivotX(357);
        secondRotate12.setPivotY(357);
        secondRotate13.setPivotX(357);
        secondRotate13.setPivotY(357);
        secondRotate14.setPivotX(357);
        secondRotate14.setPivotY(357);
        secondRotate15.setPivotX(357);
        secondRotate15.setPivotY(357);
        secondRotate16.setPivotX(357);
        secondRotate16.setPivotY(357);
        secondRotate17.setPivotX(357);
        secondRotate17.setPivotY(357);
        secondRotate18.setPivotX(357);
        secondRotate18.setPivotY(357);
        secondRotate19.setPivotX(357);
        secondRotate19.setPivotY(357);
        secondRotate20.setPivotX(357);
        secondRotate20.setPivotY(357);
        secondRotate21.setPivotX(357);
        secondRotate21.setPivotY(357);
        secondRotate22.setPivotX(357);
        secondRotate22.setPivotY(357);
        secondRotate23.setPivotX(357);
        secondRotate23.setPivotY(357);
        secondRotate24.setPivotX(357);
        secondRotate24.setPivotY(357);
        secondRotate25.setPivotX(357);
        secondRotate25.setPivotY(357);
        secondRotate26.setPivotX(357);
        secondRotate26.setPivotY(357);
        secondRotate27.setPivotX(357);
        secondRotate27.setPivotY(357);
        secondRotate28.setPivotX(357);
        secondRotate28.setPivotY(357);
        secondRotate29.setPivotX(357);
        secondRotate29.setPivotY(357);
        secondRotate30.setPivotX(357);
        secondRotate30.setPivotY(357);
        secondRotate31.setPivotX(357);
        secondRotate31.setPivotY(357);
        secondRotate32.setPivotX(357);
        secondRotate32.setPivotY(357);
        secondRotate33.setPivotX(357);
        secondRotate33.setPivotY(357);
        secondRotate34.setPivotX(357);
        secondRotate34.setPivotY(357);
        secondRotate35.setPivotX(357);
        secondRotate35.setPivotY(357);
        secondRotate36.setPivotX(357);
        secondRotate36.setPivotY(357);
        secondRotate37.setPivotX(357);
        secondRotate37.setPivotY(357);
        secondRotate38.setPivotX(357);
        secondRotate38.setPivotY(357);
        secondRotate39.setPivotX(357);
        secondRotate39.setPivotY(357);
        secondRotate40.setPivotX(357);
        secondRotate40.setPivotY(357);

        hourImage.getTransforms().add(hourRotate);
        minuteImage.getTransforms().add(minuteRotate);
        secondImage.getTransforms().add(secondRotate);
        circle1.getTransforms().add(secondRotate1);
        circle2.getTransforms().add(secondRotate2);
        circle3.getTransforms().add(secondRotate3);
        circle4.getTransforms().add(secondRotate4);
        circle5.getTransforms().add(secondRotate5);
        circle6.getTransforms().add(secondRotate6);
        circle7.getTransforms().add(secondRotate7);
        circle8.getTransforms().add(secondRotate8);
        circle9.getTransforms().add(secondRotate9);
        circle10.getTransforms().add(secondRotate10);
        circle11.getTransforms().add(secondRotate11);
        circle12.getTransforms().add(secondRotate12);
        circle13.getTransforms().add(secondRotate13);
        circle14.getTransforms().add(secondRotate14);
        circle15.getTransforms().add(secondRotate15);
        circle16.getTransforms().add(secondRotate16);
        circle17.getTransforms().add(secondRotate17);
        circle18.getTransforms().add(secondRotate18);
        circle19.getTransforms().add(secondRotate19);
        circle20.getTransforms().add(secondRotate20);
        circle21.getTransforms().add(secondRotate21);
        circle22.getTransforms().add(secondRotate22);
        circle23.getTransforms().add(secondRotate23);
        circle24.getTransforms().add(secondRotate24);
        circle25.getTransforms().add(secondRotate25);
        circle26.getTransforms().add(secondRotate26);
        circle27.getTransforms().add(secondRotate27);
        circle28.getTransforms().add(secondRotate28);
        circle29.getTransforms().add(secondRotate29);
        circle30.getTransforms().add(secondRotate30);
        circle31.getTransforms().add(secondRotate31);
        circle32.getTransforms().add(secondRotate32);
        circle33.getTransforms().add(secondRotate33);
        circle34.getTransforms().add(secondRotate34);
        circle35.getTransforms().add(secondRotate35);
        circle36.getTransforms().add(secondRotate36);
        circle37.getTransforms().add(secondRotate37);
        circle38.getTransforms().add(secondRotate38);
        circle39.getTransforms().add(secondRotate39);
        circle40.getTransforms().add(secondRotate40);
        final Timeline secondTime40 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate40.angleProperty(),
                                360 + seedSecondDegrees*20,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime39 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate39.angleProperty(),
                                360 + seedSecondDegrees*19.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime38 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate38.angleProperty(),
                                360 + seedSecondDegrees*19,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime37 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate37.angleProperty(),
                                360 + seedSecondDegrees*18.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime36 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate36.angleProperty(),
                                360 + seedSecondDegrees*18,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime35 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate35.angleProperty(),
                                360 + seedSecondDegrees*17.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime34 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate34.angleProperty(),
                                360 + seedSecondDegrees*17,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime33 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate33.angleProperty(),
                                360 + seedSecondDegrees*16.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime32 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate32.angleProperty(),
                                360 + seedSecondDegrees*16,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime31 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate31.angleProperty(),
                                360 + seedSecondDegrees*15.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime30 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate30.angleProperty(),
                                360 + seedSecondDegrees*15,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime29 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate29.angleProperty(),
                                360 + seedSecondDegrees*14.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime28 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate28.angleProperty(),
                                360 + seedSecondDegrees*14,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime27 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate27.angleProperty(),
                                360 + seedSecondDegrees*13.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime26 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate26.angleProperty(),
                                360 + seedSecondDegrees*13,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime25 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate25.angleProperty(),
                                360 + seedSecondDegrees*12.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime24 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate24.angleProperty(),
                                360 + seedSecondDegrees*12,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime23 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate23.angleProperty(),
                                360 + seedSecondDegrees*11.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime22 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate22.angleProperty(),
                                360 + seedSecondDegrees*11,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime21 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate21.angleProperty(),
                                360 + seedSecondDegrees*10.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime20 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate20.angleProperty(),
                                360 + seedSecondDegrees*10,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime19 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate19.angleProperty(),
                                360 + seedSecondDegrees*9.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime18 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate18.angleProperty(),
                                360 + seedSecondDegrees*9,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime17 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate17.angleProperty(),
                                360 + seedSecondDegrees*8.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime16 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate16.angleProperty(),
                                360 + seedSecondDegrees*8,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime15 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate15.angleProperty(),
                                360 + seedSecondDegrees*7.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime14 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate14.angleProperty(),
                                360 + seedSecondDegrees*7,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime13 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate13.angleProperty(),
                                360 + seedSecondDegrees*6.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime12 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate12.angleProperty(),
                                360 + seedSecondDegrees*6,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime11 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate11.angleProperty(),
                                360 + seedSecondDegrees*5.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime10 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate10.angleProperty(),
                                360 + seedSecondDegrees*5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime9 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate9.angleProperty(),
                                360 + seedSecondDegrees*4.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime8 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate8.angleProperty(),
                                360 + seedSecondDegrees*4,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime7 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate7.angleProperty(),
                                360 + seedSecondDegrees*3.5,
                                Interpolator.LINEAR
                        )
                )
        );

        final Timeline secondTime6 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate6.angleProperty(),
                                360 + seedSecondDegrees2*3,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime5 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate5.angleProperty(),
                                360 + seedSecondDegrees2*2.5,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime4 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate4.angleProperty(),
                                360 + seedSecondDegrees*2,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime3 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate3.angleProperty(),
                                360 + seedSecondDegrees*1.5,
                                Interpolator.LINEAR
                        )
                )
        );

        final Timeline secondTime2 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate2.angleProperty(),
                                360 + seedSecondDegrees2*1,
                                Interpolator.LINEAR
                        )
                )
        );
        final Timeline secondTime1 = new Timeline(
                new KeyFrame(
                        Duration.seconds(60),
                        new KeyValue(
                                secondRotate1.angleProperty(),
                                360 + seedSecondDegrees2*0.5,
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
        final Timeline minuteTime = new Timeline(
                new KeyFrame(Duration.minutes(60), new KeyValue(minuteRotate.angleProperty(), seedMinuteDegrees-360, Interpolator.LINEAR))
        );
        Timeline hourTime = new Timeline(
                new KeyFrame(Duration.hours(12), new KeyValue(hourRotate.angleProperty(), seedHourDegrees-360,Interpolator.LINEAR))
        );

        secondTime.setCycleCount(Animation.INDEFINITE);
        minuteTime.setCycleCount(Animation.INDEFINITE);
        hourTime.setCycleCount(Animation.INDEFINITE);
        secondTime1.setCycleCount(Animation.INDEFINITE);
        secondTime2.setCycleCount(Animation.INDEFINITE);
        secondTime3.setCycleCount(Animation.INDEFINITE);
        secondTime4.setCycleCount(Animation.INDEFINITE);
        secondTime5.setCycleCount(Animation.INDEFINITE);
        secondTime6.setCycleCount(Animation.INDEFINITE);
        secondTime7.setCycleCount(Animation.INDEFINITE);
        secondTime8.setCycleCount(Animation.INDEFINITE);
        secondTime9.setCycleCount(Animation.INDEFINITE);
        secondTime10.setCycleCount(Animation.INDEFINITE);
        secondTime11.setCycleCount(Animation.INDEFINITE);
        secondTime12.setCycleCount(Animation.INDEFINITE);
        secondTime13.setCycleCount(Animation.INDEFINITE);
        secondTime14.setCycleCount(Animation.INDEFINITE);
        secondTime15.setCycleCount(Animation.INDEFINITE);
        secondTime16.setCycleCount(Animation.INDEFINITE);
        secondTime17.setCycleCount(Animation.INDEFINITE);
        secondTime18.setCycleCount(Animation.INDEFINITE);
        secondTime19.setCycleCount(Animation.INDEFINITE);
        secondTime20.setCycleCount(Animation.INDEFINITE);
        secondTime21.setCycleCount(Animation.INDEFINITE);
        secondTime22.setCycleCount(Animation.INDEFINITE);
        secondTime23.setCycleCount(Animation.INDEFINITE);
        secondTime24.setCycleCount(Animation.INDEFINITE);
        secondTime25.setCycleCount(Animation.INDEFINITE);
        secondTime26.setCycleCount(Animation.INDEFINITE);
        secondTime27.setCycleCount(Animation.INDEFINITE);
        secondTime28.setCycleCount(Animation.INDEFINITE);
        secondTime29.setCycleCount(Animation.INDEFINITE);
        secondTime30.setCycleCount(Animation.INDEFINITE);
        secondTime31.setCycleCount(Animation.INDEFINITE);
        secondTime32.setCycleCount(Animation.INDEFINITE);
        secondTime33.setCycleCount(Animation.INDEFINITE);
        secondTime34.setCycleCount(Animation.INDEFINITE);
        secondTime35.setCycleCount(Animation.INDEFINITE);
        secondTime36.setCycleCount(Animation.INDEFINITE);
        secondTime37.setCycleCount(Animation.INDEFINITE);
        secondTime38.setCycleCount(Animation.INDEFINITE);
        secondTime39.setCycleCount(Animation.INDEFINITE);
        secondTime40.setCycleCount(Animation.INDEFINITE);

        secondTime.play();
        minuteTime.play();
        hourTime.play();
        secondTime1.play();
        secondTime2.play();
        secondTime3.play();
        secondTime4.play();
        secondTime5.play();
        secondTime6.play();
        secondTime7.play();
        secondTime8.play();
        secondTime9.play();
        secondTime10.play();
        secondTime11.play();
        secondTime12.play();
        secondTime13.play();
        secondTime14.play();
        secondTime15.play();
        secondTime16.play();
        secondTime17.play();
        secondTime18.play();
        secondTime19.play();
        secondTime20.play();
        secondTime21.play();
        secondTime22.play();
        secondTime23.play();
        secondTime24.play();
        secondTime25.play();
        secondTime26.play();
        secondTime27.play();
        secondTime28.play();
        secondTime29.play();
        secondTime30.play();
        secondTime31.play();
        secondTime32.play();
        secondTime33.play();
        secondTime34.play();
        secondTime35.play();
        secondTime36.play();
        secondTime37.play();
        secondTime38.play();
        secondTime39.play();
        secondTime40.play();

        music();

        Group root = new Group(imageView, circle40, circle39, circle38, circle37,circle36, circle35, circle34, circle33, circle32, circle31, circle30, circle29, circle28, circle27,circle26, circle25, circle24, circle23, circle22, circle21, circle20, circle19, circle18, circle17,circle16, circle15, circle14, circle13, circle12, circle11, circle10, circle9,circle8, circle7, circle6, circle5, circle4, circle3, circle2, circle1, hourImage, minuteImage, secondImage, secondHand);
        Scene scene = new Scene(root, 720,720);
        stage.setMaxHeight(755);
        stage.setMinHeight(755);
        stage.setMaxWidth(735);
        stage.setMinWidth(735);
        stage.setTitle("Wormhole Clock");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String args[]) {
        launch(args);
    }
}
