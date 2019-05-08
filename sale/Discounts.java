package sale;

import java.util.HashMap;
import java.util.Map;


public class Discounts {

	private Map<String, Double> promotiondiscounts;
	private Map<String, Map<Integer,Double>> bulkdiscounts;

	
	public Discounts(){
		promotiondiscounts = new HashMap<String, Double>();
		bulkdiscounts = new HashMap<String, Map<Integer,Double>>();
	}
	
	
	public void addPromotionDiscount(String productId, double discount) {
		if(promotiondiscounts.containsKey(productId)) {
			
			promotiondiscounts.remove(productId);
			promotiondiscounts.put(productId, new Double(discount));
						
		}
		else {			
			promotiondiscounts.put(productId, new Double(discount));
		}
	}
	public void removePromotionDiscount(String productId) {
		promotiondiscounts.remove(productId);		
	}
	
	
	public void addBulkDiscount(String productId, int qty, double discount) {
		if(bulkdiscounts.containsKey(productId)) {
			
			Map<Integer, Double> productBulkDiscounts = bulkdiscounts.get(productId);
			Integer key = new Integer(qty);
			
			if(productBulkDiscounts.containsKey(key)) {
				productBulkDiscounts.remove(key);
				productBulkDiscounts.put(key, new Double(discount));
			}
			else {
				productBulkDiscounts.put(key, new Double(discount));
			}
			
		}
		else {
			Map<Integer, Double> productBulkDiscount = new HashMap<Integer,Double>();
			productBulkDiscount.put(new Integer(qty), new Double(discount));
			
			bulkdiscounts.put(productId, productBulkDiscount);
		}
	}
	public void removeBulkDiscount(String productId, int qty) {
		if(bulkdiscounts.containsKey(productId)) {
			bulkdiscounts.get(productId).remove(new Integer(qty));
		}
		
	}
	public void removeBulkDiscountAll(String productId) {
		if(bulkdiscounts.containsKey(productId)) {
			bulkdiscounts.get(productId).clear();
		}
	}
	

	public boolean hasPromotionDiscount(String productId){
		return promotiondiscounts.containsKey(productId);
	}
	public boolean hasBulkDiscount(String productId){
		return bulkdiscounts.containsKey(productId);
	}
	
	public double getPromotionDiscount(String productId){
		double totaldiscount = 0.0;
		
		if(promotiondiscounts.containsKey(productId)) {
			totaldiscount = promotiondiscounts.get(productId);
		}
		
		return totaldiscount;
	}
	public Map<Integer,Double> getBulkDiscounts(String productId){
		return bulkdiscounts.get(productId);
	}
	
	public double calculatePromotionDiscount(String productId, int qty){
		double discount = 0.0;
	
		Double _discount = promotiondiscounts.get(productId);
		if(_discount == null ) {
			return discount;
		}
		
		discount = _discount.doubleValue();
		
		return qty * discount;		
	}
	public double calculateBulkDiscount(String productId, int qty){
		double totaldiscount = 0.0;
		
		Map<Integer,Double> productBulkDiscounts = bulkdiscounts.get(productId);
		
		if(productBulkDiscounts == null) {
			return totaldiscount;
		}
				
		
		int amount = qty;
		double discount = 0.0;
		int discountNum = 0;
		
		
		int bulkqty = amount;
		while(bulkqty > 1) {
			// System.out.println("bulkqty= " + Integer.toString(bulkqty));
			if(productBulkDiscounts.containsKey(bulkqty)) {
				discountNum = amount/bulkqty;
				discount = productBulkDiscounts.get(bulkqty);
				totaldiscount += discount * discountNum;
				amount = (amount - bulkqty * discountNum);
				bulkqty = amount;
				
//					System.out.println("\tdiscountNum= " + Integer.toString(discountNum));
//					System.out.println("\tdiscount= " + Double.toString(discount));
//					System.out.println("\ttotaldiscount= " + Double.toString(totaldiscount));
//					System.out.println("\tamount= " + Integer.toString(amount));
//					System.out.println("\tbulkqty= " + Integer.toString(bulkqty));
//					System.out.println("\t\tBREAK!!");
				
				continue;
			}
			bulkqty--;
		}				
			
		
		
		return totaldiscount;
	}
	
	
	
}
