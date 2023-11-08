package bubble.test.ex11;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BubbleFrame extends JFrame {

	private JLabel backgroundMap;

	private Player player;

	public BubbleFrame() {
		initObject();
		initSetting();
		initListner();
		setVisible(true);
	}

	private void initObject() {
		backgroundMap = new JLabel(new ImageIcon("image/backgroundMap.png"));
		setContentPane(backgroundMap);

		player = new Player();
		add(player);
	}

	private void initSetting() {
		setSize(1000, 640);
		setLayout(null); // absolute 레이아웃 (자유롭게 그림을 그릴 수 있다.)
		setLocationRelativeTo(null); // JFrame 가운데 배치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼으로 창을 끌 때 JVM 같이 종료하기
	}

	private void initListner() {
		addKeyListener(new KeyAdapter() {
			// 키보드 클릭 이벤트 핸들러
			@Override
			public void keyPressed(KeyEvent e) {
				// System.out.println(e.getKeyCode());

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_UP:
					if (!player.isUp() && !player.isDown()) { // 점프 상태가 아닐 때만 up이 되어야 한다.
						player.up();
					}
					break;
				case KeyEvent.VK_SPACE:
					Bubble bubble = new Bubble(player);
					add(bubble);
					break;
				}
			}

			// 키보드 해제 이벤트 핸들러
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				}
			}
		});
	}

	public static void main(String[] args) {
		new BubbleFrame();
	}
}
