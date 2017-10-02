package listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import resource.ProfileFrameComponent;
import service.ResourceService;

public class ListEventListener implements MouseListener{

	private ResourceService resourceService;
	
	{
		resourceService = ResourceService.getInstance();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getClickCount());
		if(e.getClickCount() == 2){
			String nickName = resourceService.getIndexNickName(e.getPoint());
			new ProfileFrameComponent(nickName).setVisible(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
