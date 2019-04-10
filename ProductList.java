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
	
	
}
