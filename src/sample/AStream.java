package sample;

import java.util.ArrayList;

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
        queueAuto = new Queue();
        queueGUI = new Queue();
    }
    public static void print(CParam param) {
        queueGUI.setTo(param);
    }
    public static CParam read() {
        return queueAuto.getFrom();
    }
    public static void sendForAutoInput(CParam param) {
        queueAuto.setTo(param);
    }
    public static CParam getFromAutoOutput() {
        return queueGUI.getFrom();
    }
    public static boolean isEmpty() {
        boolean check = queueAuto.isEmpty();
        return check && queueGUI.isEmpty();
    }
    private static volatile Queue queueAuto;
    private static volatile Queue queueGUI;
    private static class Queue
    {
        private Queue() {
            init();
        }
        private void init() {
            queue = new ArrayList<CParam>();
        }
        private void setTo(CParam param) {
            if (queueNotNull()) {
                int size = queue.size();
                if (size == 0) {
                    queue.add(param);
                }
                else {
                    ArrayList<CParam> tmpQueue
                            = new ArrayList<CParam>(queue);
                    init();
                    queue.add(param);
                    queue.addAll(tmpQueue);
                }
            }
        }
        private CParam getFrom() {
            CParam result = null;
            if (queueNotNull()) {
                int size = queue.size();
                if (size > 0) {
                    synchronized (queue) {
                        result = queue.get(size - 1);
                        queue.remove(size - 1);
                    }
                }
            }
            return result;
        }
        private boolean isEmpty() {
            return queue.size() == 0;
        }
        private boolean queueNotNull() {
            return queue != null;
        }
        private ArrayList<CParam> queue;
    }
}
