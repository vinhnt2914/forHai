package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddVehicles {
    private JComboBox vehicleTypeBox;
    private JTextField vehicleNameField;
    private JTextField vehicleCapacityField;
    private JTextField vehicleFuelField;
    private JButton createButton;
    private JComboBox vehiclePortBox;
    private JPanel mainPanel;

    public AddVehicles() throws IOException {
        DataManager.readPorts();
        for (Port p : DataManager.getPortList()) {
            vehiclePortBox.addItem(p);
        }
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vehicleName = vehicleNameField.getText();
                double vehicleCapacity = parseDoubleInput(vehicleCapacityField.getText(), "vehicle capacity");
                double vehicleFuel = parseDoubleInput(vehicleFuelField.getText(), "vehicle fuel");
                Port port = (Port) vehiclePortBox.getSelectedItem();
                String vehicleType = (String) vehicleTypeBox.getSelectedItem();
                Vehicle vehicle = createVehicleOfType(vehicleType, vehicleName, vehicleCapacity, vehicleFuel, port);
                DataManager.getVehicleList().add(vehicle);
                try {
                    DataManager.writeVehicles();
                } catch (IOException ex) {
                    System.out.println("Vehicle write unsuccessfully");
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void showErrorDialog(String fieldName, String errorMessage) {
        String fullErrorMessage = "Invalid " + fieldName + " input. " + errorMessage;
        JOptionPane.showMessageDialog(null, fullErrorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private double parseDoubleInput(String input, String fieldName) throws NumberFormatException {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            showErrorDialog(fieldName, "Must be a valid number.");
            throw e;
        }
    }

    private Vehicle createVehicleOfType(String type, String vehicleName, double vehicleCapacity, double vehicleFuel, Port currentPort) {
        switch (type) {
            case "SHIP":
                return new Ship(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            case "BASIC TRUCK":
                return new BasicTruck(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            case "REEFER TRUCK":
                return new ReeferTruck(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            case "TANKER TRUCK":
                return new TankerTruck(vehicleName, vehicleCapacity, vehicleFuel, currentPort);
            default:
                throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
