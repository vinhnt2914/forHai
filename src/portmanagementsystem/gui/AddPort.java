package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.Port;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddPort {
    private JTextField portNameField;
    private JTextField portLatitudeField;
    private JTextField portLongtitudeField;
    private JTextField portStoringField;
    private JComboBox portLandingBox;
    private JButton addPortButton;
    private JPanel mainPanel;


    public AddPort() {
        for(Port.AbleToLand landing : Port.AbleToLand.values()) {
            portLandingBox.addItem(landing);
        }
        addPortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String portName = portNameField.getText();

                try {
                    double portLatitude = parseDoubleInput(portLatitudeField.getText(), "latitude");
                    double portLongtitude = parseDoubleInput(portLongtitudeField.getText(), "longitude");
                    double portStoring = parseDoubleInput(portStoringField.getText(), "storing capacity");
                    Port.AbleToLand portLanding = (Port.AbleToLand) portLandingBox.getSelectedItem();
                    Port port = new Port(portName, portLatitude, portLongtitude, portStoring, portLanding);
                    DataManager.getPortList().add(port);
                    DataManager.writePorts();
                    System.out.println("Port write successfully!");
                } catch (IOException ex) {
                    System.out.println("Port write unsuccessfully!");
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
 
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
