package customInterface;

import java.util.ArrayList;
import java.util.List;

import model.ChatRoom;
import service.ResourceService;
import service.ServerService;

public class AbstractLogicService {

	protected static ResourceService resourceService;
	protected static ServerService serverService;
	
	
	{
		resourceService = ResourceService.getInstance();
		serverService = ServerService.getInstance();
	}
}
