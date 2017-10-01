package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import customInterface.NetworkServerClientMsgListener;

public class Client implements Runnable{

	private Thread clientThread;
	private Socket socket;
	private String nickName;
	private BufferedWriter bw;
	private BufferedReader br;
	private NetworkServerClientMsgListener networkServerClientMsgListener;
	
	public Client(Socket socket, NetworkServerClientMsgListener networkServerClientMsgListener){
		
		try {
			// 사용자 접속시 socket 설정
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.networkServerClientMsgListener = networkServerClientMsgListener;
			
			// 사용자 input 스레드 시작
			inputThread();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void msgToClient(String str){
		try {
			bw.write(str+"\n");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void inputThread(){
		clientThread = new Thread(this);
		clientThread.start();
	}
	
	
	@Override
	public void run() {
		
		while(true){
			
			try {
				String clientMsg = br.readLine();
				
				if(clientMsg != null && clientMsg.length() > 0){
					networkServerClientMsgListener.clientMsg(this, clientMsg);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
