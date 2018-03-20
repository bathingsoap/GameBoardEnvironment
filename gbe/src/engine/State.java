package engine;

import players.*;
import java.util.HashMap;

import javax.swing.Timer; // or import java.util.Timer

public abstract class State {

	Board board;
	Player playerTurn;
	HashMap<Player, Integer> score;

	
	public State(){
	}

	public abstract Player getCurrentTurn();
	public abstract void makeMove();
	public abstract void makeMove(int x, int y);
	public abstract void update(int x, int y);
	public abstract void restart();
	public abstract void exit();
}
