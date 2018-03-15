package game;
import java.awt.event.ActionListener;
import java.util.*;

import engine.Board;
import players.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MemoryBoard implements Board {
    JButton[][] buttons = new JButton[4][4];
    private Map<JButton, String> invisibleVal;
    PlayerManager pm;
    ArrayList<Integer> loggedMoves;
    int availableCards = 16;
    Player p1;
    Player p2;
    int moveCounter;
    JOptionPane winnerWindow;
    JFrame gameframe;

    public MemoryBoard(){
        p1 = new Player("player1");
        p2 = new Player("player2");
        this.pm = new PlayerManager(p1, p2);
        this.invisibleVal = new HashMap<JButton, String>();
        this.moveCounter = 0;
        this.loggedMoves = new ArrayList<Integer>();
        JOptionPane winnerWindow;

    }

    public void drawBoard(String gameType) {
        gameframe = new JFrame(gameType);
        gameframe.setLocation(500, 200);
        gameframe.setDefaultCloseOperation(gameframe.DISPOSE_ON_CLOSE);
        gameframe.setSize(600, 500);
        JPanel game = new JPanel(new GridLayout(4,4));
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel menu = new JPanel(new BorderLayout());

        gameframe.add(mainPanel);
        JLabel player1 = new JLabel(this.pm.p1.username + "\n Score: " + this.pm.score.get(p1));
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(player1);
        mainPanel.setPreferredSize(new Dimension(325, 425));

        menu.setPreferredSize(new Dimension(300,50));
        game.setPreferredSize(new Dimension(300, 300));

        gameframe.add(menu, BorderLayout.NORTH);
        gameframe.add(game, BorderLayout.SOUTH);

//        game.setPreferredSize(new Dimension(500,500));
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {

                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setVisible(true);
                buttons[i][j].addActionListener(new myActionListener());

                game.add(buttons[i][j]);
            }
        }
        this.invisibleVal.put(buttons[0][0], "A");
        this.invisibleVal.put(buttons[0][1], "A");
        this.invisibleVal.put(buttons[1][0], "B");
        this.invisibleVal.put(buttons[1][1], "B");
        this.invisibleVal.put(buttons[2][0], "C");
        this.invisibleVal.put(buttons[2][1], "C");
        this.invisibleVal.put(buttons[3][0], "D");
        this.invisibleVal.put(buttons[3][1], "D");

        this.invisibleVal.put(buttons[0][2], "E");
        this.invisibleVal.put(buttons[0][3], "E");
        this.invisibleVal.put(buttons[1][2], "F");
        this.invisibleVal.put(buttons[1][3], "F");
        this.invisibleVal.put(buttons[2][2], "G");
        this.invisibleVal.put(buttons[2][3], "G");
        this.invisibleVal.put(buttons[3][2], "H");
        this.invisibleVal.put(buttons[3][3], "H");
        gameframe.add(game);
        gameframe.setVisible(true);
        return;
    }


    public void switchTurnHelper(){
        if (this.moveCounter %2 == 0 && isMatch()){
//            System.out.println(this.pm.currentPlayer.username + " has made a match. It is still their turn. ");
        }
        else{
//            System.out.println(this.pm.currentPlayer.username + " has not made a match. Switching turns...");
            pm.swapTurn();
        }
        this.loggedMoves.clear();
    }

    public boolean isMatch(){
        int first_row = loggedMoves.get(0);
        int first_col = loggedMoves.get(1);
        int second_row = loggedMoves.get(2);
        int second_col = loggedMoves.get(3);
        String first_choice = invisibleVal.get(buttons[first_row][first_col]);
        String second_choice = invisibleVal.get(buttons[second_row][second_col]);
        if (first_choice == second_choice){
            this.availableCards = this.availableCards - 2;
            buttons[first_row][first_col].setVisible(false);
            buttons[second_row][second_col].setVisible(false);
            pm.scorePoint(pm.currentPlayer);
            System.out.println("Current player has made a match, it is still their turn! Their score is: " + this.pm.score.get(pm.currentPlayer));
            checkWinner();
            return true;
        }else{
            System.out.println("Current player did not make a match. Switching turns...");
            buttons[first_row][first_col].setText("");
            buttons[first_row][first_col].setEnabled(true);
            buttons[second_row][second_col].setText("");
            buttons[second_row][second_col].setEnabled(true);
            return false;
        }
    }

    public boolean checkWinner(){
        if (this.availableCards == 0){
            if (this.pm.score.get(p1) > this.pm.score.get(p2)){
//                System.out.println("Player 1 has won with a score of: " + this.pm.score.get(p1) + " . While " +
//                        "Player 2 has a score of: " + this.pm.score.get(p2));
                JOptionPane.showMessageDialog(null, "Player 1 has won with a score of: " + this.pm.score.get(p1) + " . While " +
                        "Player 2 has a score of: " + this.pm.score.get(p2));

                return true;
            }
            else if (this.pm.score.get(p2) > this.pm.score.get(p1)) {
//                System.out.println("Player 2 has won with a score of: " + this.pm.score.get(p2) + " . While " +
//                        "Player 1 has a score of: " + this.pm.score.get(p1));
                JOptionPane.showMessageDialog(null, "Player 2 has won with a score of: " + this.pm.score.get(p2) + " . While " +
                        "Player 1 has a score of: " + this.pm.score.get(p1));

                return true;
            }
            else {
                System.out.println("Nobody won, it was a tie!");
                return true;
            }
        }
        return false;
    }

    private class myActionListener implements ActionListener{
        public void actionPerformed(ActionEvent a){
            if (a.getSource() == buttons[0][0]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[0][0].setEnabled(false);
                buttons[0][0].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(0);
                loggedMoves.add(0);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }

            if (a.getSource() == buttons[0][1]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[0][1].setEnabled(false);
                buttons[0][1].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(0);
                loggedMoves.add(1);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[0][2]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[0][2].setEnabled(false);
                buttons[0][2].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(0);
                loggedMoves.add(2);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }

            }
            if (a.getSource() == buttons[0][3]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[0][3].setEnabled(false);
                buttons[0][3].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(0);
                loggedMoves.add(3);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }

            }
            if (a.getSource() == buttons[1][0]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[1][0].setEnabled(false);
                buttons[1][0].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(1);
                loggedMoves.add(0);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[1][1]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[1][1].setEnabled(false);
                buttons[1][1].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(1);
                loggedMoves.add(1);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[1][2]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[1][2].setEnabled(false);
                buttons[1][2].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(1);
                loggedMoves.add(2);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[1][3]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[1][3].setEnabled(false);
                buttons[1][3].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(1);
                loggedMoves.add(3);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[2][0]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[2][0].setEnabled(false);
                buttons[2][0].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(2);
                loggedMoves.add(0);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[2][1]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[2][1].setEnabled(false);
                buttons[2][1].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(2);
                loggedMoves.add(1);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[2][2]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[2][2].setEnabled(false);
                buttons[2][2].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(2);
                loggedMoves.add(2);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[2][3]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[2][3].setEnabled(false);
                buttons[2][3].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(2);
                loggedMoves.add(3);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[3][0]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[3][0].setEnabled(false);
                buttons[3][0].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(3);
                loggedMoves.add(0);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[3][1]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[3][1].setEnabled(false);
                buttons[3][1].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(3);
                loggedMoves.add(1);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[3][2]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[3][2].setEnabled(false);
                buttons[3][2].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(3);
                loggedMoves.add(2);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }
            if (a.getSource() == buttons[3][3]){
                String buttonVal = invisibleVal.get(a.getSource());
                buttons[3][3].setEnabled(false);
                buttons[3][3].setText(buttonVal);
                moveCounter++;
                loggedMoves.add(3);
                loggedMoves.add(3);
                if (moveCounter >= 2 && moveCounter %2 == 0){
                    switchTurnHelper();
                }
            }

        }
    }
}
