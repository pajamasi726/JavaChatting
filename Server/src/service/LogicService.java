package service;

import java.util.ArrayList;
import java.util.List;

import Util.MsgUtil;
import model.Client;
import protocol.Protocol;
import protocol.ProtocolMsg;

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
	
	public boolean sendNote(Client client, ProtocolMsg protocolMsg){
		
		// 1. 받는 사람찾기
		for(Client user : userList){
			
			if(user.getNickName().equals(protocolMsg.getSubCode())){
				// 쪽지 , 내용 , 보낸사람
				String msg = MsgUtil.getProtocolEncoding(Protocol.NOTE, protocolMsg.getMsg(), client.getNickName());
				user.msgToClient(msg);
				return true;
			}
		}
		
		return false;
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
