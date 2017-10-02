package service;

import java.awt.Point;

import resource.LoginFrameComponent;
import resource.UserListFrameComponent;

public class ResourceService {
	
	private static ResourceService resourceService;
	
	private LoginFrameComponent loginFrameComponent;
	private UserListFrameComponent userListFrameComponent;
	
	
	public void init(){
		loginFrameComponent = new LoginFrameComponent();
		userListFrameComponent = new UserListFrameComponent();
	}
	
	
	public void loginFrameShow(){
		loginFrameComponent.setVisible(true);
	}
	
	public void loginFrameHide(){
		loginFrameComponent.setVisible(false);
	}

	public String getNickName(){
		return loginFrameComponent.getTextField().getText();
	}
	
	// userList
	public void userListFrameShow(){
		userListFrameComponent.setVisible(true);
	}
	
	public void userListFrameHide(){
		userListFrameComponent.setVisible(false);
	}
	
	public void addUser(String nickName){
		userListFrameComponent.addUser(nickName);
	}
	
	public void addUserList(String userList){
		userListFrameComponent.addUserList(userList);
	}
	
	public void removeUser(String nickName){
		userListFrameComponent.removeUser(nickName);
	}
	
	public String getIndexNickName(Point point){
		return userListFrameComponent.getIndex(point);
	}
	
	
	
	
	/** 싱글톤 적용 */
	private ResourceService(){
		
	}
	
	public static ResourceService getInstance(){
		if(resourceService == null){
			resourceService = new ResourceService();
		}
		
		return resourceService;
	}

}
