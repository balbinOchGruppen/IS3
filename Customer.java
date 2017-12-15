import java.util.*;
public class Customer {
	private String customerNumber;
	private String name;
	private String address;
	private HashMap<String, Order> orders = new HashMap<String, Order>();
	private CustomerRegister customerRegister;
	
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}
	
	public String getCustomerNumber() {
		return this.customerNumber;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}
	
	public HashMap<String, Order> getOrders() {
		return this.orders;
	}
	
	public void setCustomerRegister(CustomerRegister customerRegister) {
		this.customerRegister = customerRegister;
	}
	
	public CustomerRegister getCustomerRegister() {
		return this.customerRegister;
	}
	
	public Order findOrder(String orderID) {
		for(Order o : orders.values()) {
			if(o.getOrderID().equals(orderID)) {
				return o;
			}
		} return null; 
	}
	
	public void addOrder(Order order) {
		orders.put(order.getOrderID(), order);
	}
	
	public Order removeOrder(String orderID) {
		Order o = this.findOrder(orderID);
		if(o.getOrderID().equals(orderID)) {
			orders.remove(orderID);
			return o;
		} return null;
	}


}
