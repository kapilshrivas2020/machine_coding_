package dao;

import java.util.Map;

public class Inventory {
	
	private Map<Product,Integer> productToQuantity;
	private Map<Integer, Product> rackNumberToProduct;
	int rackSize;
	int filledRackCount = 0;
	
	public Inventory(int rackCount){
		this.rackSize = rackCount;
	}
	
	public void addProduct(Product product, int quantity) {
		if(filledRackCount == rackSize) {
			throw new IllegalStateException("Racks are already full");
		}
		
		productToQuantity.put(product, quantity);
		rackNumberToProduct.put(filledRackCount, product);
	}
	
	public Product getProductAt(int rackNumber) {
		
		Product product = rackNumberToProduct.get(rackNumber);
		if(product == null)
			throw new IllegalStateException("Product not found on rack"); 
		
		return product;
	}
	
	public void deductProductCount(int rackNumber) {
		Product product = rackNumberToProduct.get(rackNumber);
		productToQuantity.put(product, productToQuantity.get(product) - 1);
		if(productToQuantity.get(product) == 0) {
			productToQuantity.remove(product);
			rackNumberToProduct.remove(rackNumber);
			filledRackCount--;
		}
	}
	
	public boolean checkProductAvailability(int productId) {
		for(Map.Entry<Integer, Product> rackProduct : rackNumberToProduct.entrySet()) {
			if(rackProduct.getValue().getId() == productId)
					return true;
		}
		return false;
	}
	

}
