package States;


// NoCoinInsertedState -> CoinInsertedState -> DispenseState -> NoCoinInsertedState

public interface State {
	public void insertCoins(double amount);
	public void pressButton(int rackNumber);
	public void dispense(int rackNumber);
}
