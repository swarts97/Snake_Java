import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu {

	private JFrame menuWindow;
	private JLabel bgLabel;
	
	private Game g;
	private Settings s;
	private Highscore h;
	private HighscoreData hsData;

	public static void main(String[] args) {
		Menu startingMenu = new Menu();
	}

	public Menu() {
		hsData = new HighscoreData();
		g = new Game(hsData);
		s = new Settings();
		h = new Highscore(hsData);
		
		//Window settings
		menuWindow = new JFrame();
		menuWindow.setTitle("Snake 2");
		menuWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		//menuWindow.setResizable(false);			
		
		//Background
		BufferedImage background = null;
		try { background = ImageIO.read(new File("Files\\background.jpg")); } 
		catch (IOException ioex) { ioex.printStackTrace(); }
		
		bgLabel = new JLabel(new ImageIcon(background));
		menuWindow.add(bgLabel);
		
		//Buttons
		menuButtons();
		
		//Size, location and visibility
		menuWindow.pack();
		menuWindow.setLocationRelativeTo(null);
		menuWindow.setVisible(true);
	}

	public void menuButtons() {
		
		//Play
		JButton btnPlay = new JButton("Play");
		btnPlay.setBounds(221, 161, 179, 59);
		bgLabel.add(btnPlay, BorderLayout.CENTER);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuWindow.setVisible(false);
				play();
			}
		} );
		
		//Settings
		JButton btnSettings = new JButton("Settings");
		btnSettings.setBounds(221, 241, 179, 59);
		bgLabel.add(btnSettings, BorderLayout.CENTER);
		btnSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settings();
			}
		} );
		
		//Highscore
		JButton btnHighscore = new JButton("Highscore");
		btnHighscore.setBounds(221, 321, 179, 59);
		bgLabel.add(btnHighscore, BorderLayout.CENTER);
		btnHighscore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				highscore();
			}
		} );
		
		//Exit
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(221, 401, 179, 59);
		bgLabel.add(btnExit, BorderLayout.CENTER);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exit();
			}
		} );
	}
	
	public void play() {
		g.setSpeed( s.getGameSpeed() );
		g.startGame();
	}
	
	public void settings() {
		s.enterSettings();
	}
	
	public void highscore() {
		h.enterHighscore();
	}
	
	public void exit() {
		menuWindow.dispatchEvent(new WindowEvent(menuWindow, WindowEvent.WINDOW_CLOSING));
	}
}
