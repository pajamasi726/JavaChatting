package service;


public class ServerService {
	
	private static ServerService serverService;
	
	private ResourceService resourceService;
	
	/** GUI 셋팅 */
	public void startProgram(){
		resourceService.init();
		resourceService.loginFrameShow();
	}
	
	
	
	
	/** SingleTon적용 */
	private ServerService(){
		resourceService = ResourceService.getInstance();
	}
	
	public static ServerService getInstance(){
		
		if(serverService == null){
			serverService = new ServerService();
		}
		
		return serverService;
	}
}
