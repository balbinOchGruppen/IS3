import java.util.HashMap;

import javax.swing.JFrame;


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
	
	public CustomerRegister getCustomerRegister() {
		Customer c = new Customer();
		return c.getCustomerRegister();
	}
	
	public Customer findCustomerToChange(String cNbr) {
		return getCustomerRegister().findCustomer(cNbr);
	}
	
	public void addCustomer(String cNbr, String name, String address) { 
		Customer customer = new Customer();
		customer.setName(name);
		customer.setCustomerNumber(cNbr);
		customer.setAddress(address);
		cReg.läggTillKund(customer);
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
	
	public void addOrder(String orderID, String deliveryDate) {
		Order order = new Order();
		order.setOrderID(orderID);
		order.setDeliveryDate(deliveryDate);
		customer.addOrder(order);
	}

	public Order findOrder(String orderID) {
		return customer.findOrder(orderID);
	}

	public void removeOrder(String orderID) {
			customer.removeOrder(orderID);
	}
	
	public HashMap<String, Order> fetchOrder() {
		HashMap<String, Order> o = customer.getOrders();
		return o;
	}
	
	public void addOrderLine(String number, int amount) {
		OrderLine orderLine = new OrderLine();
		orderLine.setNumber(number);
		orderLine.setAmount(amount);
		order.addOrderLine(orderLine);
	}

	public OrderLine findOrderLine(String number) {
		return order.findOrderLine(number);
	}

	public void removeOrderLine(String number) {
		order.removeOrderLine(number);
	}
	
	public HashMap<String, OrderLine> findOrderLine() {
		HashMap<String, OrderLine> o = order.getOrderLine();
		return o;
	}
	
	public double calculateAmount(double price) {
		return order.calculateSum(price);
	}
	
	public void addProductFromOrderLine(String name, String category, double price) {
		Product product = new Product();
		product.setName(name);
		product.setCategory(category);
		product.setPrice(price);
		orderLine.addProduct(product);
	}

	public Product findProductFromOrderLine(String name) {
		return orderLine.findProduct(name);
	}

	public void removeProductFromOrderLine(String name) {
			orderLine.removeProduct(name);
	}
	
	public Product fetchProductFromOrderLine() {
		Product p = orderLine.getProduct();
		return p;
	}
	
	public void addCopy(String serialNumber) {
		Copy copy = new Copy();
		copy.setSerialNumber(serialNumber);
		product.addCopy(copy);
	}

	public Copy findCopy(String serialNumber) {
		return product.findCopy(serialNumber);
	}

	public void removeCopy(String serialNumber) {
			product.removeCopy(serialNumber);
	}
	
	public HashMap<String, Copy> fetchCopy() {
		HashMap<String, Copy> c = product.getCopy();
		return c;
	}
	
	public void addProductFromRegister(String name, String category, double price) {
		Product product = new Product();
		product.setName(name); 
		product.setCategory(category);
		product.setPrice(price);
		pReg.addProduct(product);
	}

	public Product findProductFromRegister(String name) {
		return pReg.findProduct(name);
	}

	public void removeProductFromRegister(String name) {
			pReg.removeProduct(name);
	}
	
	public HashMap<String, Product> fetchProductFromRegister() {
		HashMap<String, Product> p = pReg.getProducts();
		return p;
	}

}
