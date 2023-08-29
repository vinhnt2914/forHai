package portmanagementsystem.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AdminMenuBar extends MenuBarFrame {
    private JMenuBar menuBar;

    public AdminMenuBar() {
        createMenuBar();
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu containers = new JMenu("Containers");
        JMenu ports = new JMenu("Ports");
        JMenu vehicles = new JMenu("Vehicles");
        JMenu trips = new JMenu("Trips");

        JMenuItem viewContainers = new JMenuItem("View Containers");
        JMenuItem addContainers = new JMenuItem("Add Containers");
        JMenuItem viewPorts = new JMenuItem("View Ports");
        JMenuItem addPorts = new JMenuItem("Add Ports");
        JMenuItem addVehicles = new JMenuItem("Add Vehicles");
        JMenuItem viewVehicles = new JMenuItem("View Vehicles");
        JMenuItem viewTrips = new JMenuItem("View Trips");

        containers.add(viewContainers);
        containers.add(addContainers);
        ports.add(viewPorts);
        ports.add(addPorts);
        vehicles.add(addVehicles);
        vehicles.add(viewVehicles);
        trips.add(viewTrips);

        menuBar.add(containers);
        menuBar.add(ports);
        menuBar.add(vehicles);
        menuBar.add(trips);

        /* Containers Menu Action Listeners*/
        viewContainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new ViewContainers().getMainPanel(), "ViewContainersPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "ViewContainersPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });

        addContainers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new AddContainers().getMainPanel(), "AddContainersPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "AddContainersPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });

        /* Ports Menu Action Listeners*/
        viewPorts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new ViewPorts().getMainPanel(), "ViewPortsPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "ViewPortsPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });

        addPorts.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new AddPort().getMainPanel(), "AddPortsPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "AddPortsPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });

        viewVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new ViewVehicles().getMainPanel(), "ViewVehiclesPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "ViewVehiclesPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });

        addVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AppFrame.getCardPanel().add(new AddVehicles().getMainPanel(), "AddVehiclesPanel");
                } catch (IOException ex) {
                    System.out.println("Add Vehicles load unsuccessfully");
                }
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "AddVehiclesPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });

        viewTrips.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new ViewTrips().getMainPanel(), "ViewTripsPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "ViewTripsPanel");
                menuBar.requestFocusInWindow(); // Transfer focus to the menu bar
            }
        });


    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
