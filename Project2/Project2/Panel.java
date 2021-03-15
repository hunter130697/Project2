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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextPane;

public class Panel extends JPanel{
	private int count;
	private JComboBox comboBox;
	private Collection allTheSongs;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton_2;
	private JTextArea txtrList;
	
	public Panel() {
		
		String fullFile = "Project2/finalTracks.csv";
		allTheSongs = new Collection();
		allTheSongs.readFile(fullFile);
		
		count = 0;
		setLayout(null);
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(551, 498));
		
		btnNewButton_2 = new JButton("Get Song");
		btnNewButton_2.setBounds(193, 149, 89, 23);
		add(btnNewButton_2);
		
		txtrList = new JTextArea();
		txtrList.setBounds(98, 183, 295, 174);
		add(txtrList);
		
		comboBox = new JComboBox();
		//comboBox.setModel(new DefaultComboBoxModel(allTheSongs.getGenres()));
		comboBox.setBounds(181, 103, 114, 20);
		add(comboBox);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 551, 21);
		add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
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
				//Add the song to allTheSongs
				allTheSongs.add(new Song(new_Id, newArtist, newGenre, newTitle, newAlbum, year, longtitude));
				allTheSongs.writeFile("./Project2/finalTracks.csv");
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Remove");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tryAgain = false;
				String removeId = new String();
				int remove_ID;
				do {
						// Get the attributes of the remove song
						removeId = JOptionPane.showInputDialog("Enter the Song id to remove");
						// Convert strings int
						remove_ID = Integer.parseInt(removeId);
						tryAgain = false;
				} while (tryAgain == true);
				//Delete the song from allTheSongs
				allTheSongs.remove(new Song(remove_ID));
				allTheSongs.writeFile("./Project2/finalTracks.csv");
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		JSlider slider = new JSlider();
		slider.setBounds(0, 420, 551, 26);
		add(slider);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.setBounds(227, 452, 105, 35);
		add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Genre");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setModel(new DefaultComboBoxModel(allTheSongs.getGenres()));
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Collection songs = allTheSongs.findSongByGenre(""+comboBox.getSelectedItem());
						String aString = "";
						Iterator<Song> iter = songs.getIterator();
						while(iter.hasNext())
						{
							Song s = iter.next();
							aString += s + "\n";
						}
						txtrList.setText(aString);
					}
				});
			}
		});
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(121, 59, 109, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Artist");
		rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBox.setModel(new DefaultComboBoxModel(allTheSongs.getArtists()));
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Collection songs = allTheSongs.findSongByArtist(""+comboBox.getSelectedItem());
						String aString1 = "";
						Iterator<Song> iter = songs.getIterator();
						while(iter.hasNext())
						{
							Song s = iter.next();
							aString1 += s + "\n";
						}
						txtrList.setText(aString1);
					}
				});
			}
		});
		buttonGroup.add(rdbtnNewRadioButton_2);
		rdbtnNewRadioButton_2.setBounds(234, 59, 109, 23);
		add(rdbtnNewRadioButton_2);
		
		
		
		
		
		}
}


