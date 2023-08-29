package portmanagementsystem.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JButton logInButton;
    private JPasswordField passwordField;

    public LoginScreen() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.equals("admin") && password.equals("123456")) {
                    System.out.println("Log in successfully");
                    LoginFrame.getMainFrame().dispose();
                    new AppFrame(new AdminMenuBar());
                } else {
                    LoginFrame.getMainFrame().dispose();
                    new AppFrame(new ManagerMenuBar());
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
