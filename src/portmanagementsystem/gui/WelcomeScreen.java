package portmanagementsystem.gui;

import portmanagementsystem.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen {
    private JPanel mainPanel;
    private JButton logInButton;
    private JButton signUpButton;
    public WelcomeScreen() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginFrame.getCardPanel().add(new LoginScreen().getMainPanel(), "LoginPanel");
                LoginFrame.getCardLayout().show(LoginFrame.getCardPanel(), "LoginPanel");

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
