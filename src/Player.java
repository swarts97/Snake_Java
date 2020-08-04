import java.io.Serializable;

public class Player implements Serializable {
	
	String name;
	int score;
	
	Player(String n, int s) {
		name = n;
		score = s;
	}
	
	@Override
	public String toString() {
		return name + " " + score;
	}
	
	public String getName() { return name; }
	public int getScore() { return score; }
}
