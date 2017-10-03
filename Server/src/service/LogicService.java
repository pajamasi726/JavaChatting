package service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Util.MsgUtil;
import model.ChatRoom;
import model.Client;
import protocol.Protocol;
import protocol.ProtocolMsg;

public class LogicService {
	
	private static LogicService logicService;
	
	public static List<Client> userList = new ArrayList<>();	// 접속한 사용자 리스트
	public static List<ChatRoom> roomList = new ArrayList<>();	// 채팅방 리스트

	/** 서버에 사용자 추가 */
	public void addUser(Client client){
		userList.add(client);
	}
	
	/** 새로운 사용자 알림 */
	public void newUser(Client client){
		// 접속한 사용자를 빼고 나머지 사용자들에게 접속한 사람 알리기
		
		for(Client user : userList){
			if(!user.getNickName().equals(client.getNickName())){
				String msg = MsgUtil.getProtocolEncoding(Protocol.NEW_USER, client.getNickName());
				user.msgToClient(msg);
			}
		}
	}
	
	/** 자신을 제외한 다른 사용자 리스트 받아가기 */
	public void userList(Client client){
		
		String userList = this.userList.stream()
				.filter(dto -> !dto.getNickName().equals(client.getNickName()))
				.map(dto -> dto.getNickName())
				.collect(Collectors.joining(":"));
		
		if(userList.length() > 0){
			String msg = MsgUtil.getProtocolEncoding(Protocol.USER_LIST, userList);
			client.msgToClient(msg);
		}
	}
	
	/** 이미 접속하였는지 사용자 체크하고 사용자에게 접속 알림 */
	public boolean isAlreadyUseNickName(Client client, String nickName){
		
		for(Client user : userList){
			if(user.getNickName().equals(nickName)){
				return false;
			}
		}
		
		String msg = MsgUtil.getProtocolEncoding(Protocol.NICK_NAME, "성공");
		client.msgToClient(msg);
		
		return true;
	}
	
	/** 쪽지 보내기 */
	public boolean sendNote(Client client, ProtocolMsg protocolMsg){
		
		// 1. 받는 사람찾기
		for(Client user : userList){
			
			if(user.getNickName().equals(protocolMsg.getSubCode())){
				// 쪽지 , 내용 , 보낸사람
				String msg = MsgUtil.getProtocolEncoding(Protocol.NOTE, protocolMsg.getMsg(), client.getNickName());
				user.msgToClient(msg);
				return true;
			}
		}
		
		return false;
	}
	
	/** 채팅 요청 */
	public boolean requestChat(Client client, ProtocolMsg protocolMsg){
		
		// 1. 채팅을 시작할 사용자 찾기
		for(Client user : userList){
			
			if(user.getNickName().equals(protocolMsg.getMsg())){
				// [Protocol.CHAT_START] , [채팅 방이름 유일한 값]
				String chatRoomName = new Date().toString();
				String msg = MsgUtil.getProtocolEncoding(Protocol.START_CHAT, chatRoomName);
				
				// 사용자와 참여하는 사람에게 알리기
				client.msgToClient(msg);
				user.msgToClient(msg);
				
				// 채팅 방생성해서 사용자 저장
				ChatRoom chatRoom = new ChatRoom(chatRoomName);
				chatRoom.addUser(user);
				chatRoom.addUser(client);
				
				roomList.add(chatRoom);
				return true;
			}
		}
		
		return false;
	}
	
	/** 채팅 메세지 전달 */
	public boolean messageChat(Client client, ProtocolMsg protocolMsg){
		String roomName = protocolMsg.getSubCode();
		String chatMsg = protocolMsg.getMsg();
		
		// 1. 채팅방 찾기
		for(ChatRoom chatRoom : roomList){
			
			if(chatRoom.getRoomName().equals(roomName)){
				// 전체 사용자에게 전송
				String msg = MsgUtil.getProtocolEncoding(Protocol.MESSAGE_CHAT, chatMsg, roomName);
				chatRoom.broadCast(msg);
				return true;
			}
		}
		return false;
	}

	
	/** SingleTon적용 */
	private LogicService(){
		
	}
	
	public static LogicService getInstance(){
		if(logicService == null){
			logicService = new LogicService();
		}
		
		return logicService;
	}
	
}
