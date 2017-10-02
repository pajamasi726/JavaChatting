package Util;

import java.util.StringTokenizer;

import protocol.Protocol;
import protocol.ProtocolMsg;

public class MsgUtil {
	
	public static String getProtocolEncoding(Protocol protocol, String msg){
		
		ProtocolMsg protocolMsg = new ProtocolMsg(protocol, msg);
		return protocolMsg.toString();
	}
	
	public static String getProtocolEncoding(Protocol protocol, String msg, String subCode){
		ProtocolMsg protocolMsg = new ProtocolMsg(protocol, msg, subCode);
		return protocolMsg.toString();
	}
	
	public static ProtocolMsg getProtocolDecoding(String msg){
		System.out.println("getProtocolDecoding : "+msg);
		
		StringTokenizer st = new StringTokenizer(msg, ",");
		ProtocolMsg protocolMsg = null;
		while(st.hasMoreTokens()){
			Protocol protocol = Protocol.valueOf(st.nextToken());
			String clientMsg = st.nextToken();
			String subCode = st.nextToken();
			
			protocolMsg = new ProtocolMsg(protocol, clientMsg, subCode);
		}
		
		return protocolMsg;
	}

}
