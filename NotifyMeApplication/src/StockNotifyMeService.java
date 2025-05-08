
public class StockNotifyMeService implements NotifyMe{
	
	@Autowired
	private NotifyMeRepository notifyMeRepository;
	
	public void addObserver(User user, Product product) {
		
		NotifyMe notifyMe = new NotifyMe(user, product, Stock);
		
		notifyMeRepository.save(notifyMe);
	}
	
	public void removeObserver(User user, Product product) {
		NotifyMe notifyMe = findByUserAndProduct(user, product, Stock);
		notifyMeRepository.delete(notifyMe);
	}
	
	public void notify(Product product) {
		List<User> userList = notifyMeRepository.findAll(product);
		
		for(User user : userList) {
			sendToNotificationQueue(user, product, Stock);
			removeObserver(user, product, Stock);
		}
	}

}
