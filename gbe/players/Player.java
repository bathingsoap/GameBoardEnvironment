package gbe.players;

public class Player {

	String username;
	boolean isTurn;
	int Victories;
	int Defeats;
	
	public Player(String username) {
		this.username = username;
	}
	
	boolean myTurn() {
		return this.isTurn;
	}
}
