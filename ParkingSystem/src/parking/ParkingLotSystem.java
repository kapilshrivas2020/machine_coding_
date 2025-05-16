package parking;

import model.Vehicle;
import model.VehicleType;

public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot mallParking = new ParkingLot("PL001", "Mall Parking", 10, 5, 3, 15);

        Vehicle car1 = new Vehicle("MH12AB1234", VehicleType.CAR);
        Vehicle bike1 = new Vehicle("MP09CD5678", VehicleType.BIKE);
        Vehicle truck1 = new Vehicle("KA01EF9012", VehicleType.TRUCK);
        Vehicle car2 = new Vehicle("UP16GH3456", VehicleType.CAR);

        System.out.println("Parking Vehicles:");
        String car1Ticket = mallParking.parkVehicle(car1);
        if (car1Ticket != null) {
            System.out.println("Parked car1, Ticket ID: " + car1Ticket);
        }
        String bike1Ticket = mallParking.parkVehicle(bike1);
        if (bike1Ticket != null) {
            System.out.println("Parked bike1, Ticket ID: " + bike1Ticket);
        }
        String truck1Ticket = mallParking.parkVehicle(truck1);
        if (truck1Ticket != null) {
            System.out.println("Parked truck1, Ticket ID: " + truck1Ticket);
        }
        String car2Ticket = mallParking.parkVehicle(car2);
        if (car2Ticket != null) {
            System.out.println("Parked car2, Ticket ID: " + car2Ticket);
        } else {
            System.out.println("No available spot for car2.");
        }

        System.out.println("\nAvailable Spots:");
        mallParking.displayAvailableSpots();

        System.out.println("\nOccupied Spots:");
        mallParking.displayOccupiedSpots();

        System.out.println("\nUnparking Vehicles:");
        if (car1Ticket != null) {
            double car1Fee = mallParking.unparkVehicle(car1Ticket);
            System.out.println("Unparked car1, Fee: " + car1Fee);
        }
        if (bike1Ticket != null) {
            double bike1Fee = mallParking.unparkVehicle(bike1Ticket);
            System.out.println("Unparked bike1, Fee: " + bike1Fee);
        }

        System.out.println("\nAvailable Spots After Unparking:");
        mallParking.displayAvailableSpots();

        System.out.println("\nOccupied Spots After Unparking:");
        mallParking.displayOccupiedSpots();
    }
}