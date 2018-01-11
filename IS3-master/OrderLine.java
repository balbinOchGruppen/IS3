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
//A method to add a product to an order line.
	public void addProduct(Product product) {
		this.setProduct(product);
	}
//A method to calculate the total price for the copies of a product in an order line. 
	public double calculateOrderLine() {
		double sum = 0;
		int price = product.getPrice();
		sum = (this.amount) * price;
		return sum;
		
	}
	
	
}
