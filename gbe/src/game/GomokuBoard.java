package game;

import engine.Board;
import pieces.GomokuPiece;
import pieces.Piece;
import players.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GomokuBoard extends JFrame implements Board {
    private static final String GAMETYPE = "Gomoku";
    private String gameName;
	private GomokuPanel gomokuPanel = new GomokuPanel();
    private GomokuState gomokuState = GomokuState.getInstance();
    private String turn = gomokuState.getTurn();
    private ArrayList<ArrayList<GomokuPiece>> pieces = gomokuState.getPieces();
    private boolean status = gomokuState.getStatus();
    private PlayerManager pm;
    private Button restartButton = new Button("Restart ");

    public GomokuBoard() {
        setTitle(gameName);
        pm = PlayerManager.getInstance();
        pm.newGame(GAMETYPE);
        Panel buttons = new Panel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.add(restartButton);
        Button exitButton = new Button("  Exit  ");
        buttons.add(exitButton);
        MyActionListener actionListener = new MyActionListener();
        restartButton.addActionListener(actionListener);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turn = "Black";
                gomokuState.resetPiece();
                status = false;
                dispose();
            }
        });
        add(buttons, BorderLayout.SOUTH);
        add(gomokuPanel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-getPreferredSize().width/2, dim.height/2-getPreferredSize().height/2);
        pack();
    }

    @Override
    public void drawBoard(String gameType) {
        GomokuBoard gomokuBoard = new GomokuBoard();
        this.gameName = gameType;
        gomokuBoard.setVisible(true);
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            Object object = e.getSource();
            if (object == restartButton) {
                gomokuPanel.restartGame();
            }
        }
    }

//    public static void main(String[] args) {
//        GomokuBoard gomokuBoard = new GomokuBoard();
//        gomokuBoard.setVisible(true);
//    }
}
