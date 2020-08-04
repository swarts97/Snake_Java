import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Settings {

	private JFrame settingsWindow;
	private JLabel bgLabel;
	private JTextField text;
	private int gameSpeed;
	
	public Settings() {
		
		/////Level 1 - 80
		/////Level 2 - 70
		/////Level 3 - 60
		/////Level 4 - 50
		/////Level 5 - 40
		gameSpeed = 60;
		
		/////Window settings
		settingsWindow = new JFrame();
		settingsWindow.setTitle("Settings");		
		settingsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//settingWindow.setResizable(false);
		
		/////Background
		BufferedImage background = null;
		try { background = ImageIO.read(new File("Files\\background.jpg")); } 
		catch (IOException ioex) { ioex.printStackTrace(); }
		
		bgLabel = new JLabel(new ImageIcon(background));
		settingsWindow.add(bgLabel);
		
		/////Buttons
		settingsButtons();
		
		/////Level
		text = new JTextField();
		text.setBounds(241, 261, 159, 59);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setEditable(false);
		text.setFont(new Font("Brush Script MT", Font.BOLD, 40));
		text.setText("Level: " + String.valueOf( (90-gameSpeed)/10 ));
		bgLabel.add(text);
		
		/////Size, location and visibility
		settingsWindow.pack();
		settingsWindow.setLocationRelativeTo(null);
		settingsWindow.setVisible(false);
	}
	
	public int getGameSpeed() {
		return gameSpeed;
	}
	
	public void enterSettings() {
		settingsWindow.setVisible(true);
	}
	
	public void settingsButtons() {
		
		/////Decrease
		JButton btnDecrease = new JButton("Decrease");
		btnDecrease.setBounds(121, 361, 179, 59);
		bgLabel.add(btnDecrease, BorderLayout.CENTER);
		btnDecrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameSpeed < 80)
					gameSpeed += 10;
				text.setText("Level: " + String.valueOf( (90-gameSpeed)/10 ));
			}
		} );
		
		/////Increase
		JButton btnIncrease = new JButton("Increase");
		btnIncrease.setBounds(341, 361, 179, 59);
		bgLabel.add(btnIncrease, BorderLayout.CENTER);
		btnIncrease.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (gameSpeed > 40)
					gameSpeed -= 10;
				text.setText("Level: " + String.valueOf( (90-gameSpeed)/10 ));
			}
		} );
		
		/////GO
		JButton btnGO = new JButton("GO");
		btnGO.setBounds(281, 201, 79, 39);
		bgLabel.add(btnGO, BorderLayout.CENTER);
		btnGO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsWindow.dispose();
			}
		} );				
	}
}
