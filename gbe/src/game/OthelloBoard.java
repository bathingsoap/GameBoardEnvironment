package game;

import engine.Board;
import players.PlayerManager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthelloBoard implements Board, ActionListener {
    JButton[][] buttons = new JButton[8][8];
    JFrame frame;
    OthelloState state;
    OthelloLogic logic;
    JPanel game;
    JPanel statusPanel;
    JLabel status;
    JPanel optionPanel;
    JButton restart;
    JButton exit;

    public OthelloBoard() {
        state = new OthelloState();
        logic = new OthelloLogic(state);
        frame = new JFrame("Othello");
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        game = new JPanel(new GridLayout(8, 8));
        game.setPreferredSize(new Dimension(600, 600));
        frame.setLayout(new BorderLayout());
        frame.setSize(300, 300);

        /*
        statusPanel = new JPanel(); // container
//        statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        statusPanel.setBackground(new Color(100, 72, 29));
        statusPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        frame.add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        status = new JLabel("<html>" + "<strong>Player 1:</strong> " + state.pm.score.get(state.pm.p1) + "<br/><strong>Player 2:</strong> " +
                state.pm.score.get(state.pm.p2) + "</html>"); // value, here will be username and score,
        status.setForeground(Color.white);
        status.setFont(new Font("Arial", Font.PLAIN, 18));
        status.setHorizontalAlignment(SwingConstants.CENTER);
        statusPanel.add(status);
        */
        
        
        optionPanel = new JPanel(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	c.gridx=0;c.gridy=0; c.gridwidth = 2;
    	  statusPanel = new JPanel(); // container
	      statusPanel.setBackground(new Color(100, 72, 29));
	      statusPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
	      frame.add(statusPanel, BorderLayout.SOUTH);
	      statusPanel.setPreferredSize(new Dimension(frame.getWidth(), 60));
	      statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
	      status = new JLabel("<html>" + "<strong>Player 1:</strong> " + state.pm.score.get(state.pm.p1) + "<br/><strong>Player 2:</strong> " +
	              state.pm.score.get(state.pm.p2) + "</html>"); // value, here will be username and score,
	      status.setForeground(Color.white);
	      status.setFont(new Font("Arial", Font.PLAIN, 18));
	      status.setHorizontalAlignment(SwingConstants.CENTER);
	      statusPanel.add(status);
    	optionPanel.add(statusPanel,c);
      
    	c.gridwidth=1;
    	c.weightx = 0.1;
    	c.gridx=0;
    	c.gridy=1;
    	restart = new JButton("restart");
    	exit = new JButton("exit");
    	restart.addActionListener(this);
    	exit.addActionListener(this);
    	optionPanel.add(restart, c);
    	c.gridx=1;
    	optionPanel.add(exit, c);
    	
    
    	
    	
    	frame.add(optionPanel, BorderLayout.SOUTH);
    }

    public void drawBoard(String gameType) {

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++) { // row
            for (int j = 0; j < 8; j++) { // col
                JButton button = new JButton();
                button.setForeground(Color.white);
                if (i == 3 && j == 3 || i == 4 && j == 4) {
                    button.setBackground(Color.white);
                    button.setBorder(new LineBorder(Color.black, 1));
                    button.setForeground(Color.black);
                } else if (i == 3 && j == 4 || i == 4 && j == 3) {
                    button.setBackground(Color.black);
                    button.setBorder(new LineBorder(Color.black, 1));
                    button.setForeground(Color.white);
                } else {
                    button.setBackground(new Color(0, 100, 0));
                    button.setBorder(new LineBorder(Color.black, 1));
                }
                button.setVisible(true);
                button.addActionListener(new MyActionListener());
                buttons[i][j] = button;
                game.add(button);

            }
        }
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
    }

    private class MyActionListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
        	PlayerManager pm = PlayerManager.getInstance();
            JButton click = (JButton) a.getSource();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (buttons[row][col] == click) {
                        if(!logic.checkMove(col, row)){
                            JOptionPane.showMessageDialog(null, "Invalid move");
                        } else {
                            state.update(col, row);
                            if(!state.flip.isEmpty()){
                                for(int i = 0; i < state.flip.size(); i++){
                                    buttons[state.flip.get(i).get(0)][state.flip.get(i).get(1)].setBackground(state.getColor((String) state.getCurrentTurn().getPlayerPiece()));
                                }
                            }
                            state.flip.clear();
                            state.pm.swapTurn();
                        }
                        break;
                    }
                }
            }
            // update label here
            status.setText("<html>" + "<strong>Player 1:</strong> " + state.pm.score.get(state.pm.p1) + "<br/><strong>Player 2:</strong> " +
                    state.pm.score.get(state.pm.p2) + "</html>");

            //check winning state
            if(logic.checkWinningState()){
                if (state.movesLeft.isEmpty()) {
                    if (pm.getScore(pm.p1) > pm.getScore(pm.p2)) {
                        JOptionPane.showMessageDialog(null, "Player 1 has won with a score of: " + pm.getScore(pm.p1) + " . While " +
                                "Player 2 has a score of: " + pm.getScore(pm.p2));
                    } else if (pm.getScore(pm.p2) > pm.getScore(pm.p1)) {
                        JOptionPane.showMessageDialog(null, "Player 2 has won with a score of: " + pm.getScore(pm.p2) + " . While " +
                                "Player 1 has a score of: " + pm.getScore(pm.p1));
                    } else {
                        JOptionPane.showMessageDialog(null, "Nobody won, it was a tie!");
                    }
                }
            }
            //clear row,col,diagonal moves
            state.row_moves.clear();
            state.col_moves.clear();
            state.diagonal_moves.clear();
        }
    }

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("restart")) {
        	state.restart();
        	//frame.repaint();
        }
        if(e.getActionCommand().equals("exit")) {
        	frame.dispose();
        }
	}
}


