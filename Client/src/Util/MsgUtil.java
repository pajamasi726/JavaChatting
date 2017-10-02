package Util;

import java.util.StringTokenizer;

import protocol.Protocol;
import protocol.ProtocolMsg;

public class MsgUtil {
	
	public static String getProtocolEncoding(Protocol protocol, String msg){
		
		return protocol.name()+","+msg;
	}
	
	public static ProtocolMsg getProtocolDecoding(String msg){
		
		StringTokenizer st = new StringTokenizer(msg, ",");
		ProtocolMsg protocolMsg = null;
		while(st.hasMoreTokens()){
			Protocol protocol = Protocol.valueOf(st.nextToken());
			String clientMsg = st.nextToken();
			protocolMsg = new ProtocolMsg(protocol, clientMsg);
		}
		
		return protocolMsg;
	}

}
