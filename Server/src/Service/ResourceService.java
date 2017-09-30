package Service;

import Resource.MainFrameComponent;

/**
 * @author Administrator
 * 리소스 비지니스 로직을 담당하는 서비스 singleton
 */

public class ResourceService {

	private static ResourceService resourceService;
	
	private MainFrameComponent mainFrameComponent;

	
	/** Component Resource Init*/
	public void init(){
		mainFrameComponent = new MainFrameComponent();
	}
	
	
	/** main frame show */
	public void mainFrameShow(){
		mainFrameComponent.setVisible(true);
	}
	
	
	/** log Text append */
	public void mainFrameAddText(String str){
		mainFrameComponent.addText(str);
	}

	
	/** SingleTon적용 */
	private ResourceService(){
		
	}
	
	public static ResourceService getInstance(){
		if(resourceService == null){
			resourceService = new ResourceService();
		}
		
		return resourceService;
	}
}
