package resource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Util.MsgUtil;
import listener.ButtonEventListener;
import protocol.Protocol;
import service.ServerService;

public class ProfileFrameComponent extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JTextField msgTextField;
	private JLabel nickNameLabel;
	private ServerService serverService;
	
	public ProfileFrameComponent(String nickName){
		
		serverService = ServerService.getInstance();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 240, 242);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nickNameLabel = new JLabel(nickName);
		nickNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nickNameLabel.setBounds(12, 29, 198, 15);
		contentPane.add(nickNameLabel);
		
		JButton btnNewButton = new JButton("쪽지");
		btnNewButton.setBounds(12, 171, 97, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		JButton btnNewButton_1 = new JButton("대화");
		btnNewButton_1.setBounds(115, 171, 97, 23);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(this);
		
		msgTextField = new JTextField();
		msgTextField.setBounds(12, 140, 198, 21);
		contentPane.add(msgTextField);
		msgTextField.setColumns(10);
		
		JLabel label = new JLabel("쪽지");
		label.setBounds(12, 115, 57, 15);
		contentPane.add(label);
		
	}
	
	public String getMsgText(){
		return msgTextField.getText();
	}
	
	public void setNickNameLabel(String nickName){
		nickNameLabel.setText(nickName);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ServerService serverService = ServerService.getInstance();
		
		String nickName = "";
		String note = "";
		String msg = "";
		switch(e.getActionCommand()){
			case "쪽지" :
				nickName = nickNameLabel.getText();
				note = msgTextField.getText();
				msg = MsgUtil.getProtocolEncoding(Protocol.NOTE, note, nickName);
				serverService.sendMsg(msg);
			break;
			
			case "대화" : // 대화 요청 [protocol] [받는사람]
				nickName = nickNameLabel.getText();
				msg = MsgUtil.getProtocolEncoding(Protocol.REQUEST_CHAT, nickName);
				serverService.sendMsg(msg);
			break;
			
		}
		
	}

}
