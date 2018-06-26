package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Menu
{
    public static String getTitle(int i) {
        return menu.drinks.get(i).getTitle();
    }
    public static double getPrice(int i) {
        return menu.drinks.get(i).getPrice();
    }
    public static int getSize() {
        return menu.drinks.size();
    }
    public static void load() {
        menu = new Drinks();
        menu.loadDrinks();
    }
    private static Drinks menu;
    private static class Drinks
    {
        private Drinks() {
            drinks = new ArrayList<Drink>();
        }
        private class Drink
        {
            private String title;
            private double price;
            private Drink (String title, double price) {
                this.title = title;
                this.price = price;
            }
            private String getTitle() {
                return title;
            }
            private double getPrice() {
                return price;
            }
        }
        private void loadDrinks() {
            try {
                FileReader fr = new FileReader("src/sample/Menu.txt");
                Scanner scan = new Scanner(fr);
                while(scan.hasNextLine()) {
                    String strVal = scan.nextLine();
                    Pattern p = Pattern.compile("^\'([A-Za-z0-9]+\\s+[A-Za-z0-9]+)\'\t+\'([0-9]+[.]?[0-9]+)\'.*$");
                    Matcher m = p.matcher(strVal);
                    if (m.matches()) {
                        String title = m.group(1);
                        double price = Double.parseDouble(m.group(2));
                        if (checkDrinkData(title, price)) {
                            newDrink(title, price);
                        }
                    }
                }
                scan.close();
            }
            catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        private boolean checkDrinkData(String title, double price) {
            boolean checkData;
            checkData = notNullAndNotEmpty(title);
            checkData &= moreZero(price);
            return checkData;
        }
        private boolean notNullAndNotEmpty(String strVal) {
            return (strVal != null && !strVal.equals(""));
        }
        private boolean moreZero(double val) {
            return (val > 0);
        }
        private void newDrink(String title, double price) {
            drinks.add(new Drink(title, price));
        }
        private ArrayList<Drink> drinks;
    }
}
