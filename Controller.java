import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Controller {
	CustomerRegister cReg;
	Customer customer;
	Order order;
	OrderLine orderLine;
	Product product;
	ProductRegister pReg;
	Copy copy;
	JFrame frame;
	
	public Controller(ProductRegister pReg, CustomerRegister cReg, JFrame frame) {
		this.pReg = pReg;
		this.cReg = cReg;
		this.frame = frame;
	}
	
	public HashMap<String, Customer> customersInRegister() {
		return cReg.customersInRegister();
	}
	
	public void addCustomer(String cNbr, String name, String address) { 
		Customer customer = new Customer();
		customer.setName(name);
		customer.setCustomerNumber(cNbr);
		customer.setAddress(address);
		cReg.addCustomer(customer);
		customer.setCustomerRegister(cReg);
	}

	public Customer findCustomer(String cNmbr) {
		return cReg.findCustomer(cNmbr);
	}

	public void removeCustomer(String cNmbr) {
			cReg.removeCustomer(cNmbr);
	}
	
	public HashMap<String, Customer> fetchCustomer() {
		HashMap<String, Customer> c = cReg.getCustomers();
		return c;
	}
	
	public void addOrder(String orderID, String deliveryDate, String customerNbr) {
		Order order = new Order();
		order.setOrderID(orderID);
		order.setDeliveryDate(deliveryDate);
		
		Customer c = cReg.findCustomer(customerNbr);
		c.addOrder(order);
		order.setCustomer(c);
	}

	public Order findOrder(String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		if(c != null) {
			Order o = c.findOrder(orderID);
		    return o;
		} return null;
		
	}

	public void removeOrder(String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		if (c != null) { 
			c.removeOrder(orderID);
		}
	}
	
	public HashMap<String, Order> fetchOrder(String cNbr) {
		Customer c = cReg.findCustomer(cNbr);
		if (c != null) {
			HashMap<String, Order> o = c.getOrders();
			return o;
		} return null;
	}
	
	public void addOrderLine(String number, String productName, int amount, String orderID, String customerNbr) {
		OrderLine orderLine = new OrderLine();
		orderLine.setNumber(number);
		orderLine.setProductName(productName);
		orderLine.setAmount(amount);
		
		Customer c = cReg.findCustomer(customerNbr);
		Order o = c.findOrder(orderID);
		o.addOrderLine(orderLine);
		orderLine.setOrder(o);
	}

	public OrderLine findOrderLine(String number, String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		if(c!= null) {
			Order o = c.findOrder(orderID);
		    OrderLine ol = o.findOrderLine(number);
		    return ol;
		} return null;
		
	}

	public void removeOrderLine(String number, String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		Order o = c.findOrder(orderID);
		if(c != null && o != null) {
			o.removeOrderLine(number);
		}
	}
	
	public HashMap<String, OrderLine> fetchOrderLine(String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		Order o = c.findOrder(orderID);
		if(c != null && o != null) {
			HashMap<String, OrderLine> ol = o.getOrderLines();
		    return ol;
		} return null;
		
	}
	
	public double calculateAmount() {
		return order.calculateSum();
	}
	
	public void addCopy(String serialNumber, String productName) {
		Copy copy = new Copy();
		copy.setSerialNumber(serialNumber);
		
		Product p = pReg.findProduct(productName);
		p.addCopy(copy);
	}

	public Copy findCopy(String serialNumber, String productName) {
		Product p = pReg.findProduct(productName);
		if (p != null) {
			return p.findCopy(serialNumber);
		} return null;
	}

	public void removeCopy(String serialNumber, String productName) {
		Product p = pReg.findProduct(productName);
		if (p != null) {
			p.removeCopy(serialNumber);
		}
	}
	
	public HashMap<String, Copy> fetchCopy(String productName) {
		Product p = pReg.findProduct(productName);
		if(p != null) {
			HashMap<String, Copy> c = p.getCopies();
		    return c;
		} return null;
		
	}
	
	public void addProduct(String name, String category, int price) {
		Product product = new Product();
		product.setName(name); 
		product.setCategory(category);
		product.setPrice(price);
		pReg.addProduct(product);
	}

	public Product findProduct(String name) {
		return pReg.findProduct(name);
	}

	public void removeProduct(String name) {
			pReg.removeProduct(name);
	}
	
	public HashMap<String, Product> fetchProduct() {
		HashMap<String, Product> p = pReg.getProducts();
		return p;
	}
	
	public HashMap<String, Product> productsInRegister() {
		return pReg.productsInRegister();
	}

}
