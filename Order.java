import java.util.*;
public class Order {
	
	private String orderID;
	private String deliveryDate;
	private Customer customer;
	private HashMap<String, OrderLine> orderLines = new HashMap<String, OrderLine>();
	
	public void setOrderID (String orderID) {
		this.orderID = orderID;
	}
	public String getOrderID() {
		return this.orderID;
	}
	public void setDeliveryDate (String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getDeliveryDate() {
		return this.deliveryDate;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Customer getCustomer() {
		return this.customer;
	}
	public void setOrderLine (HashMap<String, OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	public HashMap<String, OrderLine> getOrderLine() {
		return this.orderLines;
	}
	
	public OrderLine findOrderLine(String number) {
		for(OrderLine o : orderLines.values()) {
			if(o.getNumber().equals(number)) {
				return o;
			}
		} return null; 
	}
	
	public void addOrderLine(OrderLine orderLine) {
		orderLines.put(orderLine.getNumber(), orderLine);
	}
	
	public OrderLine removeOrderLine(String number) {
		OrderLine o = this.findOrderLine(number);
		if(o.getNumber().equals(number)) {
			orderLines.remove(number);
			return o;
		} return null;
	}
	
	public double calculateSum(double pris) {
		double totalSum = 0;
		for(OrderLine o : this.orderLines.values()) {
			if(o != null) {
				for(Product p : o.getProduct().getProductRegister().getProducts().values()) {
					totalSum += p.getPrice();
				} return totalSum;
				
			}
		}return 0;
	}
}
