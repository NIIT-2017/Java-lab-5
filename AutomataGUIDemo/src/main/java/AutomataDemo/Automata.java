package AutomataDemo;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Automata {
    private STATES state;
    private float cash;
    private static final HashMap<String, Float> menu = new HashMap<>();

    public Automata() throws IOException, JSONException {
        readJSON();
        state = STATES.OFF;
    }

    private static void readJSON() throws IOException, JSONException {
        File file = new File("menu.json");
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject jsonMenu = new JSONObject(content);
        JSONArray arrayMenu = jsonMenu.getJSONArray("menu");
        JSONArray arrayPrices = jsonMenu.getJSONArray("prices");
        for(int i = 0; i < arrayMenu.length(); i++){
            double temp = (double)arrayPrices.get(i);
            menu.put((String)arrayMenu.get(i), (float)temp);
        }
    }

    //возврат денежных средств (сдачи) покупателю
    private void refunds(float amount){
        if (cash >= amount)
            cash -= amount;
    }

    //списание цены за напиток
    private void writeoff(float amount){
        if (cash >= amount)
            cash -= amount;
    }

    private boolean check(float amount){
        return (cash >= amount)? true : false;
    }

    public void on(){
        if(state == STATES.OFF){
            state = STATES.ON;
        }
    }

    public void coin(float amount){
        if(state == STATES.WAIT || state == STATES.ACCEPT){
            cash += amount;
            state = STATES.ACCEPT;
        }
    }

    public void choice(String key){
        //выбор напитка покупателем
        if (state == STATES.ACCEPT){
            state = STATES.CHECK;
            float price = menu.get(key);
            if (check(price)){
                state = STATES.COOK;
                writeoff(price); //списана цена напитка
                if (cash > 0){
                    state = STATES.ACCEPT; //есть ещё средства, можно дополнить и купить что-нибудь ещё //вывести в интерфей информацию об остатке
                } else {
                    state = STATES.WAIT;
                }
            } else {
                state = STATES.ACCEPT;
            }
        }
    }
    
    public void off(){
        if (state == STATES.ON || state == STATES.WAIT)
            state = STATES.OFF;
        else if (state == STATES.ACCEPT){
            refunds(cash);
            state = STATES.OFF;
        }
    }

    public HashMap<String, Float> getMenu(){
        if (state == STATES.ON)
            state = STATES.WAIT;
        return menu;
    }

    public STATES getState(){
        return this.state;
    }

    public void cancel(){
        if (state == STATES.WAIT){
            state = STATES.ON;
        } else if (state == STATES.ACCEPT){
            this.state = STATES.WAIT;
            refunds(cash);
        }
    }

    public float getCash(){
        return cash;
    }
}
