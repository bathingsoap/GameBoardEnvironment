package gbe.engine;

import gbe.players.*;
import gbe.gui.*;
import java.util.HashMap;

import javax.swing.Timer; // or import java.util.Timer

public abstract class State {

	Board board;
	Player playerTurn;
	HashMap<Player, Integer> score;
	Timer clock;
	
	
	public State() {
		
	}

	public updateState(){}
}
