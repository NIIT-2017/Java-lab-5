package watch;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;

import java.time.LocalTime;

public class ClockFace extends Canvas implements Runnable {
    private GraphicsContext gc;

    ClockFace(double width, double height) {
        super(width, height);
        gc = this.getGraphicsContext2D();
        Thread thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
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

    void repaint() {
        //diametr
        double R = Math.min(this.getHeight(), this.getWidth())/2;

        //time
        LocalTime time = LocalTime.now();
        long second = time.getSecond();
        long minute = time.getMinute();
        long hour   = time.getHour();

        //angle
        double angelSecond = -90+second*6.0;
        double angelMinute = -90+6.0*(second/60.0+minute);
        double angelHour   = -90+30.0*(minute/60.0+hour);

        //clear
        gc.setFill(Color.rgb(25,25,60));
        gc.fillRect(0,0,this.getWidth(),this.getHeight());

        //circle
        gc.setStroke(Color.WHITE);
        gc.setLineWidth(5);
        gc.strokeOval(0,0,R*2,R*2);

        //save Transform
        Affine affine = gc.getTransform();
        //set center
        gc.translate(R,R);

        //line
        gc.setStroke(Color.WHITE);
        for (int i = 0; i < 60; i++) {
            if (i%3==0) {
                gc.setLineWidth(R*0.02);
                gc.strokeLine(0,R*0.8,0,R);
            }
            else
                gc.setLineWidth(R*0.01);
                gc.strokeLine(0,R*0.9,0,R);
            gc.rotate(30);
        }

        //line second
        gc.setLineWidth(2);
        gc.setStroke(Color.RED);
        gc.rotate(angelSecond);
        gc.strokeLine(-R*0.1,0,R,0);
        gc.rotate(-angelSecond);

        //line minute
        gc.setLineWidth(3);
        gc.setStroke(Color.WHITE);
        gc.rotate(angelMinute);
        gc.strokeLine(0,0,R*0.9,0);
        gc.rotate(-angelMinute);

        //line hour
        gc.setLineWidth(5);
        gc.setStroke(Color.WHITE);
        gc.rotate(angelHour);
        gc.strokeLine(0,0,R*0.5,0);
        gc.rotate(-angelHour);


        //center
        gc.setFill(Color.RED);
        gc.fillOval(-R*0.025,-R*0.025,R*0.05,R*0.05);

        //recovery transform
        gc.setTransform(affine);
    }
}
