package players;

public class Player {

	public String username;
	boolean isTurn;
	int victories;
	int defeats;
	
	public Player(String username) {
		this.username = username;
		this.victories = 0;
		this.defeats = 0;
//		System.out.println(username);
	}
	
	public void myTurn() {this.isTurn = true;
	}

	public void notTurn(){
		this.isTurn = false;
		}

	public boolean isTurn(){
		return this.isTurn; 
	}
}
