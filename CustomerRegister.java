import java.util.*;

public class CustomerRegister {
	private HashMap<String, Customer> customers = new HashMap<String, Customer>();

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
	}

	public HashMap<String, Customer> getCustomers() {
		return this.customers;
	}

	public Customer findCustomer(String customerNumber) {
		
		 for(Customer c : customers.values()) { 
			 if(c.getCustomerNumber().equals(customerNumber)) {
		        return c; 
		     }
	      } return null;
		 

		/*if (customerNumber != null) {
			return customers.get(customerNumber);
		}
		return null;*/
	}

	public void läggTillKund(Customer customer) {
		customers.put(customer.getCustomerNumber(), customer); 
	}

	public Customer removeCustomer(String customerNumber) {
		Customer c = this.findCustomer(customerNumber);
		if (c.getCustomerNumber().equals(customerNumber)) {
			customers.remove(customerNumber);
			return c;
		}
		return null;
	}
}
