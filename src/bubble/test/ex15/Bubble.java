package bubble.test.ex15;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 버블이 만들어질 때의 최초 위치는 플레이어의 위치와 동일해야 한다. -> 플레이어에 의존적이어야 한다. 
public class Bubble extends JLabel implements Moveable {

	// 의존성 컴포지션
	private BubbleFrame mContext;
	private Player player;
	private BackgroundBubbleService backgroundBubbleService;

	// 위치 상태
	private int x;
	private int y;

	// 움직임 상태 - 물방울은 왼쪽, 오른쪽, 위만 가능
	private boolean left;
	private boolean right;
	private boolean up;

	// 적군을 맞춘 상태 - 방울 자체가 적군에 닿을 것
	private int state; // 0(물방울), 1(적을 가둔 물방울)

	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진 상태

	public Bubble(BubbleFrame mContext) { // 생성자를 만들고 몇가지 세팅
		this.mContext = mContext;
		this.player =mContext.getPlayer();
		initObject();
		initSetting();
	}

	private void initObject() { // bubble, bubbled, bomb new 처리
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");

		backgroundBubbleService = new BackgroundBubbleService(this);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;

		x = player.getX();
		y = player.getY();
		setIcon(bubble);
		setSize(50, 50);

		state = 0; // 현재 물방울의 상태이므로 0
	}


	// 범위를 줘야 하기 때문에 while이 아닌 for문 사용
	@Override
	public void left() {
		// 상태 추가
		left = true;
		for (int i = 0; i < 400; i++) {
			x--; // 왼쪽으로 가야하므로 -
			setLocation(x, y);

			if (backgroundBubbleService.leftWall()) {
				left = false;
				break;
			}

			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for (int i = 0; i < 400; i++) {
			x++; // 오른쪽으로 가야하므로 +
			setLocation(x, y);

			if (backgroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void up() {
		up = true;
		while (up) {
			y--; // 위로 가야하므로 -
			setLocation(x, y);
			if (backgroundBubbleService.rightWall()) {
				up = false;
				break;
			}
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		clearBubble(); // 천장에 버블이 도착하고 나서 3초 후에 메모리에서 소멸
	}
	// 행위 -> clear (동사) -> bubble (목적어) 
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this); // BubbleFrame의 bubble이 메모리에서 소멸 
			mContext.repaint();        // BubbleFrame의 전체를 다시 그린다. (메모리에서 없는 건 그리지 않음)
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
