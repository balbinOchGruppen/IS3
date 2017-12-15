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

@SuppressWarnings("serial")
public class ChangeProduct extends JFrame {

	/**
	 * 
	 */
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public void productTab() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeProduct frame = new ChangeProduct();
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
	public ChangeProduct() {
		setTitle("\u00C4ndring av produkt");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblndringAvProdukt = new JLabel("\u00C4ndring av produkt");
		lblndringAvProdukt.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblndringAvProdukt.setBounds(153, 13, 164, 16);
		contentPane.add(lblndringAvProdukt);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(153, 46, 164, 22);
		contentPane.add(textField);
		
		JLabel label_1 = new JLabel("Namn:");
		label_1.setBounds(92, 49, 56, 16);
		contentPane.add(label_1);
		
		JLabel lblKategori = new JLabel("Kategori:");
		lblKategori.setBounds(92, 76, 56, 16);
		contentPane.add(lblKategori);
		
		JLabel lblPris = new JLabel("Pris:");
		lblPris.setBounds(92, 105, 56, 16);
		contentPane.add(lblPris);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(153, 106, 164, 22);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(153, 76, 164, 22);
		contentPane.add(textField_2);
		
		JButton button = new JButton("Avbryt");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(220, 157, 97, 25);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Godk\u00E4nn");
		button_1.setBounds(111, 157, 97, 25);
		contentPane.add(button_1);
	}

}
