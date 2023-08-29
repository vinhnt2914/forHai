package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.Trip;
import portmanagementsystem.models.Vehicle;

import javax.swing.*;
import java.io.IOException;

public class ViewTrips {
    private JPanel mainPanel;
    private JList<Trip> listView;
    private JComboBox comboBox;

    public ViewTrips() {
        try {
            DataManager.readTrips();
            DefaultListModel<Trip> tripListModel = new DefaultListModel<>();
            tripListModel.addAll(DataManager.getTripList());
            listView.setModel(tripListModel);
            System.out.println("Trips read successfully");
        } catch (IOException e) {
            System.out.println("Trips read unsuccessfully");
            throw new RuntimeException(e);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
