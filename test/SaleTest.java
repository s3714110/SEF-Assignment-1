package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sale.Sale;
import sale.SaleLineItem;


public class SaleTest {

	static Double doubleTestAccuracy = 0.001;
	
	String employeeId_1;
	String customerId_1;
	String saleId_1;
	Sale sale;
	
	@Before
	public void init() {
		employeeId_1 = "e01";
		customerId_1 = "c01";
		saleId_1 = "s01";
		sale = new Sale(employeeId_1,customerId_1,saleId_1);
	}
	
	@Test
	public void test_getEmployeeId() {
		assertEquals(employeeId_1, sale.getEmployeeId());
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
		
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);		
		sale.addItem(item);
		
		int listSize = 1;
		assertEquals(listSize, sale.getItemList().size());
		assertTrue(sale.getItemList().contains(item));
	}
	
	@Test
	public void test_addItem_2() { // add 2 items
		
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		SaleLineItem item_2 = new SaleLineItem(productId_2,productName_2, qty_2, price_2);
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
				
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		sale.addItem(item);	
		sale.removeItem(productId, qty);	
		
		int listSize = 0;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
	}
	
		
	@Test
	public void test_removeItem_2() { // add 2 items remove 1
		
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		SaleLineItem item_2 = new SaleLineItem(productId_2,productName_2, qty_2, price_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		
		sale.removeItem(productId, qty);
		
		int listSize = 1;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
	}
	
	
	@Test
	public void test_removeItem_3() { // add 2 items remove 2
		
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		SaleLineItem item_2 = new SaleLineItem(productId_2,productName_2, qty_2, price_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		
		sale.removeItem(productId, qty);
		sale.removeItem(productId_2, qty_2);
		
		int listSize = 0;
		assertEquals(listSize, sale.getItemList().size());
		assertFalse(sale.getItemList().contains(item));
		assertFalse(sale.getItemList().contains(item_2));
	}
	
	@Test
	public void test_removeItem_4() { // add 1 item with qty 5 then remove 3
		
		String productId = "p01";
		String productName = "product1";
		int qty = 5;
		double price = 5.50;
				
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		sale.addItem(item);	
		
		int removeQty = 3;
		sale.removeItem(productId, removeQty);	
		
		int listSize = 1;
		int newQty = qty-removeQty;
		assertEquals(listSize, sale.getItemList().size());
		assertEquals(newQty, sale.getItem(productId).getQty());
		assertTrue(sale.getItemList().contains(item));
	}

	
	@Test
	public void test_removeAll() { 
		
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double price_2 = 10.00;
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		SaleLineItem item_2 = new SaleLineItem(productId_2,productName_2, qty_2, price_2);
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
		
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);		
		sale.addItem(item);		
		sale.getItem(productId).setDiscount(discount);
		
		item = sale.getItem(productId);		
		assertEquals(discount, item.getDiscount(), doubleTestAccuracy);
	}
	
	@Test
	public void test_setPrice() { 
		String productId = "p01";
		String productName = "product1";
		int qty = 1;
		double price = 5.50;
		
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);		
		sale.addItem(item);
		
		double newPrice = 2.75;
		sale.setPrice(productId, newPrice);
		
		item = sale.getItem(productId);		
		assertEquals(newPrice, item.getPrice(), doubleTestAccuracy);
	}
	
	@Test
	public void test_getTotal() { // 1 items
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double price = 5.50;
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
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
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		sale.addItem(item);
		sale.getItem(productId).setDiscount(discount);
		
		double total = price * qty - discount;
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);
	}
	
	@Test
	public void test_getTotal_3() { // 2 items with discount
		String productId = "p01";
		String productName = "product1";
		int qty = 2;
		double discount = 3.0;
		double price = 5.50;
		
		String productId_2 = "p02";
		String productName_2 = "product2";
		int qty_2 = 5;
		double discount_2 = 0.0;
		double price_2 = 10.00;
		
		SaleLineItem item = new SaleLineItem(productId,productName, qty, price);
		SaleLineItem item_2 = new SaleLineItem(productId_2,productName_2, qty_2, price_2);
		sale.addItem(item);		
		sale.addItem(item_2);
		sale.getItem(productId).setDiscount(discount);
		sale.getItem(productId_2).setDiscount(discount_2);
		
		double subTotal_1 = price * qty - discount;
		double subTotal_2 = price_2 * qty_2 - discount_2;
		double total = subTotal_1 + subTotal_2;
		
		assertEquals(total, sale.getTotal(), doubleTestAccuracy);
	}
	
	

}
