package portmanagementsystem;

import portmanagementsystem.gui.LoginFrame;
import portmanagementsystem.gui.LoginScreen;
import portmanagementsystem.gui.WelcomeScreen;

import javax.swing.*;
import java.awt.*;

public class Test {
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame();
            }
        });
    }

}
