package portmanagementsystem;

import portmanagementsystem.models.*;

import java.io.*;
import java.util.*;

public class DataManager {
    private static List<Container> containerList = new ArrayList<>();
    private static List<Port> portList = new ArrayList<>();
    private static List<Vehicle> vehicleList = new ArrayList<>();
    private static List<Trip> tripList = new ArrayList<>();

    public static void writeContainers() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("containers.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DataManager.getContainerList());
        objectOutputStream.close();
        System.out.println("Containers written successfully");
    }

    public static void readContainers() throws IOException{
        List<Container> containersToRead;
        File file = new File("containers.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("containers.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            containersToRead = (List<Container>) objectInputStream.readObject();
            System.out.println(containersToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data of ContainerList
        DataManager.getContainerList().clear();
        DataManager.getContainerList().addAll(containersToRead);
    }

    public static void writePorts() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("ports.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DataManager.getPortList());
        objectOutputStream.close();
        System.out.println("Ports written successfully");
    }

    public static void readPorts() throws IOException{
        List<Port> portsToRead;
        File file = new File("ports.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("ports.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            portsToRead = (List<Port>) objectInputStream.readObject();
            System.out.println(portsToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data of ContainerList
        DataManager.getPortList().clear();
        DataManager.getPortList().addAll(portsToRead);
    }

    public static void writeVehicles() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("vehicles.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DataManager.getVehicleList());
        objectOutputStream.close();
        System.out.println("Vehicles written successfully");
    }

    public static void readVehicles() throws IOException{
        List<Vehicle> vehiclesToRead;
        File file = new File("vehicles.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("vehicles.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            vehiclesToRead = (List<Vehicle>) objectInputStream.readObject();
            System.out.println(vehiclesToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data of ContainerList
        DataManager.getVehicleList().clear();
        DataManager.getVehicleList().addAll(vehiclesToRead);
    }

    public static void updateVehicle(Vehicle vehicle) {
        int index = DataManager.getVehicleList().indexOf(vehicle);
        if (index != -1) {
            DataManager.getVehicleList().set(index, vehicle);
            try {
                DataManager.writeVehicles();
                System.out.println("Vehicle update successfully");
            } catch (IOException e) {
                System.out.println("Vehicle update unsuccessfully");
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeTrips() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("trips.obj");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(DataManager.getTripList());
        objectOutputStream.close();
        System.out.println("Trips written successfully");
    }

    public static void readTrips() throws IOException{
        List<Trip> tripsToRead;
        File file = new File("trips.obj");
        if (!file.exists()) {
            // File does not exist
            return;
        }
        FileInputStream fileInputStream = new FileInputStream("trips.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try {
            tripsToRead = (List<Trip>) objectInputStream.readObject();
            System.out.println(tripsToRead);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Overwrite data of ContainerList
        DataManager.getTripList().clear();
        DataManager.getTripList().addAll(tripsToRead);
    }

    public static List<Container> getContainerList() {
        return containerList;
    }

    public static List<Port> getPortList() {
        return portList;
    }

    public static List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public static List<Trip> getTripList() {
        return tripList;
    }
}
