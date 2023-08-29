package portmanagementsystem.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReeferTruck extends Truck implements Serializable {
    @Serial
    private static final long serialVersionUID = 122L;
    private static List<Container.ContainerType> compatibleContainerType = new ArrayList<>(Arrays.asList(Container.ContainerType.REFRIGERATED));

    public ReeferTruck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        setVehicleType("REEFER TRUCK");
    }

}
