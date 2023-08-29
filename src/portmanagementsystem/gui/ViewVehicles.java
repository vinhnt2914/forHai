package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.Vehicle;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewVehicles implements PanelAction{
    private JPanel mainPanel;
    private JList<Vehicle> listView;
    private JComboBox comboBox;
    private JTextField searchField;
    private JButton deleteButton;
    private JButton sortButton;
    private JPopupMenu popupMenu;
    private DefaultListModel<Vehicle> vehicleListModel;

    public ViewVehicles() {
//        try {
//            DataManager.readVehicles();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        DefaultListModel<Vehicle> vehicleListModel = new DefaultListModel<>();
//        vehicleListModel.addAll(DataManager.getVehicleList());
//        listView.setModel(vehicleListModel);
//        System.out.println("Vehicles read successfully");
        load();
        popupMenu = new JPopupMenu();
        JMenuItem moveToPort = new JMenuItem("Move to Port");
        JMenuItem loadContainers = new JMenuItem("Load Containers");
        JMenuItem refuel = new JMenuItem("Refuel");
        popupMenu.add(moveToPort);
        popupMenu.add(loadContainers);
        popupMenu.add(refuel);

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

        listView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                int index = listView.locationToIndex(e.getPoint());
                if (index >= 0 && index < listView.getModel().getSize()) {
                    // Set the selected item in the JList
                    listView.setSelectedIndex(index);

                    // Show the JPopupMenu
                    popupMenu.show(listView, e.getX(), e.getY());
                }
            }
            }
        });

        moveToPort.addActionListener(e -> {
            AppFrame.getCardPanel().add(new MoveToPort(listView.getSelectedValue()).getMainPanel(), "MoveToPortPanel");
            AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "MoveToPortPanel");
        });

        loadContainers.addActionListener(e -> {
            AppFrame.getCardPanel().add(new AddContainersToVehicle(listView.getSelectedValue()).getMainPanel(), "LoadContainersToVehiclePanel");
            AppFrame.getCardLayout().show(AppFrame.getCardPanel(), "LoadContainersToVehiclePanel");
        });

        refuel.addActionListener(e -> {
            listView.getSelectedValue().refuel();
            DataManager.updateVehicle(listView.getSelectedValue());
            load();
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.getVehicleList().remove(listView.getSelectedValue());
                try {
                    DataManager.writeVehicles();
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
        List<Vehicle> sortedList = new ArrayList<>();

        if (searchID.isBlank()) {
            vehicleListModel.clear();
            vehicleListModel.addAll(DataManager.getVehicleList());
        } else {
            DataManager.getVehicleList().forEach(vehicle -> {
                if (vehicle.getId().equalsIgnoreCase(searchID))
                    sortedList.add(vehicle);
            });
            vehicleListModel.clear();
            vehicleListModel.addAll(sortedList);
        }
    }

    @Override
    public void load() {
        try {
            DataManager.readVehicles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vehicleListModel = new DefaultListModel<>();
        vehicleListModel.addAll(DataManager.getVehicleList());
        listView.setModel(vehicleListModel);
        System.out.println("Vehicles read successfully");
    }
}
