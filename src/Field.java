import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Field extends JPanel {
	
	public static final int WIDTH 				= 640;					// 640 pixel széles lesz a kép
    public static final int HEIGHT 				= 480;					// 480 pixel magas maga a játékterület
    public static final int CELL 				= 20;					// 20 pixel = Cella(19) + 1 csík pixel
	
	private Snake snake;
	private Element apple;	
	public boolean crashHappened = false;
	
	public Field() {
        setBackground(Color.BLACK);
        setFocusable(true);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
        snake = new Snake(8, Direction.RIGHT);
        apple = new Element();
        apple.newLocButSnake(snake);
	}
	
	public Snake getSnake() { return snake; }
	public boolean getCrashHappened() { return crashHappened; }
	
	public void oneStep() {
		if ( snake.checkCollision() )
			crashHappened = true;
		else
			snake.move();				
	}
	
	public boolean snakeIsEating() {
		if (apple.equals( snake.getSnakeHead() )) {
			snake.grow();
			apple.newLocButSnake(snake);
			return true;
		}
		return false;
	}
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (!crashHappened) {
        	drawGrid(g);
            snake.drawSnake(g);
            g.setColor(Color.RED);
            apple.drawElement(g);
        }
        else 
        	snake.drawSnake(g);   	
        repaint();
    }     
       
    public void drawGrid(Graphics g) {
    	g.setColor(Color.BLUE);
    	/////Függõleges
    	for (int i = 0; i <= WIDTH; i += CELL) {
    		g.drawLine(i, 0, i, HEIGHT);
    	}
    	/////Vízszintes
    	for (int i = 0; i <= HEIGHT; i += CELL) {
    		g.drawLine(0, i, WIDTH, i);
    	}
    }
}
