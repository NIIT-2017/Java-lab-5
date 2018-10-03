package AutomataGUIDemo;

import AutomatDemo.AutomatDemo;
import libs.gui.SwingConsole;
import libs.gui.Dialog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AutomataGUIDemo extends JFrame{
    private JPanel panel = new JPanel();
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();
    private JPanel menu = new JPanel();
    private JTextArea info = new JTextArea(5,15);
    private List<JButton> buttons = new ArrayList();
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JButton cancelButton = new JButton("Cancel");
    private JTextField coin = new JTextField(5);
    private JDialog dialog;
    private final String msg = "Положите деньги в автомат и выберите напиток.\n Принимаются монеты по 5, 10, а также купюры по 10, 50 и 100 рублей";
    private Thread t;

    final AutomatDemo automat = new AutomatDemo();

    private class Cook  implements Runnable{
       int price;

       public Cook(int price){
           super();
           this.price = price;
       }

        public void run() {
            try {
                info.setText("Идет приготовление");
                for (JButton b: buttons)
                    b.setEnabled(false);
                setCursor(new Cursor(Cursor.WAIT_CURSOR));
                TimeUnit.MILLISECONDS.sleep(10000);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                dialog = new Dialog(null,"Заберите напиток",200,100);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                if (price<automat.getCash()){
                    JDialog dialog2 = new Dialog(null,"Заберите сдачу "+(automat.getCash()-price)+" руб.", 200,100);
                    dialog2.setLocationRelativeTo(null);
                    dialog2.setVisible(true);
                }
                info.setText(msg);
                for (JButton b:buttons)
                    b.setEnabled(true);
                automat.finish();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                info.setText(msg);
                for (JButton b:buttons)
                    b.setEnabled(true);
                automat.cashReset();
                dialog = new Dialog(null,"Процесс прерван!",200,100);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                cancelButton.setFocusPainted(false);
            }
        }
    }

    public AutomataGUIDemo() throws InterruptedException {
        automat.on();
        setTitle("AutomataGUIDemo");
        setLayout(new GridLayout(1,2));
        info.setBorder(new TitledBorder("Info"));
        info.setLineWrap(true);
        info.setEditable(false);
        info.setText(msg);
        coin.setBorder(new TitledBorder("Coin"));

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if ((!(automat.getCash()==0)&&t==null)||(!(automat.getCash()==0)&&!t.isAlive())){
                dialog = new Dialog(null,"Заберите деньги "+automat.getCash()+" руб.", 200,100);
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
                    automat.cashReset();
                    info.setText(msg);
                } else
                    t.interrupt();
            }
        });

        coin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (automat.getState().equals("WAIT")||automat.getState().equals("ACCEPT")){
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                String s = coin.getText();
                int c = Integer.valueOf(s);
                try {
                    automat.textFieldInputCoin(c);
                    info.setText("На вашем балансе "+Integer.toString(automat.getCash())+" руб.");
                    coin.setText("");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                }
                }
            }
        });
        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p1.add(info);
        p2.add(coin);
        p2.add(cancelButton);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.add(p1);
        panel.add(p2);
        String s;

        for (int i=0; i<automat.getMenu().size();i++){
            s=automat.getMenu().get(i);
            buttons.add(new JButton(s));
        }
        int count=0;

        for (final JButton button: buttons){
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (automat.getState().equals("ACCEPT")){
                    String title = button.getText();
                    int price = automat.getMenuMap().get(title);
                    try {
                        automat.check();
                        if (price>automat.getCash()){
                            JDialog dialog = new Dialog(null,"Недостаточно средств! Положите еще "+(price-automat.getCash())+" руб.",300,100);
                            dialog.setLocationRelativeTo(null);
                            dialog.setVisible(true);
                            button.setFocusPainted(false);
                            automat.cashReset();
                            return;
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                        t = new Thread(new Cook(price));
                            t.setDaemon(true);
                            t.start();
                    }
                }
            });
            buttonGroup.add(button);
            menu.add(button);
            JTextField txt = new JTextField(Integer.toString(automat.getPrice().get(count))+" руб.");
            txt.setEditable(false);
            menu.add(txt);
            count++;
        }
        menu.setLayout(new GridLayout(buttons.size(),2));
        menu.setBorder(new TitledBorder("Menu"));
        add(panel,BorderLayout.NORTH);
        add(menu,BorderLayout.SOUTH);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

    }
    public static void main(String[] args) throws InterruptedException {

        SwingConsole.createJFrame(new AutomataGUIDemo(),370,250);

    }
}
