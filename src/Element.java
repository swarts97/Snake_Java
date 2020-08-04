import java.awt.Graphics;

public class Element {
	
    public static final int CELLS_IN_ROW 		= 32;					// 32 db cella van 1 sorban
    public static final int CELLS_IN_COLUMN 	= 24;					// 24 db cella van 1 oszlopban
    public static final int CELL 				= 20;					// 20 pixel = Cella(19) + 1 csík pixel
    
	private int x;
	private int y;
	
	public Element() {
		x = (int) (Math.random() * CELLS_IN_ROW);
		y = (int) (Math.random() * CELLS_IN_COLUMN);
	}
	
	public int getX() { return x; }	
	public int getY() { return y; }	
	public void setX(int xx) { x = xx; }	
	public void setY(int yy) { y = yy; }
	
	boolean equals(Element e) {
		return (e.getX() == x && e.getY() == y);
	}	
	
	public void newLocButSnake(Snake snake) {
		boolean correct = false;
		while (!correct) {
			x = (int) (Math.random() * CELLS_IN_ROW);
			y = (int) (Math.random() * CELLS_IN_COLUMN);
			correct = true;
			if (snake.elementOnSnake(this))
				correct = false;
		}	
	}
	
	public void drawElement(Graphics g) {
		g.fillRect(x * CELL + 1, y * CELL + 1, CELL - 1, CELL - 1);
	}	
}
