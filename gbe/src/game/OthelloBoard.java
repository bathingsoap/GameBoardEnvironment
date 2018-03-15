package game;

import engine.Board;
import players.Player;
import players.PlayerManager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OthelloBoard implements Board {
    JButton[][] buttons = new JButton[8][8];
    JFrame frame;
    Player p1; Player p2;
    PlayerManager pm;

    public OthelloBoard() {
        p1 = new Player("player1");
        p2 = new Player("player2");
        this.pm = new PlayerManager(p1, p2);
    }

    public void drawBoard(String gameType) {
        frame = new JFrame(gameType);
        frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
        frame.setLocation(500, 100);
        JPanel game = new JPanel(new GridLayout(4, 4));
        game = new JPanel(new GridLayout(8, 8));
        game.setPreferredSize(new Dimension(600, 600));

        try {
            UIManager.setLookAndFeel(new javax.swing.plaf.metal.MetalLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 8; i++) { // row
            for (int j = 0; j < 8; j++) { // col
                JButton button = new JButton();
                button.setText(("[" + i + "," + j + "]"));
                button.setForeground(Color.white);
                if (i == 3 && j == 3 || i == 4 && j == 4) {
                    button.setBackground(Color.white);
                    button.setBorder(new LineBorder(Color.black, 1));
                    button.setForeground(Color.black);
                } else if (i == 3 && j == 4 || i == 4 && j == 3) {
                    button.setBackground(Color.black);
                    button.setBorder(new LineBorder(Color.black, 1));
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
            int k, l = 0;
            JButton click = (JButton) a.getSource();
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if (buttons[row][col] == click) {
                        k = row;
                        l = col;
                        System.out.println("[" + k + "," + l + "]");
                        click.setBackground(Color.red);
                        break;
                    }
                }
            }
        }
    }
}


