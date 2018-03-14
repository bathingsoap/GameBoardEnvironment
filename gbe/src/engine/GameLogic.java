package engine;

import players.*;
import gui.*;

public abstract class GameLogic {
	State state;

	public abstract void update(State state);
	public abstract Player currentTurn(State state);
	public abstract void checkWinningState(State state);
	public abstract boolean checkMove();
//	public GameLogic(State s) {
//		this.state = s;
//	}

//	public void update(GameState gs){
//
//	}
//
//	public Player nextTurn(State state){
//
//	}
}
