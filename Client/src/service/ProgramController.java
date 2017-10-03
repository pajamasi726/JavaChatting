package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Util.MsgUtil;
import customInterface.NetworkInMsgListener;
import model.ChatRoom;
import protocol.ProtocolMsg;
import resource.ChatFrameComponent;

public class ProgramController implements NetworkInMsgListener{
	
	private ResourceService resourceService;
	private ServerService serverService;
	
	private List<ChatRoom> roomList;
	/** 모든 리소스 로딩 */
	{
		resourceService = ResourceService.getInstance();
		serverService = ServerService.getInstance();
		roomList = new ArrayList<>();
	}
	
	
	public ProgramController(){
		// UI 로딩
		resourceService.init();
		resourceService.loginFrameShow();
		
		// 서버에 접속
		serverService.connectServer();
		
		// 서버에 리스너 설정
		serverService.setNetworkInMsgListener(this);
		
	}


	@Override
	public void inMsg(String str) {
		System.out.println("서버로부터 들어온 메세지 : "+str);
		
		ProtocolMsg protocolMsg = MsgUtil.getProtocolDecoding(str);
		
		String roomName = "";
		
		switch(protocolMsg.getProcotol()){
			
			case NICK_NAME : // 닉네임이 정상적으로 성공하였을때
				resourceService.userListFrameShow();
				// 자기자신 이름 추가
				String nickName = resourceService.getNickName();
				resourceService.addUser(nickName);
				
				// 로그인창 가리기
				resourceService.loginFrameHide();
			break;
			
			case NEW_USER :	// 새로운 사용자가 접속했을때
				resourceService.addUser(protocolMsg.getMsg());
			break;
			
			case USER_LIST :	// 사용자 리스트
				resourceService.addUserList(protocolMsg.getMsg());
			break;
			
			case START_CHAT :	// 채팅 시작
				roomName = protocolMsg.getMsg();
				ChatFrameComponent chatFrameComponent = new ChatFrameComponent(roomName);
				ChatRoom chatRoom = new ChatRoom(roomName, chatFrameComponent);
				chatFrameComponent.setVisible(true);
				roomList.add(chatRoom);
			break;
			
			case MESSAGE_CHAT :	// 채팅 메세지가 들어올때
				String chatMsg = protocolMsg.getMsg();
				roomName = protocolMsg.getSubCode();
				
				// 1.
				for(ChatRoom room : roomList){
					if(room.getRoomName().equals(roomName)){
						room.getChatFrameComponent().addChatMsg(chatMsg);
					}
				}
			break;
			
			
			case NOTE :	// 쪽지가 도착 했을때
				String from = protocolMsg.getSubCode();
				JOptionPane.showMessageDialog(null, protocolMsg.getMsg(), from+"로부터 온 쪽지", JOptionPane.PLAIN_MESSAGE);
			break;
		
			case SUCCESS :
				
			break;	
		
			case ERROR :
				String msg = protocolMsg.getMsg();
				JOptionPane.showMessageDialog(null, msg, "오류", JOptionPane.ERROR_MESSAGE);
			break;	
		}
		
	}

}
