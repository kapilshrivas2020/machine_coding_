
public interface NotifyMe {
	
	void addObserver(User user, Product product);
	void removeObserver(User user, Product product);
	void notify(Product product);

}
