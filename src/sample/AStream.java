package sample;

public class AStream
{
    public enum CType {
        DOUB, INT, STR, TMP
    }
    public static class CParam
    {
        public String name;
        public CType type;
        public String value;
        public CParam(String name, CType type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }
    }
    public static void init() {
        inQueue = null;
        outQueue = null;
    }
    public static void print(CParam param) {
        outQueue = param;
    }
    public static CParam read() {
        CParam tmpQueue;
        bReadStarted = true;
        while(inQueue == null && !Main.isExitStat()) {
            delay(1);
        }
        synchronized (inQueue) {
            tmpQueue = inQueue;
            inQueue = null;
        }
        bReadStarted = false;
        return tmpQueue;
    }
    public static void sendForAutoInput(CParam param) {
        inQueue = param;
    }
    public static CParam getFromAutoOutput() {
        CParam tmpQueue;
        bReadStarted = true;
        while(outQueue == null && !Main.isExitStat()) {
            delay(1);
        }
        synchronized (outQueue) {
            tmpQueue = outQueue;
            outQueue = null;
        }
        bReadStarted = false;
        return tmpQueue;
    }
    public static boolean onReadStarted() {
        return bReadStarted;
    }
    private static volatile boolean bReadStarted;
    private static volatile CParam inQueue;
    private static volatile CParam outQueue;
    private static void delay(int quantity) {
        try {
            Thread.sleep(100 * quantity);
        }
        catch (InterruptedException e) {
        }
    }
    public static void exit() {
        System.exit(-1);
    }
}
