package players;

import java.util.ArrayList;
import engine.*;

public class PlayerManager {
	public Player p1; // ArrayList to include unlimited number of players (maybe people can log in and log out?
	public Player p2;
	public Player currentPlayer;

	public PlayerManager(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.currentPlayer = p1;
	}

	public Player swapTurn(){
		if (this.p1.isTurn){
			this.p1.notTurn();
			this.p2.isTurn();
			this.currentPlayer = this.p2;
		}
		else{
			this.p1.isTurn();
			this.p2.notTurn();
			this.currentPlayer = this.p1;
		}
		return this.currentPlayer;
	}



//	void updateState(BoardFactory bf, Player p) {
//	}

}
