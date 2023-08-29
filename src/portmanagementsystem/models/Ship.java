package portmanagementsystem.models;

import portmanagementsystem.DataManager;

import javax.swing.*;
import java.io.*;
import java.util.Objects;

public class Ship extends Vehicle implements VehicleAction, Serializable{
    @Serial
    private static final long serialVersionUID = 11L;
    private static final String COUNTER_FILE = "shipCounter.txt";
    private static int counter = loadCounter();

    public Ship(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        this.id = generateShipId();
        setVehicleType("SHIP");
        Ship.saveCounter();
    }

    private static String generateShipId() {
        return "SH-" + counter++;
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
        this.getListOfContainers().add(container);
        this.getContainerCount().put(container.getType(), this.getContainerCount().getOrDefault(container.getType(), 0) + 1);
        DataManager.updateVehicle(this);
        JOptionPane.showMessageDialog(null, "Container is loaded onto the vehicle", "Success", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("Container loaded onto vehicle");
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
            requiredFuel += c.getFuelConsumption(Container.VehicleType.SHIP) * distance;
        }
        System.out.println(requiredFuel);
        return requiredFuel;
    }

//    @Override
//    public double calculateRequiredFuel(Port destinatedPort) {
//        double requiredFuel = 0;
//        double distance = Port.calculateDistance(this.getCurrentPort(), destinatedPort);
//        for (Container c : getListOfContainers()) {
//            requiredFuel += c.getFuelConsumption(Container.VehicleType.SHIP) * distance;
//        }
//        System.out.println(requiredFuel);
//        return requiredFuel;
//    }
}
