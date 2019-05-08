package test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;

import model.SupermarketSystem;
import sale.Sale;
import sale.SaleLineItem;
import user.Customer;
import warehouse.Product;

import org.junit.Before;
import org.junit.Test;

public class Milestone1 {

	static Double doubleTestAccuracy = 0.001;
	SupermarketSystem supermarket;
	
	@Before
	public void init() {
		supermarket = new SupermarketSystem();
	}
	
	@Test
	public void T1_reduceStockLevel() {
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		int qty = 200;
		double price = product.getPrice();
		
		int initialStockLevel = supermarket.getStockLevel(productId);
		
		String customerId = "c000004";
		String saleId = "r1";
		
		
		Sale sale = new Sale(saleId, customerId);
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);
		supermarket.processSale(sale);
				
		
		assertEquals((initialStockLevel-qty), supermarket.getStockLevel(productId));
		
	}
	
	@Test
	public void T2_restock() {
		
		String productId = "vgmt560g";
		int qty = 200;
		
		int initialStockLevel = supermarket.getStockLevel(productId);		
		supermarket.restock(productId, qty);
		
		assertEquals((initialStockLevel+qty), supermarket.getStockLevel(productId));		
	}
	
	@Test
	public void T3_computeSaleTotal() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int qty = 3;
		
		
		String customerId = "c000004";
		String saleId = "r1";
		
		
		Sale sale = new Sale(saleId, customerId);
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);
		
	
		assertEquals((double)(price*qty), sale.getTotal(), doubleTestAccuracy);		
	}
	
	
	@Test
	public void T4_applyStandardDiscount() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		int qty = 3;	
		double price = product.getPrice();
		
		double discount = 1;
		supermarket.addPromotionDiscount(productId, discount);
		
		
		String customerId = "c000004";
		String saleId = "r1";
		
			
		
		Sale sale = new Sale(saleId, customerId);
		SaleLineItem item = new SaleLineItem(product, qty);
		double itemDiscount = supermarket.calculatePromotionDiscount(productId, qty);
		item.setDiscount(itemDiscount);
		sale.addItem(item);
		
		double total = price*qty-itemDiscount;
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);		
	}
	
	@Test
	public void T5_applyBulkDiscount() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		String productName = product.getName();
		double price = product.getPrice();
		int bulkAmount = 10;
		double discount = 10.99;
		supermarket.addBulkDiscount(productId, bulkAmount, discount);
		int qty = 10;
		
		
		String customerId = "c000004";
		String saleId = "r1";
		
		
			
		Sale sale = new Sale(saleId, customerId);		
		SaleLineItem item = new SaleLineItem(product, qty);
		double itemDiscount = supermarket.calculateBulkDiscount(productId, qty);
		item.setDiscount(itemDiscount);
		sale.addItem(item);
		
		double total = price*qty-itemDiscount;
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);		
	}
	
	
	@Test
	public void T6_nonDiscountedItems() {
		
		String productId = "vgmt560g";		
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int bulkAmount = 1;
		double discount = 1;
		supermarket.addBulkDiscount(productId, bulkAmount, discount);
		int qty = 3;
		
		String productId2 = "vgmt220g";
		Product product2 = supermarket.getProduct(productId2);
		double price2 = product2.getPrice();
		int qty2 = 3;
		
		
		String customerId = "c000004";
		String saleId = "r1";
		
		
		Sale sale = new Sale(saleId, customerId);
		SaleLineItem item = new SaleLineItem(product, qty);
		double itemDiscount = supermarket.calculateBulkDiscount(productId, qty);
		item.setDiscount(itemDiscount);
		SaleLineItem item_2 = new SaleLineItem(product2, qty2);
		sale.addItem(item);
		sale.addItem(item_2);
		
		double subTotal_1 = price * qty - itemDiscount;
		double subTotal_2 = price2 * qty2;
		double total = subTotal_1 + subTotal_2;
	
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);		
	}
	
	@Test
	public void T7_loyaltyPointsAllocated() {
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);

		double price = product.getPrice();
		int qty = 3;
		
		
		String saleId = "r1";
		
		
		Sale sale = new Sale(saleId, cust1.getId());
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);
		
		double total = price * qty;
		double dollarPerPoint = 10;
		
		assertEquals(total/dollarPerPoint,sale.calculateCustomerPoints(), doubleTestAccuracy);
	}
	
	
	//Found a bug with the get customer discount. This may not effect it in the real program, just for tests.
	//Since this method calls the consume points method, each time it gets used in the tests it consumes the points
	//Meaning we can't use the getCustomerPoints() method twice within the same test, as the first will get rid of the points
	//and leave the second with no points to use.
	//Therefore I have made the assertEquals test for the expected numerical value, not for the result of the expected equation like above.
	//
	//Fixed
	
	@Test
	public void T8_loyaltyDiscount() {
		int points = 40;
		double discount = 5.0;
		double pointsPerDiscount = 20.0;
		
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(points);
		

		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);

		double price = product.getPrice();
		int qty = 3;
		


		String saleId = "r1";
		
		Sale sale = new Sale(saleId, cust1.getId());
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);
		
		double total = price * qty;
		double custDiscount = (double)(points/pointsPerDiscount) * discount;
		assertEquals(total-custDiscount, (sale.getTotal() - cust1.getCustomerDiscount()), doubleTestAccuracy);
	}
	
	@Test
	public void T9_loyaltyAndBulkDiscount() {
		int points = 40;
		double custDiscount = 5.0;
		double pointsPerDiscount = 20.0;
		
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(points);
		

		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		String productName = product.getName();
		double price = product.getPrice();
		int bulkAmount = 10;
		double discount = 10.99;
		supermarket.addBulkDiscount(productId, bulkAmount, discount);
		int qty = 10;
		
		
		String employeeId = "e0004";
		String saleId = "r1";
		
		
		Sale sale = new Sale(saleId, cust1.getId());
		SaleLineItem item = new SaleLineItem(product, qty);
		double itemDiscount = supermarket.calculateBulkDiscount(productId, qty);
		item.setDiscount(itemDiscount);
		sale.addItem(item);
		
		double total = price * qty - itemDiscount;
		double loyaltyDiscount = (double)(points/pointsPerDiscount) * custDiscount;
		
		assertEquals(total - loyaltyDiscount, (sale.getTotal()- cust1.getCustomerDiscount()), doubleTestAccuracy);
	}
	
	@Test
	public void T10_loyaltyAndPromotionDiscount() {
		int points = 40;
		double custDiscount = 5.0;
		double pointsPerDiscount = 20.0;
		
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(points);
		

		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);

		double price = product.getPrice();
		int bulkAmount = 1;
		double discount = 1;
		supermarket.addBulkDiscount(productId, bulkAmount, discount);
		int qty = 3;
		
		String saleId = "r1";
		
		
		Sale sale = new Sale(saleId, cust1.getId());
		SaleLineItem item = new SaleLineItem(product, qty);
		double itemDiscount = supermarket.calculatePromotionDiscount(productId, qty);
		item.setDiscount(itemDiscount);
		sale.addItem(item);
		
		double total = price * qty - itemDiscount;
		double loyaltyDiscount = (double)(points/pointsPerDiscount) * custDiscount;
		
		assertEquals(total - loyaltyDiscount, (sale.getTotal() - cust1.getCustomerDiscount()), doubleTestAccuracy);
	}

	
	

}
