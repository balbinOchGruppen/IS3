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
	public void setOrderLines (HashMap<String, OrderLine> orderLines) {
		this.orderLines = orderLines;
	}
	public HashMap<String, OrderLine> getOrderLines() {
		return this.orderLines;
	}
//A method that returns an orders order line.
	public OrderLine findOrderLine(String number) {
		for(OrderLine o : orderLines.values()) {
			if(o.getNumber().equals(number)) {
				return o;
			}
		} return null; 
	}
//A method that adds an order line to an order.
	public void addOrderLine(OrderLine orderLine) {
		orderLines.put(orderLine.getNumber(), orderLine);
	}
//A method that removes an order line from an order.
	public OrderLine removeOrderLine(String number) {
		OrderLine o = this.findOrderLine(number);
		if(o.getNumber().equals(number)) {
			orderLines.remove(number);
			return o;
		} return null;
	}
//A method that calculates the total sum for a specific order.
	public double calculateSum() {
		double totalSum = 0;
//		Order or = customer.findOrder(orderID);
		//if(or != null) {
		   for(OrderLine o : this.orderLines.values()) {
			   System.out.println("For-sats: " + o);
			   if(o.getProduct() != null) {
				   System.out.println("Product (if):" + o.getProduct().getName()); 
				   totalSum += o.calculateOrderLine();
			   }  
		   } 
		//} 
		   return totalSum;
    }
} 
