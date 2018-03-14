package engine;
import game.OthelloBoard;
import game.MemoryBoard;

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
			case "Memory":
				newBoard = new MemoryBoard().drawBoard();
				break;
			default:
				break;
		}
		return newBoard;
	}
}