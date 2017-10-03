package service;


import Util.MsgUtil;
import customInterface.AbstractLogicService;
import protocol.Protocol;

public class OutBoundLogicService extends AbstractLogicService{
	
	private static OutBoundLogicService outBoundLogicService;

	
	public void connect(Object object){
		String nickName = String.valueOf(object).replaceAll(" ", "").trim();
		String msg = MsgUtil.getProtocolEncoding(Protocol.NICK_NAME, nickName);
		serverService.sendMsg(msg);
	}
	

	
	
	private OutBoundLogicService(){
		
	}
	
	
	public static OutBoundLogicService getInstance(){
		
		if(outBoundLogicService == null){
			outBoundLogicService = new OutBoundLogicService();
		}
		return outBoundLogicService;
	}
}
