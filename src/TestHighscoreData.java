import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestHighscoreData {

	HighscoreData data;
	
	@Before
	public void init() {
		data = new HighscoreData();
	}
	
	@Test
	public void TestMaxSize() {
		assertFalse( data.reachedMaximumSize() );		//10
	}
}
