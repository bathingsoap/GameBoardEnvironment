package engine;

import players.*;
import gui.*;

public abstract class GameLogic {
	State state;

	public GameLogic(State s) {
		state = s;
	}

	public abstract boolean checkWinningState();

	public abstract boolean checkMove(int x, int y);
	public abstract void update(State state);

}
