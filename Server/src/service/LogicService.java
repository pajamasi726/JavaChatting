package service;

import java.util.ArrayList;
import java.util.List;

import model.Client;

public class LogicService {
	
	private static LogicService logicService;
	
	public static List<Client> userList = new ArrayList<>();

	
	public void addUser(Client client){
		userList.add(client);
	}
	
	
	public boolean isAlreadyUseNickName(Client client, String nickName){
		
		for(Client user : userList){
			if(user.getNickName().equals(nickName)){
				return false;
			}
		}
		
		return true;
	}

	
	/** SingleTon적용 */
	private LogicService(){
		
	}
	
	public static LogicService getInstance(){
		if(logicService == null){
			logicService = new LogicService();
		}
		
		return logicService;
	}
	
}
