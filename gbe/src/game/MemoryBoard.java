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
//    Player currentPlayer;
    Player p1;
    Player p2;
    int moveCounter;

    public MemoryBoard(){
        p1 = new Player("player1");
        p2 = new Player("player2");
        this.pm = new PlayerManager(p1, p2);
        this.invisibleVal = new HashMap<JButton, String>();
        this.moveCounter = 0;
        this.loggedMoves = new ArrayList<Integer>();

    }

    public void drawBoard(String gameType) {
        JPanel game = new JPanel(new GridLayout(4,4));
        game.setPreferredSize(new Dimension(500,500));
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
    }

//    public void swapTurn(){
//        if (this.pm.currentPlayer == this.p1){
//            this.p1.notTurn();
//            this.p2.myTurn();
//            this.pm.currentPlayer = this.p2;
//        }
//        else{
//            this.p2.notTurn();
//            this.p1.myTurn();
//            this.pm.currentPlayer = this.p1;
//        }
//    }

    public void switchTurnHelper(){
        if (this.moveCounter %2 == 0 && isMatch()){
            System.out.println("Player: " + this.pm.currentPlayer.username + " has made a match. It is still his turn. ");
        }
        else{
            System.out.println(this.pm.currentPlayer.username + " has not made a match. Switching turns. ");
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
            buttons[first_row][first_col].setVisible(false);
            buttons[second_row][second_col].setVisible(false);
            return true;
        }else{
            buttons[first_row][first_col].setText("");
            buttons[first_row][first_col].setEnabled(true);
            buttons[second_row][second_col].setText("");
            buttons[second_row][second_col].setEnabled(true);
            return false;
        }
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
