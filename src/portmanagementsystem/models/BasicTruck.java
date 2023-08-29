package portmanagementsystem.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

public class BasicTruck extends Truck implements Serializable{
    @Serial
    private static final long serialVersionUID = 121L;
    private static List<Container.ContainerType> compatibleContainerType = new ArrayList<>(Arrays.asList(Container.ContainerType.DRY_STORAGE, Container.ContainerType.OPEN_TOP, Container.ContainerType.OPEN_SIDE));

    public BasicTruck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        setVehicleType("BASIC TRUCK");
    }

}
