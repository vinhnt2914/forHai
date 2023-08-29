package portmanagementsystem.models;

import portmanagementsystem.DataManager;

import javax.swing.*;
import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Vehicle implements Serializable, VehicleAction{
    @Serial
    private static final long serialVersionUID = 10L;
    protected String id;
    private String vehicleType;
    private String name;
    private double currentFuel;
    private double carryingCapacity;
    private double fuelCapacity;
    private Port currentPort;
    private List<Container> listOfContainers;
    private Map<Container.ContainerType, Integer> containerCount;

    public Vehicle(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        this.name = name;
        this.currentFuel = fuelCapacity;
        this.carryingCapacity = carryingCapacity;
        this.fuelCapacity = fuelCapacity;
        this.currentPort = currentPort;
        this.listOfContainers = new ArrayList<>();
        this.containerCount = new HashMap<>();
    }

    public void moveToPort(Port destinatedPort) {
        Trip trip = new Trip(this, LocalDate.now(), this.currentPort, destinatedPort);
        double requiredFuel = this.calulateRequiredFuel(destinatedPort);
        if (this.getCurrentPort().equals(destinatedPort)) {
            System.out.println("Already at port!");
            JOptionPane.showMessageDialog(null, "Vehicle is already at port", "Cannot move to Port", JOptionPane.ERROR_MESSAGE);
        } else if (requiredFuel < this.getCurrentFuel()) {
            this.setCurrentFuel(this.getCurrentFuel() - requiredFuel);
            this.setCurrentPort(destinatedPort);
            DataManager.updateVehicle(this);
            DataManager.getTripList().add(trip);
            JOptionPane.showMessageDialog(null, "Vehicle is now moving to port", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            System.out.println("Not enough fuel to move to port");
            JOptionPane.showMessageDialog(null, "Vehicle doesn't have enough fuel", "Cannot move to Port", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void refuel() {
        this.setCurrentFuel(this.getFuelCapacity());
        JOptionPane.showMessageDialog(null, "Vehicle has been refueled", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public abstract double calulateRequiredFuel(Port destinatedPort);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public double getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(double fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public List<Container> getListOfContainers() {
        return listOfContainers;
    }

    public Map<Container.ContainerType, Integer> getContainerCount() {
        return containerCount;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Type: %s, Name: %s, Current Fuel: %.2f, " +
                                    "Fuel Capacity: %.2f, Carrying Capacity: %.2f, " +
                                    "Current Port: {ID: %s, Name: %s}, Containers: %s",
                                    getId(), getVehicleType(), getName(), getCurrentFuel(),
                                    getFuelCapacity(), getCarryingCapacity(), getCurrentPort().getId(),
                                    getCurrentPort().getName(), getContainerCount());
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;
        return id.equals(vehicle.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
