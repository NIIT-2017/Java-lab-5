package watch;

import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Shear;

import java.time.LocalTime;

public class ClockFace2 extends Pane implements Runnable {

    private Circle face;
    private Line hourLine;
    private Line minuteLine;
    private Line secondLine;
    private double r;
    private Rotate rotateSecondLine;
    private Rotate rotateMinuteLine;
    private Rotate rotateHourLine;
    private Circle pivot;
    private double centerX;
    private double centerY;

    private Text[] number;

    ClockFace2(double width,double height) {
        super();
        r = Math.min(height,width)/2;
        number = new Text[12];
        pivot = new Circle(r,r,r*0.03);
        pivot.setFill(Color.RED);
        face = new Circle();
        this.setWidth(width);
        this.setHeight(height);

        face = new Circle();
        face.setStrokeType(StrokeType.INSIDE);
        face.setStrokeWidth(5);
        face.setStroke(Color.SKYBLUE);
        face.setStyle("-fx-background: black;");

        hourLine = new Line();
        hourLine.setStroke(Color.WHITE);

        rotateHourLine = new Rotate(0, r, r);
        hourLine.getTransforms().add(rotateHourLine);

        secondLine= new Line();
        secondLine.setStroke(Color.RED);

        rotateSecondLine = new Rotate(0, r, r);
        secondLine.getTransforms().add(rotateSecondLine);

        minuteLine= new Line();
        minuteLine.setStroke(Color.WHITE);

        rotateMinuteLine = new Rotate(0, r, r);
        minuteLine.getTransforms().add(rotateMinuteLine);

        this.getChildren().add(face);

        paintLine();
        paintText();

        this.getChildren().addAll(hourLine, minuteLine, secondLine,pivot);

        this.getTransforms().add(new Shear(-0.35, -0.35));

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.GRAY);
        dropShadow.setOffsetX(r);
        dropShadow.setOffsetY(r);

        this.setEffect(dropShadow);
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    private void paintLine() {
        centerX = this.getWidth()/2;
        centerY = this.getHeight()/2;

        r = Math.min(centerX, centerY)/2;

        face.setCenterX(centerX);
        face.setCenterY(centerY);
        face.setRadius(r);

        hourLine.setStrokeWidth(r*0.03);
        hourLine.setStartX(centerX);
        hourLine.setStartY(centerY-0.5*r);
        hourLine.setEndX(centerX);
        hourLine.setEndY(centerY);

        secondLine.setStrokeWidth(r*0.005);
        secondLine.setStartX(centerX);
        secondLine.setStartY(centerY-r*0.95);
        secondLine.setEndX(centerX);
        secondLine.setEndY(centerY);

        minuteLine.setStrokeWidth(r*0.02);
        minuteLine.setStartX(centerX);
        minuteLine.setStartY(centerY-r*0.9);
        minuteLine.setEndX(centerX);
        minuteLine.setEndY(centerY);
    }


    @Override
    public void run() {
        while (true) try {
            Thread.sleep(1000);
            repaint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void paintText() {

        for (int i = 1; i <= 12; i++) {
            double angel = i * Math.PI / 6 - Math.PI / 2;
            number[i - 1] = new Text(String.valueOf(i));
            number[i - 1].setFont(Font.font(16));
            number[i - 1].setFill(Color.WHITE);

            number[i - 1].setX(0.8*r *Math.cos(angel)+centerX-10);
            number[i - 1].setY(0.8*r *Math.sin(angel)+centerY+5);

            this.getChildren().add(number[i - 1]);
        }
    }
    private void repaint() {

        //time
        LocalTime time = LocalTime.now();
        long second = time.getSecond();
        long minute = time.getMinute();
        long hour   = time.getHour();

        //angle
        double angelSecond = second*6.0;
        double angelMinute = 6.0*(second/60.0+minute);
        double angelHour   = 30.0*(minute/60.0+hour);

        rotateSecondLine.setAngle(angelSecond);
        rotateMinuteLine.setAngle(angelMinute);
        rotateHourLine.setAngle(angelHour);
    }

}
