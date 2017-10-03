package customInterface;

import java.awt.event.ActionEvent;

import protocol.Protocol;

public interface NetworkInMsgListener {

	public void inMsg(String str);
	
	public void outBoundMsg(Protocol protocol, Object object);
}
