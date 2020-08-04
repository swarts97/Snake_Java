import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Highscore {

	private JFrame highscoreWindow;
	private JTable table;
	private JScrollPane jsp;
	private HighscoreData hsData;
	
	public Highscore( HighscoreData data ) {
		
		hsData = data;
		/////Window settings
		highscoreWindow = new JFrame();
		highscoreWindow.setTitle("Highscore");		
		highscoreWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//settingWindow.setResizable(false);
		
		/////JTable
		table = new JTable(data);
		jsp = new JScrollPane(table);
		//table.setRowSorter(new TableRowSorter<HighscoreData>(data));
		highscoreWindow.add(jsp, BorderLayout.CENTER);
		
		/////Size, location and visibility
		highscoreWindow.pack();
		highscoreWindow.setLocationRelativeTo(null);
		highscoreWindow.setVisible(false);
	}

	public void enterHighscore() {
		highscoreWindow.setVisible(true);
	}	
}
