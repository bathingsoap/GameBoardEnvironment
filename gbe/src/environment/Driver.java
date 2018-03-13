package engine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Driver extends JFrame{
	JFrame frame = new JFrame("Game Board Environment");
	String[] availableGames = {"Gomoku", "Othello", "Battleship","Chutes and Ladders"};
	private JButton playButton;
	private JComboBox<String> games;
	private String gameType;

	public Driver() {
		super();
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void mainMenu(){
		JPanel mainPanel = new JPanel(new GridLayout(4,2));
		JLabel mainLabel = new JLabel("Pick a game");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(mainLabel);
		games = new JComboBox<String>(availableGames);
		mainPanel.add(games);
		JLabel playerone = new JLabel("Player One");
		JLabel playertwo = new JLabel("Player Two");
		playerone.setHorizontalAlignment(SwingConstants.CENTER);
		playertwo.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(playerone);
		mainPanel.add(playertwo);
		mainPanel.add(new JTextField());
		mainPanel.add(new JTextField());
		mainPanel.add(new JPanel());
		playButton = new JButton("Play");
		mainPanel.add(playButton);
		mainPanel.setPreferredSize(new Dimension(500,500));
		frame.add(mainPanel);
		frame.setVisible(true);

		//Play button -> create new Game
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BoardFactory boardFactory = new BoardFactory();
				JFrame gameframe = new JFrame(gameType);
				gameframe.setDefaultCloseOperation(gameframe.DISPOSE_ON_CLOSE);
				gameframe.setSize(500, 500);
				gameframe.add(boardFactory.createBoard(gameType));
				gameframe.setVisible(true);
			}
		});

		//Get the Game selected
		games.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
				gameType = (String)cb.getSelectedItem();
			}
		});
	}

	public static void main(String[] args) {
		Driver driver = new Driver();
		driver.mainMenu();
	}
}