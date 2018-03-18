package pieces;

public class PieceFactory {
	
	public Piece getPiece(String piece) {
		if(piece == null) {
			return null;
		}
		if(piece.equals("Gomoku")) {
			return new GomokuPiece();
		}
		
		return null;
	}
}
