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
//				new GomokuBoard().drawBoard();
//				break;
//			case "Chutes and Ladders":
//				new CLBoard().drawBoard();
//				break;
			default:
				break;
		}
<<<<<<< HEAD
=======
		return ;
>>>>>>> 02c03be068d0ab18e2a47688ac37205b5a1a5d93
	}
}