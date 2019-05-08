 package controller;


import java.util.List;
import java.util.Map;

import model.SupermarketSystem;
import sale.Sale;
import sale.SaleLineItem;
import view.SupermarketCustomerView;
import view.SupermarketView;
import warehouse.Product;

public class SupermarketCustomerController extends SupermarketController {
	
	public static enum STATE { START, ADD_PRODUCT, CHECK_PRICE, CHECK_BULK_DISCOUNT, VIEW_CART, CHECKOUT, VIEW_PRODUCT_LIST };
	
	public final static String add_product = "1";
	public final static String check_price = "2";
	public final static String check_bulk_discount = "3";
	public final static String view_cart = "4";	
	public final static String checkout = "5";	
	public final static String view_product_list = "6";	
	
	private String customerId;
	private STATE state;	
	private SupermarketCustomerView view;
	
	public SupermarketCustomerController(SupermarketSystem supermarket, SupermarketView view, String customerId) {
		super(supermarket, view);
		this.view = (SupermarketCustomerView)view;
		this.customerId = customerId;
		state = STATE.START;
	}

	public void setStateStart() {
		state = STATE.START;
	}
	public STATE getState() {
		return state;
	}
	public String getCustomerId() {
		return customerId;
	}
	
	
	// getUserInput() calls this function
	public boolean processInput(String userinput) {
		boolean validInput = false;
		
		// logout
		if(0 == userinput.toLowerCase().compareTo(logout)) {
			logout();
			return true;
		}
		if(0 == userinput.toLowerCase().compareTo(back)) {
			state = STATE.START;
			return true;
		}
		
		
		// continue process input
		switch (state)
		{
			case START:
				validInput = processStart(userinput);
				break;
			case ADD_PRODUCT:
				validInput = processAddProduct(userinput);
				break;
			case CHECK_PRICE:
				validInput = processCheckPrice(userinput);
				break;
			case CHECK_BULK_DISCOUNT:
				validInput = processCheckBulkDiscount(userinput);
				break;
			case VIEW_CART:
				break;
			case CHECKOUT:
				break;
			case VIEW_PRODUCT_LIST:
				break;				
		}
		
		return validInput;
	}
	
	
	
	public boolean processStart(String userinput) {
		boolean validInput = false;
		
		
		// process input
		if(0 == userinput.compareTo(add_product)) {
			state = STATE.ADD_PRODUCT;
			validInput = true;
		}
		else if(0 == userinput.compareTo(check_price)) {
			state = STATE.CHECK_PRICE;
			validInput = true;
		}
		else if(0 == userinput.compareTo(check_bulk_discount)) {
			state = STATE.CHECK_BULK_DISCOUNT;
			validInput = true;
		}
		else if(0 == userinput.compareTo(view_cart)) {
			state = STATE.VIEW_CART;
			validInput = true;
		}
		else if(0 == userinput.compareTo(checkout)) {
			state = STATE.CHECKOUT;
			validInput = true;
		} 
		else if(0 == userinput.compareTo(view_product_list)) {
			state = STATE.VIEW_PRODUCT_LIST;
			validInput = true;
		} 
		
		return validInput;
	}
	
	public boolean processAddProduct(String userinput) {
		boolean validInput = false;
		int selected;
		List<Product> productList = supermarket.getProductList();
		
		try {
			selected = Integer.parseInt(userinput);
			
			
			if(selected > 0 && selected <= productList.size()) {
				int qty = -1;
						
				do {
					qty = getQty();
				} while (qty < 0);
				 
				
				int index = selected - 1;
				Product product = productList.get(index);
				supermarket.addItem(customerId, product, qty);
				
				
				state = STATE.START;
			}			
		}
		catch(NumberFormatException e) {
			
		}
		
		
		return validInput;
	}
	public boolean processCheckPrice(String userinput) {
		boolean validInput = false;
		
		if(supermarket.validProductId(userinput)) {
			String productId = userinput;
			view.showPrice(supermarket.getProduct(productId).getPrice());
			
			state = STATE.START;
		}
		
		return validInput;
	}
	public boolean processCheckBulkDiscount(String userinput) {
		boolean validInput = false;
		
		if(supermarket.validProductId(userinput)) {
			String productId = userinput;
			view.showBulkDiscounts(productId);
			
			state = STATE.START;
		}
		
		return validInput;
	}
	public boolean processCheckout() {
		boolean success = false;
		
		Sale sale = supermarket.getActiveSale(customerId);
		if(sale != null) {
			supermarket.processSale(sale);
			success = true;
		}
		
		return success;
	}

}
