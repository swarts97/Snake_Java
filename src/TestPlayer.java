import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

	Player player;
	
	@Before
	public void init() {
		player = new Player("Gizella", 100);
	}
	
	@Test
	public void TestName() {
		assertEquals(player.getName(), "Gizella");		//8
	}
	
	@Test
	public void TestScore() {
		assertEquals(player.getScore(), 100);			//9
	}
}
