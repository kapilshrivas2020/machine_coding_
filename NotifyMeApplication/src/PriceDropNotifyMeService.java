
public class PriceDropNotifyMeService implements NotifyMe{
	
	@Autowired
	private NotifyMeRepository notifyMeRepository;
	
	public void addObserver(User user, Product product) {
		
		NotifyRecord notifyMe = new NotifyRecord(user, product, PriceDrop);
		
		notifyMeRepository.save(notifyMe);
	}
	
	public void removeObserver(User user, Product product) {
		NotifyRecord notifyMe = findByUserAndProduct(user, product, PriceDrop);
		notifyMeRepository.delete(notifyMe);
	}
	
	public void notify(Product product) {
		List<User> userList = notifyMeRepository.findAll(product);
		
		for(User user : userList) {
			sendToNotificationQueue(user, product, PriceDrop);
			removeObserver(user, product, PriceDrop);
		}
	}

}
