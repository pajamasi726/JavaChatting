package protocol;

public class ProtocolMsg {
	
	private Protocol procotol;
	private String msg;
	private String subCode;
	
	public ProtocolMsg(Protocol protocol, String msg){
		this.procotol = protocol;
		this.msg = msg;
		this.subCode = "null";
	}
	
	public ProtocolMsg(Protocol protocol, String msg, String subCode){
		this.procotol = protocol;
		this.msg = msg;
		this.subCode = subCode;
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
	
	
	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	@Override
	public String toString() {
		return this.getProcotol().name()+","+this.getMsg()+","+this.getSubCode();
	}
}
