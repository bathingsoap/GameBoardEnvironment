package game;

import engine.Board;

import javax.swing.*;
import java.awt.*;

public class OthelloBoard implements Board {
    JButton[][] buttons = new JButton[3][3];

    public OthelloBoard(){

    }
    public JPanel drawBoard() {
        JPanel game = new JPanel(new GridLayout(3,3));
        game.setPreferredSize(new Dimension(500,500));
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < 3; j++)
            {

                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setVisible(true);

                game.add(buttons[i][j]);
            }
        }
        return game;
    }
}
