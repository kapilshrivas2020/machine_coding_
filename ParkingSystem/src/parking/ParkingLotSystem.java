package parking;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.ParkingSpot;
import model.Vehicle;
import model.VehicleType;

public class ParkingLotSystem {
	
	LinkedList<ParkingSpot> twoWheelerParkingSpots;
	LinkedList<ParkingSpot> fourWheelerParkingSpots;
	
	int twoWheelerAvailableSpots;
	int fourWheelerAvailableSpots;
	
	public ParkingLotSystem(LinkedList<ParkingSpot> twoWheelerParkingSpots, LinkedList<ParkingSpot> fourWheelerParkingSpots){
		this.twoWheelerParkingSpots = twoWheelerParkingSpots;
		this.fourWheelerParkingSpots = fourWheelerParkingSpots;
		
		this.twoWheelerAvailableSpots = this.fourWheelerParkingSpots.size();
		this.fourWheelerAvailableSpots = this.fourWheelerParkingSpots.size();
	}
	
	public int parkVehicle(Vehicle vehicle){
		
		if(vehicle.getVehicleType().equals(VehicleType.TWO_WHEELER)) {
			if(twoWheelerAvailableSpots == 0) {
				throw new IllegalStateException("No Spot available");
			}
			
			ParkingSpot parkingSpot =  this.twoWheelerParkingSpots.getFirst();
			this.twoWheelerParkingSpots.removeFirst();
			this.twoWheelerAvailableSpots--;
			parkingSpot.setAvailable(false);
			parkingSpot.setVehicle(vehicle);
			this.twoWheelerParkingSpots.addLast(parkingSpot);
			return parkingSpot.getParkingSpotId();
		}
		
		if(vehicle.getVehicleType().equals(VehicleType.FOUR_WHEELER)) {
			if(fourWheelerAvailableSpots == 0) {
				throw new IllegalStateException("No Spot available");
			}
			
			ParkingSpot parkingSpot =  this.fourWheelerParkingSpots.getFirst();
			this.twoWheelerParkingSpots.removeFirst();
			this.fourWheelerAvailableSpots--;
			parkingSpot.setAvailable(false);
			parkingSpot.setVehicle(vehicle);
			this.fourWheelerParkingSpots.addLast(parkingSpot);
			return parkingSpot.getParkingSpotId();
		}
		
		return -1;
		
	}
	
	public void removeVehicle(int spotId, Vehicle vehicle) {
		
		if(vehicle.getVehicleType().equals(VehicleType.TWO_WHEELER)) {
			
			Iterator spotIterator = this.twoWheelerParkingSpots.iterator();
			ParkingSpot spot = null;
			while(spotIterator.hasNext()) {
				spot = (ParkingSpot) spotIterator.next();
				if(spot.getParkingSpotId() == spotId) {
					spotIterator.remove();
					break;
				}
			}
			
			spot.setAvailable(true);
			spot.setVehicle(null);
			this.twoWheelerAvailableSpots++;
			this.twoWheelerParkingSpots.add(spot);
		}
		
		if(vehicle.getVehicleType().equals(VehicleType.FOUR_WHEELER)) {
			Iterator spotIterator = this.fourWheelerParkingSpots.iterator();
			ParkingSpot spot = null;
			while(spotIterator.hasNext()) {
				spot = (ParkingSpot) spotIterator.next();
				if(spot.getParkingSpotId() == spotId) {
					spotIterator.remove();
					break;
				}
			}
			
			spot.setAvailable(true);
			spot.setVehicle(null);
			this.fourWheelerAvailableSpots++;
			this.fourWheelerParkingSpots.add(spot);
		}
		
	}
	
	
	
	
}
