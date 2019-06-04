package view;


import controller.SupermarketController;
import controller.SupermarketCustomerController;
import model.SupermarketSystem;
import sale.Sale;

public class SupermarketCustomerView extends SupermarketView {

	private SupermarketCustomerController controller;
		
	public SupermarketCustomerView(SupermarketSystem supermarket) {
		super(supermarket);
	}
	
	public void setController(SupermarketController controller) {
		this.controller = (SupermarketCustomerController)controller;
	}
	
	
	public void show() {
	
		switch (controller.getState())
		{
			case START:
				showStart();
				controller.processInput(controller.getUserInput());
				break;
			case ADD_PRODUCT:
				showAddProduct();
				controller.processInput(controller.getUserInput());	
				break;
			case CHECK_PRICE:
				showCheckPrice();
				controller.processInput(controller.getUserInput());	
				break;
			case CHECK_BULK_DISCOUNT:
				showCheckBulkDiscount();
				controller.processInput(controller.getUserInput());	
				break;
			case VIEW_CART:
				showViewCart();
				controller.setStateStart();
				break;
			case CHECKOUT:
				boolean success = controller.processCheckout();
				showCheckout(success);
				
				if(success) {
					controller.logout();
				}
				else {
					controller.setStateStart();
				}
				break;
			case VIEW_PRODUCT_LIST:
				showViewProductList();
				controller.setStateStart();
				break;
		}
	}
	
	public void showStart() {
		String show = "\n\n";
		
		String customerId = controller.getCustomerId();
		show = show.concat(supermarket.getCustomer(customerId).getName() + "(" + customerId + ")\n");
		show = show.concat("\tAdd Product         :  1\n");
		show = show.concat("\tCheck Price         :  2\n");
		show = show.concat("\tCheck Bulk Discount :  3\n");
		show = show.concat("\tView Cart           :  4\n");
		show = show.concat("\tCheckout            :  5\n");
		show = show.concat("\tView Product List   :  6\n");
		show = show.concat("\tTo Logout enter 'logout'\n");
		
		System.out.println(show);
	}
	
	public void showAddProduct() {
		String show = "\n\n";
		
		showProductList();
		show = show.concat("\n\tEnter 'back' to return to menu'\n");
		show = show.concat("\tSelect Item:");
		
		System.out.println(show);
	}
		
	public void showCheckPrice() {
		String show = "\n\n";
		
		show = show.concat("\n\tEnter 'back' to return to menu'\n");
		show = show.concat("\tEnter Product ID:");
		
		System.out.println(show);
	}
	
	public void showCheckBulkDiscount() {
		String show = "\n\n";
		
		show = show.concat("\n\tEnter 'back' to return to menu'\n");
		show = show.concat("\tEnter Product ID:");
		
		System.out.println(show);
	}
	
	public void showViewCart() {
		String customerId = controller.getCustomerId();
		Sale sale = supermarket.getActiveSale(customerId);
		
		showSale(sale);		
	}
		
	public void showCheckout(boolean success) {
		if(success) {
			System.out.println("\n\nTransaction Complete\n\n\n\n");
		}
	}
	public void showViewProductList() {
		showProductList();
	}
	
	public void showPrice(double price) {
		String output = String.format("Price: %5.2f", price);
		System.out.println(output);
	}
	

}
