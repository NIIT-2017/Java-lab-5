package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Logger
{
    public static void init() {
        fLog = new File("src/sample/Log.txt");
        sLog = new File("src/sample/sLog.txt");
        log = "";
        fAppend = false;
        sAppend = false;
    }
    public static void show(String message) {
        log += message;
        writeToFile(fLog, message, fAppend);
        print(message);
        if (!fAppend) {
            fAppend = true;
        }
    }
    public static void toSystemLog(String message) {
        writeToFile(sLog, message, sAppend);
        if (!sAppend) {
            sAppend = true;
        }
    }
    private static void writeToFile(File file, String message, boolean append) {
        try {
            FileWriter wr = new FileWriter(file, append);
            String[] strArr = message.split("\n");
            for(String strVal : strArr) {
                wr.write(strVal);
                wr.write(System.lineSeparator());
            }
            wr.close();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    private static File fLog;
    private static File sLog;
    private static String log;
    private static boolean fAppend;
    private static boolean sAppend;
    private static void print(String string) {
        System.out.print(string);
    }
}
