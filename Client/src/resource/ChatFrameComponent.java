package resource;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Util.MsgUtil;
import protocol.Protocol;
import service.ServerService;

public class ChatFrameComponent extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	private JTextField textField;
	private String roomName;
	private JTextArea chatArea;
	
	private ServerService serverService;
	
	public ChatFrameComponent(String roomName){
		
		this.roomName = roomName;
		serverService = ServerService.getInstance();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 274, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 234, 284);
		contentPane.add(scrollPane);
		
		chatArea = new JTextArea();
		scrollPane.setViewportView(chatArea);
		
		textField = new JTextField();
		textField.setBounds(12, 304, 130, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("전송");
		btnNewButton.setBounds(149, 303, 97, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
	}
	
	public void addChatMsg(String msg){
		this.chatArea.append(msg+"\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String chatMsg = textField.getText();
		//[ Protocol ] [ 메세지 ] [채팅방]
		String msg = MsgUtil.getProtocolEncoding(Protocol.MESSAGE_CHAT, chatMsg , this.roomName);
		serverService.sendMsg(msg);
	}

}
