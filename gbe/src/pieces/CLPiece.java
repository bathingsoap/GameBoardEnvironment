package pieces;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CLPiece extends JPanel implements Piece {

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
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		this.color = color;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("hi");
		g.setColor(color);
		g.fillOval(20, 20, 20, 20);
	}

}
