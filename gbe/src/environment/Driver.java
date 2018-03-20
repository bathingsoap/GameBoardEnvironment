//package engine;
package environment;
import engine.BoardFactory;
import players.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import engine.BoardFactory;
import static java.awt.GridBagConstraints.HORIZONTAL;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
//import engine.*;

public class Driver extends JFrame{
	JFrame frame = new JFrame("Game Board Environment");
	String[] availableGames = {"Memory", "Othello", "Chutes and Ladders","Gomoku"};
	private JButton playButton;
	private JComboBox<String> games;
	private String gameType;
	private PlayerManager pm;
        private String logoPath = "/images/MainPanel/gameslogo.png";
	public Driver() {
		super();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pm = PlayerManager.getInstance();
	}

	public void mainMenu(){
		JPanel mainPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
         
                

               
		
		c.gridx= 0; c.gridy=1;
		JLabel mainLabel = new JLabel("Pick a game:");
		mainPanel.add(mainLabel, c);
		
		c.gridx=1; c.gridy=1;
		games = new JComboBox<String>(availableGames);
		gameType= availableGames[0];
		mainPanel.add(games, c);
		
		c.gridx=0; c.gridy=2;
		JLabel p1 = new JLabel("Player 1:");
		mainPanel.add(p1, c);
		
		c.gridx=1; c.gridy=2;
		JLabel p2 = new JLabel("Player 2:");
		mainPanel.add(p2, c);
		
		c.gridx=0; c.gridy=3;
		JPanel p1input = new JPanel();
		p1input.setLayout(new BoxLayout(p1input, BoxLayout.X_AXIS));
		JTextField p1text = new JTextField();
		p1text.setPreferredSize(new Dimension(150,20));
		JButton p1add = new JButton("Ready");
		p1input.add(p1text);
		p1input.add(p1add);
		mainPanel.add(p1input, c);
		
		c.gridx=1; c.gridy=3;
		JPanel p2input = new JPanel();
		p2input.setLayout(new BoxLayout(p2input, BoxLayout.X_AXIS));
		JTextField p2text = new JTextField();
		p2text.setPreferredSize(new Dimension(150,20));
		JButton p2add = new JButton("Ready");
		p2input.add(p2text);
		p2input.add(p2add);
		mainPanel.add(p2input, c);
		
		c.gridx = 0; c.gridy = 4;
		JLabel p1l = new JLabel("---");
		mainPanel.add(p1l,c);
		
		c.gridx = 1; c.gridy = 4;
		JLabel p2l = new JLabel("---");
		mainPanel.add(p2l,c);
		
		c.gridx = 0; c.gridy = 5;
		JLabel p1w = new JLabel("---");
		mainPanel.add(p1w,c);
		
		c.gridx = 1; c.gridy = 5;
		JLabel p2w = new JLabel("---");
		mainPanel.add(p2w,c);
		
		c.gridx = 0; c.gridy = 6; c.gridwidth = 2;
		playButton = new JButton("Play");
		playButton.setEnabled(false);
		mainPanel.add(playButton, c);
		//mainPanel.setPreferredSize(new Dimension(500,500));
                
            try {
                c.gridx = 0;c.gridy = 0; c.gridwidth= 2;
                Image img = ImageIO.read(getClass().getResource(logoPath));
                ImageIcon icon = new ImageIcon(img);
                img = icon.getImage() ;  
                Image newimg = img.getScaledInstance( 250, 100,  java.awt.Image.SCALE_SMOOTH ) ; 
                JLabel logoLabel = new JLabel();
                logoLabel.setIcon(new ImageIcon(newimg));
                mainPanel.add(logoLabel, c);
            } catch (IOException ex) {
                Logger.getLogger(Driver.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		frame.add(mainPanel);
		frame.pack();
		frame.setVisible(true);
		/*
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
		*/
		//Play button -> create new Game
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!p1text.getText().equals(p2text.getText())) {
				pm.setPlayer1(p1text.getText());
				pm.setPlayer2(p2text.getText());
				p1text.setText("");
				p2text.setText("");
				p1w.setText("---");
				p1l.setText("---");
				p2w.setText("---");
				p2l.setText("---");
				
				playButton.setEnabled(false);
				BoardFactory boardFactory = new BoardFactory();
				boardFactory.createBoard(gameType);
				}
				else {
					JFrame error = new JFrame();
					error.add(new JLabel("Please enter different usernames!!!"));
					error.pack();
					error.setVisible(true);
				}
			}
		});
		
		p1add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//String p1user = p1add.getText();
				//p1text.setEnabled(false);
				//p1add.setEnabled(false);
				Player p;
				if(pm.containPlayer(p1text.getText())==-1) {
					p= new Player(p1text.getText());
					pm.players.add(p);
				}
				else {
					p=pm.players.get(pm.containPlayer(p1text.getText()));
				}
				
				p1w.setText("Wins: " + p.getWins());
				p1l.setText(p1text.getText());
				//frame.repaint();
			}
		});
		
		p2add.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				Player p;
				if(pm.containPlayer(p2text.getText())==-1) {
					p= new Player(p2text.getText());
					pm.players.add(p);
				}
				else {
					p=pm.players.get(pm.containPlayer(p2text.getText()));
				}
				p2w.setText("Wins: " + p.getWins());
				p2l.setText(p2text.getText());
				
				playButton.setEnabled(true);
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