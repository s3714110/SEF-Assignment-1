package test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.Customer;
import main.Product;
import main.Receipt;
import main.SuperMarketSystem;

public class Milestone1 {

	SuperMarketSystem supermarket;
	
	@Before
	public void init() {
		supermarket = new SuperMarketSystem();
	}
	
	@Test
	public void T1_reduceStockLevel() {
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		int qty = 200;
		
		int initialStockLevel = supermarket.getWarehouseInventory().get(productId).intValue();
		
		String employeeId = "e0004";
		String customerId = "c000004";
		String receiptId = "r1";
		
		Receipt receipt = new Receipt(employeeId, customerId, receiptId);
		receipt.addItem(product,qty);
		supermarket.processSale(receipt);
		
		assertEquals((initialStockLevel-qty), supermarket.getWarehouseInventory().get(productId).intValue());		
	}
	
	@Test
	public void T2_restock() {
		
		String productId = "vgmt560g";
		int qty = 200;
		
		int initialStockLevel = supermarket.getWarehouseInventory().get(productId).intValue();		
		supermarket.warehouseRestock(productId, qty);
		
		assertEquals((initialStockLevel+qty), supermarket.getWarehouseInventory().get(productId).intValue());		
	}
	
	@Test
	public void T3_computeSaleTotal() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int qty = 3;
		
		
		String employeeId = "e0004";
		String customerId = "c000004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, customerId, receiptId);
		receipt.addItem(product,qty);
		
	
		assertEquals((double)(price*qty), receipt.getTotal(), 0.001);		
	}
	
	
	@Test
	public void T4_applyStandardDiscount() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int bulkAmount = 1;
		double discount = 1;
		product.addDiscount(bulkAmount, discount);
		int qty = 3;
		
		
		String employeeId = "e0004";
		String customerId = "c000004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, customerId, receiptId);
		receipt.addItem(product,qty);
		
	
		assertEquals((double)(price*qty-qty*discount), receipt.getTotal(), 0.001);		
	}
	
	@Test
	public void T5_applyBulkDiscount() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int bulkAmount = 10;
		double discount = 10.99;
		product.addDiscount(bulkAmount, discount);
		int qty = 10;
		
		
		String employeeId = "e0004";
		String customerId = "c000004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, customerId, receiptId);
		receipt.addItem(product,qty);
		
	
		assertEquals((double)(price*qty-discount), receipt.getTotal(), 0.001);		
	}
	
	//New tests...
	
	@Test
	public void T6_nonDiscountedItems() {
		
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int bulkAmount = 1;
		double discount = 1;
		product.addDiscount(bulkAmount, discount);
		int qty = 3;
		
		String productId2 = "vgmt220g";
		Product product2 = supermarket.getProduct(productId2);
		double price2 = product2.getPrice();
		int qty2 = 3;
		
		
		String employeeId = "e0004";
		String customerId = "c000004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, customerId, receiptId);
		receipt.addItem(product,qty);
		receipt.addItem(product2, qty2);
		
	
		assertEquals((double)(price*qty-qty*discount+price2*qty2), receipt.getTotal(), 0.001);		
	}
	
	@Test
	public void T7_loyaltyPointsAllocated() {
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int qty = 3;
		
		
		String employeeId = "e0004";
		//String customerId = "c000004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, cust1.getId(), receiptId);
		receipt.addItem(product,qty);
		double total = receipt.getTotal();
		assertEquals(total/10,supermarket.calculateCustomerPoints(total), 0.001 );
	}
	
	
	//Found a bug with the get customer discount. This may not effect it in the real program, just for tests.
	//Since this method calls the consume points method, each time it gets used in the tests it consumes the points
	//Meaning we can't use the getCustomerPoints() method twice within the same test, as the first will get rid of the points
	//and leave the second with no points to use.
	//Therefore I have made the assertEquals test for the expected numerical value, not for the result of the expected equation like above.
	
	@Test
	public void T8_loyaltyDiscount() {
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(40);
		

		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int qty = 3;
		
		
		String employeeId = "e0004";
		String receiptId = "r1";
		//double customerDiscount = supermarket.getCustomerDiscount(cust1);
		//System.out.println(customerDiscount);
		
		Receipt receipt = new Receipt(employeeId, cust1.getId(), receiptId);
		receipt.addItem(product,qty);		
		assertEquals((double)(price*qty-10), (receipt.getTotal() - supermarket.getCustomerDiscount(cust1)),0.001);
	}
	
	@Test
	public void T9_loyaltyAndBulkDiscount() {
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(40);
		

		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int bulkAmount = 10;
		double discount = 10.99;
		product.addDiscount(bulkAmount, discount);
		int qty = 10;
		
		
		String employeeId = "e0004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, cust1.getId(), receiptId);
		receipt.addItem(product,qty);

		assertEquals(58.91, (receipt.getTotal() - supermarket.getCustomerDiscount(cust1)),0.001);
	}
	
	@Test
	public void T10_loyaltyAndPromotionDiscount() {
		Customer cust1 = new Customer("123", "Harris", "41 Happy st", "041234456");
		cust1.addPoints(40);
		

		String productId = "vgmt560g";
		Product product = supermarket.getProduct(productId);
		double price = product.getPrice();
		int bulkAmount = 1;
		double discount = 1;
		product.addDiscount(bulkAmount, discount);
		int qty = 3;
		
		String employeeId = "e0004";
		String receiptId = "r1";
		
		
		Receipt receipt = new Receipt(employeeId, cust1.getId(), receiptId);
		receipt.addItem(product,qty);
		
		//System.out.println(receipt.getTotal());

		assertEquals(10.97, (receipt.getTotal() - supermarket.getCustomerDiscount(cust1)),0.001);
	}
	
	

}
