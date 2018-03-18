package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import pieces.GomokuPiece;
import pieces.Piece;
import pieces.PieceFactory;

public class GomokuState {
	private static String turn;
	private static ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();
	private static boolean status;
	private static GomokuState single_instance = null;
	
	private GomokuState() {
	    initTurn();
	    initPieces();
	    initStatus();
    }

	// --- SingleTon ---

	static GomokuState getInstance() {
		if (single_instance == null)
			single_instance = new GomokuState();
		return single_instance;
	}

    // --- getter functions ---

    String getTurn() {
    		return turn;
    }
    
    ArrayList<ArrayList<Piece>> getPieces() {
    		return pieces;
    }
    
    Boolean getStatus() {
    		return status;
    }

    // --- init functions ---

    private void initTurn() {
    		turn = "Black";
    }
    
    private void initPieces() {
        for (int i = 0; i < 16; i++) {
            ArrayList<Piece> arrayList = new ArrayList<>();
            PieceFactory pf = new PieceFactory();
            for (int j = 0; j < 16; j++) {
                Piece gomokuPiece = pf.getPiece("Gomoku");
                gomokuPiece.createPiece(i, j, new Color(0, 0, 0, 0));
                arrayList.add(gomokuPiece);
            }
            pieces.add(arrayList);
        }
    }
    
    private void initStatus() {
    		status = false;
    }

    void resetPiece() {
        for (ArrayList<Piece> piece : pieces) {
            for (Piece aPiece : piece) {
                aPiece.setColor(new Color(0, 0, 0, 0));
            }
        }
    }
}
