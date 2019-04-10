package test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
	
	

}
