package Automata;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Automata {
    enum STATES{Off,Wait,Accept,Check,Cook};
    private int cash=0;
    private int drinkIndex;
    private ArrayList<String> menu;
    private ArrayList<Integer> prices;
    private STATES state=STATES.Off;
    private Boolean checkResult=false;
    private  String nameOfMenuFile;
    private File fileMenu=null;
    private  Exception exception=null;
    public  Automata(String nameOfMenuFile)
    {
        this.nameOfMenuFile=nameOfMenuFile;
    }
    private void loadMenuFromTXT()
    {
        try
        {
            FileReader fileReader = new FileReader(fileMenu);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            menu=new ArrayList<String>();
            prices=new ArrayList<Integer>();
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] splitedLine = line.split(" ");
                String name = splitedLine[0];
                menu.add(name);
                int price = Integer.parseInt(splitedLine[1]);
                prices.add(price);
            }
            if(menu.size()==0)
                throw new Exception("File is empty");
            if(prices.size()!=menu.size())
                throw  new Exception("File is incomplete");

        }
        catch (Exception ex)
        {
            exception=ex;
        }
    }
    private  void  loadMenuFromJson()
    {
        menu=new ArrayList<>();
        prices=new ArrayList<>();
        try {
                JSONParser parser = new JSONParser();
                JSONObject jsonObject=(JSONObject) parser.parse(new FileReader(fileMenu));
                JSONArray jsonArray =(JSONArray) jsonObject.get("Drinks");
                for(int i=0;i<jsonArray.size();i++)
                {
                    menu.add(((JSONObject)jsonArray.get(i)).values().toArray()[1].toString());
                    prices.add(Integer.parseInt(((JSONObject)jsonArray.get(i)).values().toArray()[0].toString()));
                }
            }
        catch (Exception ex)
        {
            exception=ex;
        }
    }
    private  boolean isFileJson(String nameOfMenuFile)
    {
        if(nameOfMenuFile.charAt(nameOfMenuFile.length()-1)=='n')
            return true;
        else
            return  false;
    }
    private  void loadMenu()
    {
        try
        {
            ClassLoader classLoader= Thread.currentThread().getContextClassLoader();
            fileMenu = Paths.get(classLoader.getResource(nameOfMenuFile).toURI()).toFile();
            if(isFileJson(nameOfMenuFile))
                loadMenuFromJson();
            else
                loadMenuFromTXT();
        }
        catch (Exception ex)
        {
            exception=ex;
            return;
        }

    }
    public void on()
    {
        try
        {
            loadMenu();
            if(state==STATES.Off)
            {
                state=STATES.Wait;
            }
        }
        catch (Exception ex)
        {
        }
    }
    public  void off()
    {
        if(state==STATES.Wait)
        {
            state=STATES.Off;
        }
    }
    public void coin(int money)
    {
        if(state==STATES.Wait||state==STATES.Accept)
        {
            cash += money;
            state=STATES.Accept;
        }
    }
    public ArrayList<String> getMenu()
    {
        if(state!=STATES.Off)
        {
            ArrayList<String> menuList = new ArrayList<>();
            for (int i = 0; i < menu.size(); i++) {
                menuList.add(menu.get(i)+" : "+prices.get(i).toString());
            }

        return  menuList;
        }
        return  null;
    }
    public STATES getState()
    {
        return  state;
    }
    public void choice(String name)
    {
        checkResult=false;
        for(int i=0;i<menu.size();i++)
        {
            if(!(name==menu.get(i)))
            {
                checkResult = check(i);
                drinkIndex=i;
                break;
            }
        }
    }
    private Boolean check(int nameIndex)
    {
        state=STATES.Check;
        if(prices.get(nameIndex)<=cash)
            return  true;
        else
            return  false;
    }
    public int cancel()
    {int tmpCash=0;
        if(state==STATES.Accept||state==STATES.Check)
        {
            state=STATES.Wait;
            tmpCash=cash;
            cash = 0;
        }
        return  tmpCash;
    }
    public  Drink cook( )
    {
        if(drinkIndex>-1)
        {
            state = STATES.Cook;
            cash -= prices.get(drinkIndex);
            return new Drink(menu.get(drinkIndex));
        }
        else
            return null;
    }
    public void finish()
    {
        state=STATES.Wait;
    }
    public int returnMoney()
    {
        int money=cash;
        cash=0;
        return money;
    }
    public int getCash() {
        return cash;
    }
    public void choice(int index)
    {
        if(check(index))
            drinkIndex=index;
        else
            drinkIndex=-1;
    }
}
