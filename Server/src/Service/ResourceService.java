package Service;

import Resource.MainFrameComponent;

/**
 * @author Administrator
 * 리소스 비지니스 로직을 담당하는 서비스
 */

public class ResourceService {
	
	private MainFrameComponent mainFrameComponent;

	{
		mainFrameComponent = new MainFrameComponent();
	}
	
	public void mainFrameShow(){
		mainFrameComponent.setVisible(true);
	}

}
