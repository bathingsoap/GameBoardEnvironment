package pieces;

import java.awt.Color;

public class CLPiece implements Piece{

	private Color color;
	private static final int DIAMETER=30;
	
	@Override
	public void createPiece() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPiece(int x, int y, Color color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static int getDiameter() {
		return DIAMETER;
	}

}
