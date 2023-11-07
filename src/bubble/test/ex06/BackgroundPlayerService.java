package bubble.test.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 백그라운드에서 플레이어를 관찰하는 서비스 
// 메인스레드 바쁨 - 키보드 이벤트를 처리하기 바쁨 
// 백그라운드에서 계속 관찰 - 새로운 스레드가 되어야 한다 
public class BackgroundPlayerService implements Runnable {
	
	private BufferedImage image;
	private Player player;
															// 이렇게 사용하는 걸 컴포지션이라고 한다. 
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			// 일단 이 패키지는 test 파일로 연습을 해볼 것 
			image = ImageIO.read(new File("image/backgroundMapService.png")); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		// 이미지를 버퍼로 불러와야 테스트를 해볼 수 있다. 
		while(true) {
			// 색상 확인  - 플레이어위치에 따른 색상 
			Color leftColor = new Color(image.getRGB(player.getX()-10, player.getY()+25));
			Color rightColor = new Color(image.getRGB(player.getX()+50+15, player.getY()+25));
			//System.out.println("leftColor 색상 : " + leftColor );
			//System.out.println("rightColor 색상 : " + rightColor );
			
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				System.out.println("왼쪽 벽에 충돌함");
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0)
				System.out.println("오른쪽 벽에 충돌함");
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}
}
