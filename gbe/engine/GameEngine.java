package gbe.engine;

import gbe.players.*;
import gbe.gui.*;

public class GameEngine {

	GameLogic logic;
	State state;
	
	public GameEngine(GameLogic l, State s) {
		this.logic = l;
		this.state = s;
	}
	
}
