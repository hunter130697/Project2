//Anh Dang
//Java Project 2

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
import javax.swing.JDialog;
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
import javax.swing.JProgressBar;

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
		btnNewButton_2.setBounds(222, 149, 89, 23);
		add(btnNewButton_2);
		
		txtrList = new JTextArea();
		txtrList.setBounds(119, 183, 295, 174);
		add(txtrList);
		
		comboBox = new JComboBox();
		//comboBox.setModel(new DefaultComboBoxModel(allTheSongs.getGenres()));
		comboBox.setBounds(204, 103, 114, 20);
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
		
		//add song
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
						newId = JOptionPane.showInputDialog("Enter the Song ID");
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
		
		//remove song by ID
		JMenuItem mntmNewMenuItem = new JMenuItem("Remove");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean tryAgain = false;
				String removeId = new String();
				int remove_Id = 0;
				
				do {
						// Get the attributes of the new song
					removeId = JOptionPane.showInputDialog("Enter the Song ID to remove");
						// Convert strings to doubles or ints
					remove_Id = Integer.parseInt(removeId);
					tryAgain = false;
				} while (tryAgain == true);
				//Add the song to allTheSongs
				//allTheSongs.remove(remove_Id); (Error)
				allTheSongs.writeFile("./Project2/finalTracks.csv");
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem);
		
		//Edit(I did it wrong so I'm think i will do it later)
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Edit song");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_3);
		
		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.setBounds(227, 452, 105, 35);
		add(btnNewButton_1);
		
		//get Songs by Genre
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
		rdbtnNewRadioButton.setBounds(133, 59, 109, 23);
		add(rdbtnNewRadioButton);
		
		//Get songs by Artist
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
		rdbtnNewRadioButton_2.setBounds(281, 59, 109, 23);
		add(rdbtnNewRadioButton_2);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Panel.class.getResource("/Project2/Music-image.jpg")));
		lblNewLabel.setBounds(0, 21, 551, 415);
		add(lblNewLabel);
		
		JSlider slider = new JSlider();
		slider.setBackground(Color.WHITE);
		slider.setBounds(378, 452, 152, 26);
		add(slider);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(0, 436, 551, 14);
		add(progressBar);
		
		}

}


