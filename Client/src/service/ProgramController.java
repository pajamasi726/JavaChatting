package service;

import java.awt.event.ActionEvent;

import Util.MsgUtil;
import customInterface.NetworkInMsgListener;
import protocol.Protocol;
import protocol.ProtocolMsg;

public class ProgramController implements NetworkInMsgListener{
	
	private InBoundLogicService logicService;
	private OutBoundLogicService outBoundLogicService;
	
	/** 모든 리소스 로딩 */
	{
		logicService = InBoundLogicService.getInstance();
		outBoundLogicService = OutBoundLogicService.getInstance();
	}
	
	
	public ProgramController(){
		logicService.init(this);
	}


	@Override
	public void inMsg(String str) {
		System.out.println("서버로부터 들어온 메세지 : "+str);
		
		ProtocolMsg protocolMsg = MsgUtil.getProtocolDecoding(str);
		
		String roomName = "";
		
		switch(protocolMsg.getProcotol()){
			
			case NICK_NAME : 	// 닉네임이 정상적으로 성공하였을때
				logicService.loginSuccess();
			break;
			
			case NEW_USER :		// 새로운 사용자가 접속했을때
				logicService.addUser(protocolMsg);
			break;
			
			case USER_LIST :	// 사용자 리스트
				logicService.addUserList(protocolMsg);
			break;
			
			case START_CHAT :	// 채팅 시작
				logicService.startChat(protocolMsg);
			break;
			
			case MESSAGE_CHAT :	// 채팅 메세지가 들어올때
				logicService.messageChat(protocolMsg);
			break;
			
			
			case NOTE :			// 쪽지가 도착 했을때
				logicService.receiveNote(protocolMsg);
			break;
		
			case SUCCESS :
				
			break;	
		
			case ERROR :
				logicService.errorAlert(protocolMsg);
			break;	
		}
		
	}





	@Override
	public void outBoundMsg(Protocol protocol, Object object) {
		System.out.println("OutBound Protocol : "+protocol+" , Object : "+String.valueOf(object));
		
		String msg = "";
		switch(protocol){
			
			case NICK_NAME :
				outBoundLogicService.connect(object);
			break;
		}
		
		
		
	}

}
