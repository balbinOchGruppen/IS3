import java.util.*;

public class Product {

	private String name;
	private String category;
	private int price;
	private int amountOfCopies;
	private int amount = 0;
	private HashMap<String, OrderLine> orderLines = new HashMap<String, OrderLine>();
	private HashMap<String, Copy> copies = new HashMap<String, Copy>();
	private ProductRegister productRegister;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
		return this.category;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}
	
	public void setAmountOfCopies(int amount) {
		this.amountOfCopies = amount;
	}
	
	public int getAmountOfCopies() {
		return this.amountOfCopies;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setOrderLine(HashMap<String, OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public HashMap<String, OrderLine> getOrderLine() {
		return this.orderLines;
	}

	public void setCopies(HashMap<String, Copy> copies) {
		this.copies = copies;
	}

	public HashMap<String, Copy> getCopies() {
		return this.copies;
	}
	
	public void setProductRegister(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}
	
	public ProductRegister getProductRegister() {
		return this.productRegister;
	}
	
	public Copy findCopy(String serialNumber) {
		for(Copy e : copies.values()) {
			if(e.getSerialnumber().equals(serialNumber)) {
				return e;
			}
		} return null; 
	}
	
	public void addCopy(Copy copy) {
		this.copies.put(copy.getSerialnumber(), copy);
		
		this.amount += 1;
		this.setAmountOfCopies(amount);
		this.setAmount(amount);
	}
	
	public Copy removeCopy(String serialNumber) {
		Copy c = this.findCopy(serialNumber);
		if(c.getSerialnumber().equals(serialNumber)) {
			this.copies.remove(serialNumber);
			return c;
		} return null;
	}
	
	public int getTheProductsCopies() {
		HashMap<String, Copy> allCopies = new HashMap<String, Copy>();
		for(Copy c : copies.values()) {
			allCopies.put(c.getSerialnumber(), c);
			this.amount += 1;
	    }
		return amount;	
	}


}
