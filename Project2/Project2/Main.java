package Project2;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		//String fullFile = "Project2/finalTracks.csv";
		//Collection allTheSongs = new Collection();
		//allTheSongs.readFile(fullFile);

		//System.out.print("All Gernes: ");
		//String[] genres = allTheSongs.getGenres();
		//System.out.print("("+genres.length+")");
		//for (int i = 0; i < genres.length; i++) {
		//	String s = genres[i];
		//	System.out.print(s+"/");
		//}
		
		JFrame frame = new JFrame("Music");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Panel panel = new Panel();
		frame.getContentPane().add(panel);
		
		frame.pack();
		frame.setVisible(true);
		
		
	}
}