package portmanagementsystem.gui;

import javax.swing.*;

public abstract class MenuBarFrame {
    private JMenuBar menuBar;

    public MenuBarFrame() {
        createMenuBar();
    }

    private void createMenuBar() {
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
