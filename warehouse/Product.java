package warehouse;
import java.util.HashMap;
import java.util.Map;

public class Product {
	
	private String id;
	private String name;
	private String type;
	private double price;
	private Map<Integer,Double> discounts;
	
	public Product(String id, String name, String type, double price){
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		discounts = new HashMap<Integer, Double>();
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public double getPrice() {
		return price;
	}
	public Map<Integer,Double> getDiscounts() {
		return discounts;
	}
	
	public void addDiscount(Integer qty, Double discount) {
		discounts.put(qty, discount);
	}
	public void removeDiscount(Integer qty, Double discount) {
		discounts.remove(qty, discount);
	}
	public void removeAllBulkDiscount(Integer qty, Double discount) {
		discounts.clear();
	}
		
	@Override
	public boolean equals(Object object) {
		boolean equal = false;
				
		
	    Product product = (Product)object;
	    if (0 == id.compareTo(product.getId())) {
	       equal = true;
	    }		
		
		return equal;
	}
	
	@Override
	public String toString() {
		return "Product: \n\tid:  " + id + "\n\tname:  " + name + "\n\ttype:  " + type + "\n\tprice:  " + Double.toString(price);
	}
	
}
