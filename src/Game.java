import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Game implements ActionListener {
	
	private JFrame gameWindow;
	private JTextField score;
	private Field gameField;
	
	private HighscoreData hsData;
	
	private boolean drawdone = true;
	private int speed;
	private int point;
	private Timer timer;
	
	private JLabel bgLabel;
	
	public Game( HighscoreData data ) {
		point = 0;
		hsData = data;
		
		/////Window
		gameWindow = new JFrame();		
		gameWindow.setDefaultCloseOperation(gameWindow.EXIT_ON_CLOSE);
		gameWindow.setTitle("Game");
        
		/////Field
		gameField = new Field();
		gameField.addKeyListener(new SnakeKeyAdapter());
		gameWindow.add(gameField, BorderLayout.CENTER);
		
        /////Score
        score = new JTextField();
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setEditable(false);
        score.setFont(new Font("ModeNine", Font.BOLD, 16));
        score.setText("Score: " + String.valueOf( point ));
        gameWindow.add(score, BorderLayout.SOUTH);
		
		/////Size, location and visibility
        gameWindow.setResizable(false); //???
        gameWindow.pack();
        gameWindow.setLocationRelativeTo(null);
        gameWindow.setVisible(false);       
	}
	
	public void setSpeed(int s) { 
		speed = s;
		timer = new Timer(speed, this); 
	}
	
	public void addPoint() {
		point += 90 - speed;
		score.setText("SCORE: " + String.valueOf( point ));
	}
	
	public void startGame() {
		gameWindow.setVisible(true);
		timer.start();
	}
	
    @Override
    public void actionPerformed(ActionEvent e) {   	
    	if( !gameField.getCrashHappened() ) {
    		gameField.oneStep();
    		if ( gameField.snakeIsEating() )
    			addPoint();
            drawdone = true;
    	}
    	else {
    		timer.stop();
    		typeName();
    	}
    }
    
    public void typeName() {
    	gameWindow.setFocusable(false);
    	/////Window
    	JFrame frame = new JFrame();
    	frame.setTitle("Game Over");
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(gameWindow.DO_NOTHING_ON_CLOSE);
    	
    	/////TextField
    	JTextField text = new JTextField(25);
		text.setHorizontalAlignment(SwingConstants.CENTER);
    	text.setText("Type in your name here!");
    	text.setEditable(true);
    	frame.add(text, BorderLayout.CENTER);
    	text.selectAll();
    	
    	/////Button
    	JButton button = new JButton("Save");
		frame.add(button, BorderLayout.SOUTH);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if ( !hsData.reachedMaximumSize() ) 
					hsData.addPlayer(text.getText(), point);
				else
					hsData.updateHighscoreData(text.getText(), point);
				try { hsData.save(); } 
	    		catch (IOException e1) {
					e1.printStackTrace();
				}
	    		frame.dispose();
    			gameWindow.dispose();
    			Menu restartMenu = new Menu();
			}
		} );
    	
    	/////Size, location and visibility
    	frame.pack();
    	frame.setLocationRelativeTo(null);
    	frame.setVisible(true);
    }
	
	private class SnakeKeyAdapter extends KeyAdapter {
    	
	    public void keyPressed(KeyEvent ke) {
	    	/////getSnake helyett nem tudom mit lehetne
	    	int button = ke.getKeyCode();  		
	    	if (drawdone) {    			
	    		if (button == KeyEvent.VK_UP && gameField.getSnake().getDirection() != Direction.DOWN) {
	    			gameField.getSnake().setDirection(Direction.UP);
	    			drawdone = false;
	    		}	
	    		if (button == KeyEvent.VK_DOWN && gameField.getSnake().getDirection() != Direction.UP) {
	    			gameField.getSnake().setDirection(Direction.DOWN);
	    			drawdone = false;
	    		}    				
	    		if (button == KeyEvent.VK_LEFT && gameField.getSnake().getDirection() != Direction.RIGHT) {
	    			gameField.getSnake().setDirection(Direction.LEFT);
	    			drawdone = false;
	    		}    				
	    		if (button == KeyEvent.VK_RIGHT && gameField.getSnake().getDirection() != Direction.LEFT) {
	    			gameField.getSnake().setDirection(Direction.RIGHT);
	    			drawdone = false;
	    		}
	    		if (button == KeyEvent.VK_ESCAPE) {
	    			gameWindow.dispose();
	    			Menu restartMenu = new Menu();
	    		} 
	    	}
	    }
	}		
}
