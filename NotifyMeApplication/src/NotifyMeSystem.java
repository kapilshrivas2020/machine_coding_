
public class NotifyMeSystem {
	
	User user1 = new User();
	User user2 = new User();
	User user3 = new User();
	
	NotifyMe stockNotificationService = new StockNotifyMeService();
	NotifyMe priceDropNotifyMeService = new PriceDropNotifyMeService();
	
	Product p1 = new Product();
	Product p2 = new Product();
	
	stockNotifyMeService.addObserver(user1, p1);
	priceDropNotifyMeService.addObserver(user2, p1);
	stockNotifyMeService.addObserver(user2, p2);
	
	//InventoryService inventoryService = new InventoryService();
	ProductService productService = new ProductService(stockNotificationService, priceDropNotifyMeService);
	productService.setPrice(p1, 100);
	productService.setQuantity(p1, 4);
}
