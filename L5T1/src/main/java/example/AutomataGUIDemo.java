package example;
import java.util.ArrayList;
public class AutomataGUIDemo
{
    enum STATES {OFF, WAIT, ACCEPT, CHECK, COOK}
    private int cash;
    private int change;
    public void setMenu()
    {
        menu.add("Кофе");
        menu.add("Чай");
        menu.add("Крепкий кофе");
        menu.add("Чай с молоком");
        menu.add("Холодный чай");
        menu.add("Кофе с лимоном");
        menu.add("Вода");
    }
    public void setPrices()
    {
        prices.add(25);
        prices.add(25);
        prices.add(30);
        prices.add(40);
        prices.add(20);
        prices.add(40);
        prices.add(10);
    }
    private ArrayList<String> menu = new ArrayList<>();
    private ArrayList<Integer> prices = new ArrayList<>();
    private STATES state;
    private String message;
    public AutomataGUIDemo()
    {
        cash = 0;
        state = STATES.OFF;
    }
    public ArrayList<String> getMenu()
    {
        return menu;
    }
    public ArrayList<Integer> getPrices()
    {
        return prices;
    }
    public int getCash()
    {
        return cash;
    }
    public int getChange()
    {
        return change;
    }
    public STATES getState()
    {
        return state;
    }
    public String getMessage()
    {
        return message;
    }
    public void on()
    {
        if(state.equals(STATES.OFF))
        {
            message="Внесите нужную сумму!";
            state = STATES.WAIT;
        }
        else
            message="Ошибка!";
    }
    public void coin(int value)
    {
        if(state.equals(STATES.WAIT))
        {
            change=0;
            cash+=value;
            state=STATES.ACCEPT;
            message="Выберите напиток!";
        }
        else if(state.equals(STATES.ACCEPT))
        {
            cash+=value;
        }
        else
            message="Ошибка!";
    }
    public int choice(int numberOfButton)
    {
        int price=0;
        if(state.equals(STATES.ACCEPT))
        {
            price = prices.get(numberOfButton);
            state = STATES.CHECK;
        }
        else
            message="Ошибка!";
        return price;
    }
    public boolean check(int price)
    {
        boolean check = false;
        if(state.equals(STATES.CHECK))
        {
            if (cash<price)
            {
                cancel();
            }
            else
                {
                change=cash-price;
                cash=0;
                check=true;
            }
        }
        return check;
    }
    public void cancel ()
    {
        if(state.equals(STATES.ACCEPT) || state.equals(STATES.CHECK))
        {
            change = cash;
            cash = 0;
            state = STATES.WAIT;
            message="Отмена операции!";
        }
        else
            message="Ошибка отмены!";
    }
    public void cook ()
    {
        if(state.equals(STATES.CHECK))
        {
            state = STATES.COOK;
            finish();
        }
    }
    private void finish()
    {
        if(state.equals(STATES.COOK))
        {
            state = STATES.WAIT;
        }
    }
    public void off()
    {
        if(state.equals(STATES.WAIT))
        {
            state = STATES.OFF;
            message="Включите автомат!";
        }
        else
            message="Ошибка!";
    }
}