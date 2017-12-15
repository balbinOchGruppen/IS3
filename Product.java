import java.util.*;

public class Product {

	private String name;
	private String category;
	private double price;
	private HashMap<String, OrderLine> orderLines = new HashMap<String, OrderLine>();
	private HashMap<String, Copy> copy = new HashMap<String, Copy>();
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

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setOrderLine(HashMap<String, OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public HashMap<String, OrderLine> getOrderLine() {
		return this.orderLines;
	}

	public void setCopy(HashMap<String, Copy> copy) {
		this.copy = copy;
	}

	public HashMap<String, Copy> getCopy() {
		return this.copy;
	}
	
	public void setProductRegister(ProductRegister productRegister) {
		this.productRegister = productRegister;
	}
	
	public ProductRegister getProductRegister() {
		return this.productRegister;
	}
	
	public Copy findCopy(String serialNumber) {
		for(Copy e : copy.values()) {
			if(e.getSerialnumber().equals(serialNumber)) {
				return e;
			}
		} return null; 
	}
	
	public void addCopy(Copy copy) {
		this.copy.put(copy.getSerialnumber(), copy);
	}
	
	public Copy removeCopy(String serialNumber) {
		Copy e = this.findCopy(serialNumber);
		if(e.getSerialnumber().equals(serialNumber)) {
			this.copy.remove(serialNumber);
			return e;
		} return null;
	}

}
