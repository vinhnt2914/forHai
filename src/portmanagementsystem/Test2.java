//package portmanagementsystem;
//
//import portmanagementsystem.gui.WelcomeScreen;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class Test2 {
//    private static final String WELCOME_PANEL = "Welcome";
//    private static final String LOGIN_PANEL = "Login";
//
//    private static JPanel cardPanel;
//    private static CardLayout cardLayout;
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Port Management System");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(800, 600);
//
//            // Create the menu bar
//            JMenuBar menuBar = new JMenuBar();
//            JMenu fileMenu = new JMenu("File");
//            JMenuItem exitItem = new JMenuItem("Exit");
//            exitItem.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    System.exit(0);
//                }
//            });
//            fileMenu.add(exitItem);
//            menuBar.add(fileMenu);
//
//            // Add the menu bar to the frame
//            frame.setJMenuBar(menuBar);
//
//            cardLayout = new CardLayout();
//            cardPanel = new JPanel(cardLayout);
//
//            WelcomeScreen welcomeScreen = new WelcomeScreen(cardLayout, cardPanel);
//            cardPanel.add(welcomeScreen.getMainPanel(), WELCOME_PANEL);
//
//            // Other panels (Login, SignUp, etc.) can be added similarly
//
//            frame.add(cardPanel);
//            frame.setVisible(true);
//        });
//    }
//}
//
