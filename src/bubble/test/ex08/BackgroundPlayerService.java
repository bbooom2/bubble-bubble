package bubble.test.ex08;

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
			// -2가 나온 다는 뜻은 바닥에 색깔이 없이 흰색 
			int bottomColor = image.getRGB(player.getX() + 10, player.getY()+50+5)  
					+  image.getRGB(player.getX()+50 -10, player.getY()+50+5);
			
			// 바닥 충돌 확인 
			if(bottomColor != -2) {
				//System.out.println("바텀 칼라 :" + bottomColor);
				//System.out.println("바닥에 충돌함");
				player.setDown(false);
			}
			
			// 외벽 충돌 확인 
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				//System.out.println("왼쪽 벽에 충돌함");
				player.setLeftWallCrash(true);
				player.setLeft(false); // 충돌했을 때 false를 넣어주면 멈추게 됨 
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				//System.out.println("오른쪽 벽에 충돌함");
				player.setRightWallCrash(true);
				player.setRight(false);
			} else {
				player.setLeftWallCrash(false);
				player.setRightWallCrash(false);
			}
				
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}
}
