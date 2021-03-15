 package Project2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTabbedPane;
import java.awt.ScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class Panel extends JPanel{
	private int count;
	private JComboBox comboBox;
	private Collection allTheSongs;
	
	
	

	
	
	
	public Panel() {
		
		String fullFile = "Project2/finalTracks.csv";
		allTheSongs = new Collection();
		allTheSongs.readFile(fullFile);
		
		count = 0;
		setLayout(null);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(551, 498));
		
		JLabel lblNewLabel = new JLabel("Genre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(39, 57, 53, 23);
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 117, 177, 156);
		add(scrollPane);
		
		JTextArea SongByGen = new JTextArea();
		scrollPane.setViewportView(SongByGen);
		
		JButton btnNewButton = new JButton("Get Song By Genre");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collection songs = allTheSongs.findSongByGenre(""+comboBox.getSelectedItem());
				String aString = "";
				Iterator<Song> iter = songs.getIterator();
				while(iter.hasNext())
				{
					Song s = iter.next();
					aString += s + "\n";
					
				}
				SongByGen.setText(aString);
			}
		});
		btnNewButton.setBounds(39, 91, 177, 23);
		add(btnNewButton);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(allTheSongs.getGenres()));
		comboBox.setBounds(102, 60, 114, 20);
		add(comboBox);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 551, 21);
		add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Exit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Add");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tryAgain = false;
				String newId = new String();
				String newArtist = new String();
				String newGenre = new String();
				String newTitle = new String();
				String newAlbum = new String(); 
				String newYear = new String(); 
				String newLong = new String();
				int new_Id = 0;
				int year = 0;
				double longtitude = 0;
				do {
					
						// Get the attributes of the new song
						newId = JOptionPane.showInputDialog("Enter the Song id");
						newArtist = JOptionPane.showInputDialog("Enter the artist's name");
						newGenre = JOptionPane.showInputDialog("Enter the song genre");
						newTitle = JOptionPane.showInputDialog("Enter the track title");
						newAlbum = JOptionPane.showInputDialog("Enter the album title");
						newYear = JOptionPane.showInputDialog("Enter the year of creation");
						newLong = JOptionPane.showInputDialog("Enter the longitude");
						// Convert strings to doubles or ints
						new_Id = Integer.parseInt(newId);
						year = Integer.parseInt(newYear);
						longtitude = Double.parseDouble(newLong);
						tryAgain = false;
				} while (tryAgain == true);
				//Add the song to all
				allTheSongs.add(new Song(new_Id, newArtist, newGenre, newTitle, newAlbum, year, longtitude));
				allTheSongs.writeFile("./Project2/finalTracks.csv");
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JSlider slider = new JSlider();
		slider.setBounds(0, 420, 551, 26);
		add(slider);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.setBounds(242, 452, 53, 35);
		add(btnNewButton_1);
	}
}


