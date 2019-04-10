package main;
import java.util.HashMap;
import java.util.Map;

public class Product {
	
	private String id;
	private String name;
	private String type;
	private double price;
	private Map<Integer,Double> discounts;
	
	Product(String id, String name, String type, double price){
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
		
		System.out.println("Product.equals()");
		if (object instanceof String) {
			System.out.println("String Instance");
			System.out.println();
	        String _product = (String)object;
	        if (0 == id.compareTo(_product)) {
	           equal = true;
	        }
	    }
		else if (object instanceof Product) {
			System.out.println("Product Instance");
	        Product _product = (Product)object;
	        if (0 == id.compareTo(_product.getId())) {
	           equal = true;
	        }
	    }
		
		
		return equal;
	}
	
	@Override
	public String toString() {
		return "Product: \n\tid:  " + id + "\n\tname:  " + name + "\n\ttype:  " + type + "\n\tprice:  " + Double.toString(price);
	}
	
}
