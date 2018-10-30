package sample;

import sample.Automata;

import java.util.Arrays;

public class AutomataDemo {
    public static void main(String[] args) {
        Automata a=new Automata(new String[]{"Coca-cola","Tea","Coffee"},new int[]{12,10,15});
        System.out.println(a.printState());
        System.out.println(a.on());
        System.out.println(a.printState());
        System.out.println(a.coin(100));
        System.out.println(Arrays.deepToString(a.printMenu()));
        System.out.println(a.choice(0));
        System.out.println(a.getCash());
        System.out.println(a.cancel());
        System.out.println(a.choice(0));
        System.out.println(a.printState());
        System.out.println(a.cook());
        System.out.println(a.printState());
        System.out.println(a.getCash());
        System.out.println(a.coin(18));
        System.out.println(a.choice(1));
        System.out.println(a.cook());
        System.out.println(a.finish());
        System.out.println(a.getCash());
        System.out.println(a.printState());

    }
}
