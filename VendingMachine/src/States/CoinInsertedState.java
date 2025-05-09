package States;

import java.rmi.UnexpectedException;

import dao.Inventory;
import dao.Product;
import vm.VendingMachine;

public class CoinInsertedState implements State{
	
	VendingMachine vendingMachine;
	
	CoinInsertedState(VendingMachine machine){
		this.vendingMachine = machine;
	}

	@Override
	public void insertCoins(double amount) {
		// TODO Auto-generated method stub
		vendingMachine.setAmount(vendingMachine.getAmount() + amount);
	}

	@Override
	public void pressButton(int rackNumber) {
		Inventory inventory = vendingMachine.getInventory();
		Product product = inventory.getProductAt(rackNumber);
		
		if(!vendingMachine.isSufficientAmount(product.getPrice())) {
			throw new IllegalStateException("Insufficient amount, please add money");
		}
		
		if(!inventory.checkProductAvailability(product.getId())) {
			throw new IllegalStateException("Product not found, please other product");
		}
		
		vendingMachine.setCurrentState(new DispenseState(vendingMachine));
	}

	@Override
	public void dispense(int rackNumber) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Dispense Operation Not Allowed without inserting coins");
	}

}
