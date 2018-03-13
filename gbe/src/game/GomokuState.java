

import java.awt.*;

public class RenjuPiece implements Piece {
    private int x;
    private int y;
    private Color color;
    public static final int DIAMETER=30;


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
