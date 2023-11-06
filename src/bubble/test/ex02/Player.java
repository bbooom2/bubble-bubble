package bubble.test.ex02;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel{ // player 자체가 label이기 때문에 

	private int x;
	private int y;
	
	private ImageIcon playerR, playerL; // 플레이어의 왼쪽면, 오른쪽면 
	
	public Player() {
		initObject();
		initSetting();
	}
	
	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}
	
	private void initSetting() {
		x = 55; 
		y = 535;
		
		setIcon(playerR);
		setSize(50,50);
		setLocation(x,y);
	}
	
}
