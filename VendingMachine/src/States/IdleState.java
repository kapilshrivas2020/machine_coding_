package States;

import vm.VendingMachine;

public class IdleState implements State{
	
	VendingMachine vendingMachine;
	
	public IdleState(VendingMachine machine) {
		this.vendingMachine = machine;
	}

	@Override
	public void insertCoins(double amount) {
		// TODO Auto-generated method stub
		vendingMachine.setAmount(amount);
		vendingMachine.setCurrentState(new CoinInsertedState(vendingMachine));
	}

	@Override
	public void pressButton(int rackNumber) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("No coins inserted");
	}

	@Override
	public void dispense(int rackNumber) {
		// TODO Auto-generated method stub
		throw new IllegalStateException("Dispense Operation Not Allowed without inserting coins");
	}

}
