package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Util.MsgUtil;
import model.Client;
import protocol.Protocol;
import protocol.ProtocolMsg;

public class LogicService {
	
	private static LogicService logicService;
	
	public static List<Client> userList = new ArrayList<>();

	/** 서버에 사용자 추가 */
	public void addUser(Client client){
		userList.add(client);
	}
	
	/** 새로운 사용자 알림 */
	public void newUser(Client client){
		// 접속한 사용자를 빼고 나머지 사용자들에게 접속한 사람 알리기
		
		for(Client user : userList){
			if(!user.getNickName().equals(client.getNickName())){
				String msg = MsgUtil.getProtocolEncoding(Protocol.NEW_USER, client.getNickName());
				user.msgToClient(msg);
			}
		}
	}
	
	/** 자신을 제외한 다른 사용자 리스트 받아가기 */
	public void userList(Client client){
		
		String userList = this.userList.stream()
				.filter(dto -> !dto.getNickName().equals(client.getNickName()))
				.map(dto -> dto.getNickName())
				.collect(Collectors.joining(":"));
		
		if(userList.length() > 0){
			String msg = MsgUtil.getProtocolEncoding(Protocol.USER_LIST, userList);
			client.msgToClient(msg);
		}
	}
	
	/** 이미 접속하였는지 사용자 체크하고 사용자에게 접속 알림 */
	public boolean isAlreadyUseNickName(Client client, String nickName){
		
		for(Client user : userList){
			if(user.getNickName().equals(nickName)){
				return false;
			}
		}
		
		String msg = MsgUtil.getProtocolEncoding(Protocol.NICK_NAME, "성공");
		client.msgToClient(msg);
		
		return true;
	}
	
	/** 쪽지 보내기 */
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
