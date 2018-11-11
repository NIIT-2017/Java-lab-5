package sample;

import java.util.*;


class Automat {

    private static String choice;
   // private static int index;
   private static int  index;


    enum States {
        ON, OFF, PRINTMENU, CHOICE, CANCEL, COOK, WAIT
    }

    private static int Cash;
    private static int DrinkSelection;
    private static int res;
    private static int EnCook;
    private static int CookFinal;
    private static Deque<String> Result = new ArrayDeque<String>();
    private static int FlagPower;
    private Map<String,Integer> menu = new HashMap<String, Integer>(){
        {
            put("Молоко", 37);
            put("Вода", 50);
            put("Кофе", 83);
            put("Сок", 20);
            put("Пиво", 130);
        }
    };

   Automat(){

        }

    public void coin(int cash) {
        Cash +=cash;
//return Cash;
    }


  /*  public static Object choice(Map<String,Integer> choice,int index){
        return (String)choice.get( (choice.keySet().toArray())[ index ] );}*/
    public String choice(Map<String,Integer> choice,int index)
    {
        Object key = choice.keySet().toArray()[index];
       // Object key = keys[4];
        return (String) key;
    }
    public int check(int p){
        int k = p-Cash;
        if (k <= 0) Result.offer("Нажмите \"Приготовить\"");
        else Result.offer("Недостаточно денег, внесите еще");
        return k;
    }

    public ArrayList<String> printMenu(Map<String,Integer> menu) {
        ArrayList<String> s = new ArrayList<String>();
        for (Map.Entry<String,Integer> entry: menu.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            s.add(key+ " "+value);
            Result.offer(String.format("%s : %s", key, value));
        }
return s;
    }

public Deque<String> getResult(){
        return Result;
}

    public int getEnCook(){
        return EnCook;
    }

    public int setIndex(int i){
        index=i;
        return index;
    }

    public int getCash(){
        return Cash;
    }

    public int setFlagPower(int i){
                FlagPower=i;
                return FlagPower;
    }

    public void printState(String result){
        Result.offer(result);
    }

    public void automatStates(States state) {


        switch (state) {
            case ON:
                if (FlagPower == 0) {
                    FlagPower = 1;
                    printState("Автомат включен");
                    automatStates(States.WAIT);
                } else
                    printState("Автомат уже включен");
                break;
            case OFF:
                FlagPower = 0;
                res = 0;
                printState("Автомат выключен");
                break;
            case PRINTMENU:
                if (FlagPower == 1) {
                    printMenu(menu);
                    printState("Выберите напиток");
 //                   automatStates(States.CHOICE);
                } else printState("Автомат выключен");
                break;
            case CHOICE:
                if (FlagPower == 1) {
                    //menu.get(4);
                    choice = choice(menu,index);
                    int price = menu.get(choice);
                    DrinkSelection = 1;
                    printState("Вы выбрали: " + choice + " за " + price);
                    res = check(price);
                    if (res <= 0) {
                        EnCook = 1;
                        } else automatStates(States.WAIT);
                } else printState("Автомат выключен");
                break;
            case CANCEL:
                if (FlagPower == 1) {
                    printState("Ваша сдача: " + Cash);
                    DrinkSelection = 0;
                    automatStates(States.WAIT);
                }
                else printState("Автомат выключен");
                break;
            case COOK:
                if ( FlagPower == 1) {
                    if ((EnCook == 1) && (DrinkSelection == 1)) {
                //        printState("Приготовление напитка");
                        printState("Напиток " + choice + " готов. Ваша сдача: " + res);
                        CookFinal = 1;
                        automatStates(States.WAIT);
                    } else printState("Вы забыли внести деньги");
                }
                else printState("Автомат выключен");
                break;
            case WAIT:
                if (FlagPower == 1) {
                    if (DrinkSelection == 0)
                        Cash = 0;
                    if (CookFinal == 1) {
                        DrinkSelection = 0;
                        CookFinal = 0;
                        Cash = 0;
                    }
                    EnCook = 0;
                    printState("Режим ожидания");
                }
                else printState("Автомат выключен");
                break;

        }

    }
}

public class DemoAutomat{
    public static String [] s;//= {"ON", "200", "PRINTMENU", "COOK","10", "120","PRINTMENU", "COOK", "OFF"};
    public static String k="";
    public static void main(String[] args){
        Automat a = new Automat();
int cash=0;

            for (int i=0; i<s.length;i++) {
                k = s[i];
                int myInt = checkString(k) ? 1 : 0;
                if (myInt == 1) {
                a.coin(Integer.parseInt(k));
                 //   System.out.println("На счету: " + a.Cash + ". При необходимости внесите еще.");
                    a.printState("На счету: "  + a.getCash() + ". При необходимости внесите еще.");
                } else {
                    Automat.States readline = Automat.States.valueOf(k);
                    a.automatStates(readline);
                //    while(Automat.Result.size() != 0)   {
                 //       System.out.println(Automat.Result.pop());
                   // }
                }
            }

    }

    public static boolean checkString(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
