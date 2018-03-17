//package engine;
package environment;
import engine.BoardFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.BoardFactory;
import java.util.ArrayList;
//import engine.*;

public class Driver extends JFrame{
	JFrame frame = new JFrame("Game Board Environment");
	String[] availableGames = {"Memory", "Othello", "Chutes and Ladders","Gomoku"};
	private JButton playButton;
	private JComboBox<String> games;
	private String gameType;

	public Driver() {
		super();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void mainMenu(){
		JPanel mainPanel = new JPanel(new GridLayout(4,2));
		JLabel mainLabel = new JLabel("Pick a game:");
		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(mainLabel);
		games = new JComboBox<String>(availableGames);
		gameType= availableGames[0];
		mainPanel.add(games);
		JLabel playerone = new JLabel("Player One");
		JLabel playertwo = new JLabel("Player Two");
		playerone.setHorizontalAlignment(SwingConstants.CENTER);
		playertwo.setHorizontalAlignment(SwingConstants.CENTER);
		mainPanel.add(playerone);
		mainPanel.add(playertwo);
		JPanel leftTextFieldPanel = new JPanel();
		JPanel rightTextFieldPanel = new JPanel();
		JTextField playerOneTextField = new JTextField();
		playerOneTextField.setPreferredSize(new Dimension(225,20));
		JTextField playerTwoTextField = new JTextField();
		playerTwoTextField.setPreferredSize(new Dimension(225,20));
		leftTextFieldPanel.add(playerOneTextField);
		rightTextFieldPanel.add(playerTwoTextField);
		mainPanel.add(leftTextFieldPanel);
		mainPanel.add(rightTextFieldPanel);
		mainPanel.add(new JPanel());
		playButton = new JButton("Play");
		mainPanel.add(playButton);
		mainPanel.setPreferredSize(new Dimension(500,500));
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-250, dim.height/2-250);

		//Play button -> create new Game
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BoardFactory boardFactory = new BoardFactory();
				boardFactory.createBoard(gameType);
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