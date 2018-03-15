package game;

import engine.Board;
import players.Player;
import players.PlayerManager;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MemoryBoard implements Board {
    private static final Character[] LETTERS = {'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G', 'G', 'H', 'H'};
    private JButton[][] buttons = new JButton[4][4];
    private StatusBar statusBar;
    private PlayerManager pm;
    private java.util.List<Integer> loggedMoves;
    private int availableCards = 16;
    private Player p1, p2;
    private int moveCounter;
    private Map<JButton, Character> invisibleVal;
    private Map<JButton, int[]> buttonCoords;
    private Object lock;
    private Timer misMatchTimer;

    public MemoryBoard() {
        statusBar = new StatusBar();
        p1 = new Player("player1");
        p2 = new Player("player2");
        this.pm = new PlayerManager(p1, p2);
        lock = new Object();
        this.invisibleVal = new HashMap<JButton, Character>();
        this.buttonCoords = new HashMap<JButton, int[]>();
        this.moveCounter = 0;
        this.loggedMoves = new ArrayList<Integer>();
    }

    public JPanel drawBoard() {
        JPanel window = new JPanel(new BorderLayout());
        JPanel game = new JPanel(new GridLayout(4, 4));
        window.add(BorderLayout.NORTH, statusBar);
        window.add(BorderLayout.CENTER, game);
        window.setPreferredSize(new Dimension(500, 500));
        java.util.List<Character> letters = Arrays.asList(LETTERS);
        Collections.shuffle(letters);
        int letterIndex = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setVisible(true);
                buttons[i][j].addActionListener(new myActionListener());
                buttonCoords.put(buttons[i][j], new int[]{i, j});
                invisibleVal.put(buttons[i][j], letters.get(letterIndex));
                game.add(buttons[i][j]);
                letterIndex++;
            }
        }
        return window;
    }


    public void switchTurnHelper() {
        if (moveCounter % 2 != 0 || !isMatch())
            pm.swapTurn();
        this.loggedMoves.clear();
    }

    public boolean isMatch() {
        int first_row = loggedMoves.get(0);
        int first_col = loggedMoves.get(1);
        int second_row = loggedMoves.get(2);
        int second_col = loggedMoves.get(3);
        Character first_choice = invisibleVal.get(buttons[first_row][first_col]);
        Character second_choice = invisibleVal.get(buttons[second_row][second_col]);
        if (first_choice.equals(second_choice)) {
            this.availableCards = this.availableCards - 2;
            buttons[first_row][first_col].setVisible(false);
            buttons[second_row][second_col].setVisible(false);
            pm.scorePoint(pm.currentPlayer);
            statusBar.setStatus("Current player has made a match, it is still their turn! Their score is: " + this.pm.score.get(pm.currentPlayer));
            checkWinner();
            return true;
        } else {
            statusBar.setStatus("Current player did not make a match. Switching turns...");
            misMatchTimer = new Timer(500, new OnMisMatchActionListener(buttons[first_row][first_col], buttons[second_row][second_col]));
            misMatchTimer.setRepeats(false);
            misMatchTimer.start();
            return false;
        }
    }

    public boolean checkWinner() {
        if (this.availableCards == 0) {
            if (this.pm.score.get(p1) > this.pm.score.get(p2)) {
                JOptionPane.showMessageDialog(null, "Player 1 has won with a score of: " + this.pm.score.get(p1) + " . While " +
                        "Player 2 has a score of: " + this.pm.score.get(p2));
                return true;
            } else if (this.pm.score.get(p2) > this.pm.score.get(p1)) {
                JOptionPane.showMessageDialog(null, "Player 2 has won with a score of: " + this.pm.score.get(p2) + " . While " +
                        "Player 1 has a score of: " + this.pm.score.get(p1));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nobody won, it was a tie!");
                return true;
            }
        }
        return false;
    }

    private class OnMisMatchActionListener implements ActionListener {
        private JButton a, b;

        public OnMisMatchActionListener(JButton a, JButton b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            synchronized (lock) {
                a.setText("");
                a.setEnabled(true);
                b.setText("");
                b.setEnabled(true);
            }
        }
    }

    private class myActionListener implements ActionListener {
        public void actionPerformed(ActionEvent a) {
            synchronized (lock) {
                statusBar.setStatus("");
                JButton button = (JButton) a.getSource();
                int[] coords = buttonCoords.get(button);
                Character buttonVal = invisibleVal.get(button);
                button.setEnabled(false);
                button.setText(buttonVal.toString());
                moveCounter++;
                loggedMoves.add(coords[0]);
                loggedMoves.add(coords[1]);
                if (moveCounter >= 2 && moveCounter % 2 == 0) {
                    switchTurnHelper();
                }
            }

        }
    }

    private class StatusBar extends JPanel {
        private JLabel statusLabel;

        public StatusBar() {
            setLayout(new BorderLayout(2, 2));
            statusLabel = new JLabel("");
            statusLabel.setBorder(BorderFactory.createLoweredBevelBorder());
            statusLabel.setForeground(Color.black);
            add(BorderLayout.CENTER, statusLabel);
        }

        public void setStatus(String status) {
            statusLabel.setText(status);
        }
    }
}
