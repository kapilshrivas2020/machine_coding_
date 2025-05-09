package vm;

import java.util.HashMap;
import java.util.Map;

import States.IdleState;
import States.State;
import dao.Inventory;
import dao.Product;

public class VendingMachine {
	
	private Inventory inventory;
	private double amount;
	private static final int RACK_COUNT = 2; 
	private State machineCurrentState;
	
	
	VendingMachine(){
		this.amount = 0.0;
		this.inventory = new Inventory(RACK_COUNT);
		this.machineCurrentState = new IdleState(this);
	}
	
	public void initialize() {
		Map<Product, Integer> productQuantity = new HashMap<>();
		
		Product lays = new Product("LAYS", 10);
		Product coke = new Product("COKE", 20);
		Product lassi = new Product("LASSI", 30);
		Product chips = new Product("CHIPS", 10);
		Product peanuts = new Product("PEANUTS", 10);
		
		productQuantity.put(lays, 10);
		productQuantity.put(coke, 5);
		productQuantity.put(lassi, 2);
		productQuantity.put(chips, 5);
		
		for(Map.Entry<Product, Integer> p : productQuantity.entrySet()) {
			inventory.addProduct(p.getKey(), p.getValue());
		}
	}
	
	public boolean isSufficientAmount(double expectedAmount) {
		return (this.amount >= expectedAmount);
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public double getAmount() {
		return this.amount;
	}
	
	public void setAmount(double amount) {
		this.amount += amount;
	}
	
	public State getCurrentState() {
		return this.machineCurrentState;
	}
	
	public void setCurrentState(State currentState) {
		this.machineCurrentState = currentState;
	}

}
