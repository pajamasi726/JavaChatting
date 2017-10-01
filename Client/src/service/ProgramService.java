package service;

import customInterface.NetworkInMsgListener;

public class ProgramService implements NetworkInMsgListener{
	
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
		resourceService.loginFrameShow();
		
		// 서버에 리스너 설정
		serverService.setNetworkInMsgListener(this);
		
	}


	@Override
	public void inMsg(String str) {
		System.out.println("서버로부터 들어온 메세지 : "+str);
		
	}

}
