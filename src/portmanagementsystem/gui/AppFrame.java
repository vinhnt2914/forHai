package portmanagementsystem.gui;

import javax.swing.*;
import java.awt.*;

public class AppFrame {
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;
    private static MenuBarFrame menuBar;
    public AppFrame(MenuBarFrame menuBar) {
        createAppFrame(menuBar);
//        mainFrame = new JFrame("Port Management System");
//        mainFrame.setSize(600, 500);
//        mainFrame.setLocationRelativeTo(null);
//        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        mainFrame.setJMenuBar(new AdminMenuBar().getMenuBar());
//
//        cardPanel = new JPanel(new CardLayout());
//        cardLayout = (CardLayout) cardPanel.getLayout();
//
////        HomepageScreen homepageScreen = new HomepageScreen();
////        cardPanel.add(homepageScreen.getMainPanel(), "HomepagePanel");
//
//        int frameWidth = 800;
//        int frameHeight = 600;
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        int x = (screenSize.width - frameWidth) / 2;
//        int y = (screenSize.height - frameHeight) / 2;
//
//        // ... Add more panels to cardPanel here
//        mainFrame.setSize(frameWidth, frameHeight);
//        mainFrame.setLocation(x, y);
//        mainFrame.add(cardPanel);
//        mainFrame.setVisible(true);
    }

    public void createAppFrame(MenuBarFrame menuBar) {
        mainFrame = new JFrame("Port Management System");
        mainFrame.setSize(600, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setJMenuBar(menuBar.getMenuBar());

        cardPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout) cardPanel.getLayout();

        int frameWidth = 800;
        int frameHeight = 600;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frameWidth) / 2;
        int y = (screenSize.height - frameHeight) / 2;

        // ... Add more panels to cardPanel here
        mainFrame.setSize(frameWidth, frameHeight);
        mainFrame.setLocation(x, y);
        mainFrame.add(cardPanel);
        mainFrame.setVisible(true);
    }



    public static JPanel getCardPanel() {
        return cardPanel;
    }

    public static CardLayout getCardLayout() {
        return cardLayout;
    }
}
