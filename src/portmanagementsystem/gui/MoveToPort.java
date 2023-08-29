package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class MoveToPort {
    private JList<Port> listView;
    private JButton moveToPortButton;
    private JPanel mainPanel;
    private JLabel vehicleIDLabel;
    private JLabel vehicleNameLabel;
    private JLabel vehicleTypeLabel;
    private JLabel currentFieldLabel;
    private JLabel portIDLabel;
    private JLabel portNameLabel;

    public MoveToPort(Vehicle vehicle) {
        try {
            DataManager.readPorts();
            DefaultListModel<Port> portListModel = new DefaultListModel<>();
            portListModel.addAll(DataManager.getPortList());
            listView.setModel(portListModel);
            System.out.println("Port read successfully");
        } catch (IOException e) {
            System.out.println("Ports read unsuccessful");
            throw new RuntimeException(e);
        }

        vehicleIDLabel.setText("Vehicle ID: " + vehicle.getId());
        vehicleNameLabel.setText("Vehicle Name: " + vehicle.getName());
        vehicleTypeLabel.setText("Vehicle Name: " + vehicle.getVehicleType());
        currentFieldLabel.setText("Current Field: " + vehicle.getCurrentFuel());
        portIDLabel.setText("Port ID: " + vehicle.getCurrentPort().getId());
        portNameLabel.setText("Port Name: " + vehicle.getCurrentPort().getName());

        moveToPortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle.moveToPort(listView.getSelectedValue());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
