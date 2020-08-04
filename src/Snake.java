import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

public class Snake {
	
    public static final int CELLS_IN_ROW 		= 32;					// 32 db cella van 1 sorban
    public static final int CELLS_IN_COLUMN 	= 24;					// 24 db cella van 1 oszlopban
    public static final Color DARK_GREEN 		= new Color(0,185,0);	// Sötétzöld szín
    
	private LinkedList<Element> body = new LinkedList<Element>();
	private Direction dir;
	
	public Snake(int size, Direction startDir) {
		
		for (int i = 0; i < size; i++) {
			Element e = new Element();
			e.setX(4 + i);
			e.setY(4);
			body.add(e);
		}
		dir = startDir;
	}	
	
	public void setDirection(Direction d) 	{ dir = d; }
	public Direction getDirection() 		{ return dir; }	
	public Element getSnakeHead() 			{ return body.getLast(); }
	public Element getSnakeTail() 			{ return body.getFirst(); }
	
	public Element nextHeadMove() {
		Element next = new Element();
		if (dir == Direction.UP) {
			next.setX(getSnakeHead().getX());
			next.setY(getSnakeHead().getY() - 1);
		}
		else if (dir == Direction.DOWN) {
			next.setX(getSnakeHead().getX());
			next.setY(getSnakeHead().getY() + 1);
		}
		else if (dir == Direction.LEFT) {
			next.setY(getSnakeHead().getY());
			next.setX(getSnakeHead().getX() - 1);
		}
		else if (dir == Direction.RIGHT) {
			next.setY(getSnakeHead().getY());
			next.setX(getSnakeHead().getX() + 1);
		}
		
		/////Falon áthaladás
		if(next.getX() == CELLS_IN_ROW)
			next.setX(0);
		if(next.getX() == -1)
			next.setX(CELLS_IN_ROW - 1);
		if(next.getY() == CELLS_IN_COLUMN)
			next.setY(0);
		if(next.getY() == -1)
			next.setY(CELLS_IN_COLUMN - 1);		
		return next;
	}
    
	public void move() {
		/////Testelemek
		for (int i = 0; i < body.size() - 1; i++)
			body.set(i, body.get(i + 1));
		/////Fejrész
		body.set(body.size() - 1, nextHeadMove());
	}
	
	public void grow() {
		Element newTail = new Element();
		newTail.setX(getSnakeTail().getX());
		newTail.setY(getSnakeTail().getY());
		body.addFirst(newTail);
	}
	
	public boolean checkCollision() {
		/////Ahová kerüli kerülni fog a fej
		Element nextElem = nextHeadMove();		
		for(Element e : body) {
			////Farok mezõt nem kell vizsgálni
			if( !e.equals(getSnakeTail()) && e.equals(nextElem) )
				return true;
		}
		return false;
	}
	
	public boolean elementOnSnake(Element element) {
		for(Element e : body)
			if (e.equals(element))
				return true;
		return false;
	}
		
	public void drawSnake(Graphics g) {
    	g.setColor(Color.GREEN);
    	for (Element e : body) {
    		if (!e.equals( getSnakeHead() ))
    			e.drawElement(g);
    		/////Fej rész sötétzöld
    		else {
    			g.setColor(DARK_GREEN);
    			e.drawElement(g);
    		}    			
    	}    		
    }    	
}
