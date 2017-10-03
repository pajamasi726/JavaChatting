package service;

import Util.MsgUtil;
import customInterface.NetworkServerClientMsgListener;
import model.Client;
import protocol.Protocol;
import protocol.ProtocolMsg;

public class ProgramController implements NetworkServerClientMsgListener{
	
	private ResourceService resourceService;
	private ServerService serverService;
	private LogicService logicService;
	
	/** 모든 리소스 로딩 */
	{
		resourceService = ResourceService.getInstance();
		serverService = ServerService.getInstance();
		logicService = LogicService.getInstance();
	}
	
	
	public ProgramController(){
		// UI 로딩
		resourceService.init();
		resourceService.mainFrameShow();
		
		
		// 서버에 리스너 설정
		serverService.setNetworkServerClientMsgListener(this);
		
	}


	@Override
	public void clientMsg(Client client, String clientMsg) {
		ProtocolMsg protocolMsg = MsgUtil.getProtocolDecoding(clientMsg);
		System.out.println("Client : "+client.getNickName()+ " 으로 부터 들어온 메세지 : "+protocolMsg);

		String msg = "";
		
		switch(protocolMsg.getProcotol()){
			
			case NICK_NAME : // 닉네임이 들어왔을때 처리 하는 로직
				// 1. 기존 접속자중에 중첩되는 이름이 있는지 확인 한다.
				boolean value = logicService.isAlreadyUseNickName(client, protocolMsg.getMsg());
				
				if(value){
					// 닉네임 지정
					client.setNickName(protocolMsg.getMsg());
					
					// 접속자 리스트에 추가
					logicService.addUser(client);
					
					// 다른 사용자에게 자신의 접속 알리기
					logicService.newUser(client);
					
					// 나를 제외한 다른 사용자 가져가기
					logicService.userList(client);
				}else{
					msg = MsgUtil.getProtocolEncoding(Protocol.ERROR, "같은 닉네임의 사용자가 존재 합니다");
				}
				
			break;
			
			case NOTE :	// 쪽지 보내기 subCode : 받는 사람 , msg 메세지
				if(!logicService.sendNote(client, protocolMsg)){
					msg = MsgUtil.getProtocolEncoding(Protocol.ERROR, "쪽지 보내기 실패");
				}
			break;
			
			case REQUEST_CHAT :	// 채팅 시작 요청
				logicService.requestChat(client, protocolMsg);
			break;
			
			case MESSAGE_CHAT :	//	
				logicService.messageChat(client, protocolMsg);
			break;
		}
		
	}


}
