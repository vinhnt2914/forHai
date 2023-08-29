package portmanagementsystem.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManagerMenuBar extends MenuBarFrame{
    private JMenuBar menuBar;

    public ManagerMenuBar() {
        createMenuBar();
    }

    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu containers = new JMenu("Containers");
        JMenu vehicles = new JMenu("Vehicles");
        JMenu trips = new JMenu("Trips");

        JMenuItem viewContainers = new JMenuItem("View Containers");
        JMenuItem viewVehicles = new JMenuItem("View Vehicles");
        JMenuItem viewTrips = new JMenuItem("View Trips");
        JMenuItem approveTrips = new JMenuItem("Approve Trips");

        containers.add(viewContainers);
        vehicles.add(viewVehicles);
        trips.add(viewTrips);
        trips.add(approveTrips);

        menuBar.add(containers);
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

        viewVehicles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppFrame.getCardPanel().add(new ViewVehicles().getMainPanel(), "ViewVehiclesPanel");
                AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "ViewVehiclesPanel");
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
