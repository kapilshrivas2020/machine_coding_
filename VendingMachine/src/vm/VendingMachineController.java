package vm;

import java.util.HashMap;
import java.util.Map;

import States.IdleState;
import States.State;
import dao.Product;

public class VendingMachineController {
	
	public static void main(String args[]) {
		VendingMachine machine1 = new VendingMachine();
		
		machine1.initialize();
		State currentVendingMachine = new IdleState(machine1);
		
		//machine1.choosePaymentMethod();
		currentVendingMachine.insertCoins(10.0);
		currentVendingMachine.pressButton(2);
		currentVendingMachine.dispense(2);
	}

}
