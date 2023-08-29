package portmanagementsystem.models;

import java.io.*;
import java.util.Map;

import static java.util.Map.*;

public class Container implements Serializable{
    @Serial
    private static final long serialVersionUID = 2L;
    private static final String COUNTER_FILE = "containerCounter.txt";
    private static int counter = loadCounter();
    private String id;
    private double weight;
    private ContainerType type;
    private static final Map<ContainerType, Double> SHIP_CONSUMPTION_RATES = Map.ofEntries(
            entry(ContainerType.DRY_STORAGE, 3.5),
            entry(ContainerType.OPEN_TOP, 2.8),
            entry(ContainerType.OPEN_SIDE, 2.7),
            entry(ContainerType.REFRIGERATED, 4.5),
            entry(ContainerType.LIQUID, 4.8)
    );
    private static final Map<ContainerType, Double> TRUCK_CONSUMPTION_RATES = Map.ofEntries(
            entry(ContainerType.DRY_STORAGE, 4.6),
            entry(ContainerType.OPEN_TOP, 3.2),
            entry(ContainerType.OPEN_SIDE, 3.2),
            entry(ContainerType.REFRIGERATED, 5.4),
            entry(ContainerType.LIQUID, 5.3)
    );

    public enum ContainerType{
        DRY_STORAGE,
        OPEN_TOP,
        OPEN_SIDE,
        REFRIGERATED,
        LIQUID
    }

    public enum VehicleType{
        SHIP,
        TRUCK
    }

    public Container(double weight, ContainerType type) {
        this.id = generateContainerId();
        this.weight = weight;
        this.type = type;
        saveCounter();
    }

    private static String generateContainerId() {
        return "C-" + counter++;
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


    public double getConsumptionRate(VehicleType vehicleType) {
        if (vehicleType.equals(VehicleType.TRUCK))
            return TRUCK_CONSUMPTION_RATES.get(this.getType());
        else
            return SHIP_CONSUMPTION_RATES.get(this.getType());
    }

    public double getFuelConsumption(VehicleType vehicleType) {
        return getConsumptionRate(vehicleType) * this.weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public ContainerType getType() {
        return type;
    }

    public void setType(ContainerType type) {
        this.type = type;
    }



    @Override
    public String toString() {
        return String.format("{ID: %s, Weight: %.2f, Type: %s}", getId(), getWeight(), getType());
    }
}
