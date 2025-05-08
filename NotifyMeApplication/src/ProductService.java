
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	private NotifyMe stockNotifyService;
	private NotifyMe priceDropNotifyService;
	
	public ProductService(NotifyMe stockNotifyService, NotifyMe priceDropNotifyService) {
		this.stockNotifyService = stockNotifyService;
		this.priceDropNotifyService = priceDropNotifyService;
	}
	
	public void setPrice(Product product, int price) {
		Product p = productRepository.findById(product);
		
		p.setPrice(price);
		productReposiotry.save(p);
		
		if(p.getPrice() > price) {
			priceDropNotifyService.notify(p);
		}
	}
	
	public void setQuantity(Product product, int quantity){
		Product p = productRepository.findById(product);
		
		p.setQuantity(quantity);
		productRepository.save(p);
		
		if(p.getQuantity() == 0 && notifyMeService != null) {
			stockNotifyService.notify(p);
		}
	}

}
