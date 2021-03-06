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

public class MemoryBoard implements Board, ActionListener {
    private static final Character[] LETTERS = {'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G', 'G', 'H', 'H'};
    private static final String GAMETYPE = "Memory";
    private JButton[][] buttons = new JButton[4][4];
    private JFrame gameframe;
    private StatusBar statusBar;
    private PlayerManager pm;
    private java.util.List<Integer> loggedMoves;
    private int availableCards = 16;
    private int moveCounter;
    private Map<JButton, Character> invisibleVal;
    private Map<JButton, int[]> buttonCoords;
    private Object lock;
    private Timer misMatchTimer;

    JPanel optionPanel;
    JButton restart;
    JButton exit;

    public MemoryBoard() {
        statusBar = new StatusBar();
        pm = PlayerManager.getInstance();
        pm.newGame(GAMETYPE);
        lock = new Object();
        this.invisibleVal = new HashMap<JButton, Character>();
        this.buttonCoords = new HashMap<JButton, int[]>();
        this.moveCounter = 0;
        this.loggedMoves = new ArrayList<Integer>();
    }

    public void drawBoard(String gameType) {
        gameframe = new JFrame(gameType);
        gameframe.setLocation(500, 200);
        gameframe.setDefaultCloseOperation(gameframe.DISPOSE_ON_CLOSE);
        JPanel window = new JPanel(new BorderLayout());
        JPanel game = new JPanel(new GridLayout(4, 4,2,2));
        window.setPreferredSize(new Dimension(500, 500));
        window.add(BorderLayout.NORTH, statusBar);
        window.add(BorderLayout.CENTER, game);
        gameframe.add(window);
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

        optionPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

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

        gameframe.add(optionPanel, BorderLayout.SOUTH);

        gameframe.pack();
        gameframe.setVisible(true);
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
            pm.scorePoint(pm.getCurrentPlayer());
            statusBar.setStatus(pm.getCurrentPlayer().getUsername()+" has made a match, it is still their turn! Their score is: " + this.pm.getScore(pm.currentPlayer));
            checkWinner();
            return true;
        } else {
            statusBar.setStatus(pm.getCurrentPlayer().getUsername()+ " did not make a match. Switching turns...");
            misMatchTimer = new Timer(500, new OnMisMatchActionListener(buttons[first_row][first_col], buttons[second_row][second_col]));
            misMatchTimer.setRepeats(false);
            misMatchTimer.start();
            return false;
        }
    }

    public boolean checkWinner() {
        if (this.availableCards == 0) {
            if (this.pm.getScore(pm.p1) > this.pm.getScore(pm.p2)) {
                JOptionPane.showMessageDialog(null, pm.p1.getUsername() + " has won with a score of: " + this.pm.score.get(pm.p1) + " . While " +
                		pm.p2.getUsername()+ " has a score of: " + this.pm.getScore(pm.p2));
                pm.scoreWin(pm.p1);
                gameframe.dispose();
                return true;
            } else if (this.pm.getScore(pm.p2) > this.pm.getScore(pm.p1)) {
                JOptionPane.showMessageDialog(null, pm.p2.getUsername() + " has won with a score of: " + this.pm.score.get(pm.p2) + " . While " +
                		pm.p1.getUsername() +" has a score of: " + this.pm.getScore(pm.p1));
                pm.scoreWin(pm.p2);
                gameframe.dispose();
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nobody won, it was a tie!");
                return true;
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("restart")) {
            MemoryBoard newGame = new MemoryBoard();
            newGame.drawBoard(GAMETYPE);
            gameframe.dispose();

//          pm.newGame(GAMETYPE);
        }
        if (e.getActionCommand().equals("exit")) {
            gameframe.dispose();
        }
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


