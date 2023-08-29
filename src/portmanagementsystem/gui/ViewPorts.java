package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.Port;
import portmanagementsystem.models.Vehicle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewPorts implements PanelAction{
    private JPanel mainPanel;
    private JList<Port> listView;
    private JComboBox comboBox;
    private JTextField searchField;
    private JButton deleteButton;
    private JButton sortButton;
    private DefaultListModel<Port> portListModel;

    public ViewPorts() {
        load();

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearchResults();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearchResults();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.getPortList().remove(listView.getSelectedValue());
                try {
                    DataManager.writePorts();
                    load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void updateSearchResults() {
        String searchID = searchField.getText();
        List<Port> sortedList = new ArrayList<>();

        if (searchID.isBlank()) {
            portListModel.clear();
            portListModel.addAll(DataManager.getPortList());
        } else {
            DataManager.getPortList().forEach(port -> {
                if (port.getId().equalsIgnoreCase(searchID))
                    sortedList.add(port);
            });
            portListModel.clear();
            portListModel.addAll(sortedList);
        }
    }

    @Override
    public void load() {
        try {
            DataManager.readPorts();
            portListModel = new DefaultListModel<>();
            portListModel.addAll(DataManager.getPortList());
            listView.setModel(portListModel);
            System.out.println("Port read successfully");
        } catch (IOException e) {
            System.out.println("Port read unsuccessfully");
        }
    }
}
