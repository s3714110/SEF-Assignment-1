package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sale.Sale;
import sale.SaleLineItem;
import warehouse.Product;


public class SaleTest {

	static Double doubleTestAccuracy = 0.001;
	
	
	String customerId_1;
	String saleId_1;
	Sale sale;
	
	
	@Before
	public void init() {
		customerId_1 = "c01";
		saleId_1 = "s01";
		sale = new Sale(saleId_1,customerId_1);
	}
	
	
	@Test
	public void test_getCustomerId() {
		assertEquals(customerId_1, sale.getCustomerId());
	}
	
	@Test
	public void test_getSaleId() {
		assertEquals(saleId_1, sale.getSaleId());
	}
	
	@Test
	public void test_addItem_1() { // add item
		
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		Product product = new Product(productId, productName, "type", price);
		
		
		SaleLineItem item = new SaleLineItem(product, qty);		
		sale.addItem(item);
		
		int listSize = 1;
		assertEquals(listSize, sale.getItemList().size());
		assertTrue(sale.getItemList().contains(item));
	}
	
	@Test
	public void test_addItem_2() { // add 2 items
		
		String productId_1 = "p01";
		String productName_1 = "product1";
		int qty = 2;
		double price_1 = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		Product product1 = new Product(productId_1, productName_1, "type", price_1);
		Product product2 = new Product(productId_2, productName_2, "type",price_2);
		
		SaleLineItem item = new SaleLineItem(product1, qty);
		SaleLineItem item_2 = new SaleLineItem(product2, qty_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		
		int listSize = 2;
		assertEquals(listSize, sale.getItemList().size());
		assertTrue(sale.getItemList().contains(item));
		assertTrue(sale.getItemList().contains(item_2));
	}
	
	@Test
	public void test_removeItem_1() { // add 1 item then remove
		
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
				
		Product product = new Product(productId, productName, "type", price);
		
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);	
		sale.removeItem(productId);	
		
		int listSize = 0;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
	}
	
		
	@Test
	public void test_removeItem_2() { // add 2 items remove 1
		
		String productId_1 = "p01";
		String productName_1 = "product1";
		int qty = 2;
		double price_1 = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		Product product1 = new Product(productId_1, productName_1, "type", price_1);
		Product product2 = new Product(productId_2, productName_2, "type",price_2);
		
		SaleLineItem item = new SaleLineItem(product1, qty);
		SaleLineItem item_2 = new SaleLineItem(product2, qty_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		
		sale.removeItem(productId_1);
		
		int listSize = 1;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
	}
	
	
	@Test
	public void test_removeItem_3() { // add 2 items remove 2
		
		String productId_1 = "p01";
		String productName_1 = "product1";
		int qty = 2;
		double price_1 = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		Product product1 = new Product(productId_1, productName_1, "type", price_1);
		Product product2 = new Product(productId_2, productName_2, "type",price_2);
		
		
		SaleLineItem item = new SaleLineItem(product1, qty);
		SaleLineItem item_2 = new SaleLineItem(product2, qty_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		
		sale.removeItem(productId_1);
		sale.removeItem(productId_2);
		
		int listSize = 0;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
		assertFalse(sale.getItemList().contains(item_2));
	}
	
	
	
	@Test
	public void test_removeAll() { 
		
		String productId_1 = "p01";
		String productName_1 = "product1";
		int qty = 2;
		double price_1 = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		Product product1 = new Product(productId_1, productName_1, "type", price_1);
		Product product2 = new Product(productId_2, productName_2, "type",price_2);
		
		
		SaleLineItem item = new SaleLineItem(product1, qty);
		SaleLineItem item_2 = new SaleLineItem(product2, qty_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		
		sale.removeAllItems();
		
		int listSize = 0;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
		assertFalse(sale.getItemList().contains(item_2));
	}
	
	@Test
	public void test_setDiscount() { 
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double discount = 3.0;
		double price = 5.50;
		
		Product product = new Product(productId, productName, "type", price);
		
		
		SaleLineItem item = new SaleLineItem(product, qty);		
		sale.addItem(item);		
		sale.getItem(productId).setDiscount(discount);
		
		item = sale.getItem(productId);		
		assertEquals(discount, item.getDiscount(), doubleTestAccuracy);
	}
	
	@Test
	public void test_setUnitPrice() { 
		String productId = "p01";
		String productName = "product1";
		int qty = 1;
		double price = 5.50;
		
		Product product = new Product(productId, productName, "type", price);
		
		
		SaleLineItem item = new SaleLineItem(product, qty);		
		sale.addItem(item);
		
		double newUnitPrice = 2.75;
		sale.setUnitPrice(productId, newUnitPrice);
		
		item = sale.getItem(productId);		
		assertEquals(newUnitPrice, item.getUnitPrice(), doubleTestAccuracy);
	}
	
	
	@Test
	public void test_getTotal() { // 1 items
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		Product product = new Product(productId, productName, "type", price);
		
		
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);
		double total = price * qty;
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);
	}
	
	@Test
	public void test_getTotalwDiscount() {// 1 item with discount
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double discount = 4.50;
		double price = 5.50;
		
		Product product = new Product(productId, productName, "type", price);
		
		
		SaleLineItem item = new SaleLineItem(product, qty);
		sale.addItem(item);
		sale.getItem(productId).setDiscount(discount);
		
		double total = price * qty - discount;
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);
	}
	
	@Test
	public void test_getTotal_3() { // 2 items with discount
		String productId_1 = "p01";
		String productName_1 = "product1";
		int qty = 2;
		double discount = 3.0;
		double price_1 = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double discount_2 = 0.0;
		double price_2 = 10.00;
		
		Product product1 = new Product(productId_1, productName_1, "type", price_1);
		Product product2 = new Product(productId_2, productName_2, "type",price_2);
		
		
		SaleLineItem item = new SaleLineItem(product1, qty);
		SaleLineItem item_2 = new SaleLineItem(product2, qty_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		sale.getItem(productId_1).setDiscount(discount);
		sale.getItem(productId_2).setDiscount(discount_2);
		
		double subTotal_1 = price_1 * qty - discount;
		double subTotal_2 = price_2 * qty_2 - discount_2;
		double total = subTotal_1 + subTotal_2;
		
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);
	}
	
	

}
