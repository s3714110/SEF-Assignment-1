import java.util.*;
public class ReceiptItem {
	private String productId; 
	private int qty = 1; 
	private double total;
	private ProductList productList;
	
	public ReceiptItem(String productId, ProductList productList) {
		this.productId = productId;
		this.productList = productList;
	}

	
	public String getProductId() {
		return productId;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public int getQty() {
		return qty;
	}
	
	public double getTotal() {
		// needs a products (collection of product) to get the single price of item
		double singlePrice = productList.getProduct(productId).getPrice();
		total = singlePrice * qty;
		if (qty >= productList.getProduct(productId).getQtyBulkDiscount()) {
			total = total - total * productList.getProduct(productId).getBulkDiscount()/100;
		}
		
		return total;
		
	}
	
	public String toString() {
		String str = String.format("Product Id: %s, Quantity: %s, Total Price: %s \n", getProductId(), getQty(), getTotal());
		return str;
	}
}
