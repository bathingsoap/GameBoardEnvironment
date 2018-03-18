package pieces;

import java.awt.Color;

public interface Piece {
	
	void createPiece();
    void createPiece(int x, int y, Color color);

    int getX() ;

    int getY();

    Color getColor();

    void setColor(Color color);
    static int getDiameter() {return 0;};
}
