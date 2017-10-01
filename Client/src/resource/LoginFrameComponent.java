package resource;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import listener.ButtonEventListener;

public class LoginFrameComponent extends JFrame{
	
	private JTextField textField;
	private JLabel errorLabel;
	private JButton loginBtn;
	
	public LoginFrameComponent(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 178, 241);
		
		this.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("닉네임");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 56, 138, 15);
		this.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(12, 81, 138, 21);
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		this.add(textField);
		
		loginBtn = new JButton("접속");
		loginBtn.setBounds(12, 126, 138, 23);
		loginBtn.addActionListener(new ButtonEventListener());
		this.add(loginBtn);
		
		errorLabel = new JLabel("");
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setBounds(22, 159, 116, 15);
		this.add(errorLabel);
	}
	
	public void setErrorText(String str){
		errorLabel.setText(str);
	}
	
	
	

}
