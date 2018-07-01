package sample;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Clock extends JComponent
{
    public static void main(String[] args) {
        Clock clock = new Clock();
        JFrame jFrame = new JFrame("Clock");
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
        jFrame.setBounds(75, 25, 250, 250);
        jFrame.setMinimumSize(new Dimension(250, 250));
        jFrame.add(clock);
        jFrame.show();
        clock.refresh();
    }
    private Clock() {
        setImages();
        (new Thread()).start();
    }
    private static final String dirImages = "CImages/";
    private static final String path = "src/" + "sample/" + dirImages;
    private void setImages() {
        background = loadImage(path + "CBGround.jpg");
        handHour = getImage("HHour.jpg");
        handMin = getImage("HMin.jpg");
        handSec = getImage("HSec.jpg");
        divHour = getImage("DHour.jpg");
        divMin = getImage("DMin.jpg");
        divSec = getImage("DSec.jpg");
    }
    private Image getImage(String name) {
        return getTransparentImage(loadImage(path + name));
    }
    private void refresh() {
        try {
            for(;;) {
                Thread.sleep(1000);
                repaint();
            }
        }
        catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
    public void paint(Graphics graphics) {
        super.paint(graphics);

        graphics.drawImage(background, 0, 0, null);

        Graphics2D graphic = (Graphics2D) graphics;
        graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        size = getSize(size);
        graphic.translate((double)size.width / 2D,(double)size.height / 2D);

        int radius = Math.min(size.width, size.height) * 4 / 10;

        for(int j=0; j < 480; j++) {
            graphic.drawImage(divSec, radius, 0, radius * 3 / 40, radius * 7 / 400, null);
            graphic.rotate(6.0 * Math.PI / 180 / 8);
        }
        for(int j=0; j < 60; j++) {
            if ((j % 5) == 0) {
                graphic.drawImage(divHour, radius * 78 / 100, -radius * 3 / 100, radius * 18 / 40, radius * 25 / 400, null);
            }
            else {
                graphic.drawImage(divMin, radius * 93 / 100, -radius / 100, radius * 9 / 40, radius * 10 / 400, null);
            }
            graphic.rotate(6.0 * Math.PI / 180);
        }

        LocalTime time = LocalTime.now();

        long sec = time.getSecond();
        long min = time.getMinute();
        long hour = time.getHour();

        double secAng = -Math.PI / 2 + sec * 6.0 * Math.PI / 180;
        double minAng = -Math.PI / 2 + 6.0 * Math.PI / 180 * (min + sec / 60.0);
        double hourAng = -Math.PI / 2 + 30.0 * Math.PI / 180 * (hour + min / 60.0);

        graphic.rotate(hourAng);
        graphic.drawImage(handHour, -radius * 12 / 400, -radius * 19 / 400, radius * 80 / 100, radius / 10, null);
        graphic.rotate(-hourAng);

        graphic.rotate(minAng);
        graphic.drawImage(handMin, -radius * 22 / 400, -radius * 27 / 400, radius * 97 / 100, radius / 10, null);
        graphic.rotate(-minAng);

        graphic.rotate(secAng);
        graphic.drawImage(handSec, -radius * 11 / 400, -radius * 4 / 400, radius * 105 / 100, radius / 40, null);
        graphic.rotate(-secAng);
    }
    private Image loadImage(String pathName) {
        Image image = null;
        try {
            if (pathName != null) {
                image = ImageIO.read(new File(pathName));
            }
        }
        catch (IOException ex) {
            System.out.println("Can't read input file: \"" + pathName + "\".");
        }
        return image;
    }
    private Image getTransparentImage(Image image) {
        Color color = Color.WHITE;
        Image result = null;
        if (image != null) {
            class ColorFilter extends RGBImageFilter {
                private Color color;
                public ColorFilter(Color color) {
                    super();
                    this.color = color;
                }
                public int filterRGB(int x, int y, int rgb) {
                    if ((rgb | 0xFF000000) == (color.getRGB() | 0xFF000000)) {
                        return 0x00FFFFFF & rgb;
                    }
                    else {
                        return rgb;
                    }
                }
            }
            ColorFilter colorFilter = new ColorFilter(color);
            FilteredImageSource filteredImageSource = new FilteredImageSource(image.getSource(), colorFilter);
            result = Toolkit.getDefaultToolkit().createImage(filteredImageSource);
        }
        return result;
    }
    private Dimension size = null;
    private Image background, handHour, handMin, handSec, divHour, divMin, divSec;

    public static class Test
    {
        private static Clock clock = new Clock();
        public static Image getImage(String name) {
            return clock.getImage(name);
        }
        public static Image loadImage(String pathName) {
            return clock.loadImage(pathName);
        }
        public static Image getTransparentImage(Image image) {
            return clock.getTransparentImage(image);
        }
        public static Image getHandHour() {
            return clock.handHour;
        }
        public static Image getHandMin() {
            return clock.handMin;
        }
        public static Image getHandSec() {
            return clock.handSec;
        }
        public static Image getDivHour() {
            return clock.divHour;
        }
        public static Image getDivMin() {
            return clock.divMin;
        }
        public static Image getDivSec() {
            return clock.divSec;
        }
    }
}
