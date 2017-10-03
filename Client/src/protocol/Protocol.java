package protocol;

public enum Protocol {
	
	NICK_NAME("닉네임"),
	ERROR("에러"),
	SUCCESS("성공"),
	NOTE("쪽지"),
	NEW_USER("새로운사용자"),
	USER_LIST("사용자리스트"),
	
	// 채팅
	REQUEST_CHAT("채팅요청"),
	START_CHAT("채팅시작"),
	MESSAGE_CHAT("채팅메세지"),
	;
	
	
	String description;
	
	Protocol(String description){
		this.description = description;
	}
}
