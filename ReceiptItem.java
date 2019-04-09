import java.util.*;
public class ReceiptItem {
	private String productId; 
	private int qty; 
	private int total;
	
	public ReceiptItem(String productId) {
		this.productId = productId;
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
	
	public int getTotal() {
		// needs a products (collection of product) to get the single price of item
		return 0;
		
	}
	
	public String toString() {
		String str = String.format("Product Id: %s, Quantity: %s, Total Price: %s \n", getProductId(), getQty(), getTotal());
		return str;
	}
}
