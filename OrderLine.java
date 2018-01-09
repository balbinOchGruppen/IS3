public class OrderLine {
	private String number;
	private String productName;
	private int amount;
	private Order order;
	private Product product;
	
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNumber() {
		return this.number;
	}
	public void setProductName(String name) {
		this.productName = name;
	}
	public String getProductName() {
		return this.productName;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getAmount() {
		return this.amount;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Order getOrder() {
		return this.order;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Product getProduct() {
		return this.product;
	}

	public Product findProduct(String name) {
		if(product.getName().equals(name)) {
			return getProduct();
		} return null;
	}
	
	public void addProduct(Product product) {
		this.setProduct(product);
	}
	
	public Product removeProduct(String name) {
		Product p = this.findProduct(name);
		p = null;
		return p;
	} 
}
