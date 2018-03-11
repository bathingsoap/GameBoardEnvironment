package gbe.engine;

import gbe.players.*;
import gbe.gui.*;

public abstract class GameLogic {

	State state;
	
	public GameLogic(State s) {
		this.state = s;
	}

	public void update(GameState gs){

	}

	public Player nextTurn(State state){

	}
}
