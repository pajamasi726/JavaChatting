package model;

import java.util.ArrayList;
import java.util.List;

import resource.ChatFrameComponent;

public class ChatRoom {
	
	public static List<ChatRoom> roomList = new ArrayList<>();
	
	private String roomName;		// date toString()
	private ChatFrameComponent chatFrameComponent;
	
	public ChatRoom(String roomName, ChatFrameComponent chatFrameComponent){
		this.roomName = roomName;
		this.chatFrameComponent = chatFrameComponent;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public ChatFrameComponent getChatFrameComponent() {
		return chatFrameComponent;
	}

	public void setChatFrameComponent(ChatFrameComponent chatFrameComponent) {
		this.chatFrameComponent = chatFrameComponent;
	}
	
	
}
