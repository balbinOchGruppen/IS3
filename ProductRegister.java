import java.util.*;
public class ProductRegister {
	private HashMap<String, Product> products = new HashMap<String, Product>();

	public void setProducts(HashMap<String, Product> products) {
		this.products = products;
	}
	public HashMap<String, Product> getProducts() {
		return this.products;
	}
	
	public Product findProduct(String name) {
		for(Product p : products.values()) {
			if(p.getName().equals(name)) {
				return p;
			}
		} return null; 
	}
	
	public void addProduct(Product product) {
		products.put(product.getName(), product);
	}
	
	public Product removeProduct(String name) {
		Product p = this.findProduct(name);
		if(p.getName().equals(name)) {
			products.remove(name);
			return p;
		} return null;
	}
	
	public HashMap<String, Product> productsInRegister() {
		
		for(Product p : products.values()) {
			products.put(p.getName(), p);
			
		} return products;
	}
}

