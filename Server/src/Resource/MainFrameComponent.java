package Resource;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Listener.ButtonEventListener;

public class MainFrameComponent extends JFrame{

	private JTextArea logTextArea;
	private JButton startBtn;
	private JButton endBtn;
	
	public MainFrameComponent(){
		this.setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		logTextArea = new JTextArea();
		logTextArea.setBounds(12, 10, 410, 209);
		logTextArea.setEditable(false);
		this.getContentPane().add(logTextArea);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 410, 213);
		this.add(scrollPane);
		scrollPane.setViewportView(logTextArea);
		
		
		
		startBtn = new JButton("서버 시작");
		startBtn.setBounds(12, 229, 190, 23);
		this.getContentPane().add(startBtn);
		
		endBtn = new JButton("서버 종료");
		endBtn.setBounds(232, 229, 190, 23);
		this.getContentPane().add(endBtn);
		
		
		
		
		// 이벤트 리스너 설정
		ButtonEventListener buttonEventListener = new ButtonEventListener();
		startBtn.addActionListener(buttonEventListener);
		endBtn.addActionListener(buttonEventListener);
	}
	
	
	/**
	 * 문자열을 받아서 로그 창에 출력
	 * @param str
	 */
	public void addText(String str){
		logTextArea.append(str+"\n");
	}
	
}
