package portmanagementsystem.models;

import java.io.*;

public class Port implements Serializable{
    @Serial
    private static final long serialVersionUID = 3L;
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final String COUNTER_FILE = "portCounter.txt";
    private static int counter = loadCounter();
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private double storingCapacity;
    private AbleToLand landingAbility;
    private int numberOfContainers = 0;
    private int numberOfVehicles = 0;

    public enum AbleToLand {
        TRUE,
        FALSE
    }

    public Port(String name, double latitude, double longitude, double storingCapacity, AbleToLand landingAbility) {
        this.id = generatePortId();
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        Port.saveCounter();
    }

    public boolean canLoadContainers() {
        return true;
    }

    private static String generatePortId() {
        return "P-" + counter++;
    }

//    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
//        double dLat = Math.toRadians(lat2 - lat1);
//        double dLon = Math.toRadians(lon2 - lon1);
//
//        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
//                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
//                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
//
//        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
//
//        return EARTH_RADIUS_KM * c;
//    }

    public static double calculateDistance(Port port1, Port port2) {
        double lat1 = Math.toRadians(port1.getLatitude());
        double lon1 = Math.toRadians(port1.getLongitude());
        double lat2 = Math.toRadians(port2.getLatitude());
        double lon2 = Math.toRadians(port2.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Port port = (Port) obj;
        return id.equals(port.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public AbleToLand getLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(AbleToLand landingAbility) {
        this.landingAbility = landingAbility;
    }

    public int getNumberOfContainers() {
        return numberOfContainers;
    }

    public void setNumberOfContainers(int numberOfContainers) {
        this.numberOfContainers = numberOfContainers;
    }

    public int getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public void setNumberOfVehicles(int numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
    }

    @Override
    public String toString() {
        return "Port{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", storingCapacity=" + storingCapacity +
                ", landingAbility=" + landingAbility +
                ", numberOfContainers=" + numberOfContainers +
                ", numberOfVehicles=" + numberOfVehicles +
                '}';
    }
}
