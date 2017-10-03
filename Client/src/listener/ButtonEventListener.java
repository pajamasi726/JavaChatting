package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Util.MsgUtil;
import protocol.Protocol;
import service.ResourceService;
import service.ServerService;

public class ButtonEventListener implements ActionListener{

	private ResourceService resourceService;
	private ServerService serverService;
	
	{
		resourceService = ResourceService.getInstance();
		serverService = ServerService.getInstance();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button click event : "+e.getActionCommand());
		String command = e.getActionCommand();
		switch(command){
		
			case "접속":
				String nickName = resourceService.getNickName();
				serverService.outBountMsg(Protocol.NICK_NAME, nickName);
			break;
		}
	}
	
}
