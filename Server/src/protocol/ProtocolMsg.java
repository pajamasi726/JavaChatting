package protocol;

public class ProtocolMsg {
	
	private Protocol procotol;
	private String msg;
	
	public ProtocolMsg(Protocol protocol, String msg){
		this.procotol = protocol;
		this.msg = msg;
	}

	public Protocol getProcotol() {
		return procotol;
	}

	public void setProcotol(Protocol procotol) {
		this.procotol = procotol;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Override
	public String toString() {
		return this.getProcotol().name()+" : "+this.getMsg();
	}
}
