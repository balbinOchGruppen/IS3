
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ChangeCustomer extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtCustomerNbr;

	/**
	 * Launch the application.
	 */
	public void customerTab() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeCustomer frame = new ChangeCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChangeCustomer() {
		
		Applikation applikation = new Applikation();
		CustomerRegister customerRegister = new CustomerRegister();
		ProductRegister productRegister = new ProductRegister();
		JFrame frame = new JFrame();
		Controller controller = new Controller(productRegister, customerRegister, frame);
		
		setTitle("\u00C4ndring av kund");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u00C4ndring av kund");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(154, 13, 164, 16);
		contentPane.add(label);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(154, 73, 164, 22);
		contentPane.add(txtName);
		
		JLabel lblNyttNamn = new JLabel("Nytt namn:");
		lblNyttNamn.setBounds(84, 76, 75, 16);
		contentPane.add(lblNyttNamn);
		
		JLabel lblSkrivKundensNummer = new JLabel("Skriv kundens nummer:");
		lblSkrivKundensNummer.setBounds(12, 44, 149, 16);
		contentPane.add(lblSkrivKundensNummer);
		
		JLabel lblNyAdress = new JLabel("Ny adress:");
		lblNyAdress.setBounds(84, 108, 75, 16);
		contentPane.add(lblNyAdress);
		
		txtAddress = new JTextField();
		txtAddress.setColumns(10);
		txtAddress.setBounds(154, 106, 164, 22);
		contentPane.add(txtAddress);
		
		txtCustomerNbr = new JTextField();
		txtCustomerNbr.setColumns(10);
		txtCustomerNbr.setBounds(154, 42, 164, 22);
		contentPane.add(txtCustomerNbr);
		
		JLabel messageBox = new JLabel("");
		messageBox.setBounds(25, 195, 380, 45);
		contentPane.add(messageBox);
		
		JButton button = new JButton("Avbryt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(221, 157, 97, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Godk\u00E4nn");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = txtName.getText();
				String customerNbr = txtCustomerNbr.getText();
				String address = txtAddress.getText();
				Customer c = controller.findCustomerToChange(customerNbr);
				
				if (name.isEmpty() || customerNbr.isEmpty() || address.isEmpty()) {
					messageBox.setText("Du måste fylla i alla fält.");
				}
				
				else if (c != null) {
					c.setName(name);
					c.setAddress(address);
					dispose();
				}
				else if (c == null) {
					messageBox.setText("Kunden finns inte.");
				}
				
			}
		});
		button_1.setBounds(112, 157, 97, 25);
		contentPane.add(button_1);
		
		
	}
}
