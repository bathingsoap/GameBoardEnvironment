package players;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Player {

	public String username;
	boolean isTurn;
	int victories;
	int totalGamesPlayed;
	HashMap<String, Integer> games;
	String playerPiece;
	
	public Player(String username) {
		this.username = username;
		this.victories = 0;
		this.games = new HashMap<String, Integer>();
		this.totalGamesPlayed = 0;
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

	public void incrementGame(String gameType){

		if(!games.containsKey(gameType))
			games.put(gameType, 0);
		games.replace(gameType, games.get(gameType) + 1);
	}

	public Map.Entry<String, Integer> getFavoriteGame(){
//		if(games == null)
//			return new AbstractMap.SimpleEntry<String, Integer> ("N/A", 0);

		Map.Entry<String, Integer> max = null;

		for(Map.Entry<String, Integer> entry : games.entrySet()){
			if(max == null || entry.getValue().compareTo(max.getValue()) > 0)
				max = entry;
		}

		if(max == null)
			return new AbstractMap.SimpleEntry<String, Integer> ("N/A", 0);

		return max;
	}

	public int getTotalGamesPlayed(){
		return totalGamesPlayed;
	}
}
