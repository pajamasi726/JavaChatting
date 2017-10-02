package protocol;

public enum Protocol {
	
	NICK_NAME("닉네임"),
	ERROR("에러"),
	SUCCESS("성공"),
	NOTE("쪽지"),
	NEW_USER("새로운사용자"),
	USER_LIST("사용자리스트"),
	;
	
	
	String description;
	
	Protocol(String description){
		this.description = description;
	}
}
