import AutomataDemo.Automata;
import AutomataDemo.STATES;
import org.json.JSONException;
import java.io.IOException;

import static org.junit.Assert.*;

public class AutomataTest {

    @org.junit.Test
    public void on() throws IOException, JSONException {
        Automata a = new Automata();
        a.on();
        assertEquals(a.getState(), STATES.ON);

        a.getMenu();
        a.on();
        assertEquals(a.getState(), STATES.WAIT);

        a.coin(100);
        a.on();
        assertEquals(a.getState(), STATES.ACCEPT);

        a.choice("Каппучино");
        a.on();
        assertEquals(a.getState(), STATES.WAIT);

        Float balance = a.getCash();
        boolean result = balance.equals(0.0f);
        assertEquals(result, true);

    }

    @org.junit.Test
    public void coin() throws IOException, JSONException {

        Automata a = new Automata();
        //state = OFF
        a.coin(100);
        assertEquals(a.getState(), STATES.OFF);
        Float balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);

        a.on();
        a.coin(100);
        assertEquals(a.getState(), STATES.ON);
        balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);

        a.getMenu();

        a.coin(100);
        assertEquals(a.getState(), STATES.ACCEPT);
        balance = a.getCash();
        assertEquals(balance.equals(100.0f), true);

        a.coin(100);
        balance = a.getCash();
        assertEquals(balance.equals(200.0f), true);
        assertEquals(a.getState(), STATES.ACCEPT);

    }

    @org.junit.Test
    public void choice() throws IOException, JSONException {
        Automata a = new Automata();

        a.choice("Каппучино");
        assertEquals(a.getState(), STATES.OFF);
        Float balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);

        a.on();
        a.choice("Каппучино");
        assertEquals(a.getState(), STATES.ON);
        balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);

        a.getMenu();
        a.choice("Каппучино");
        assertEquals(a.getState(), STATES.WAIT);
        balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);

        a.coin(100);
        assertEquals(a.getState(), STATES.ACCEPT);
        balance = a.getCash();
        assertEquals(balance.equals(100.0f), true);
        a.choice("Каппучино");
        assertEquals(a.getState(), STATES.WAIT);
        balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);


        a.coin(90);
        assertEquals(a.getState(), STATES.ACCEPT);
        balance = a.getCash();
        assertEquals(balance.equals(90.0f), true);
        a.choice("Каппучино");
        balance = a.getCash();
        assertEquals(balance.equals(90.0f), true);
        assertEquals(a.getState(), STATES.ACCEPT);
        a.cancel();

        a.coin(120);
        assertEquals(a.getState(), STATES.ACCEPT);
        balance = a.getCash();
        assertEquals(balance.equals(120.0f), true);
        a.choice("Каппучино");
        assertEquals(a.getState(), STATES.ACCEPT);
        balance = a.getCash();
        assertEquals(balance.equals(20.0f), true);

    }

    @org.junit.Test
    public void off() throws IOException, JSONException {
        Automata a = new Automata();

        a.on();
        assertEquals(a.getState(), STATES.ON);
        a.off();
        assertEquals(a.getState(), STATES.OFF);

        a.on();
        a.getMenu();
        assertEquals(a.getState(), STATES.WAIT);
        a.off();
        assertEquals(a.getState(), STATES.OFF);

        a.on();
        a.getMenu();
        a.coin(100);
        assertEquals(a.getState(), STATES.ACCEPT);
        Float balance = a.getCash();
        assertEquals(balance.equals(100.0f), true);

        a.off();
        assertEquals(a.getState(), STATES.OFF);
        balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);

    }

    @org.junit.Test
    public void getMenu() throws IOException, JSONException {
        Automata a = new Automata();
        a.getMenu();
        assertEquals(a.getState(), STATES.OFF);

        a.on();
        a.getMenu();
        assertEquals(a.getState(), STATES.WAIT);

        a.coin(100);
        a.getMenu();
        assertEquals(a.getState(), STATES.ACCEPT);

        a.choice("Каппучино");
        a.getMenu();
        assertEquals(a.getState(), STATES.WAIT);

    }

    @org.junit.Test
    public void cancel() throws IOException, JSONException {

        Automata a = new Automata();
        a.cancel();
        assertEquals(a.getState(), STATES.OFF);

        a.on();
        a.cancel();
        assertEquals(a.getState(), STATES.ON);

        a.getMenu();
        assertEquals(a.getState(), STATES.WAIT);
        a.cancel();
        assertEquals(a.getState(), STATES.ON);

        a.getMenu();
        a.coin(50);
        assertEquals(a.getState(), STATES.ACCEPT);
        Float balance = a.getCash();
        assertEquals(balance.equals(50.0f), true);
        a.cancel();
        assertEquals(a.getState(), STATES.WAIT);
        balance = a.getCash();
        assertEquals(balance.equals(0.0f), true);


    }
}