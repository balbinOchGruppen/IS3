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
public class Applikation {

	private Controller controller;
	private CustomerRegister customerRegister;
	private ProductRegister productRegister;

	private JFrame frame;
	private JPanel contentPane;
	private JTextField txtCustomerName;
	private JLabel lblProdukt;
	private JTextField txtProductName;
	private JTextField txtCustomerNbr_Order;
	private JTextField txtOrderID;
	private JTextField txtOrderID_OrderLine;
	private JTextField txtOrderLineNbr;
	private JTextField txtOrderLineNbr_Product;
	private JTextField txtProductNbr_Copy;
	private JTextField txtCopySerialNbr;
	private JTextField txtOneSpecificOrderNbr;
	private JTextField txtCustomerAddress;
	private JTextField txtCustomerNbr;
	private JTextField txtProductCategory;
	private JTextField txtProductPrice;
	private JTextField txtOrderDeliveryDate;
	private JTextField txtOrderLineAmount;
	private JLabel messageBox;

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
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 654, 805);
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

		JButton btnSkapa = new JButton("Skapa");
		btnSkapa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String name = txtCustomerName.getText();
				String customerNbr = txtCustomerNbr.getText();
				String address = txtCustomerAddress.getText();
				Customer c = controller.findCustomer(customerNbr);
				
				if (name.isEmpty() || customerNbr.isEmpty() || address.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}

				else if (controller.fetchCustomer().containsValue(c)) {
					messageBox.setText("En kund med kundnummer " + customerNbr + " finns redan.");
				} 
				
				else {
					controller.addCustomer(customerNbr, name, address);
					messageBox.setText(name + " är tillagd i kundregistret.");
					txtCustomerName.setText("");
					txtCustomerAddress.setText("");
					txtCustomerNbr.setText("");
				}
				
			}
		});
		btnSkapa.setBounds(32, 191, 97, 25);
		frame.getContentPane().add(btnSkapa);

		JButton btnTaBort = new JButton("Ta bort");
		btnTaBort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String customerNbr = txtCustomerNbr.getText();
				Customer c = controller.findCustomer(customerNbr);

				if (customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer");
				}
				
				else if (controller.fetchCustomer().containsValue(c)) {
					controller.removeCustomer(customerNbr);
					messageBox.setText(c.getName() +" är borttagen.");
					txtCustomerNbr.setText("");
				}
				else if (c == null) {
					messageBox.setText("Kunden gick inte att hitta.");
					txtCustomerNbr.setText("");
				}
			}
		});
		btnTaBort.setBounds(141, 191, 97, 25);
		frame.getContentPane().add(btnTaBort);

		JButton btnSk = new JButton("S\u00F6k");
		btnSk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String customerNbr = txtCustomerNbr.getText();
				Customer c = controller.findCustomer(customerNbr);

				if (customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer");
				}

				else if (c != null) {
					messageBox.setText("Kunden du söker är " + c.getName() + " med personnummer " + c.getCustomerNumber() + " och adressen " + c.getAddress());
					txtCustomerNbr.setText("");
				} else {
					messageBox.setText("Kunden gick inte att hitta.");
					txtCustomerNbr.setText("");
				}
			}
		});
		btnSk.setBounds(32, 229, 97, 25);
		frame.getContentPane().add(btnSk);

		JButton btnndra = new JButton("\u00C4ndra");
		btnndra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String name = txtCustomerName.getText();
				String customerNbr = txtCustomerNbr.getText();
				String address = txtCustomerAddress.getText();
				Customer c = controller.findCustomer(customerNbr);
				
				if (customerNbr.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer.");
				}
				else if (!name.isEmpty()) {
					c.setName(name);
					txtCustomerName.setText("");
					messageBox.setText("Kund med kundnummer: " + customerNbr + " är ändrad.");
				}
				else if (!address.isEmpty()) {
					c.setAddress(address);
					messageBox.setText("Kund med kundnummer: " + customerNbr + " är ändrad.");
					txtCustomerAddress.setText("");
				}
				else if (c != null) {
					c.setName(name);
					c.setAddress(address);
					txtCustomerName.setText("");
					txtCustomerAddress.setText("");
					txtCustomerNbr.setText("");
					messageBox.setText("Kund med kundnummer: " + customerNbr + " är ändrad.");
				
				}
				else if (c == null) {
					messageBox.setText("Kunden finns inte.");
				}
				
			}
		});
		btnndra.setBounds(141, 229, 97, 25);
		frame.getContentPane().add(btnndra);

		JButton btnSkapa_1 = new JButton("Skapa");
		btnSkapa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtProductName.getText();
				String category = txtProductCategory.getText();
				int price = Integer.parseInt(txtProductPrice.getText());
				String orderLineNbr = txtOrderLineNbr_Product.getText();
				Product p = controller.findProductFromRegister(name);
				
				if (name.isEmpty() || category.isEmpty() || orderLineNbr.isEmpty() || !txtProductPrice.contains(1,2) || !txtProductPrice.contains(3,4) || !txtProductPrice.contains(5,6) || !txtProductPrice.contains(7,8) || !txtProductPrice.contains(9,0)) {
					messageBox.setText("Du måste fylla i alla fält.");
				}

				else if (controller.fetchProductFromRegister().containsValue(p)) {
					controller.addProductFromOrderLine(name, category, price);
					messageBox.setText("En produkt med namnet " + name + " finns redan i registret men läggs till på vald orderrad.");
				} 
				
				else {
					controller.addProductFromRegister(name, category, price);
					controller.addProductFromOrderLine(name, category, price);
					messageBox.setText(name + " är tillagd i produktregistret och till vald orderrad.");
					txtProductName.setText("");
					txtProductCategory.setText("");
					txtProductPrice.setText("");
				}	
			}
		});
		btnSkapa_1.setBounds(344, 216, 97, 25);
		frame.getContentPane().add(btnSkapa_1);

		JButton btnTaBort_1 = new JButton("Ta bort");
		btnTaBort_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String productName = txtProductName.getText();
				Product p = controller.findProductFromOrderLine(productName);

				if (productName.isEmpty()) {
					messageBox.setText("Du måste fylla i kundnummer");
				}

				else if (p != null) {
					controller.removeProductFromRegister(productName);
					controller.removeProductFromOrderLine(productName);
					messageBox.setText("Produkten är borttagen.");
					txtProductName.setText("");
				} else {
					messageBox.setText("Produkten gick inte att hitta.");
					txtProductName.setText("");
				}
				
			}
		});
		btnTaBort_1.setBounds(453, 216, 97, 25);
		frame.getContentPane().add(btnTaBort_1);

		JButton btnSk_1 = new JButton("S\u00F6k");
		btnSk_1.setBounds(344, 254, 97, 25);
		frame.getContentPane().add(btnSk_1);

		JButton btnndra_1 = new JButton("\u00C4ndra");
		btnndra_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtProductName.getText();
				String category = txtProductCategory.getText();
				int price = Integer.parseInt(txtProductPrice.getText());
				Product p = controller.findProductFromRegister(name);
				
				if (name.isEmpty()) {
					messageBox.setText("Du måste fylla i produktnamn.");
				}
				else if (!category.isEmpty()) {
					p.setName(name);
					txtProductCategory.setText("");
					messageBox.setText("Produkten " + name + " är ändrad.");
				}
				else if (!txtProductPrice.contains(1,2) || !txtProductPrice.contains(3,4) || !txtProductPrice.contains(5,6) || !txtProductPrice.contains(7,8) || !txtProductPrice.contains(9,0)) {
					p.setPrice(price);
					messageBox.setText("Produkten " + name + " är ändrad.");
					txtProductPrice.setText("");
				}
				else if (p != null) {
					p.setCategory(category);
					p.setPrice(price);
					txtProductName.setText("");
					txtProductCategory.setText("");
					txtProductPrice.setText("");
					messageBox.setText("Produkten " + name + " är ändrad.");
				
				}
				else if (p == null) {
					messageBox.setText("Produkten finns inte.");
				}
				
			}
		});
		btnndra_1.setBounds(453, 254, 97, 25);
		frame.getContentPane().add(btnndra_1);

		JLabel lblLggTilltaBort = new JLabel("L\u00E4gg till/ta bort order fr\u00E5n kund");
		lblLggTilltaBort.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLggTilltaBort.setBounds(21, 410, 275, 16);
		frame.getContentPane().add(lblLggTilltaBort);

		JLabel lblLggTilltaBort_1 = new JLabel("L\u00E4gg till/ta bort orderrad fr\u00E5n order");
		lblLggTilltaBort_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLggTilltaBort_1.setBounds(21, 571, 275, 16);
		frame.getContentPane().add(lblLggTilltaBort_1);

		JLabel lblKund_1 = new JLabel("Kundnr:");
		lblKund_1.setBounds(21, 439, 87, 16);
		frame.getContentPane().add(lblKund_1);

		JLabel lblOrder = new JLabel("OrderID:");
		lblOrder.setBounds(21, 468, 56, 16);
		frame.getContentPane().add(lblOrder);

		txtCustomerNbr_Order = new JTextField();
		txtCustomerNbr_Order.setBounds(111, 436, 116, 22);
		frame.getContentPane().add(txtCustomerNbr_Order);
		txtCustomerNbr_Order.setColumns(10);

		txtOrderID = new JTextField();
		txtOrderID.setBounds(111, 465, 116, 22);
		frame.getContentPane().add(txtOrderID);
		txtOrderID.setColumns(10);

		JLabel lblNewLabel = new JLabel("OrderID:");
		lblNewLabel.setBounds(21, 600, 56, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblOrderrad = new JLabel("Orderradsnr:");
		lblOrderrad.setBounds(21, 629, 87, 16);
		frame.getContentPane().add(lblOrderrad);

		txtOrderID_OrderLine = new JTextField();
		txtOrderID_OrderLine.setBounds(112, 597, 116, 22);
		frame.getContentPane().add(txtOrderID_OrderLine);
		txtOrderID_OrderLine.setColumns(10);

		txtOrderLineNbr = new JTextField();
		txtOrderLineNbr.setBounds(112, 626, 116, 22);
		frame.getContentPane().add(txtOrderLineNbr);
		txtOrderLineNbr.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Orderradsnr:");
		lblNewLabel_1.setBounds(278, 188, 111, 16);
		frame.getContentPane().add(lblNewLabel_1);

		txtOrderLineNbr_Product = new JTextField();
		txtOrderLineNbr_Product.setBounds(360, 185, 164, 22);
		frame.getContentPane().add(txtOrderLineNbr_Product);
		txtOrderLineNbr_Product.setColumns(10);

		JLabel lblLggTilltaBort_3 = new JLabel("L\u00E4gg till/ta bort exemplar fr\u00E5n produkt");
		lblLggTilltaBort_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblLggTilltaBort_3.setBounds(308, 410, 291, 16);
		frame.getContentPane().add(lblLggTilltaBort_3);

		JLabel lblNewLabel_2 = new JLabel("Produktnr:");
		lblNewLabel_2.setBounds(294, 440, 72, 16);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblExemplar = new JLabel("Serienr:");
		lblExemplar.setBounds(294, 469, 72, 16);
		frame.getContentPane().add(lblExemplar);

		txtProductNbr_Copy = new JTextField();
		txtProductNbr_Copy.setBounds(385, 437, 116, 22);
		frame.getContentPane().add(txtProductNbr_Copy);
		txtProductNbr_Copy.setColumns(10);

		txtCopySerialNbr = new JTextField();
		txtCopySerialNbr.setBounds(385, 466, 116, 22);
		frame.getContentPane().add(txtCopySerialNbr);
		txtCopySerialNbr.setColumns(10);

		JLabel lblOrdersummering = new JLabel("Ordersummering");
		lblOrdersummering.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblOrdersummering.setBounds(398, 571, 126, 16);
		frame.getContentPane().add(lblOrdersummering);

		JLabel lblOrder_1 = new JLabel("Ordernr:");
		lblOrder_1.setBounds(344, 610, 56, 16);
		frame.getContentPane().add(lblOrder_1);

		txtOneSpecificOrderNbr = new JTextField();
		txtOneSpecificOrderNbr.setBounds(404, 607, 116, 22);
		frame.getContentPane().add(txtOneSpecificOrderNbr);
		txtOneSpecificOrderNbr.setColumns(10);

		JButton btnVisa = new JButton("Visa");
		btnVisa.setBounds(532, 606, 97, 25);
		frame.getContentPane().add(btnVisa);

		JLabel lblTotalbelopp = new JLabel("Totalbelopp:");
		lblTotalbelopp.setBounds(326, 647, 80, 16);
		frame.getContentPane().add(lblTotalbelopp);

		JTextArea txtOrderTotalSum = new JTextArea();
		txtOrderTotalSum.setBounds(404, 644, 116, 22);
		frame.getContentPane().add(txtOrderTotalSum);

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
		
		JButton btnLggTill = new JButton("L\u00E4gg till");
		btnLggTill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String customerNbr = txtCustomerNbr_Order.getText();
				String orderID = txtOrderID.getText();
				String deliveryDate = txtOrderDeliveryDate.getText();
				Order o = controller.findOrder(orderID);
				
				if (customerNbr.isEmpty() || orderID.isEmpty() || deliveryDate.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}

				else if (controller.fetchOrder().containsValue(o)) {
					messageBox.setText("En order med orderID " + orderID + " finns redan hos kunden.");
				} 
				
				else {
					controller.addOrder(orderID, deliveryDate);
					messageBox.setText("Ordern är tillagd till kunden");
					txtCustomerNbr_Order.setText("");
					txtOrderID.setText("");
					txtOrderDeliveryDate.setText("");
				}
				
			}
		});
		btnLggTill.setBounds(40, 526, 97, 25);
		frame.getContentPane().add(btnLggTill);
		
		JButton btnTaBort_2 = new JButton("Ta bort");
		btnTaBort_2.setBounds(149, 526, 97, 25);
		frame.getContentPane().add(btnTaBort_2);
		
		JButton btnLggTill_1 = new JButton("L\u00E4gg till");
		btnLggTill_1.setBounds(73, 686, 97, 25);
		frame.getContentPane().add(btnLggTill_1);
		
		JButton btnTaBort_3 = new JButton("Ta bort");
		btnTaBort_3.setBounds(182, 686, 97, 25);
		frame.getContentPane().add(btnTaBort_3);
		
		JButton btnLggTill_3 = new JButton("L\u00E4gg till");
		btnLggTill_3.setBounds(344, 498, 97, 25);
		frame.getContentPane().add(btnLggTill_3);
		
		JButton btnTaBort_5 = new JButton("Ta bort");
		btnTaBort_5.setBounds(453, 498, 97, 25);
		frame.getContentPane().add(btnTaBort_5);
		
		JLabel lblId = new JLabel("Leveransdatum:");
		lblId.setBounds(12, 497, 126, 16);
		frame.getContentPane().add(lblId);
		
		txtOrderDeliveryDate = new JTextField();
		txtOrderDeliveryDate.setColumns(10);
		txtOrderDeliveryDate.setBounds(111, 494, 116, 22);
		frame.getContentPane().add(txtOrderDeliveryDate);
		
		JLabel lblOrderradsantal = new JLabel("Antal exemplar:");
		lblOrderradsantal.setBounds(12, 658, 117, 16);
		frame.getContentPane().add(lblOrderradsantal);
		
		txtOrderLineAmount = new JTextField();
		txtOrderLineAmount.setColumns(10);
		txtOrderLineAmount.setBounds(112, 654, 116, 22);
		frame.getContentPane().add(txtOrderLineAmount);
		
		JLabel lblFrAtt = new JLabel("* F\u00F6r att \u00E4ndra kund kr\u00E4vs Kundnr.");
		lblFrAtt.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt.setBounds(32, 297, 235, 16);
		frame.getContentPane().add(lblFrAtt);
		
		JLabel lblFrAtt_1 = new JLabel("* F\u00F6r att \u00E4ndra produkt kr\u00E4vs Namn.");
		lblFrAtt_1.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt_1.setBounds(32, 314, 243, 16);
		frame.getContentPane().add(lblFrAtt_1);
		
		JLabel lblFrAtt_2 = new JLabel("* F\u00F6r att ta bort produkt fr\u00E5n orderrad kr\u00E4vs orderradsnr & produktnamn");
		lblFrAtt_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt_2.setBounds(31, 332, 428, 16);
		frame.getContentPane().add(lblFrAtt_2);
		
		JLabel lblFrAtt_3 = new JLabel("* F\u00F6r att ta bort produkt fr\u00E5n produktregister, ange produktnamn.");
		lblFrAtt_3.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblFrAtt_3.setBounds(32, 351, 427, 16);
		frame.getContentPane().add(lblFrAtt_3);

	}
}
