package main;
import java.util.*;

public class ProductList {
	
	private Map<String, Product> products = new HashMap<String, Product>();
	
	public void addProduct(String id, String name, String type, double price) {
		products.put(id, new Product( id,  name,  type,  price));
	}
	
	public void removeProduct(String id) {
		products.remove(id);
	}
	
	public Product getProduct(String id) {
		
		return products.get(id);
			
	}
	
	public String printAllProducts() {
		String total = "";
		
		for (String id: products.keySet()) {
			total += String.format("Product ID: %s, Product Name: %s, Type: %s, Price: %.2f \n", products.get(id).getID(),
					products.get(id).getName(), products.get(id).getType(), products.get(id).getPrice());
		}
		
		return total;
	}
	
	
}
