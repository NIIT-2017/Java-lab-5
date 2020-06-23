package Methods;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.*;

public class Automata {

    private enum States {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }

    private Integer cash;
    private HashMap<String,Integer> Menu = new HashMap<>();
    private States state;

    public Automata(String menu) {
        cash = 0;
        state = States.OFF;
        try {
            URL resource = getClass().getResource(menu);
            File file = Paths.get(resource.toURI()).toFile();
            String content = FileUtils.readFileToString(file, "utf-8");
            JSONArray menu1 = new JSONArray(content);
            for (int i = 0;i<menu1.length();i++){
                JSONObject menu2 = menu1.getJSONObject(i);
                Menu.put(menu2.getString("name"),menu2.getInt("price"));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String on() {
        if (state == States.OFF) {
            state = States.WAIT;
        }
        return state.toString();
    }
    public String off() {
        if (state == States.WAIT) {
            state = States.OFF;
        }
        return state.toString();
    }

    public Integer getPrice (String name){
        int i = Menu.get(name);
        return Menu.get(name);
    }
    public String coin(int coin) {
        if ((state == States.WAIT || state == States.ACCEPT || state == States.CHECK)&& coin>0) {
            state = States.ACCEPT;
            cash = cash + coin;
            return Integer.toString(cash);
        } else return "Операция невозможна!";
    }


    public String getState() {
        return state.toString();
    }
    private boolean check(Integer price){
        if (cash>= price)
            return true;
        else
            return false;
    }
    private String cook(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state = States.COOK;
        return state.toString();
    }
    public String finish(String surrender){
        if (state == States.WAIT){
            if(surrender.equals("YES")&& cash%5 == 0){
                state = States.WAIT;
                int money = cash;
                cash = 0;
                return "Ваша сдача - "+money;
            }
            else {
                state = States.WAIT;
                cash = 0;
                return "Благодарим за покупку!";
            }
        }
        else return "Операция невозможна!";
    }
    public String cancel(){
        if (state == States.ACCEPT || state == States.COOK){
            state = States.WAIT;
            return state.toString();
        }
        else return "Операция невозможна!";
    }
    public Integer checkcash (){
        if (state!=States.OFF){
            return cash;
        }
        else return 0;
    }
    public String choise(String name){
        if (state == States.ACCEPT||state == States.WAIT){
            if (Menu.containsKey(name)){
                state = States.CHECK;
                if(check(Menu.get(name))){
                    cash=cash-Menu.get(name);
                    return cook();
                }
                else{
                    return "Недостаточно средств на счете!";
                }
            }
            else{
                return "Выбранного вами напитка не существует";
            }
        }
        else return "Операция невозможна!";
    }
}