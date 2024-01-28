package ui;

import javax.swing.*;
import java.awt.*;

// creates splash screens for the program
// code based on:
// https://www.c-sharpcorner.com/article/developing-a-splash-screen-using-java/
public class SplashScreens extends JWindow {

    Image screen;
    ImageIcon imageIcon;

    // EFFECTS: creates a splash screen
    public SplashScreens() {
        screen = Toolkit.getDefaultToolkit().getImage("./images/IMG_1850.gif");
        imageIcon = new ImageIcon(screen);
        setSize(500, 500);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - getSize().width) / 2;
        int y = (screenSize.height - getSize().height) / 2;
        setLocation(x, y);
        setVisible(true);
    }

    //  EFFECTS: paints the image on the screen
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(screen, 0, 0, this);
    }
}
