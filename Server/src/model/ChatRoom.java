package model;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
	
	private String roomName;		// date toString()
	private List<Client> userList;	// 참여한 사용자 리스트
	
	
	public ChatRoom(String roomName){
		this.roomName = roomName;
		userList = new ArrayList<>();
	}
	
	public void addUser(Client client){
		userList.add(client);
	}
	
	public void removeUser(Client client){
		userList.remove(client);
	}
	
	public void broadCast(String msg){
		
		for(Client client : userList){
			client.msgToClient(msg);
		}
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public List<Client> getUserList() {
		return userList;
	}

	public void setUserList(List<Client> userList) {
		this.userList = userList;
	}

}
