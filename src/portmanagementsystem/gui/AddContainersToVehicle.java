package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddContainersToVehicle {
    private JButton addContainerButton;
    private JLabel vehicleIDLabel;
    private JLabel vehicleNameLabel;
    private JLabel vehicleTypeLabel;
    private JLabel currentFuelLabel;
    private JList<Container> listView;
    private JPanel mainPanel;

    public AddContainersToVehicle(Vehicle vehicle) {
        try {
            DataManager.readContainers();
            DefaultListModel<Container> containerListModel = new DefaultListModel<>();
            containerListModel.addAll(DataManager.getContainerList());
            listView.setModel(containerListModel);
            System.out.println("Containers read successfully");
        } catch (IOException e) {
            System.out.println("Containers read unsuccessful");
            throw new RuntimeException(e);
        }
        vehicleIDLabel.setText("Vehicle ID: " + vehicle.getId());
        vehicleNameLabel.setText("Vehicle Name: " + vehicle.getName());
        vehicleTypeLabel.setText("Vehicle Name: " + vehicle.getVehicleType());
        currentFuelLabel.setText("Vehicle Name: " + vehicle.getCurrentFuel());

        addContainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vehicle.loadContainer(listView.getSelectedValue());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
