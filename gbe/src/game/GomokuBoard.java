import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RenjuBoard extends JFrame implements Board{
    private PlayerManager playerManager = PlayerManager.getInstance();
    private ArrayList<ArrayList<RenjuPiece>> pieces = playerManager.getPieces();
    private BoardFactory boardFactory = new BoardFactory();
    private MyActionListener actionListener = new MyActionListener();
    private Panel buttons = new Panel();
    private Button restartButton = new Button("Restart");
    private Button exitButton = new Button("Exit");

    @Override
    public void createBoard() {
        initGame();
        initPieces();
        setTitle("Gomoku");
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.add(restartButton);
        buttons.add(exitButton);
        restartButton.addActionListener(actionListener);
        exitButton.addActionListener(actionListener);
        add(buttons, BorderLayout.SOUTH);
        add(boardFactory);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object object = e.getSource();
            if (object == restartButton) {
                boardFactory.restartGame();
            } else if (object == exitButton) {
                boardFactory.exitGame();
            }
        }
    }

    private void initGame() {
        boardFactory.setMargin(30);
        boardFactory.setGridSpan(35);
        boardFactory.setRows(15);
        boardFactory.setCols(15);
        boardFactory.setBoard();
    }

    private void initPieces() {
        for (int i = 0; i < pieces.size(); i++) {
            for (int j = 0; j < pieces.get(i).size(); j++) {
                RenjuPiece renjuPiece = new RenjuPiece();
                renjuPiece.createPiece(i, j, new Color(0, 0, 0, 0));
                pieces.get(i).add(renjuPiece);
            }
        }
    }
}
