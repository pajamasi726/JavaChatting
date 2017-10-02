package protocol;

public enum Protocol {
	
	NICK_NAME("닉네임"),
	ERROR("에러"),
	SUCCESS("성공")
	;
	
	
	String description;
	
	Protocol(String description){
		this.description = description;
	}
}
