import java.util.*;
public class ProductRegister {
	private HashMap<String, Product> products = new HashMap<String, Product>();

	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}
	public HashMap<String, Product> getProducts() {
		return this.products;
	}
//A method to locate and return a specific product from the product register.
	public Product findProduct(String name) {
		for(Product p : products.values()) {
			if(p.getName().equals(name)) {
				return p;
			}
		} return null; 
	}
//A method to add a product to the product register.
	public void addProduct(Product product) {
		products.put(product.getName(), product);
	}
//A method to locate and remove a product from the product register.
	public Product removeProduct(String name) {
		Product p = this.findProduct(name);
		if(p.getName().equals(name)) {
			products.remove(name);
			return p;
		} return null;
	}
}

