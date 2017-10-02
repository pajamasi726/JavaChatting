package service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import customInterface.NetworkServerClientMsgListener;
import model.Client;
import resource.AppConfig;

public class ServerService {
	
	private static ServerService serverService;
	
	private ResourceService resourceService;
	
	private ServerSocket serverSocket;
	private Thread serverThread;
	
	private NetworkServerClientMsgListener networkServerClientMsgListener;
	
	public void setNetworkServerClientMsgListener(NetworkServerClientMsgListener networkServerClientMsgListener){
		this.networkServerClientMsgListener = networkServerClientMsgListener;
	}
	
	
	/** 서버 시작 */
	public void startServer(){
		
		serverThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					serverSocket = new ServerSocket(AppConfig.PORT);
					resourceService.mainFrameAddText("서버 시작 PORT : "+AppConfig.PORT);
					while(true){
						Socket socket = serverSocket.accept();
						resourceService.mainFrameAddText("사용자 접속 IP : "+socket.getInetAddress());
						
						new Client(socket,networkServerClientMsgListener);
					}
					
					
				} catch (IOException e) {
					System.out.println("서버 에러 : "+e.getLocalizedMessage());
				}
				
			}
		});
		
		serverThread.start();
		
	}
	
	/** 서버 종료 */
	public void closeServer(){
		
		try {
			serverSocket.close();
			serverThread.interrupt();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/** SingleTon적용 */
	private ServerService(){
		resourceService = ResourceService.getInstance();
	}
	
	public static ServerService getInstance(){
		
		if(serverService == null){
			serverService = new ServerService();
		}
		
		return serverService;
	}
}
