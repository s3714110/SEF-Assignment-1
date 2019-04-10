

public class Product {

	private String id;
	private String name;
	private String type;
	private double price;
	private double discount;
	private double bulkDiscount;
	private int qtyBulkDiscount;

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
	
	public void setDiscount(double percentage) {
		discount = percentage;
		price = price - price * percentage/100;
	}
	
	public void setBulkDiscount(double percentage, int qty){
		bulkDiscount = percentage;
		qtyBulkDiscount = qty;
	}
	
	public double getBulkDiscount() {
		return bulkDiscount;
	}
	
	public double getQtyBulkDiscount() {
		return qtyBulkDiscount;
	}
}