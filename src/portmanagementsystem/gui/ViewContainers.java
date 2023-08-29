package portmanagementsystem.gui;

import portmanagementsystem.DataManager;
import portmanagementsystem.models.Container;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewContainers implements PanelAction {
    private JPanel mainPanel;
    private JComboBox comboBox;
    private JList<Container> listView;
    private JButton sortButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deleteButton;
    private DefaultListModel<Container> containerListModel;

    public ViewContainers() {
        try {
            DataManager.readContainers();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        containerListModel = new DefaultListModel<>();
        containerListModel.addAll(DataManager.getContainerList());
        listView.setModel(containerListModel);

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

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSearchResults();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataManager.getContainerList().remove(listView.getSelectedValue());
                try {
                    DataManager.writeContainers();
                    load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void updateSearchResults() {
        String searchID = searchField.getText();
        List<Container> sortedList = new ArrayList<>();

        if (searchID.isBlank()) {
            containerListModel.clear();
            containerListModel.addAll(DataManager.getContainerList());
        } else {
            DataManager.getContainerList().forEach(container -> {
                if (container.getId().equalsIgnoreCase(searchID))
                    sortedList.add(container);
            });
            containerListModel.clear();
            containerListModel.addAll(sortedList);
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    @Override
    public void load() {
        containerListModel = new DefaultListModel<>();
        containerListModel.addAll(DataManager.getContainerList());
        listView.setModel(containerListModel);
    }
}
