package vm;

import java.util.Map;

public class VendingMachine {
	
	Map<Product, Integer> productQuantity;
	int machineAmount = 0;
	int numberOfRacks = 0;
	Rack[] racks;
	
	VendingMachine(int numberOfRacks){
		racks = new Rack[numberOfRacks];
	}
	
	void initialize(Map<Product, Integer> productQuantity, int amount) {
		int rackId = 0;
		for(Map.Entry<Product, Integer> p : productQuantity.entrySet()) {
			racks[rackId++] = new Rack(p.getKey(), p.getValue());
		}
		
		machineAmount += amount;
	}
	
	void insertCash(int cash, int rkId) {
		if(racks[rkId].quantity > 0 && racks[rkId].product.price >= cash) {
			int productPrice = racks[rkId].product.price;
			machineAmount += cash;
			if(cash - productPrice > 0)
				disburseCash(cash - productPrice);
			racks[rkId].quantity--;
			disburseProduct(rkId);
		}else {
			// print insufficient funds
			disburseCash(cash);
		}
	}
	
	void disburseCash(int refundCash) {
		machineAmount -= refundCash;
	}
	
	void disburseProduct(int rkId) {
		// run this rack
	}

}
