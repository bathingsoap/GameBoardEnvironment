package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class GomokuState {
	private static String turn;
	private static ArrayList<ArrayList<GomokuPiece>> pieces = new ArrayList<>();
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
    
    ArrayList<ArrayList<GomokuPiece>> getPieces() {
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
            ArrayList<GomokuPiece> arrayList = new ArrayList<>();
            for (int j = 0; j < 16; j++) {
                GomokuPiece gomokuPiece = new GomokuPiece();
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
        for (ArrayList<GomokuPiece> piece : pieces) {
            for (GomokuPiece aPiece : piece) {
                aPiece.setColor(new Color(0, 0, 0, 0));
            }
        }
    }
}
