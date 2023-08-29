package portmanagementsystem.models;

import portmanagementsystem.DataManager;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Truck extends Vehicle implements Serializable, VehicleAction{
    @Serial
    private static final long serialVersionUID = 12L;
    private static final String COUNTER_FILE = "truckCounter.txt";
    private static int counter = loadCounter();
    private static List<Container.ContainerType> compatibleContainerType = new ArrayList<>(Arrays.asList(Container.ContainerType.DRY_STORAGE, Container.ContainerType.OPEN_TOP, Container.ContainerType.OPEN_SIDE, Container.ContainerType.REFRIGERATED, Container.ContainerType.LIQUID));
    public Truck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        this.id = generateTruckId();
        Truck.saveCounter();
    }

    private static String generateTruckId() {
        return "TR-" + counter++;
    }

    private static int loadCounter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COUNTER_FILE))) {
            return Integer.parseInt(reader.readLine());
        } catch (FileNotFoundException e) {
            // Counter file not found, start from 1
            return 1;
        } catch (IOException e) {
            e.printStackTrace();
            return 1;
        }
    }

    public static void saveCounter() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COUNTER_FILE))) {
            writer.write(String.valueOf(counter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadContainer(Container container) {
//        if (compatibleContainerType.contains(container.getType())) {
//            this.getListOfContainers().add(container);
//            this.getContainerCount().put(container.getType(), this.getContainerCount().getOrDefault(container.getType(), 0) + 1);
//            DataManager.updateVehicle(this);
//            JOptionPane.showMessageDialog(null, "Container is loaded onto the vehicle", "Success", JOptionPane.INFORMATION_MESSAGE);
//            System.out.println("Container loaded onto vehicle");
//        } else {
//            JOptionPane.showMessageDialog(null, "Container type is not compatible for this vehicle", "Cannot load container", JOptionPane.ERROR_MESSAGE);
//            System.out.println("Container is not compatible");
//        }

        if (isCompatible(container)) {
            this.getListOfContainers().add(container);
            this.getContainerCount().put(container.getType(), this.getContainerCount().getOrDefault(container.getType(), 0) + 1);
            DataManager.updateVehicle(this);
            JOptionPane.showMessageDialog(null, "Container is loaded onto the vehicle", "Success", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Container loaded onto vehicle");
        } else {
            JOptionPane.showMessageDialog(null, "Container type is not compatible for this vehicle", "Cannot load container", JOptionPane.ERROR_MESSAGE);
            System.out.println("Container is not compatible");
        }
    }

    public boolean isCompatible(Container container) {
        return compatibleContainerType.contains(container.getType());
    }

    @Override
    public void unloadContainer(Container container) {
        if (Objects.isNull(getCurrentPort())) {
            System.out.println("Not at a port! Cannot unload containers");
        } else {
            getListOfContainers().clear();
            getContainerCount().clear();
        }
    }

    @Override
    public double calulateRequiredFuel(Port destinatedPort) {
        double requiredFuel = 0;
        double distance = Port.calculateDistance(this.getCurrentPort(), destinatedPort);
        for (Container c : getListOfContainers()) {
            requiredFuel += c.getFuelConsumption(Container.VehicleType.TRUCK) * distance;
        }
        System.out.println(requiredFuel);
        return requiredFuel;
    }

}
