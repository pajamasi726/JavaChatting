package listner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import service.ResourceService;
import service.ServerService;

public class ButtonEventListener implements ActionListener{

	private ResourceService resourceService;
	private ServerService programService;
	
	{
		resourceService = ResourceService.getInstance();
		programService = ServerService.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button click event : "+e.getActionCommand());

		String command = e.getActionCommand();
		switch(command){
		
			case "서버 시작":
				programService.startServer();
			break;
			
			case "서버 종료":
				programService.closeServer();
			break;
		}
	}
	
}
