package gbe.engine;

public interface Piece {

	private String pieceType;

	public Piece(String pT){
		this.pieceType = pT;
	}

	public Piece createPiece();
}
