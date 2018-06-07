package Avtomat.controller;

import Avtomat.model.Drink;
import javafx.application.Platform;
import javafx.beans.property.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.*;


public class Automat extends Thread{

    //Members
    enum States {OFF, WAIT,CHECK,COOK}
    private int compliteDrinkIndex;
    private States stateAutomat;
    private double cash;
    private double money;
    private int indexSelectedDrink;
    private ArrayList<Drink> drinks;
    private StringProperty message;
    private DoubleProperty percents;
    //Methods
    public Automat(InputStream stream) {
        this.stateAutomat = States.OFF;
        cash = 0.0f;
        money = 0.0f;
        drinks = new ArrayList<Drink>();
        indexSelectedDrink=0;
        readFromXML(stream);
        message = new SimpleStringProperty("автомат выключен");
        percents = new SimpleDoubleProperty();
        setDaemon(true);
    }

    @Override
    public void run() {
        while (true) {
            work();
            System.out.println(stateAutomat);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //метод переводит автомат в состояние on
    public void on(){
        //включение возможено только из off

        if (stateAutomat == States.OFF) {
            stateAutomat = States.WAIT;
            message.setValue("automat on");
        }
    }

    //метод переводит автомат в состояние off
    public void off() {
        //выключение только из состояния wait

        if (stateAutomat == States.WAIT) {
            stateAutomat = States.OFF;
            message.setValue("automat off");
        }

    }

    //выбор напитка
    public void choiceDrink(int drink){
        if(stateAutomat ==States.WAIT) {
            indexSelectedDrink = drink;
            stateAutomat = States.CHECK;
            message.setValue(drinks.get(drink).getDrink());
        }
    }

    private void pay( double price){
        if (stateAutomat ==States.CHECK){
            //вход в состояние готовки только из состояния check
            cash-=price;
            money+=price;
            stateAutomat =States.COOK;
        }
    }

    //метод внесения денег на счет в автомате
    public void coin(double cash){
        if (cash>0.0) {
            //занесение денег только из состояния check
            if (stateAutomat == States.CHECK) this.cash += cash;
        }
    }

    //напиток приготовлен
    private void complite() {
        compliteDrinkIndex = indexSelectedDrink;
        stateAutomat =States.WAIT;
    }

    //возвращает деньги со счета
    public double returnMoney() {
        double result=cash;
        cash=0.0f;
        return result;
    }

    //цыкл работы автомата
    private void work() {
        switch (stateAutomat){
            //waiting
            case WAIT:  {

                Platform.runLater(() -> message.setValue("Выберите напиток"));
            }
            break;
            //checking
            case CHECK: {
                checkMoney();
                Platform.runLater(() -> message.setValue("Внесите деньги"));
            }   break;
            //cooling drink
            case COOK:  {
                cookDrink();
                Platform.runLater(() -> message.setValue("Напиток в производстве"));
            }    break;
            default:    break;
        }
    }

    private void checkMoney(){
        //проверить хватает ли на счете денег
        double price =drinks.get(indexSelectedDrink).getCost();
        if (price <= cash) pay(price);
    }

    private void cookDrink(){
        if (percents.getValue()>=1.0) {
            percents.setValue(0.0);
            Platform.runLater(() -> message.setValue("напиток готов"));
            complite();
        }
        else percents.setValue(percents.getValue()+0.1);
    }

    private void readFromXML(InputStream file){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            NodeList nodeList = doc.getElementsByTagName("drink");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType()==Node.ELEMENT_NODE)
                {
                    Element element = (Element) node;
                    String name=element.getElementsByTagName("name").item(0).getTextContent();
                    String cost=element.getElementsByTagName("cost").item(0).getTextContent();
                    this.drinks.add(new Drink(name,Double.parseDouble(cost)));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }

    //gets and sets

    int getCompliteDrinkIndex() {
        return compliteDrinkIndex;
    }

    double getMoney() {
        return money;
    }

    public double getCash() {
        return cash;
    }

    String getStateAutomat() {
        return stateAutomat.toString();
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty stringProperty() {
        return message;
    }

    public double getPercents() {
        return percents.get();
    }

    public DoubleProperty percentsProperty() {
        return percents;
    }
}
