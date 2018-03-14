package engine;
import game.OthelloBoard;
import game.MemoryBoard;

import javax.swing.JPanel;

public class BoardFactory {
	public BoardFactory(){

	}

	public void createBoard(String gameType){
		switch (gameType) {
			case "Othello":
				new OthelloBoard().drawBoard(gameType);
				break;
			case "Memory":
				new MemoryBoard().drawBoard(gameType);
				break;
//			case "Gomoku":
//				newBoard = new GomokuBoard().drawBoard();
//				break;
//			case "Chutes and Ladders":
//				newBoard = new CLBoard().drawBoard();
//				break;
			default:
				break;
		}
		return ;
	}
}