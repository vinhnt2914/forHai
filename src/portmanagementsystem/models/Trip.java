package portmanagementsystem.models;

import java.io.*;
import java.time.LocalDate;

public class Trip implements Serializable{
    @Serial
    private static final long serialVersionUID = 4L;
    private static final String COUNTER_FILE = "tripCounter.txt";
    private static int counter = loadCounter();
    private String id;
    private Vehicle vehicle;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Port departurePort;
    private Port destinatedPort;
    private TripStatus status;

    public enum TripStatus {
        COMPLETED,
        ON_PROGRESS
    }

    public Trip(Vehicle vehicle, LocalDate departureDate, Port departurePort, Port destinatedPort) {
        this.id = generateTripID();
        this.vehicle = vehicle;
        this.departureDate = departureDate;
        this.departurePort = departurePort;
        this.destinatedPort = destinatedPort;
        this.status = TripStatus.ON_PROGRESS;
        saveCounter();
    }

    private static String generateTripID() {
        return "TRIP-" + counter++;
    }

    public void completeTrip() {
        if (status == TripStatus.ON_PROGRESS) {
            status = TripStatus.COMPLETED;
            arrivalDate = LocalDate.now();
        } else {
            System.out.println("This trip is already completed!");
        }
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
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", vehicle=" + vehicle +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", departurePort=" + departurePort +
                ", destinatedPort=" + destinatedPort +
                ", status=" + status +
                '}';
    }
}
