package States;

import dao.Inventory;
import dao.Product;
import vm.VendingMachine;

public class DispenseState implements State{
	
	private VendingMachine vendingMachine;
	
	public DispenseState(VendingMachine machine) {
		this.vendingMachine = machine;
	}

	@Override
	public void insertCoins(double amount) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Dispense Operation Not Allowed without inserting coins");
	}

	@Override
	public void pressButton(int rackNumber) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Product already choosed");
	}

	@Override
	public void dispense(int rackNumber) {
		// TODO Auto-generated method stub
		Inventory inventory = vendingMachine.getInventory();
		Product product = inventory.getProductAt(rackNumber);
		
		inventory.deductProductCount(rackNumber);
		double change = vendingMachine.getAmount() - product.getPrice();
		vendingMachine.setAmount(0);
		
		vendingMachine.setCurrentState(new IdleState(vendingMachine));
		
	}

}
