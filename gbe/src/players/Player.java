package players;

public class Player {

	public String username;
	boolean isTurn;
	int victories;
	int defeats;
	Object playerPiece;
	public Player(String username) {
		this.username = username;
		this.victories = 0;
		this.defeats = 0;
	}
	
	public void myTurn() {this.isTurn = true;}

	public void notTurn(){
		this.isTurn = false;
		}

	public boolean isTurn(){
		return this.isTurn; 
	}
	public void setPlayerPiece(Object piece){
		playerPiece = piece;
	}
	public Object getPlayerPiece(){
		return playerPiece;
	}
	public String getUsername() { return this.username; }
}
