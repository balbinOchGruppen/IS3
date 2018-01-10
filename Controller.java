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
		//return cReg.customersInRegister();
		return cReg.getCustomers();
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
//A method that connects the addOrder method in the customer class to the application window.
	public void addOrder(String orderID, String deliveryDate, String customerNbr) {
		Order order = new Order();
		order.setOrderID(orderID);
		order.setDeliveryDate(deliveryDate);
		
		Customer c = cReg.findCustomer(customerNbr);
		c.addOrder(order);
		order.setCustomer(c);
	}
//A method that connects the findOrder method in the customer class to the application window.
	public Order findOrder(String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		if(c != null) {
			Order o = c.findOrder(orderID);
		    return o;
		} return null;
		
	}
//A method that connects the removeOrder method in the customer class to the application window.
	public void removeOrder(String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		if (c != null) { 
			c.removeOrder(orderID);
		}
	}
//A method that returns a specific customers orders to the application window.
	public HashMap<String, Order> fetchOrder(String cNbr) {
		Customer c = cReg.findCustomer(cNbr);
		if (c != null) {
			HashMap<String, Order> o = c.getOrders();
			return o;
		} return null;
	}
/*A method that connects the addOrderLine method in the order class to the application window and creates
a two way connection between the order and an order line.*/
	public void addOrderLine(String number, String productName, int amount, String orderID, String customerNbr, Product product) {
		OrderLine orderLine = new OrderLine();
		orderLine.setNumber(number);
		orderLine.setProductName(productName);
		orderLine.setAmount(amount);
		orderLine.addProduct(product);
		
		Customer c = cReg.findCustomer(customerNbr);
		Order o = c.findOrder(orderID);
		o.addOrderLine(orderLine);
		orderLine.setOrder(o);
		
	}
//A method that connects the findOrderLine method in the order class to the application window which returns a specific order line.
	public OrderLine findOrderLine(String number, String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		if(c!= null) {
			Order o = c.findOrder(orderID);
		    OrderLine ol = o.findOrderLine(number);
		    return ol;
		} return null;
		
	}
/*A method that connects the removeOrderLine method in the order class to the application window which makes it possible to
  remove a specific order line from an order.*/
	public void removeOrderLine(String number, String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		Order o = c.findOrder(orderID);
		if(c != null && o != null) {
			o.removeOrderLine(number);
		}
	}
/*A method that connects the fetchOrderLine method in the class order and returns a customers order lines to the application window. */
	public HashMap<String, OrderLine> fetchOrderLine(String orderID, String customerNbr) {
		Customer c = cReg.findCustomer(customerNbr);
		Order o = c.findOrder(orderID);
		if(c != null && o != null) {
			HashMap<String, OrderLine> ol = o.getOrderLines();
		    return ol;
		} return null;
		
	}
//A method that calculates the sum of an order and returns it to the application window.
	public double calculateSum(String orderID, String customerNbr) {
		Order o = this.findOrder(orderID, customerNbr);
		return o.calculateSum();
	}
//A method that connects the addCopy method from the class product to the application window.	
	public void addCopy(String serialNumber, String productName) {
		Copy copy = new Copy();
		copy.setSerialNumber(serialNumber);
		
		Product p = pReg.findProduct(productName);
		p.addCopy(copy);
	}
//A method that connects the findCopy method in the product class to the application window and returns the specific copy.
	public Copy findCopy(String serialNumber, String productName) {
		Product p = pReg.findProduct(productName);
		if (p != null) {
			return p.findCopy(serialNumber);
		} return null;
	}
//A method that connects the removeCopy method in the product class to the application window.
	public void removeCopy(String serialNumber, String productName) {
		Product p = pReg.findProduct(productName);
		if (p != null) {
			p.removeCopy(serialNumber);
			p.setAmountOfCopies(p.getAmountOfCopies() - 1);
		}
	}
//A method that connects the fetchCopy method in the class product and returns the products copies to the application window.
 	
	public HashMap<String, Copy> fetchCopy(String productName) {
		Product p = pReg.findProduct(productName);
		if(p != null) {
			HashMap<String, Copy> c = p.getCopies();
		    return c;
		} return null;
		
	}
//A method that connects the addProduct method in the product register to the application window.	
	public void addProduct(String name, String category, int price) {
		Product product = new Product();
		product.setName(name); 
		product.setCategory(category);
		product.setPrice(price);
		pReg.addProduct(product);
		System.out.print("In controller " + product.getPrice());
	}
//A method that connects the findProduct method in the product register to the application window and returns a product.
	public Product findProduct(String name) {
		return pReg.findProduct(name);
	}
/*A method that connects the removeProduct method in the product register class to the application window and 
	removes a product from the product register.*/
	public void removeProduct(String name) {
			pReg.removeProduct(name);
	}
/*A method that connects the fetchProduct method in the class product register and returns the product 
	from the product register to the application window*/
	public HashMap<String, Product> fetchProduct() {
		HashMap<String, Product> p = pReg.getProducts();
		return p;
	}
//OOOOOOOOOOOOOBBBBBBBBBBBBSSSSSSSSSS XXX
	public HashMap<String, Product> productsInRegister() {
		return pReg.productsInRegister();
	}
	
	/*public int getTheProductCopies(String productName) {
		Product p = pReg.findProduct(productName);
		return p.getTheProductCopies();
	}*/
//A method that connects the getAmountOfCopies in the product class to the application window which returns the amount of copies for a specific product.
	public int getAmountOfCopies(String productName) {
		Product p = pReg.findProduct(productName);
		return p.getAmountOfCopies();
	}

}
