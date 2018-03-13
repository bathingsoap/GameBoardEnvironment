package engine;
import game.OthelloBoard;

import javax.swing.JPanel;

public class BoardFactory {
	public BoardFactory(){

	}

	public JPanel createBoard(String gameType){
		JPanel newBoard = null;
		switch (gameType) {
			case "Othello":
				newBoard = new OthelloBoard().drawBoard();
				break;
			default:
				break;
		}
		return newBoard;
	}
}