package game;

import engine.Board;
import engine.State;
import pieces.*;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import players.Player;
import players.PlayerManager;


public class CLBoard implements Board, ActionListener {
    
    JButton[][] buttons;
    JLabel[][] labels;
  	JPanel gbl;
    JFrame frame;
    Player p1; Player p2;
    PlayerManager pm;
    
    String blackPiecePath;
    String whitePiecePath;
    String bothPiecePath;
    String emptyPiecePath;
    
    ImageIcon[][] blackPieces;
    ImageIcon[][] whitePieces;
    ImageIcon[][] bothPieces;
    ImageIcon[][] emptyPieces;
    
    JPanel infoPanel;
    JLabel welcomeLabel;
    JPanel p1Panel;
    JLabel p1Label;
    TextField p1Position;
    JPanel p2Panel;
    JLabel p2Label;
    TextField p2Position;
    JPanel rollPanel;
    JLabel turnLabel;
    JButton rollButton;
    
    JPanel messagePanel;
    JLabel message1;
    JLabel message2;
    JLabel message3;
    JLabel message4;
    JLabel message5;
    
    JPanel optionPanel;
    JButton restart;
    JButton exit;
    
    CLPiece white;
    CLPiece black;
    State state;
    boolean winner = false;
    
    JPanel game;
    
    int buttonWidth;
    int buttonHeight;
    
    
    
    public CLBoard(){
        //buttons = new JButton[10][10];
    	labels = new JLabel[10][10];
    	gbl = new JPanel(new GridBagLayout());
    	
    	/*
    	PieceFactory pf = new PieceFactory();
    	white = (CLPiece) pf.getPiece("CL");
    	white.setColor(Color.white);
    	
    	black = (CLPiece) pf.getPiece("CL");
    	black.setColor(Color.black);
    	*/
    	
        blackPiecePath = "/images/BlackBoard/BlackBoard [www.imagesplitter.net]-";
        whitePiecePath = "/images/WhiteBoard/WhiteBoard [www.imagesplitter.net]-";
        bothPiecePath = "/images/BothBoard/BothBoard [www.imagesplitter.net]-";
        emptyPiecePath = "/images/ClearBoard/ClearBoard [www.imagesplitter.net]-";
        
        blackPieces = createImageIcons(blackPiecePath);
        whitePieces = createImageIcons(whitePiecePath);
        bothPieces = createImageIcons(bothPiecePath);
        emptyPieces = createImageIcons(emptyPiecePath);
        
        //drawBoard("a");
        
    }
    
    @Override
    public void drawBoard(String gameType){
        frame = new JFrame(gameType);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setLocation(300, 50);
        //game = new JPanel(new GridLayout(5,5));
        GridBagConstraints c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        for(int i = 0;i<10;i++) {
        	for(int j = 0;j<10;j++) {
        		JLabel jp = new JLabel();
        		gbl.add(jp, c);
        		jp.setIcon(emptyPieces[i][j]);
        		labels[i][j]= jp;
        		c.gridx++;
        	}
        	c.gridy++;
        	c.gridx=0;
        }
        
        //gbl.add(black,c);
        frame.add(gbl);
        
        /*
        game = new JPanel(new GridLayout(10, 10));
        game.setPreferredSize(new Dimension(600, 600));
        
        for (int i = 0; i < 10; i++) { // row
            for (int j = 0; j < 10; j++) { // col
                JButton button = new JButton();
                button.setVisible(true);
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setContentAreaFilled(false);
                button.setIcon(emptyPieces[i][j]);
                //Image img = ImageIO.read(getClass().getResource("/images/400-chutes-and-ladders [www.imagesplitter.net]-"+i+"-"+j+".jpeg"));
                buttons[i][j] = button;
                game.add(button);
                
            }
        }
        buttonWidth = buttons[0][0].getWidth();
        buttonHeight = buttons[0][0].getHeight();
        */
        
        //frame.add(game);
        JPanel info = drawInfoBoard();
        frame.add(info);
        
        
        JPanel messages = drawMessageBoard();
        frame.add(messages);
        JPanel options = drawOptionBoard();
        frame.add(options);
        //welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.pack();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(600,600));
        state  = new CLState(this);
        
    }
    
    public JPanel drawInfoBoard() {
    	infoPanel = new JPanel(new GridBagLayout());
    	
    	welcomeLabel = new JLabel("Welcome to Chutes and Ladders!");
    	
    	p1Panel = new JPanel();
    	p1Panel.setLayout(new BorderLayout());
    	p1Label = new JLabel("Player 1 position: ");
    	p1Position = new TextField("0");
    	p1Position.setEditable(false);
    	p1Panel.add(p1Label, BorderLayout.LINE_START);
    	p1Panel.add(p1Position, BorderLayout.LINE_END);
    	      
    	p2Panel = new JPanel();
    	p2Panel.setLayout(new BorderLayout());
    	p2Label = new JLabel("Player 2 position: ");
    	p2Position = new TextField("0");
    	p2Position.setEditable(false);
    	p2Panel.add(p2Label, BorderLayout.LINE_START);
    	p2Panel.add(p2Position, BorderLayout.LINE_END);
    	
    	rollPanel = new JPanel();
    	rollPanel.setLayout(new BoxLayout(rollPanel,BoxLayout.X_AXIS));
    	turnLabel = new JLabel("Player 1:");
    	rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
        rollPanel.add(turnLabel);
        rollPanel.add(rollButton);
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy=0;
        c.gridwidth = 3;
    	infoPanel.add(welcomeLabel, c);
    	
    	c.gridwidth =1 ;
    	c.gridy=1;
    	c.weightx = 0.2;
    	infoPanel.add(p1Panel, c);
    	
    	c.gridx = 2;
    	infoPanel.add(p2Panel, c);
    	
    	c.gridx = 1;
    	infoPanel.add(rollPanel, c);
    	//infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
    	/*
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
	welcomeLabel = new JLabel("Welcome to Chutes and Latters!");
	      
	p1Panel = new JPanel();
	p1Panel.setLayout(new BoxLayout(p1Panel,BoxLayout.Y_AXIS));
	p1Label = new JLabel("Player 1 position: ");
	p1Position = new TextField("0");
	p1Position.setEditable(false);
	p1Panel.add(p1Label);
	p1Panel.add(p1Position);
	      
	p2Panel = new JPanel();
	p2Panel.setLayout(new BoxLayout(p2Panel,BoxLayout.Y_AXIS));
	p2Label = new JLabel("Player 2 position: ");
	p2Position = new TextField("0");
	p2Position.setEditable(false);
	p2Panel.add(p2Label);
	p2Panel.add(p2Position);
	      
	      
	rollPanel = new JPanel();
	rollPanel.setLayout(new BoxLayout(rollPanel,BoxLayout.Y_AXIS));
	turnLabel = new JLabel("Player 1:");
	rollButton = new JButton("Roll");
        rollButton.addActionListener(this);
        rollPanel.add(turnLabel);
        rollPanel.add(rollButton);
	      
	infoPanel.add(welcomeLabel);
	infoPanel.add(p1Panel);
	infoPanel.add(p2Panel);
        infoPanel.add(rollPanel);
        
        message1 = new JLabel();
        message2 = new JLabel();
        message3 = new JLabel();
        message4 = new JLabel();
        message5 = new JLabel();
        
        infoPanel.add(message1);
        infoPanel.add(message2);
        infoPanel.add(message3);
        infoPanel.add(message4);
        infoPanel.add(message5);
	*/      
	return infoPanel;
	
    }
    
    public JPanel drawMessageBoard() {
    	messagePanel = new JPanel(new GridBagLayout());

    	GridBagConstraints c = new GridBagConstraints();
            message1 = new JLabel("---");
            message2 = new JLabel("---");
            message3 = new JLabel("---");
            message4 = new JLabel("---");
            message5 = new JLabel("---");
            
            c.gridx=0;
            c.gridy=0;
            messagePanel.add(message1,c);
            c.gridy=1;
            messagePanel.add(message2,c);
            c.gridy=2;
            messagePanel.add(message3,c);
            c.gridy=3;
            messagePanel.add(message4,c);
            c.gridy=4;
            messagePanel.add(message5,c);
            
       return messagePanel;
    }
    
    public JPanel drawOptionBoard() {
    	optionPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	restart = new JButton("restart");
    	exit = new JButton("exit");
    	restart.addActionListener(this);
    	exit.addActionListener(this);
    	
    	c.weightx = 0.1;
    	c.gridx=0;
    	c.gridy=0;
    	
    	optionPanel.add(restart, c);
    	c.gridx=1;
    	optionPanel.add(exit, c);
    	
    	return optionPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getActionCommand().equals("Roll"))
    		if(!winner){
    			state.makeMove();
    		}
        if(e.getActionCommand().equals("restart")) {
        	state.restart();
        	for (int i = 0; i < 10; i++) { // row
                for (int j = 0; j < 10; j++) { // col
                    
                }
            }
        }
        if(e.getActionCommand().equals("exit")) {
        	frame.dispose();
        }
    }
    
    public void updateWinner(boolean winner){
        this.winner = winner;
    }
    public void updateP1Position(String message){
        p1Position.setText(message);
        p1Position.repaint();
    }
    
    public void updateP2Position(String message){
        p2Position.setText(message);
        p2Position.repaint();
    }
    
    public void updateTurnLabel(String message){
        turnLabel.setText(message);
        turnLabel.repaint();
    }
    
    public void updateMessage1(String message){
        message1.setText(message);
        message1.repaint();
    }
    
    public void updateMessage2(String message){
        message2.setText(message);
        message2.repaint();
    }
    
    public void updateMessage3(String message){
        message3.setText(message);
        message3.repaint();
    }
    
    public void updateMessage4(String message){
        message4.setText(message);
        message4.repaint();
    }
    
    public void updateMessage5(String message){
        message5.setText(message);
        message5.repaint();
    }
    
    public void movePlayer(int player, int x, int y, int oldx, int oldy, boolean sameScore){
    	/*
    	 if(buttons[oldx][oldy].getIcon().equals(bothPieces[oldx][oldy])){
             if(player == 1){
                 buttons[oldx][oldy].setIcon(whitePieces[oldx][oldy]);
             }else{
                 buttons[oldx][oldy].setIcon(blackPieces[oldx][oldy]);
             }
         }else{
             buttons[oldx][oldy].setIcon(emptyPieces[oldx][oldy]);
         }
         if(sameScore){
             buttons[x][y].setIcon(bothPieces[x][y]);
         }else{
             if(player == 1)
                 buttons[x][y].setIcon(blackPieces[x][y]);
             else
                 buttons[x][y].setIcon(whitePieces[x][y]);
         }
         
         buttons[oldx][oldy].repaint();
         buttons[x][y].repaint();   
         */
         //JLabel jp = new JLabel();
         //jp.setIcon(bothPieces[x][y]);
    	if(labels[oldx][oldy].getIcon().equals(bothPieces[oldx][oldy])){
            if(player == 1){
                labels[oldx][oldy].setIcon(whitePieces[oldx][oldy]);
            }else{
                labels[oldx][oldy].setIcon(blackPieces[oldx][oldy]);
            }
        }else{
            labels[oldx][oldy].setIcon(emptyPieces[oldx][oldy]);
        }
        if(sameScore){
            labels[x][y].setIcon(bothPieces[x][y]);
        }else{
            if(player == 1)
                labels[x][y].setIcon(blackPieces[x][y]);
            else
                labels[x][y].setIcon(whitePieces[x][y]);
        }
    }
    
    public void removePlayer() {
    	for(int i = 0;i<10;i++) {
    		for(int j = 0;j<10;j++) {
    			labels[i][j].setIcon(emptyPieces[i][j]);
    		}
    	}
    	
    }
    
    public ImageIcon[][] createImageIcons(String path){
        ImageIcon[][] imageIcons = new ImageIcon[10][10];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                try {
                    //Image img = ImageIO.read(getClass().getResource("/images/400-chutes-and-ladders [www.imagesplitter.net]-"+i+"-"+j+".jpeg"));
                    Image img = ImageIO.read(getClass().getResource(path+i+"-"+j+".jpeg"));
                    ImageIcon icon = new ImageIcon(img);
                    img = icon.getImage() ;  
                    Image newimg = img.getScaledInstance( 60, 60,  java.awt.Image.SCALE_SMOOTH ) ;  
                    imageIcons[i][j] = new ImageIcon(newimg);
                } catch (IOException ex) {
                    Logger.getLogger(CLBoard.class.getName()).log(Level.SEVERE, null, ex);
                }           
            }
        }
        return imageIcons;
    }
    
}