package windowbuilder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class UI_PROFILE extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI_PROFILE frame = new UI_PROFILE();
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
	public UI_PROFILE() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 29, 198, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(12, 171, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(115, 171, 97, 23);
		contentPane.add(btnNewButton_1);
		
		textField = new JTextField();
		textField.setBounds(12, 140, 198, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("쪽지");
		label.setBounds(12, 115, 57, 15);
		contentPane.add(label);
	}
}
