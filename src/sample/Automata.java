package sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Automata extends Thread
{
    private boolean workStat;
    private State state;
    private Command command;
    private double cash;
    private double coin;
    private int drinksNum;
    private double change;
    public Automata () {
        this(true, State.OFF, Command.NULL, 0);
    }
    private Automata (boolean workStat, State state, Command command, double cash) {
        this.workStat = workStat;
        this.state = state;
        this.command = command;
        this.cash = cash;
    }
    public enum State {
        OFF, WAIT, ACCEPT, CHECK, COOK
    }
    public enum Command {
        NULL, START, COIN, DRINK, CHANGE, CHECKTRUE, CHECKFALSE, COOKFINISH, EXIT
    }
    private void coin() {
        cash += coin;
        delay(3);
        comForAStream("MESSAGE", AStream.CType.STR, "Внесена сумма: " + doubStrForm(coin) + " руб.><" + cash + "");
        delay(7);
        coin = 0;
    }
    private void drink() {
        comForAStream("MESSAGE", AStream.CType.STR, "Выбран напиток: " + Menu.getTitle(drinksNum) + ".><" + cash + "");
        delay(3);
    }
    private void change() {
        change = cash;
        cash = 0;
        comForAStream("MESSAGE", AStream.CType.STR, "Сдача к выдаче: " + doubStrForm(change) + " руб.><" + cash + "");
        delay(7);
    }
    private void end() {
        if (state == State.WAIT) {
            comForAStream("MESSAGE", AStream.CType.STR, "Автомат выключен.><" + cash + "");
            workStat = false;
        }
    }
    private void waitCom() {
        AStream.CParam param;
        boolean flag = false;
        while(!flag && workStat && !Main.isExitStat()) {
            if ((param = AStream.read()) != null) {
                if (userComEquals(param)) {
                    flag = true;
                }
            }
        }

    }
    private boolean userComEquals(AStream.CParam param) {
        boolean res = false;
        Command comName = Automata.Command.valueOf(param.name);
        switch(state) {
            case WAIT:
                switch(comName) {
                    case COIN:
                        coin = Double.parseDouble(param.value);
                        coin();
                        res = true;
                        break;
                    case EXIT:
                        end();
                        res = true;
                        break;
                }
                break;
            case ACCEPT:
                switch(comName) {
                    case COIN:
                        coin = Double.parseDouble(param.value);
                        coin();
                        res = true;
                        break;
                    case DRINK:
                        drinksNum = Integer.parseInt(param.value);
                        drink();
                        res = true;
                        break;
                    case CHANGE:
                        change();
                        res = true;
                        break;
                }
                break;
        }
        if (res) {
            command = comName;
        }
        return res;
    }
    private void onStateOff() {
        comForAStream("MESSAGE", AStream.CType.STR, "Автомат включен.><" + cash + "");
        delay(5);
        command = Command.START;
    }
    private void onStateWait() {
        comForAStream("MESSAGE", AStream.CType.STR, "Внесите сумму...><" + cash + "");
        delay(5);
        waitCom();
    }
    private void onStateAccept() {
        comForAStream("MESSAGE", AStream.CType.STR, "Выберите напиток или добавьте сумму...><" + cash + "");
        delay(5);
        waitCom();
    }
    private void onStateCheck() {
        comForAStream("MESSAGE", AStream.CType.STR, "Проверка суммы.><" + cash + "");
        delay(5);
        double drinksPrice = Menu.getPrice(drinksNum);
        if (cash >= drinksPrice) {
            cash -= drinksPrice;
            comForAStream("MESSAGE", AStream.CType.STR, "Сумма к списанию: " + doubStrForm(drinksPrice) + ".><" + cash + "");
            delay(5);
            command = Command.CHECKTRUE;
        }
        else {
            comForAStream("MESSAGE", AStream.CType.STR, "Недостаточно средств.><" + cash + "");
            delay(5);
            command = Command.CHECKFALSE;
        }
    }
    private void onStateCook() {
        comForAStream("MESSAGE", AStream.CType.STR, "Приготовление " + Menu.getTitle(drinksNum) + " начато.><" + cash + "");
        delay(5);
        String p = "";
        for(int l=0; l < 30; l++) {
            p += ".";
            comForAStream("MESSAGE", AStream.CType.TMP, "Приготовление " + Menu.getTitle(drinksNum) + "." + p + "><" + cash + "");
            delay(3);
        }
        comForAStream("MESSAGE", AStream.CType.STR, "Приготовление " + Menu.getTitle(drinksNum) + " завершено.><" + cash + "");
        delay(5);
        command = Command.COOKFINISH;
    }
    private void delay(int quantity) {
        try {
            Thread.sleep(quantity * 100);
        }
        catch (InterruptedException e) {
        }
    }
    private String doubStrForm(double val) {
        return String.format("%(.2f", val);
    }
    private void comForAStream(String name, AStream.CType type, String value) {
        AStream.print(new AStream.CParam(name, type, value));
    }
    private void toSystemLogger() {
        DateTimeFormatter dateTimeForm = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        String message = "<" + LocalDateTime.now().format(dateTimeForm) + "> <state:" + state.toString() + "> <command:" + command.toString() + "> <cash:" + doubStrForm(cash) + ">\n";
        Logger.toSystemLog(message);
    }
    private void onStateChanged() {
        comForAStream("ONSTATE", AStream.CType.STR, state.toString());
        switch(state) {
            case OFF:
                onStateOff();
                break;
            case WAIT:
                onStateWait();
                break;
            case ACCEPT:
                onStateAccept();
                break;
            case CHECK:
                onStateCheck();
                break;
            case COOK:
                onStateCook();
                break;
        }
        toSystemLogger();
    }
    private void moveToNextState () {
        switch(state) {
            case OFF:
                switch(command) {
                    case START:
                        state = State.WAIT;
                        break;
                }
                break;
            case WAIT:
                switch(command) {
                    case COIN:
                        state = State.ACCEPT;
                        break;
                    case EXIT:
                        state = State.OFF;
                        break;
                }
                break;
            case ACCEPT:
                switch(command) {
                    case COIN:
                        state = State.ACCEPT;
                        break;
                    case DRINK:
                        state = State.CHECK;
                        break;
                    case CHANGE:
                        state = State.WAIT;
                        break;
                }
                break;
            case CHECK:
                switch(command) {
                    case CHECKFALSE:
                        state = State.ACCEPT;
                        break;
                    case CHECKTRUE:
                        state = State.COOK;
                        break;
                }
                break;
            case COOK:
                switch(command) {
                    case COOKFINISH:
                        state = State.ACCEPT;
                        break;
                }
                break;
        }
    }
    public void run() {
        while(workStat && !Main.isExitStat()) {
            onStateChanged();
            moveToNextState();
        }
    }
}
