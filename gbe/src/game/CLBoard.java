package game;

import engine.Board;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import players.Player;
import players.PlayerManager;


public class CLBoard implements Board {
    
    JButton[][] buttons;
    JFrame frame;
    Player p1; Player p2;
    PlayerManager pm;
    
    public CLBoard(){
        buttons = new JButton[10][10];
        
    }
    
    @Override
    public void drawBoard(String gameType){
        frame = new JFrame(gameType);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        JPanel game = new JPanel(new GridLayout(10, 10));
        game.setPreferredSize(new Dimension(600, 600));
        for (int i = 0; i < 10; i++) { // row
            for (int j = 0; j < 10; j++) { // col
                JButton button = new JButton();
                //button.setText(("[" + i + "," + j + "]"));
               // button.setForeground(Color.white);
                button.setVisible(true);
                //button.addActionListener(new OthelloBoard.MyActionListener());
                buttons[i][j] = button;
                game.add(button);

            }
        }
        frame.add(game);
        frame.pack();
        frame.setVisible(true);
        
    }
}
