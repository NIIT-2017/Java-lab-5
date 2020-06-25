package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    Timer timer;
    @FXML
    Pane pane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pane.setBackground(new Background(new BackgroundFill(Color.web("#2F4355"), CornerRadii.EMPTY, Insets.EMPTY)));
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Calendar calendar=Calendar.getInstance();
                Platform.runLater(()->pane.getChildren().clear());
                int radius=(int)(Math.min(getCentralX(),getCentralY())/1.2);
                Color linesColor=Color.web("#FA0054");
                drawLine( (calendar.get(Calendar.SECOND)+45)*6,radius,1,getCentralX(),getCentralY(),
                        -1,-1,linesColor);
                drawLine((calendar.get(Calendar.MINUTE)+45)*6,(int)(radius/1.1),4,getCentralX(),getCentralY(),
                        -1,-1,linesColor);
                drawLine((calendar.get(Calendar.HOUR)+45)*30,(int)(radius/1.2),7,getCentralX(),getCentralY(),
                        -1,-1,linesColor);
                drawClockFace(radius);
            }
            private void drawLine(double degree,int length,int width,double startPosX,double startPosY,
                                  double endPosX,double endPosY,Color color)
            {
                if(color==null)
                    color=Color.BLACK;
                if(endPosX==-1)
                    endPosX=getXOnCircle(degree,length);
                if(endPosY==-1)
                    endPosY=getYOnCircle(degree,length);
                Line line=new Line(startPosX,startPosY,endPosX,endPosY);
                line.setStrokeWidth(width);
                line.setStroke(color);
                Platform.runLater(()->pane.getChildren().addAll(line));
            }
            private double getXOnCircle(double degree,double radius)
            {
               return radius*Math.cos(degree*Math.PI/180)+getCentralX();
            }
            private double getYOnCircle(double degree,double radius)
            {
               return radius*Math.sin(degree*Math.PI/180)+getCentralY();
            }
            private double getCentralX()
            {
                return pane.getWidth()/2;
            }
            private double getCentralY()
            {
                return pane.getHeight()/2;
            }
            private void drawClockFace(double radius)
            {
                for(int i=0;i<360;i+=6)
                {
                    Circle circle=new Circle(getXOnCircle(i,radius),getYOnCircle(i,radius),radius/50);
                    circle.setStroke(Color.web("#D1FA00"));
                    circle.setFill(Color.web("#D1FA00"));
                    Platform.runLater(()->pane.getChildren().addAll(circle));
                }
                for(int i=0;i<12;i++) {
                    Text textNumber;
                    if(i!=0)
                        textNumber=new Text(String.valueOf(i));
                    else
                        textNumber=new Text(String.valueOf(12));

                    double fontSize=getCentralX()/6;
                    textNumber.setStyle("-fx-font: "+fontSize+" arial;");
                    textNumber.setX(getXOnCircle(i*30-90,radius-fontSize)-fontSize/3.5);
                    textNumber.setY(getYOnCircle(i*30-90,radius-fontSize));
                    textNumber.setFill(Color.web("#00FAA6"));
                    Platform.runLater(()->pane.getChildren().addAll(textNumber));
                }
                for(int i=0;i<60;i++)
                {
                    Text textMinutesNumber;
                    textMinutesNumber=new Text(String.valueOf(i));
                    double fontSize=getCentralX()/16;
                    textMinutesNumber.setStyle("-fx-font: "+fontSize+" arial;");
                    textMinutesNumber.setX(getXOnCircle(i*6-90,radius+fontSize)-fontSize/2.5);
                    textMinutesNumber.setY(getYOnCircle(i*6-90,radius+fontSize)+fontSize/2.5);
                    textMinutesNumber.setFill(Color.web("#00FAA6"));
                    Platform.runLater(()->pane.getChildren().addAll(textMinutesNumber));
                }
            }
        }, 0, 1000);
    }
}
