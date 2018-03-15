package game;

import engine.Piece;
import java.awt.*;

public class GomokuPiece implements Piece {
    private int x;
    private int y;
    private Color color;
    static final int DIAMETER=30;

    @Override
    public void createPiece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        this.color = color;
    }
}
