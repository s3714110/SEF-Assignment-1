package product;

public class Product {

	private String id;
	private String name;
	private String type;
	private double price;
	private double discount;
	private double bulkDiscount;

	public Product(String id, String name, String type, double price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public String getID() {
		return id;
	}

	public String getType() {
		return type;
	}

	public double getPrice() {
		return price;
	}

	public double getDiscount() {
		return discount;
	}

	public double getBulkDiscount() {
		return bulkDiscount;
	}
}
