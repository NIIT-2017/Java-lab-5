package Avtomat.controller;

import Avtomat.model.Drink;
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


public class Automat implements Runnable{


    //Members
    enum States {OFF, WAIT,CHECK,COOK}
    private int compliteDrinkIndex;
    private States stateAutomat;
    private double cash;
    private double money;
    private int indexSelectedDrink;
    private ArrayList<Drink> drinks;

    //Methods
    public Automat(InputStream stream) {
        this.stateAutomat = States.OFF;
        cash = 0.0f;
        money = 0.0f;
        drinks = new ArrayList<Drink>();
        indexSelectedDrink=0;
        readFromXML(stream);
    }

    @Override
    public void run() {
        while(true){
            switch (stateAutomat){
                //waiting
                case WAIT:  break;
                //checking
                case CHECK: checkMoney();   break;
                //cooling drink
                case COOK:  cookDrink();    break;
                default:    break;
            }
        }
    }

    public void readFromXML(InputStream file){
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

    //метод переводит автомат в состояние on
    void on(){
        //включение возможено только из off

        if (stateAutomat == States.OFF) stateAutomat = States.WAIT;

    }

    //метод переводит автомат в состояние off
    void off() {
        //выключение только из состояния wait

        if (stateAutomat == States.WAIT) stateAutomat = States.OFF;

    }

    int getCompliteDrinkIndex() {
        return compliteDrinkIndex;
    }

    double getMoney() {
        return money;
    }

    double getCash() {
        return cash;
    }

    String getStateAutomat() {
        return stateAutomat.toString();
    }

    public ArrayList<Drink> getDrinks() {
        return drinks;
    }

    //выбор напитка
    void choiceDrink(int drink){
        if(stateAutomat ==States.WAIT) {
            indexSelectedDrink = drink;
            stateAutomat = States.CHECK;
            work();
        }
    }

    private void pay( double price){
        if (stateAutomat ==States.CHECK){
            //вход в состояние готовки только из состояния check
            cash-=price;
            money+=price;
            stateAutomat =States.COOK;
        }
        work();
    }

    //метод внесения денег на счет в автомате
    void coin(double cash){
        if (cash>0.0) {
            //занесение денег только из состояния check
            if (stateAutomat == States.CHECK) this.cash += cash;
            work();
        }
    }

    //напиток приготовлен
    private void complite() {
        compliteDrinkIndex = indexSelectedDrink;
        stateAutomat =States.WAIT;
    }
    //возвращает деньги со счета
    double returnMoney() {
        double result=cash;
        cash=0.0f;
        return result;
    }

    //цыкл работы автомата
    private void work() {
        switch (stateAutomat){
            //waiting
            case WAIT:  break;
            //checking
            case CHECK: checkMoney();   break;
            //cooling drink
            case COOK:  cookDrink();    break;
            default:    break;
        }
    }
    private void checkMoney(){
        //проверить хватает ли на счете денег
        double price =drinks.get(indexSelectedDrink).getCost();
        if (price <= cash) pay(price);
    }

    private void cookDrink(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        complite();
    }

}
