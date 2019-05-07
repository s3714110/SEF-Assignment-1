package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sale.SaleLineItem;

public class SaleLinetItemTest {

	
	final Double doubleTestAccuracy = 0.001;	
	
	String productId;
	String productName;
	int qty;
	double price;
	SaleLineItem item;
	
	@Before
	public void init() {
		productId = "p01";
		productName = "product1";
		qty  = 3;
		price = 5.50;
		
		item = new SaleLineItem(productId,productName,qty,price);
	}
	
	@Test
	public void test_getProductId() {
		assertEquals(productId, item.getProductId());
	}
	
	@Test
	public void test_getProductName() {
		assertEquals(productName, item.getProductName());
	}
	
	@Test
	public void test_getQty() {
		assertEquals(qty, item.getQty());
	}
			
	@Test
	public void test_getPrice() {
		assertEquals(price, item.getPrice(), doubleTestAccuracy);
	}
	
	@Test
	public void test_getDiscount() {
		double discount = 0.0;
		assertEquals(discount, item.getDiscount(), doubleTestAccuracy);
	}
	
		
	@Test	
	public void test_add() {
		int addQty = 5;
		
		item.add(addQty);
		
		int newQty = qty + addQty;
		assertEquals(newQty, item.getQty());
	}
	
	@Test
	public void test_remove() {
		int removeQty = 1;
		
		item.remove(removeQty);
		
		int newQty = qty - removeQty;
		assertEquals(newQty, item.getQty());
	}
	
	@Test
	public void test_setDiscount() {
		double discount = 2.0;
		
		item.setDiscount(discount);
		
		assertEquals(discount, item.getDiscount(), doubleTestAccuracy);
	}
	
	@Test
	public void test_getSubtotal_1() {	// without discount
				
		double subtotal = qty * price;
		assertEquals(subtotal, item.getSubtotal(), doubleTestAccuracy);		
	}
	
	@Test
	public void test_getSubtotal_2() {	// with discount
		double discount = 2.0;
		
		item.setDiscount(discount);
		
		double subtotal = qty * price - discount;
		assertEquals(subtotal, item.getSubtotal(), doubleTestAccuracy);			
	}
	
	@Test
	public void test_setPrice() {
		double discount = 1.0;
		double newPrice = 3.5;
		
		item.setDiscount(discount);
		item.setPrice(newPrice);
		
		double newDiscount = 0.0;
		assertEquals(newPrice, item.getPrice(), doubleTestAccuracy);	
		assertEquals(newDiscount, item.getDiscount(), doubleTestAccuracy);	
	}

}
