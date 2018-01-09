import java.util.*;

public class CustomerRegister {
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}

	public HashMap<String, Customer> getCustomers() {
		return this.customers;
	}
//A method that searches for a specific customer in the customer register. 
	public Customer findCustomer(String customerNumber) {
		 for(Customer c : customers.values()) { 
			 if(c.getCustomerNumber().equals(customerNumber)) {
		        return c; 
		     }
	      } return null;
	}
//A method to add a customer to the customer register.
	public void addCustomer(Customer customer) {
		customers.put(customer.getCustomerNumber(), customer); 
	}
//A method to remove a customer from the customer register.
	public Customer removeCustomer(String customerNumber) {
		Customer c = this.findCustomer(customerNumber);
		if (c.getCustomerNumber().equals(customerNumber)) {
			customers.remove(customerNumber);
			return c;
		}
		return null;
	}

	/*public HashMap<String, Customer> customersInRegister() {
		
		for(Customer c : customers.values()) {
			customers.put(c.getCustomerNumber(), c);
			
		} return customers;
	}*/
}
