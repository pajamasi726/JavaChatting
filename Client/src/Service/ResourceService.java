package Service;

import Resource.LoginFrameComponent;

public class ResourceService {
	
	private static ResourceService resourceService;
	
	private LoginFrameComponent loginFrameComponent;
	
	
	public void init(){
		loginFrameComponent = new LoginFrameComponent();
	}
	
	public void loginFrameShow(){
		loginFrameComponent.setVisible(true);
	}

	
	
	
	
	/** 싱글톤 적용 */
	private ResourceService(){
		
	}
	
	public static ResourceService getInstance(){
		if(resourceService == null){
			resourceService = new ResourceService();
		}
		
		return resourceService;
	}

}
