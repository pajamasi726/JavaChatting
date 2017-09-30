package Resource;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

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
		this.getContentPane().add(logTextArea);
		
		startBtn = new JButton("서버 시작");
		startBtn.setBounds(12, 229, 190, 23);
		this.getContentPane().add(startBtn);
		
		endBtn = new JButton("서버 종료");
		endBtn.setBounds(232, 229, 190, 23);
		this.getContentPane().add(endBtn);
	}
	
}
