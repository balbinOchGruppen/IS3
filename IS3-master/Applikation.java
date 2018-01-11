import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JPanel;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
public class Applikation {

	private Controller controller;
	private CustomerRegister customerRegister;
	private ProductRegister productRegister;

	private JFrame frame;
	private JTextField txtCustomerName;
	private JLabel lblProdukt;
	private JTextField txtProductName;
	private JTextField txtOrderID;
	private JTextField txtOrderLineNbr;
	private JTextField txtProductName_Copy;
	private JTextField txtCopySerialNbr;
	private JTextField txtCustomerAddress;
	private JTextField txtCustomerNbr;
	private JTextField txtProductCategory;
	private JTextField txtProductPrice;
	private JTextField txtOrderDeliveryDate;
	private JTextField txtAmount;
	private JLabel messageBox;
	private JTextField txtProduct;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Applikation window = new Applikation();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Applikation() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		customerRegister = new CustomerRegister();
		productRegister = new ProductRegister();
		controller = new Controller(productRegister, customerRegister, frame);
		
		
		frame =  new JFrame();
		frame.setBounds(100, 100, 868, 805);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblKund = new JLabel("Kund");
		lblKund.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblKund.setBounds(116, 69, 114, 16);
		frame.getContentPane().add(lblKund);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(73, 128, 164, 22);
		frame.getContentPane().add(txtCustomerName);
		txtCustomerName.setColumns(10);

		lblProdukt = new JLabel("Produkt");
		lblProdukt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProdukt.setBounds(410, 69, 114, 16);
		frame.getContentPane().add(lblProdukt);

		txtProductName = new JTextField();
		txtProductName.setBounds(360, 94, 164, 22);
		frame.getContentPane().add(txtProductName);
		txtProductName.setColumns(10);

		messageBox = new JLabel("");
		messageBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageBox.setBounds(12, 13, 612, 43);
		frame.getContentPane().add(messageBox);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(330, 332, 505, 154);
		frame.getContentPane().add(textPane);
		textPane.setEditable(false);

		// A button to create a customer.
		JButton btnCreatCustomer = new JButton("Skapa");
		btnCreatCustomer.addActionListener(new ActionListener() {
			// A method to create a customer.
			public void actionPerformed(ActionEvent arg0) {

				String name = txtCustomerName.getText();
				String customerNbr = txtCustomerNbr.getText();
				String address = txtCustomerAddress.getText();
				Customer c = controller.findCustomer(customerNbr);
				// Makes sure that both the customer and address field are not empty.
				if (name.isEmpty() || customerNbr.isEmpty() || address.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}
				// Checks if the customer already exists.
				else if (controller.fetchCustomer().containsValue(c)) {
					messageBox.setText("En kund med kundnummer " + customerNbr + " finns redan.");
				} 
				// Adding a customer.
				else {
					controller.addCustomer(customerNbr, name, address);
					messageBox.setText(name + " är tillagd i kundregistret.");
					txtCustomerName.setText("");
					txtCustomerAddress.setText("");
					txtCustomerNbr.setText("");
				}
				
			}
		});
		btnCreatCustomer.setBounds(32, 191, 97, 25);
		frame.getContentPane().add(btnCreatCustomer);
		
		// A button to remove a customer. 
		JButton btnRemoveCustomer = new JButton("Ta bort");
		btnRemoveCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String customerNbr = txtCustomerNbr.getText();
				Customer c = controller.findCustomer(customerNbr);
				// Makes sure that the customer number field is not empty.
				if (customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer");
				}
				// Removes a customer from the customer register.
				else if (controller.fetchCustomer().containsValue(c)) {
					controller.removeCustomer(customerNbr);
					messageBox.setText(c.getName() +" är borttagen.");
					txtCustomerNbr.setText("");
				}
				// No customer could be found and send an error message.
				else if (c == null) {
					messageBox.setText("Kunden gick inte att hitta.");
					txtCustomerNbr.setText("");
				}
			}
		});
		btnRemoveCustomer.setBounds(141, 191, 97, 25);
		frame.getContentPane().add(btnRemoveCustomer);
		
		// A button to search for a customer.
		JButton btnSearchCustomer = new JButton("S\u00F6k");
		btnSearchCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String customerNbr = txtCustomerNbr.getText();
				Customer c = controller.findCustomer(customerNbr);
				
				// Checks that the customer number field is not empty.
				if (customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer");
				}
				// Prints out the customer and its orders.
				else if (c != null) {
					String answere = "Kunden du söker är " + c.getName() + " med personnummer " + c.getCustomerNumber() + " och adressen " + c.getAddress();
					answere += ("\n" + c.getName() + " har följande ordrar: \n" );
					
					for(Order o : c.getOrders().values()) {
						answere += ("OrderID: " + o.getOrderID() + " Leveransdatum: " + o.getDeliveryDate() + "\n");
					}
					textPane.setText(answere);
					txtCustomerNbr.setText("");
				// No customer could be found.
				} else {
					messageBox.setText("Kunden gick inte att hitta.");
					txtCustomerNbr.setText("");
				}
			}
		});
		btnSearchCustomer.setBounds(32, 229, 97, 25);
		frame.getContentPane().add(btnSearchCustomer);
		// A button to change a customer.
		JButton btnChangeCustomer = new JButton("\u00C4ndra");
		btnChangeCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtCustomerName.getText();
				String customerNbr = txtCustomerNbr.getText();
				String address = txtCustomerAddress.getText();
				Customer c = controller.findCustomer(customerNbr);
				// Makes sure customer number field is not empty.
				if (customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer.");
				}
				// Changes the customers name.
				else if (!name.isEmpty() && address.isEmpty()) {
					c.setName(name);
					txtCustomerName.setText("");
					messageBox.setText("Kundens namn med kundnummer " + customerNbr + " är ändrat.");
				}
				// Changes the customers address.
				else if (!address.isEmpty() && name.isEmpty()) {
					c.setAddress(address);
					messageBox.setText("Kundens adress med kundnummer: " + customerNbr + " är ändrad.");
					txtCustomerAddress.setText("");
				}
				// Checks if the address and name fields are empty.
				else if(address.isEmpty() && name.isEmpty()) {
					messageBox.setText("Namn och/eller adress måste fyllas i för att ändra kunden.");
				}
				// Changes the customers name and address.
				else if (c != null) {
					c.setName(name);
					c.setAddress(address);
					txtCustomerName.setText("");
					txtCustomerAddress.setText("");
					txtCustomerNbr.setText("");
					messageBox.setText("Kund med kundnummer: " + customerNbr + " är ändrad.");
				}
				// No customer could be found.
				else if (c == null) {
					messageBox.setText("Kunden finns inte.");
				}
				
			}
		});
		btnChangeCustomer.setBounds(141, 229, 97, 25);
		frame.getContentPane().add(btnChangeCustomer);
		
		// A button to create a product.
		JButton btnCreatProduct = new JButton("Skapa");
		btnCreatProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtProductName.getText();
				String category = txtProductCategory.getText();
				String stringPrice = txtProductPrice.getText();
				Product p = controller.findProduct(name);
				
				//Checks if all required fields are not empty.
				if (name.isEmpty() || category.isEmpty() || stringPrice.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}
				// Checks if a product already exists.
				else if (controller.fetchProduct().containsValue(p)) {
					messageBox.setText("En produkt med namnet " + name + " finns redan i registret.");
				} 
				// Adds a product to the product register.
				else {
					int price = Integer.parseInt(stringPrice);
					controller.addProduct(name, category, price);
					messageBox.setText(name + " är tillagd i produktregistret.");
					txtProductName.setText("");
					txtProductCategory.setText("");
					txtProductPrice.setText("");
				}	
			}
		});
		btnCreatProduct.setBounds(318, 191, 97, 25);
		frame.getContentPane().add(btnCreatProduct);
		
		// A button to remove a product.
		JButton btnRemoveProductFromReg = new JButton("Ta bort");
		btnRemoveProductFromReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productName = txtProductName.getText();
				Product p = controller.findProduct(productName);
				
				// Checks if the product name field is empty.
				if (productName.isEmpty()) {
					messageBox.setText("Du måste fylla i produktnamn");
				}
				// Removes the product.
				else if (p != null) {
					controller.removeProduct(productName);
					for(OrderLine or : p.getOrderLines().values()) {
						or.setAmount(0);
						or.setProduct(null);
					}
					messageBox.setText("Produkten är borttagen från registret.");
					txtProductName.setText("");
				} else {
					messageBox.setText("Produkten gick inte att hitta.");
					txtProductName.setText("");
				}
				
			}
		});
		btnRemoveProductFromReg.setBounds(427, 191, 97, 25);
		frame.getContentPane().add(btnRemoveProductFromReg);
		
		// A button to search for a product.
		JButton btnSearchProduct = new JButton("S\u00F6k");
		btnSearchProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productName = txtProductName.getText();
				Product p = controller.findProduct(productName);
				// Checks if the product name field is empty.
				if (productName.isEmpty()) {
					messageBox.setText("Du måste fylla i produktnamn");
				}
				// Prints out the product.
				else if (p != null) {
					textPane.setText("Produkten du söker är " + p.getName() + " inom kategorin " + p.getCategory() + " med priset " + p.getPrice() + ", Antal i lager: " + p.getAmountOfCopies());
					txtProductName.setText("");
					
					// No product could be found.
				} else {
					messageBox.setText("Produkten gick inte att hitta.");
					txtProductName.setText("");
				}
				
			}
		});
		btnSearchProduct.setBounds(318, 229, 97, 25);
		frame.getContentPane().add(btnSearchProduct);
		
		// Button to change a product.
		JButton btnChangeProduct = new JButton("\u00C4ndra");
		btnChangeProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtProductName.getText();
				String category = txtProductCategory.getText();
				String stringPrice = txtProductPrice.getText();
				Product p = controller.findProduct(name);
				
				// Checks if the product name field is empty.
				if (name.isEmpty()) {
					messageBox.setText("Du måste fylla i produktnamn.");
				}
				
				// Changes the products category.
				else if (!category.isEmpty() && stringPrice.isEmpty()) {
					p.setCategory(category);
					txtProductCategory.setText("");
					messageBox.setText("Produkten " + name + "s kategori är ändrad.");
				}
				
				// Changes the products price.
				else if (!stringPrice.matches("[0-9]+") && !(stringPrice.length() > 1) && category.isEmpty()) {
					int price = Integer.parseInt(stringPrice);
					p.setPrice(price);
					messageBox.setText("Produkten " + name + "s pris är ändrad.");
					txtProductPrice.setText("");
				}
				
				// Checks if both the category and price field are empty.
				else if(category.isEmpty() && stringPrice.isEmpty()) {
					messageBox.setText("Du måste fylla i kategori och /eller pris.");
				}
				
				// Changes the products category and price.
				else if (p != null) {
					p.setCategory(category);
                    int price = Integer.parseInt(stringPrice);
					p.setPrice(price);
					txtProductName.setText("");
					txtProductCategory.setText("");
					txtProductPrice.setText("");
					messageBox.setText("Produkten " + name + " är ändrad.");
				
				}
				
				// No product exists.
				else if (p == null) {
					messageBox.setText("Produkten finns inte.");
				}
				
			}
		});
		btnChangeProduct.setBounds(427, 229, 97, 25);
		frame.getContentPane().add(btnChangeProduct);

		JLabel lblLggTilltaBort = new JLabel("Order");
		lblLggTilltaBort.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLggTilltaBort.setBounds(116, 305, 275, 16);
		frame.getContentPane().add(lblLggTilltaBort);

		JLabel lblLggTilltaBort_1 = new JLabel("Orderrad");
		lblLggTilltaBort_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLggTilltaBort_1.setBounds(102, 438, 275, 16);
		frame.getContentPane().add(lblLggTilltaBort_1);

		JLabel lblOrder = new JLabel("OrderID:");
		lblOrder.setBounds(12, 337, 56, 16);
		frame.getContentPane().add(lblOrder);

		txtOrderID = new JTextField();
		txtOrderID.setBounds(116, 334, 116, 22);
		frame.getContentPane().add(txtOrderID);
		txtOrderID.setColumns(10);

		JLabel lblOrderrad = new JLabel("Orderradsnr:");
		lblOrderrad.setBounds(12, 470, 87, 16);
		frame.getContentPane().add(lblOrderrad);

		txtOrderLineNbr = new JTextField();
		txtOrderLineNbr.setBounds(116, 467, 116, 22);
		frame.getContentPane().add(txtOrderLineNbr);
		txtOrderLineNbr.setColumns(10);

		JLabel lblLggTilltaBort_3 = new JLabel("Exemplar\r\n");
		lblLggTilltaBort_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLggTilltaBort_3.setBounds(623, 69, 354, 16);
		frame.getContentPane().add(lblLggTilltaBort_3);

		JLabel lblNewLabel_2 = new JLabel("Produktnamn:");
		lblNewLabel_2.setBounds(562, 97, 80, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblExemplar = new JLabel("Serienr:");
		lblExemplar.setBounds(572, 131, 72, 16);
		frame.getContentPane().add(lblExemplar);

		txtProductName_Copy = new JTextField();
		txtProductName_Copy.setBounds(655, 94, 116, 22);
		frame.getContentPane().add(txtProductName_Copy);
		txtProductName_Copy.setColumns(10);

		txtCopySerialNbr = new JTextField();
		txtCopySerialNbr.setBounds(655, 128, 116, 22);
		frame.getContentPane().add(txtCopySerialNbr);
		txtCopySerialNbr.setColumns(10);

		// A button to show an order.
		JButton btnShow = new JButton("Visa");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerNbr = txtCustomerNbr.getText();
				String orderID = txtOrderID.getText();
				
				Customer c = controller.findCustomer(customerNbr);
				Order o = controller.findOrder(orderID, customerNbr);
				
				// Checks if the customer exists.
				if (!controller.fetchCustomer().containsValue(c)) {
					messageBox.setText("Kunden hittades inte.");
				}
				// Checks if the order exist.
				else if (!controller.fetchOrder(customerNbr).containsValue(o)) {
					messageBox.setText("Ordern hittades inte.");
				}
				// Check if order ID and order number field is empty.
				else if (customerNbr.isEmpty() || orderID.isEmpty()) {
					messageBox.setText("Du måste fylla i orderID och kundnummer.");
				}
				
				// Print out the order.
				else {
					if(o != null) {
						String answere = ("Namn: " + o.getCustomer().getName() + " Kundnummer: " + o.getCustomer().getCustomerNumber());
						for(OrderLine ol : o.getOrderLines().values()) {
							answere += ("\nProdukt: " + ol.getProductName() + " Antal: " + ol.getAmount());
					    }
					String string = ("\nTotalbelopp: " + controller.calculateSum(orderID, customerNbr));
					textPane.setText(answere + string);	
					
				    }
				}
			}
		});
		btnShow.setBounds(221, 400, 97, 25);
		frame.getContentPane().add(btnShow);

		JLabel lblNamn = new JLabel("Namn:");
		lblNamn.setBounds(12, 131, 56, 16);
		frame.getContentPane().add(lblNamn);

		JLabel lblKundnr = new JLabel("Kundnr:");
		lblKundnr.setBounds(12, 100, 56, 16);
		frame.getContentPane().add(lblKundnr);

		JLabel lblNewLabel_3 = new JLabel("Adress:");
		lblNewLabel_3.setBounds(12, 155, 56, 16);
		frame.getContentPane().add(lblNewLabel_3);

		txtCustomerAddress = new JTextField();
		txtCustomerAddress.setColumns(10);
		txtCustomerAddress.setBounds(73, 156, 164, 22);
		frame.getContentPane().add(txtCustomerAddress);

		txtCustomerNbr = new JTextField();
		txtCustomerNbr.setColumns(10);
		txtCustomerNbr.setBounds(73, 98, 164, 22);
		frame.getContentPane().add(txtCustomerNbr);

		JLabel lblNewLabel_4 = new JLabel("Namn:");
		lblNewLabel_4.setBounds(294, 97, 56, 16);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblKategori = new JLabel("Kategori:");
		lblKategori.setBounds(294, 124, 56, 16);
		frame.getContentPane().add(lblKategori);

		JLabel lblNewLabel_5 = new JLabel("Pris:");
		lblNewLabel_5.setBounds(294, 156, 56, 16);
		frame.getContentPane().add(lblNewLabel_5);

		txtProductCategory = new JTextField();
		txtProductCategory.setColumns(10);
		txtProductCategory.setBounds(360, 124, 164, 22);
		frame.getContentPane().add(txtProductCategory);

		txtProductPrice = new JTextField();
		txtProductPrice.setColumns(10);
		txtProductPrice.setBounds(360, 154, 164, 22);
		frame.getContentPane().add(txtProductPrice);
		
		// A button to add an order.
		JButton btnAddOrder = new JButton("L\u00E4gg till");
		btnAddOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerNbr = txtCustomerNbr.getText();
				String orderID = txtOrderID.getText();
				String deliveryDate = txtOrderDeliveryDate.getText();
				Customer c = controller.findCustomer(customerNbr);
				Order o = controller.findOrder(orderID, customerNbr);

				// Check if customer number, order ID or delivery date field is empty.
				if (customerNbr.isEmpty() || orderID.isEmpty() || deliveryDate.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}

				// Check if a customer is registered.
				else if(!controller.fetchCustomer().containsValue(c)) {
					messageBox.setText("Kunden är inte registrerad.");
				}
				
				// Check if a customer already is registered.
				else if (controller.fetchOrder(customerNbr).containsValue(o)) {
					messageBox.setText("En order med orderID " + orderID + " finns redan hos kunden.");
				}				
				// Add an order to a customer.
				else {
					controller.addOrder(orderID, deliveryDate, customerNbr);
					messageBox.setText("Ordern är tillagd till kunden");
					txtCustomerNbr.setText("");
					txtOrderID.setText("");
					txtOrderDeliveryDate.setText("");
				}
				
			}
		});
		btnAddOrder.setBounds(12, 400, 97, 25);
		frame.getContentPane().add(btnAddOrder);
		
		// A button to remove an order.
		JButton btnRemoveOrder = new JButton("Ta bort");
		btnRemoveOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String orderID = txtOrderID.getText();
				String customerNbr = txtCustomerNbr.getText();
				Order o = controller.findOrder(orderID, customerNbr);
				Customer c = controller.findCustomer(customerNbr);
				
				// Check if order ID and customer number field is empty.
				if (orderID.isEmpty() || customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i orderID och kundnummer.");
				}
				
				// Removes an order from a customer.
				else if (o != null) {
					controller.removeOrder(orderID, customerNbr);
					messageBox.setText("Ordern är borttagen från kunden " + c.getCustomerNumber());
					txtOrderID.setText("");
					txtCustomerNbr.setText("");
					
					// No order could be found.
				} else {
					messageBox.setText("Ordern gick inte att hitta.");
					txtOrderID.setText("");
					txtCustomerNbr.setText("");
				}
			} 
		});
		btnRemoveOrder.setBounds(116, 400, 97, 25);
		frame.getContentPane().add(btnRemoveOrder);
		
		// A button to add an order line.
		JButton btnAddOrderLine = new JButton("L\u00E4gg till");
		btnAddOrderLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerNbr = txtCustomerNbr.getText();
				String orderID = txtOrderID.getText();
				String orderLineNbr = txtOrderLineNbr.getText();
				String productName = txtProduct.getText();
				String stringAmount = txtAmount.getText();
				Customer c = controller.findCustomer(customerNbr);
				Order o = controller.findOrder(orderID, customerNbr);
				OrderLine ol = controller.findOrderLine(orderLineNbr, orderID, customerNbr);
				
				Product p = controller.findProduct(productName);
				
				// Check if all required fields are empty.
				if (customerNbr.isEmpty() || orderID.isEmpty() || orderLineNbr.isEmpty() || stringAmount.isEmpty()  || productName.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}
				// Check if the customer is registered. 
				else if(!controller.fetchCustomer().containsValue(c)) {
					messageBox.setText("Kunden finns inte registrerad.");
				}
				// Check if the order is added to the customer.
				else if(!controller.fetchOrder(customerNbr).containsValue(o)) {
					messageBox.setText("Ordern är inte tillagd till någon kund.");
				}
				// Check if an order already have a order line.
				else if (controller.fetchOrderLine(orderID, customerNbr).containsValue(ol)) {
					messageBox.setText("En orderrad med ordernummer " + orderLineNbr + " finns redan på ordern.");
				}
				// Check if a product doesn't exist in the product register.
				else if (!controller.pReg.getProducts().keySet().contains(productName)) {
					messageBox.setText("Den valda produkten finns inte i produktregistret.");
				}
				
				// Check if there are enough copies of a product in the register.
				else if (Integer.parseInt(stringAmount) > controller.getAmountOfCopies(productName)) {
					messageBox.setText("Det finns inte tillräckligt med exemplar av den valda produkten i registret.");
				}
				
				// Adding an order line to an order.
				else {
					int amount = Integer.parseInt(stringAmount);
					p.setAmountOfCopies(p.getAmountOfCopies() - amount);
					controller.addOrderLine(orderLineNbr, productName, amount, orderID, customerNbr, p);
					
					messageBox.setText("Orderraden är tillagd till ordern");
					txtOrderID.setText("");
					txtOrderLineNbr.setText("");
					txtAmount.setText("");
					txtProduct.setText("");
				}
			}
		});
		btnAddOrderLine.setBounds(32, 557, 97, 25);
		frame.getContentPane().add(btnAddOrderLine);
		
		// A button to remove an order line.
		JButton btnRemoveOrderLine = new JButton("Ta bort");
		btnRemoveOrderLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerNbr = txtCustomerNbr.getText();
				String orderID = txtOrderID.getText();
				String orderLineNbr = txtOrderLineNbr.getText();
				OrderLine ol = controller.findOrderLine(orderID, orderLineNbr, customerNbr);

				// Check if the required fields are empty.
				if (customerNbr.isEmpty() || orderID.isEmpty() || orderLineNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer, orderID och orderradsnummer.");
				}

				// Order line is removed from the order.
				else if (ol != null) {
					controller.removeOrderLine(orderLineNbr, orderID, customerNbr);
					messageBox.setText("Orderraden är borttagen från ordern.");
					txtOrderID.setText("");
					txtOrderLineNbr.setText("");
					
				// The order line could not be found.
				} else {
					messageBox.setText("Orderraden gick inte att hitta.");
					txtOrderID.setText("");
					txtOrderLineNbr.setText("");
					txtProduct.setText("");
				}
				
			}
		});
		btnRemoveOrderLine.setBounds(141, 557, 97, 25);
		frame.getContentPane().add(btnRemoveOrderLine);
		
		// Button to add a copy.
		JButton btnAddCopy = new JButton("L\u00E4gg till");
		btnAddCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productName = txtProductName_Copy.getText();
				String serialNbr = txtCopySerialNbr.getText();
				Product p = controller.findProduct(productName);
				Copy c = controller.findCopy(serialNbr, productName);
				
				// Check if the required fields are empty.
				if (productName.isEmpty() || serialNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}
				
				// Check if the product is registered.
				else if(!controller.fetchProduct().containsValue(p)) {
					messageBox.setText("Produkten är inte registrerad.");
				}
				// A copy already exist in the register.
				else if (controller.fetchCopy(productName).containsValue(c)) {
					messageBox.setText("Ett exemplar med serienummer " + serialNbr + " finns redan i registret.");
				}
				// Add a copy to the register.
				else {
					controller.addCopy(serialNbr, productName);
					messageBox.setText("Exemplaret är tillagt i registret");
					txtProductName_Copy.setText("");
					txtCopySerialNbr.setText("");
				}
			}
		});
		btnAddCopy.setBounds(564, 163, 97, 25);
		frame.getContentPane().add(btnAddCopy);
		
		// Button to remove a copy.
		JButton btnRemoveCopy = new JButton("Ta bort");
		btnRemoveCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productName = txtProductName_Copy.getText();
				String serialNbr = txtCopySerialNbr.getText();
				Copy c = controller.findCopy(serialNbr, productName);

				// Check if the required fields are empty.
				if (productName.isEmpty() || serialNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}

				// Removes a copy from the register.
				else if (c != null) {
					controller.removeCopy(serialNbr, productName);
					messageBox.setText("Exemplaret är borttaget från produktregistret.");
					txtProductName_Copy.setText("");
					txtCopySerialNbr.setText("");
					// No copy could be found in the register.
				} else {
					messageBox.setText("Exemplaret gick inte att hitta.");
					txtProductName_Copy.setText("");
					txtCopySerialNbr.setText("");
				}
			}
		});
		btnRemoveCopy.setBounds(674, 163, 97, 25);
		frame.getContentPane().add(btnRemoveCopy);
		
		JLabel lblId = new JLabel("Leveransdatum:");
		lblId.setBounds(12, 366, 126, 16);
		frame.getContentPane().add(lblId);
		
		txtOrderDeliveryDate = new JTextField();
		txtOrderDeliveryDate.setColumns(10);
		txtOrderDeliveryDate.setBounds(116, 361, 116, 22);
		frame.getContentPane().add(txtOrderDeliveryDate);
		
		JLabel lblOrderradsantal = new JLabel("Antal exemplar:");
		lblOrderradsantal.setBounds(12, 528, 117, 16);
		frame.getContentPane().add(lblOrderradsantal);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(116, 525, 116, 22);
		frame.getContentPane().add(txtAmount);
		
		// Labels with requirements to use the program correctly.
		
		JLabel lblFrAtt = new JLabel("* Kundnr kr\u00E4vs f\u00F6r att \u00E4ndra, ta bort eller s\u00F6ka efter kund.");
		lblFrAtt.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt.setBounds(329, 496, 499, 47);
		frame.getContentPane().add(lblFrAtt);
		
		JLabel lblFrAtt_1 = new JLabel("* Produktnamn kr\u00E4vs f\u00F6r att \u00E4ndra, ta bort eller s\u00F6ka efter produkt.");
		lblFrAtt_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt_1.setBounds(329, 527, 391, 16);
		frame.getContentPane().add(lblFrAtt_1);
		
		JLabel lblFrAtt_2 = new JLabel("* Kundnr kr\u00E4vs f\u00F6r att l\u00E4gga till och ta bort order.");
		lblFrAtt_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt_2.setBounds(328, 543, 428, 16);
		frame.getContentPane().add(lblFrAtt_2);
		
		// Button to show the customer register.
		JButton btnAllCustomers = new JButton("Visa kundregister");
		btnAllCustomers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HashMap<String, Customer> customers = new HashMap<String, Customer>();
				customers = controller.getCustomers();
				String answere = "Kunder:";
				// Prints the customer register.
				for(Customer c : customers.values()) {
					answere += ("\nNamn: " + c.getName() + " Kundnummer: " + c.getCustomerNumber() + " Adress: " + c.getAddress() + "\n");
					
					for(Order o : c.getOrders().values()) {
						answere += ( c.getName() + " har följande order med ordernummer: ");
						answere += (o.getOrderID() + "\n");
				    } 
				}	
					
				
				textPane.setText(answere);
			}
		});
		btnAllCustomers.setBounds(32, 267, 205, 25);
		frame.getContentPane().add(btnAllCustomers);
		
		// Button to show the product register.
		JButton btnAllProducts = new JButton("Visa produktregister");
		btnAllProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				HashMap<String, Product> products = new HashMap<String, Product>();
				products = controller.getProducts();
				String answere = "Produkter: ";
				
				// Prints the product register.
				for(Product p : products.values()) {
					answere += ("\nProduktamn: " + p.getName() + " Kategori: " + p.getCategory() + " Pris: " + p.getPrice()  + "\n\n");
					if(p.getAmountOfCopies() > 0) {
						answere += (p.getName() + " har följande serienummer på dess exemplar: ");
					}
					for(Copy c : p.getCopies().values()) {
						answere += (c.getSerialnumber() + "   ");	
					}
				} 
				textPane.setText(answere);
			}
		});
		btnAllProducts.setBounds(318, 267, 206, 25);
		frame.getContentPane().add(btnAllProducts);
		
		txtProduct = new JTextField();
		txtProduct.setBounds(116, 496, 116, 22);
		frame.getContentPane().add(txtProduct);
		txtProduct.setColumns(10);
		
		JLabel lblProdukt_1 = new JLabel("Produktnamn:");
		lblProdukt_1.setBounds(12, 499, 87, 16);
		frame.getContentPane().add(lblProdukt_1);
		
		JLabel lblNewLabel = new JLabel("* Kundnr och orderID kr\u00E4vs f\u00F6r att l\u00E4gga till och ta bort orderrad.");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel.setBounds(329, 560, 442, 16);
		frame.getContentPane().add(lblNewLabel);
		
	
		
		
	}
}
