package sample;

import sun.misc.IOUtils;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class Automat {
    enum STAGES {
        OFF, WAIT,ACCEPT, CHECK, COOK}
    private int cash;
    private ArrayList<String> menu = new ArrayList<String>();
    int[] prices;
    private STAGES stage;

    Automat(){
        //download menu
       /* try{ //in the first time I wrote like that
           // URL resource= Automat.class.getResource("/menu.txt");
           // File file = Paths.get(resource.toURI()).toFile();
           // FileReader fr = new FileReader(file);

            //for jar need to read like this, but we into constructor(static method,
            // toString() - not static!!!
            InputStream inputStream=getClass().getResourceAsStream("/menu.txt");
            String fr= (String) IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fr);
            String line=null;

            while ((line=reader.readLine()) != null) {
                menu.add(line);
            }
        }
        catch (URISyntaxException e) {
            e.printStackTrace(); }
        catch (FileNotFoundException e){
            e.printStackTrace(); }
        catch (java.io.IOException e){
            e.printStackTrace(); }
            */
       //I am forced to initialize like this
        menu.add("  Expresso");
        menu.add(" Americano");
        menu.add("Cappuccino");
        menu.add(" Mokachino");
        menu.add("   Dalgona");
        prices = new int[]{30,35,45,40,45}; //array initialization
        stage = STAGES.OFF; //ready for work!
    }

    public int getCash(){
        return cash;
    }

    public STAGES on(){
        if (stage == STAGES.OFF)
            stage = STAGES.WAIT;

        return stage;
    }

    public STAGES getStage(){
        return stage;
    }

    public ArrayList<String> getMenu(){
        ArrayList<String> theMenu = new ArrayList<String>();
        if (stage == STAGES.WAIT || stage == STAGES.ACCEPT)
            for (int i = 0; i < menu.size(); i++) {
                theMenu.add(menu.get(i) + "   " + prices[i] + " rub.");
            }

        return theMenu;
    }

    public int coin(int coins){
        if (stage == STAGES.WAIT || stage == STAGES.ACCEPT) {
            stage = STAGES.ACCEPT;
            cash=cash+coins;
        }

        return cash;
    }

    public boolean choice(int numberCoffee){

        if (stage == STAGES.ACCEPT) {
            stage = STAGES.CHECK;
            //System.out.println("Choose coffee from the menu."); //can to be, but we have args of this method

            if(check(prices[numberCoffee])) { //check money
                cook(numberCoffee);
                return true;}

            else //if cash<price
                cancel(true); //go to WAIT
        }

        return false;
    }
    protected boolean check(int priceCoffee){
        if (stage == STAGES.CHECK) {
            if((cash-priceCoffee)>=0){
                cash=cash-priceCoffee; //for surrender
                return true;
            }
        }

        return false;
    }

    protected STAGES cook(int numberCoffee){
        if (stage == STAGES.CHECK) {
            stage = STAGES.COOK;
            //System.out.println("Making " + menu.get(numberCoffee) + " begins.");
            //System.out.println("Expect...");
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            //System.out.println("Coffee tree for your coffee has already been planted.");
            //System.out.println("It remains to wait for the first grains...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            //System.out.println("This is a joke)))");
            finish(numberCoffee);
        }

        return stage;
    }

    protected int finish(int numberCoffee){
        if (stage == STAGES.COOK) {
            stage = STAGES.WAIT;
            if(cash>0) //for surrender
                cash=0;//System.out.println("Take your remaining money: "+cash+" rub.");
        }

        return cash;
    }


    public int cancel(boolean leaving){
        if (stage == STAGES.ACCEPT || stage == STAGES.CHECK || stage == STAGES.WAIT) {
            stage = STAGES.WAIT;
            if((cash>0)&& !leaving) //for surrender in this method too, because user can not want coffee
                cash=0;//System.out.println("Take your remaining money: "+cash+" rub.");
        }

        return cash;
    }

    public STAGES off(){
        if (stage == STAGES.WAIT)
            stage = STAGES.OFF;

        return stage;
    }
}