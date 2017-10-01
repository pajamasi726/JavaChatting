package service;

import customInterface.NetworkServerClientMsgListener;
import model.Client;

public class ProgramService implements NetworkServerClientMsgListener{
	
	private ResourceService resourceService;
	private ServerService serverService;
	
	/** 모든 리소스 로딩 */
	{
		resourceService = ResourceService.getInstance();
		serverService = ServerService.getInstance();
	}
	
	
	public ProgramService(){
		// UI 로딩
		resourceService.init();
		resourceService.mainFrameShow();
		
		
		// 서버에 리스너 설정
		serverService.setNetworkServerClientMsgListener(this);
		
	}


	@Override
	public void clientMsg(Client client, String clientMsg) {
		System.out.println("Client : "+client.getNickName()+ " 으로 부터 들어온 메세지 : "+clientMsg);
		
		//echo
		client.msgToClient(clientMsg);
	}


}
