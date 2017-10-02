package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import customInterface.NetworkInMsgListener;
import resource.AppConfig;

public class ServerService {
	
	private static ServerService serverService;
	
	private Socket socket;
	private Thread clientThread;
	private Thread inputThread;
	private BufferedReader br;
	private BufferedWriter bw;
	private NetworkInMsgListener networkInMsgListener;
	
	public void setNetworkInMsgListener(NetworkInMsgListener networkInMsgListener){
		this.networkInMsgListener = networkInMsgListener;
	}
	
	public void connectServer(){
		
		clientThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					// 서버에 연결
					socket = new Socket(AppConfig.SERVER_IP, AppConfig.PORT);
					
					// 연결된 직후 stream 지정
					bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					inputThread();
					
				} catch (UnknownHostException e) {
					System.out.println("모르는 서버 : "+e.getLocalizedMessage());
				} catch (IOException e) {
					System.out.println("IOException : "+e.getLocalizedMessage());
				}
				
			}
		});
		clientThread.start();
		
	}
	
	
	public void closeServer(){
		try {
			socket.close();
			clientThread.interrupt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sendMsg(String msg){
		System.out.println("서버로 보내는 메세지 : "+msg);
		
		if(msg != null && msg.length() > 0){
			try {
				bw.write(msg+"\n");
				bw.flush();
			} catch (IOException e) {
				System.out.println("ServerService sendMsg Error : "+e.getLocalizedMessage());
			}
		}
	}
	
	private void inputThread(){
		
		inputThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true){
					try {
						
						String readMsg = br.readLine();
						
						if(readMsg != null && readMsg.length() > 0){
							networkInMsgListener.inMsg(readMsg);
						}
						
					} catch (IOException e) {
						System.out.println("ServerService readLine Error : "+e.getLocalizedMessage());
						break;
					}
				}
				
			}
		});
		inputThread.start();
	}
	
	
	
	/** SingleTon적용 */
	private ServerService(){
		
	}
	
	public static ServerService getInstance(){
		
		if(serverService == null){
			serverService = new ServerService();
		}
		
		return serverService;
	}
}
