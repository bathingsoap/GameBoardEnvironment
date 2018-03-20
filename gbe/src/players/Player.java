package players;

public class Player {

	public String username;
	boolean isTurn;
	int victories;
	int defeats;
	String playerPiece;
	
	public Player(String username) {
		this.username = username;
		this.victories = 0;
		isTurn= false;
	}
	
	public void myTurn() {this.isTurn = true;}

	public void notTurn(){
		this.isTurn = false;
		}

	public boolean isTurn(){
		return this.isTurn; 
	}
	public void setPlayerPiece(String piece){
		playerPiece = piece;
	}
	public Object getPlayerPiece(){
		return playerPiece;
	}
	public String getUsername() { return this.username; }
	
	public int getWins() {
		return victories;
	}
}
