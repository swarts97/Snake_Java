import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class HighscoreData extends AbstractTableModel{

    private List<Player> players;

    public HighscoreData() {
    	players = new ArrayList<>();
    	try { load(); } 
    	catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		  }
    }
    
	class PlayerComparator implements Comparator<Player> {
		public int compare(Player one, Player other) {
			if ( one.getScore() < other.getScore() )
				return 1;
			else if ( one.getScore() > other.getScore() )
				return -1;
			else 
				return 0;
		}
	}
	
	public void save() throws IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Files\\data.txt"));
		oos.writeObject(players);
		oos.close();
	}
	
	public void load() throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Files\\data.txt"));
		players = (ArrayList<Player>) ois.readObject();
		ois.close();
	}
    
	public boolean reachedMaximumSize() {
		return players.size() >= 10;
	}
	
	public void updateHighscoreData(String name, int score) {
		if ( score >= players.get(players.size() - 1).getScore() ) {
			players.remove(players.size() - 1);
			addPlayer(name, score);
		}
	}
	
    /////8. laboralkalomról
	public void addPlayer(String name, int score) {
		players.add(new Player(name, score));
		players.sort(new PlayerComparator());
		this.fireTableDataChanged();
	}
    
	@Override
	public int getRowCount() {
		return players.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int row, int column) {
		Player p = players.get(row);
		switch(column) {
			case 0: return (Integer)row + 1;
			case 1: return p.getName();	
			default: return p.getScore();
		}
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column) {
			case 0: return "Rank";
			case 1: return "Name";
			default: return "Score";
		}
	}
    
	@Override
	public Class<?> getColumnClass(int column) {
		switch(column) {
			case 0: return Integer.class;
			case 1: return String.class;
			default: return Integer.class;
		}
	}
}
