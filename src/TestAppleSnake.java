import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestAppleSnake {

	private Element apple;
	private Snake snake;
	
	@Before
	public void init() {
		apple = new Element();
		snake = new Snake(8, Direction.RIGHT);
	}
		
	@Test
	public void TestApple() {
		apple.setX(10);							//1
		apple.setY(20);							//2
		assertEquals(apple.getX(), 10);			//3
		assertEquals(apple.getY(), 20);			//4
	}
	
	@Test
	public void TestEqual() {
		Element otherApple = new Element();
		otherApple.setX(10);
		otherApple.setY(20);
		apple.setX(10);
		apple.setY(20);
		assertTrue(apple.equals(otherApple));	//5
	}
		
	@Test
	public void TestNewApple() {
		apple.newLocButSnake(snake);				//6
		assertFalse(snake.elementOnSnake(apple));	//7
	}
}
