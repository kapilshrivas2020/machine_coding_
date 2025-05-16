package model;

public class ParkingSpot {
	
	private Vehicle parkedVehicle;
	private String parkingSpotId;
	private ParkingSpotType parkingSpotType;
	private boolean isAvailable;
	
    public ParkingSpot(String spotId, ParkingSpotType type) {
        this.parkingSpotId = spotId;
        this.parkingSpotType = type;
        this.isAvailable = false;
        this.parkedVehicle = null;
    }
    
    

	public String getParkingSpotId() {
		return parkingSpotId;
	}



	public void setParkingSpotId(String parkingSpotId) {
		this.parkingSpotId = parkingSpotId;
	}



	public void setParkedVehicle(Vehicle parkedVehicle) {
		this.parkedVehicle = parkedVehicle;
	}



	public ParkingSpotType getParkingSpotType() {
		return parkingSpotType;
	}
	public void setParkingSpotType(ParkingSpotType parkingSpotType) {
		this.parkingSpotType = parkingSpotType;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean assignVehicle(Vehicle vehicle) {
        if (!isAvailable && canPark(vehicle.getVehicleType())) {
            this.isAvailable = true;
            this.parkedVehicle = vehicle;
            return true;
        }
        return false;
    }

    public void removeVehicle() {
        this.isAvailable = false;
        this.parkedVehicle = null;
    }
    
    public boolean canPark(VehicleType vehicleType) {
        if (vehicleType == VehicleType.BIKE) {
            return this.parkingSpotType == ParkingSpotType.BIKE_SPOT || this.parkingSpotType == ParkingSpotType.COMPACT || this.parkingSpotType == ParkingSpotType.MEDIUM || this.parkingSpotType == ParkingSpotType.LARGE;
        } else if (vehicleType == VehicleType.CAR) {
            return this.parkingSpotType == ParkingSpotType.COMPACT || this.parkingSpotType == ParkingSpotType.MEDIUM || this.parkingSpotType == ParkingSpotType.LARGE;
        } else if (vehicleType == VehicleType.TRUCK) {
            return this.parkingSpotType == ParkingSpotType.LARGE;
        }
        return false;
    }
	
	
}
