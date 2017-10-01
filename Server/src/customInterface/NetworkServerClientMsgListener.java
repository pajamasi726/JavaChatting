package customInterface;

import model.Client;

public interface NetworkServerClientMsgListener {
	
	public void clientMsg(Client client, String clientMsg);

}
