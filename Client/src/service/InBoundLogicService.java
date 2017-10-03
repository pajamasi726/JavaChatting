package service;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import customInterface.AbstractLogicService;
import customInterface.NetworkInMsgListener;
import model.ChatRoom;
import protocol.ProtocolMsg;
import resource.ChatFrameComponent;

public class InBoundLogicService extends AbstractLogicService{

	private static InBoundLogicService logicService;
	

	/** 프로그램 진입 */
	public void init(NetworkInMsgListener listener) {
		// UI 로딩
		resourceService.init();
		resourceService.loginFrameShow();
		
		// 서버에 접속
		serverService.connectServer();
		
		// 서버에 리스너 설정
		serverService.setNetworkInMsgListener(listener);
	}
	
	
	/** 로그인에 성공했을때 */
	public void loginSuccess() {
		// 사용자 리스트 화면에 출력
		resourceService.userListFrameShow();
		
		// 자기자신 이름 추가
		String nickName = resourceService.getNickName();
		resourceService.addUser(nickName);
		
		// 로그인창 가리기
		resourceService.loginFrameHide();
	}
	
	
	/** 사용자 추가 */
	public void addUser(ProtocolMsg protocolMsg) {
		resourceService.addUser(protocolMsg.getMsg());
	}


	/** 사용자 리스트 추가 */
	public void addUserList(ProtocolMsg protocolMsg) {
		resourceService.addUserList(protocolMsg.getMsg());		
	}
	
	/** 채팅 시작 */
	public void startChat(ProtocolMsg protocolMsg) {
		String roomName = protocolMsg.getMsg();
		ChatFrameComponent chatFrameComponent = new ChatFrameComponent(roomName);
		ChatRoom chatRoom = new ChatRoom(roomName, chatFrameComponent);
		chatFrameComponent.setVisible(true);
		ChatRoom.roomList.add(chatRoom);
	}
	
	/** 채팅 메세지 관리 */
	public void messageChat(ProtocolMsg protocolMsg) {
		String chatMsg = protocolMsg.getMsg();
		String roomName = protocolMsg.getSubCode();
		
		// 1.
		for(ChatRoom room : ChatRoom.roomList){
			if(room.getRoomName().equals(roomName)){
				room.getChatFrameComponent().addChatMsg(chatMsg);
			}
		}
		
	}
	
	/** 쪽지 관리 */
	public void receiveNote(ProtocolMsg protocolMsg) {
		String from = protocolMsg.getSubCode();
		JOptionPane.showMessageDialog(null, protocolMsg.getMsg(), from+"로부터 온 쪽지", JOptionPane.PLAIN_MESSAGE);
	}
	
	/** 에러메세지관리 */
	public void errorAlert(ProtocolMsg protocolMsg) {
		String msg = protocolMsg.getMsg();
		JOptionPane.showMessageDialog(null, msg, "오류", JOptionPane.ERROR_MESSAGE);
	}
	
	
	private InBoundLogicService(){
		
	}
	
	
	public static InBoundLogicService getInstance(){
		
		if(logicService == null){
			logicService = new InBoundLogicService();
		}
		
		return logicService;
	}
	
}
