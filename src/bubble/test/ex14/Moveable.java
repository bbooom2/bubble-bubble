package bubble.test.ex14;

/*
 * default 인터페이스도 몸체가 있는 메서드를 만들 수 있다. 
 * 다중 상속이 안 되는 것이 많기 때문이다.
 * 그래서 어댑터 패턴보다는 default를 사용하는 것이 좋다. 
*/
public interface Moveable {
	
	public abstract void left();
	public abstract void right();
	public abstract void up();
	default public  void down() {};
	default public  void attack() {};
}
