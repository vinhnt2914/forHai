package portmanagementsystem.models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TankerTruck extends Truck implements Serializable {
    @Serial
    private static final long serialVersionUID = 123L;
    private static List<Container.ContainerType> compatibleContainerType = new ArrayList<>(Arrays.asList(Container.ContainerType.LIQUID));

    public TankerTruck(String name, double carryingCapacity, double fuelCapacity, Port currentPort) {
        super(name, carryingCapacity, fuelCapacity, currentPort);
        setVehicleType("TANKER TRUCK");
    }

}
