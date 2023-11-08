package bubble.test.ex15;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class BackgroundBubbleService  {
	
	private BufferedImage image;
	private Bubble bubble;
															// 이렇게 사용하는 걸 컴포지션이라고 한다. 
	public BackgroundBubbleService(Bubble bubble) {
		this.bubble = bubble;
		try {
			image = ImageIO.read(new File("image/backgroundMapService.png")); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	// 스레드를 안 만들고 boolean을 리턴하는 leftWall, rightWall, topWall 을 만들어 줄 것
	public boolean leftWall() {
		Color leftColor = new Color(image.getRGB(bubble.getX()-10, bubble.getY()+25));
		if(leftColor.getRed() == 255 && leftColor.getGreen() == 0 && leftColor.getBlue() == 0) {
			return true;
		} 
		return false;
	}
	public boolean rightWall() {
		Color rightColor = new Color(image.getRGB(bubble.getX()+50+15, bubble.getY()+25));
		if(rightColor.getRed() == 255 && rightColor.getGreen() == 0 && rightColor.getBlue() == 0) {
			return true;
		}
		return false;
	}
	public boolean topWall() {
		Color topColor = new Color(image.getRGB(bubble.getX()+25, bubble.getY()-10));
		if(topColor.getRed() == 255 && topColor.getGreen() == 0 && topColor.getBlue() == 0) {
			return true;
		} 
		return false;
	}
}
