package game;

import engine.Board;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Objects;

public class GomokuPanel extends JPanel implements MouseListener {
	private GomokuLogic gomokuLogic = new GomokuLogic();
    private GomokuState gomokuState = GomokuState.getInstance();
    private String turn = gomokuState.getTurn();
    private ArrayList<ArrayList<GomokuPiece>> pieces = gomokuState.getPieces();
    private boolean status = gomokuState.getStatus();

    // --- Current Attributes ---

    private Color color;
    private int currentX, currentY;
    // currentX is 第几列
    // currentY is 第几行

    // --- Info of Board ---

    private static int MARGIN = 30;
    private static int GRID_SPAN = 35;
    private static int ROWS = 15;
    private static int COLS = 15;

    GomokuPanel() {
        // --- set bg color ---
        setBackground(new Color(122, 165, 210));
        addMouseListener(this);
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                currentX = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                currentY = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN;
                if (currentX < 0 || currentX > ROWS || currentY < 0 || currentY > COLS || boolPiece(currentX, currentY) || status) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                } else {
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        if (status)
            return;
        // --- update drop position ---
        currentX = (e.getX() - MARGIN + GRID_SPAN / 2) / GRID_SPAN; // 列
        currentY = (e.getY() - MARGIN + GRID_SPAN / 2) / GRID_SPAN; // 行
//        System.out.println("Local: " + currentX + " " + currentY);
        if (currentX < 0 || currentX > ROWS || currentY < 0 || currentY > COLS || boolPiece(currentX, currentY)) {
            return;
        }
        // --- pass current x, y, ROWS, COLS, color and piece to game state ---
        gomokuLogic.setCurrentX(currentY);
        gomokuLogic.setCurrentY(currentX);
        gomokuLogic.setRows(ROWS);
        gomokuLogic.setCols(COLS);
        getCurrentColor();
        gomokuLogic.setCurrentColor(color);
        // --- updates piece
        pieces.get(currentY).get(currentX).setColor(color);
        // ---
        gomokuLogic.setPieces(pieces);

        // --- Drawing ---
        repaint();
        if (gomokuLogic.boolWin()) {
            String message = String.format("Congratulation to %s Player!", turn);
            JOptionPane.showMessageDialog(this, message);
            status = true;
        } else if (boolFullPiece()) {
            String message = "DRAW";
            JOptionPane.showMessageDialog(this, message);
            status = true;
        }
        updateTurn();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int i = 0; i <= ROWS; i++) {
            graphics.drawLine(MARGIN, MARGIN + i * GRID_SPAN, MARGIN + COLS * GRID_SPAN, MARGIN + i * GRID_SPAN);
        }
        for (int i = 0; i <= COLS; i++) {
            graphics.drawLine(MARGIN + i * GRID_SPAN, MARGIN, MARGIN + i * GRID_SPAN, MARGIN + ROWS * GRID_SPAN);
        }
        for (int i = 0; i < pieces.size(); i++) {
            for (int j = 0; j < pieces.get(i).size(); j++) {
                int diameter = GomokuPiece.DIAMETER;
                if (boolPiece(j, i)) {
                    int x = i * GRID_SPAN + MARGIN;
                    int y = j * GRID_SPAN + MARGIN;
                    graphics.setColor(pieces.get(i).get(j).getColor());
                    graphics.fillOval(y - diameter / 2, x - diameter / 2, diameter, diameter);
                }
            }
        }
    }

    private boolean boolPiece(int x, int y) {
        return pieces.get(y).get(x).getColor().getAlpha() != new Color(0, 0, 0, 0).getAlpha();
    }

    private boolean boolFullPiece() {
        for (ArrayList<GomokuPiece> piece : pieces) {
            for (GomokuPiece aPiece : piece) {
                if (aPiece.getColor().getAlpha() == new Color(0, 0, 0, 0).getAlpha())
                    return false;
            }
        }
        return true;
    }

    private void updateTurn() {
        if (Objects.equals(turn, "Black"))
            turn = "White";
        else
            turn = "Black";
    }

    void restartGame() {
        status = false;
        gomokuState.resetPiece();
        turn = "Black";
        repaint();
    }

    private void getCurrentColor() {
        if (Objects.equals(turn, "Black"))
            color = new Color(72, 77, 81);
        else
            color = new Color(246, 246, 246);
    }

    public Dimension getPreferredSize() {
        return new Dimension(MARGIN * 2 + GRID_SPAN * COLS, MARGIN * 2 + GRID_SPAN * ROWS);
    }
}
