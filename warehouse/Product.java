package warehouse;

public class Product {
	
	private String id;
	private String name;
	private String type;
	private double price;	
	
	public Product(String id, String name, String type, double price){
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
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
