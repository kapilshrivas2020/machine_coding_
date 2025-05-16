package parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import model.ParkingSpot;
import model.ParkingSpotType;
import model.ParkingTicket;
import model.Vehicle;
import model.VehicleType;

class ParkingLot {
    private String parkingLotId;
    private String name;
    private List<ParkingSpot> parkingSpots;
    private Map<String, ParkingTicket> activeTickets;
    private double hourlyRateCar = 2.0;
    private double hourlyRateBike = 1.0;
    private double hourlyRateTruck = 3.0;

    public ParkingLot(String parkingLotId, String name, int compactSpots, int mediumSpots, int largeSpots, int bikeSpots) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.parkingSpots = new ArrayList<>();
        this.activeTickets = new HashMap<>();

        // Initialize parking spots
        for (int i = 1; i <= compactSpots; i++) {
            this.parkingSpots.add(new ParkingSpot(parkingLotId + "-C" + i, ParkingSpotType.COMPACT));
        }
        for (int i = 1; i <= mediumSpots; i++) {
            this.parkingSpots.add(new ParkingSpot(parkingLotId + "-M" + i, ParkingSpotType.MEDIUM));
        }
        for (int i = 1; i <= largeSpots; i++) {
            this.parkingSpots.add(new ParkingSpot(parkingLotId + "-L" + i, ParkingSpotType.LARGE));
        }
        for (int i = 1; i <= bikeSpots; i++) {
            this.parkingSpots.add(new ParkingSpot(parkingLotId + "-B" + i, ParkingSpotType.BIKE_SPOT));
        }
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public String getName() {
        return name;
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public Map<String, ParkingTicket> getActiveTickets() {
        return activeTickets;
    }

    public String parkVehicle(Vehicle vehicle) {
        ParkingSpot availableSpot = findAvailableSpot(vehicle.getVehicleType());
        if (availableSpot != null) {
            availableSpot.assignVehicle(vehicle);
            String ticketId = generateTicketId(vehicle.getLicensePlate());
            ParkingTicket ticket = new ParkingTicket(ticketId, vehicle, availableSpot, LocalDateTime.now(java.time.Clock.system(java.time.ZoneId.of("Asia/Kolkata"))));
            activeTickets.put(ticketId, ticket);
            return ticketId;
        }
        return null; // No available spot
    }

    public double unparkVehicle(String ticketId) {
        ParkingTicket ticket = activeTickets.get(ticketId);
        if (ticket != null) {
            ticket.setExitTime(LocalDateTime.now(java.time.Clock.system(java.time.ZoneId.of("Asia/Kolkata"))));
            double fee = calculateParkingFee(ticket);
            ticket.setParkingFee(fee);
            ticket.getAssignedSpot().removeVehicle();
            activeTickets.remove(ticketId);
            return fee;
        }
        return -1.0; // Invalid ticket ID
    }

    public ParkingSpot findParkingSpot(String spotId) {
        return parkingSpots.stream().filter(spot -> spot.getParkingSpotId().equals(spotId)).findFirst().orElse(null);
    }

    private ParkingSpot findAvailableSpot(VehicleType vehicleType) {
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable() && spot.canPark(vehicleType)) {
                return spot;
            }
        }
        return null;
    }

    private String generateTicketId(String licensePlate) {
        return "TKT-" + licensePlate + "-" + System.currentTimeMillis();
    }

    private double calculateParkingFee(ParkingTicket ticket) {
        long durationInHours = java.time.Duration.between(ticket.getEntryTime(), ticket.getExitTime()).toHours();
        VehicleType vehicleType = ticket.getVehicle().getVehicleType();
        if (vehicleType == VehicleType.CAR) {
            return durationInHours * hourlyRateCar;
        } else if (vehicleType == VehicleType.BIKE) {
            return durationInHours * hourlyRateBike;
        } else if (vehicleType == VehicleType.TRUCK) {
            return durationInHours * hourlyRateTruck;
        }
        return 0.0;
    }

    public void displayAvailableSpots() {
        System.out.println("Available Parking Spots in " + name + ":");
        Map<ParkingSpotType, Integer> counts = new HashMap<>();
        for (ParkingSpot spot : parkingSpots) {
            if (!spot.isAvailable()) {
                counts.put(spot.getParkingSpotType(), counts.getOrDefault(spot.getParkingSpotType(), 0) + 1);
            }
        }
        counts.forEach((type, count) -> System.out.println(type + ": " + count));
    }

    public void displayOccupiedSpots() {
        System.out.println("Occupied Parking Spots in " + name + ":");
        for (ParkingSpot spot : parkingSpots) {
            if (spot.isAvailable()) {
                System.out.println("Spot ID: " + spot.getParkingSpotId() + ", Vehicle: " + spot.getParkedVehicle().getLicensePlate() + " (" + spot.getParkedVehicle().getVehicleType() + ")");
            }
        }
    }
}
