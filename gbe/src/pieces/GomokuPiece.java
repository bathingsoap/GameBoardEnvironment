package pieces;

import pieces.*;
import java.awt.*;

public class GomokuPiece implements Piece {
    private int x;
    private int y;
    private Color color;
    private static final int DIAMETER=30;

	@Override
	public void createPiece() {	}
	
    @Override
    public void createPiece(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

	public static int getDiameter() {
		return DIAMETER;
	}


}
