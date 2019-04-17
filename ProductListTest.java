package tests;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.Product;
import main.ProductList;

public class ProductListTest {

	ProductList productList;
	String test;
	@Before
	public void setUp() throws Exception {
		 productList = new ProductList();
	}

	@Test
	public void testAddProduct() {
		test = "";
		productList.addProduct("1", "Chicken", "Food", 5.00);
		test += "Product ID: 1, Product Name: Chicken, Type: Food, Price: 5.00 \n";
		
		Assert.assertEquals(test, productList.printAllProducts());
		productList.addProduct("2", "Soap", "Kitchen", 10.00);
		
		test += "Product ID: 2, Product Name: Soap, Type: Kitchen, Price: 10.00 \n";
		Assert.assertEquals(test, productList.printAllProducts());
	}
	
	@Test
	public void testRemoveProduct() {
		test = "";
		productList.addProduct("1", "Chicken", "Food", 5.00);
		productList.addProduct("2", "Soap", "Kitchen", 10.00);
		productList.removeProduct("1");
		test += "Product ID: 2, Product Name: Soap, Type: Kitchen, Price: 10.00 \n";
		Assert.assertEquals(null,productList.getProduct("1") );
		Assert.assertEquals(test, productList.printAllProducts());	
	}
	
	@Test
	public void testReturnProduct() {
		productList.addProduct("1", "Chicken", "Food", 5.00);
		Product productTest = new Product("1", "Chicken", "Food", 5.00);
		
		productList.addProduct("2", "Soap", "Kitchen", 10.00);
		
		Assert.assertEquals(productTest.getID(), productList.getProduct("1").getID());
		Assert.assertEquals(productTest.getName(), productList.getProduct("1").getName());
		Assert.assertEquals(productTest.getType(), productList.getProduct("1").getType());
		Assert.assertEquals(productTest.getPrice(), productList.getProduct("1").getPrice(),0);
	}

}
