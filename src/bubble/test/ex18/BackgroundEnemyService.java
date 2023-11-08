package bubble.test.ex18;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class BackgroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;
	
	// 플레이어, 버블													
	public BackgroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			// 일단 이 패키지는 test 파일로 연습을 해볼 것 
			image = ImageIO.read(new File("image/backgroundMapService.png")); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void run() {
		while(true) {
			
			// 1. 벽 충돌 체크 
			// 색상 확인  - 플레이어위치에 따른 색상 
			Color leftColor = new Color(image.getRGB(enemy.getX()-10, enemy.getY()+25));
			Color rightColor = new Color(image.getRGB(enemy.getX()+50+15, enemy.getY()+25));
			// -2가 나온 다는 뜻은 바닥에 색깔이 없이 흰색 
			int bottomColor = image.getRGB(enemy.getX() + 10, enemy.getY()+50+5)  
					+  image.getRGB(enemy.getX()+50 -10, enemy.getY()+50+5);
			
			// 바닥 충돌 확인 
			if(bottomColor != -2) {
				//System.out.println("바텀 칼라 :" + bottomColor);
				//System.out.println("바닥에 충돌함");
				enemy.setDown(false);
			} else { // -2일 때 실행, 바닥 색깔이 하얀색이라는 것 
				if(!enemy.isUp() && !enemy.isDown()) {			
					//System.out.println("다운 실행 됨");
					enemy.down();
				}
			}
			
			// 외벽 충돌 확인 
			if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
				enemy.setLeft(false); // 충돌했을 때 false를 넣어주면 멈추게 됨 
				// 왼쪽으로 가다가 왼쪽 외벽에 걸려을 때 오른쪽을 바라보면서 왼쪽 외벽에 걸릴 수도 있기 때문 
				if(!enemy.isRight()) { 
					enemy.right();
				}
			} else if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
				enemy.setRight(false);
				if(!enemy.isLeft()) {
					enemy.left();
				}
			} 
				
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}
}
