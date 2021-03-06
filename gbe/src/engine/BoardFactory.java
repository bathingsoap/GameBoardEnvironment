package engine;

import game.CLBoard;
import game.GomokuBoard;
import game.OthelloBoard;
import game.MemoryBoard;

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
			case "Chutes and Ladders":
				new CLBoard().drawBoard(gameType);
				break;
			case "Gomoku":
				new GomokuBoard().drawBoard(gameType);
				break;
			default:
				break;
		}
	}
}