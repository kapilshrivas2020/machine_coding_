package vm;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineController {
	
	int numberOfRacks = 10;
	VendingMachine machine1 = new VendingMachine(numberOfRacks);
	
	Map<Product, Integer> listOfProducts = new HashMap<>();
	
	Product lays = new Product("LAYS", 10);
	Product coke = new Product("COKE", 20);
	Product lassi = new Product("LASSI", 30);
	Product chips = new Product("CHIPS", 10);
	Product peanuts = new Product("PEANUTS", 10);
	
	listOfProducts.put(lays, 10);
	listOfProducts.put(coke, 5);
	listOfProducts.put(lassi, 2);
	listOfProducts.put(chips, 5);
	
	
	machine1.initialize(listOfProducts, 50);
	
	//machine1.choosePaymentMethod();
	
	machine1.insertCash(10, 1);
	machine1.insertCash(20, 2);

}
