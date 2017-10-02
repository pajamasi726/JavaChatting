package service;

import javax.swing.JOptionPane;

import Util.MsgUtil;
import customInterface.NetworkInMsgListener;
import protocol.ProtocolMsg;

public class ProgramController implements NetworkInMsgListener{
	
	private ResourceService resourceService;
	private ServerService serverService;
	
	/** 모든 리소스 로딩 */
	{
		resourceService = ResourceService.getInstance();
		serverService = ServerService.getInstance();
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
		
		switch(protocolMsg.getProcotol()){
			
			case NICK_NAME : // 닉네임이 정상적으로 성공하였을때
				resourceService.userListFrameShow();
				// 자기자신 이름 추가
				String nickName = resourceService.getNickName();
				resourceService.addUser(nickName);
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
