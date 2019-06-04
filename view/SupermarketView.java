package view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import controller.SupermarketController;
import model.SupermarketSystem;
import sale.Sale;
import sale.SaleLineItem;
import warehouse.Product;

public abstract class SupermarketView {

	protected SupermarketSystem supermarket;
	
	SupermarketView(SupermarketSystem supermarket) {
		this.supermarket = supermarket;
	}
	
	public abstract void setController(SupermarketController controller);
	
	public abstract void show();
	
	public void showMessage(String message) {
		System.out.println("\n" + message + "\n");
	}
	public void showSale(Sale sale) {
		
		int count = 0;
		String output;		
		
		
		output = String.format("     %-32s %-10s %-3s %-8s %-8s", "Product Name", "Unit Price", "Qty", "Discount", "Subtotal");
		System.out.println(output);
		
		output = "----------------------------------------------------------------";
		System.out.println(output);
		
		if(sale == null) {
			System.out.println("\n\tCart Empty\n\n");
		}
		else {
			List<SaleLineItem> saleList = sale.getItemList();
			for (SaleLineItem item : saleList) {
				count++;
				output = String.format("%2d |   %-30s %10.2f %3d %8.2f %6.2f", count, item.getProductName(), item.getUnitPrice(), item.getQty(), item.getDiscount(), item.getSubtotal());
				System.out.println(output);
			}
		}
	}
	
	public void showProductList() {
		
		List<Product> productList = supermarket.getProductList();
		
		int count = 0;
		String output;	
		
		output = String.format("      %-10s %-30s" , "ID ","Product Name");
		System.out.println(output);
		
		output = "--------------------------------------";
		System.out.println(output);
		
		for (Product product : productList) {
			count++;
			output = String.format("%2d |  %-10s %-30s", count, product.getId(), product.getName());
			System.out.println(output);
		}
	}
	
	public void showActiveSales() {
		
		List<Sale> saleList = supermarket.getActiveSalesList();
		int count = 0;
		String output;	
		
		output = "     Customer ID";
		System.out.println(output);
		
		output = "--------------------------------------";
		System.out.println(output);
		
		if(saleList.size() == 0) {
			output = "\n\tNo Active Sales";
			System.out.println(output);
		}
		else {
			for (Sale sale : saleList) {
				count++;
				output = String.format("%2d |   %-7s", count, sale.getCustomerId());
				System.out.println(output);
			}
		}
	}
	
	public void showBulkDiscounts(String productId){
		
		Map<Integer,Double> bulkDiscounts = supermarket.getBulkDiscounts(productId);
		Product product = supermarket.getProduct(productId);
		
		String output;
		
		output = "     Bulk Discount: " + product.getName() + "(" + product.getId() + ")";
		System.out.println(output);
		
		output = "--------------------------------------";
		System.out.println(output);
		
		if(bulkDiscounts == null) {
			output = "\n\t Nill";
			System.out.println(output);
		}
		else {
			Set<Map.Entry<Integer,Double>> discountList = bulkDiscounts.entrySet();
			Iterator<Map.Entry<Integer,Double>> itr = discountList.iterator();
			
			while(itr.hasNext()) {
				Map.Entry<Integer,Double> entry = itr.next();
				String.format("%3d :   %-5.2s", entry.getKey().intValue(), entry.getValue().doubleValue());				
			}
			System.out.println(output);
		}		
	}
	
	public void showEnterQty() {
		System.out.println("\nEnter Qty: ");
	}
	
	
	public void showTotalTransaction(double total) {
		String output = String.format("Total:  %-6.2f", total);
		System.out.println(output);
	}
	
	
	
}
