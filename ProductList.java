import java.util.*;

public class ProductList {
	
	private List<Product> products = new LinkedList<Product>();
	
	public void addProduct(String id, String name, String type, double price) {
		products.add(new Product( id,  name,  type,  price));
	}
	
	public void removeProduct(String id) {
		for(Product temp: products) {
			if(temp.getID() == id) {
				products.remove(temp);
			}
		}
	}
	
	public Product getProduct(String id) {
		for(Product temp: products) {
			if(temp.getID() == id) {
				return temp;
			}
		}
		return null;
	}
	
	
}
