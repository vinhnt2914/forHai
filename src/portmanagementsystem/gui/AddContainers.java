package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.Container;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AddContainers {
    private JTextField containerWeightField;
    private JComboBox containerTypeBox;
    private JButton addContainerButton;
    private JPanel mainPanel;

    public AddContainers() {
        for (Container.ContainerType containerType : Container.ContainerType.values()) {
            containerTypeBox.addItem(containerType);
        }

        addContainerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double containerWeight = 0;
                try {
                    containerWeight = Double.parseDouble(containerWeightField.getText());
                    Container.ContainerType containerType = (Container.ContainerType) containerTypeBox.getSelectedItem();
                    Container container = new Container(containerWeight, containerType);
                    DataManager.getContainerList().add(container);
                    try {
                        DataManager.writeContainers();
                    } catch (IOException ex) {
                        System.out.println("Write unsuccessful");
                    }
                    System.out.println(container);
                } catch (NumberFormatException exception) {
                    System.out.println(exception.getMessage());
                    String errorMessage = "Invalid weight input. Must be number";
                    JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
