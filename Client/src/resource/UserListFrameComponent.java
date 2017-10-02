package resource;

import java.awt.Point;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import listener.ListEventListener;

public class UserListFrameComponent extends JFrame{
	
	JList jlist;
	private Vector<String> userList = new Vector<>();
	
	public UserListFrameComponent(){
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 263, 548);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 223, 490);
		contentPane.add(scrollPane);
		
		jlist = new JList();
		jlist.setListData(userList);
		scrollPane.setViewportView(jlist);
		jlist.addMouseListener(new ListEventListener());
		
	}
	
	
	
	public void addUser(String nickName){
		userList.add(nickName);
		reload();
	}
	
	public void addUserList(String userList){
		
		StringTokenizer st = new StringTokenizer(userList, ":");
		
		while(st.hasMoreTokens()){
			this.userList.add(st.nextToken());
		}
		
		reload();
	}
	
	public void removeUser(String nickName){
		
		for(String user : userList){
			
			if(user.equals(nickName)){
				userList.remove(nickName);
			}
		}
		
		reload();
	}
	
	public String getIndex(Point point){
		return userList.get(jlist.locationToIndex(point));
		
	}
	
	public void reload(){
		jlist.setListData(userList);
	}

}
