package ui;

import javax.swing.*;

// Runs the application
public class Main extends JWindow {
    public static void main(String[] args) {
        SplashScreens start = new SplashScreens();
        try {
            Thread.sleep(2500);
            start.dispose();
        } catch (InterruptedException e) {
            System.out.println("Exception caught");
        }
        new DisplayPanel();
    }
}
