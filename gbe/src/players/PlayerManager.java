package players;

import players.*;
import java.util.*;
import java.util.Map.Entry;

import engine.*;

public class PlayerManager {
	ArrayList<Player> players = new ArrayList<Player>();
	public Player p1; // ArrayList to include unlimited number of players (maybe people can log in and log out?
	public Player p2;
	public HashMap<Player, Integer> score = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> totalScore = new HashMap<Player, Integer>();
	public Player currentPlayer;
	private static PlayerManager pm = null;
	
	private PlayerManager() {
		
	}
	
	public static PlayerManager getInstance() {
		if(pm==null) {
			pm = new PlayerManager();
		}
		return pm;
	}
	
	public void setPlayer1(String un) {
		if(containPlayer(un)==-1) {
			players.add(new Player(un));
			p1 = players.get(players.size()-1);
		}
		else {
			p1 = players.get(containPlayer(un));
		}
	}
	
	public void setPlayer2(String un) {
		if(containPlayer(un)==-1) {
			players.add(new Player(un));
			p2 = players.get(players.size()-1);
		}
		else {
			p2 = players.get(containPlayer(un));
		}
	}
	
	public int containPlayer(String un) {
		//int place = -1;
		for(int i = 0;i<players.size();i++) {
			if(players.get(i).getUsername().equals(un))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	public void newGame() {
		currentPlayer = p1;
		p1.isTurn = true;
		for (Entry<Player, Integer> entry : score.entrySet()) {
	          score.put(entry.getKey(), 0);
	      }
		
		//System.out.println(score.containsKey(currentPlayer));
		//System.out.println(score.containsKey(p2));
	}
	
	
	/*public void scorePoint(Player player){
		int currentScore = this.score.get(player);
		this.score.put(player, currentScore+1);
	}*/
	
	public void setScore(Player p, int s) {
		score.put(p, s);
	}
	
	public void scorePoint(Player p) {
		score.put(p, getScore(p.getUsername())+1);
	}
	
	public int getScore(String un) {
		for (Entry<Player, Integer> entry : score.entrySet()) {
			if(entry.getKey().getUsername().equals(un)) {
				return entry.getValue();
			}
	    }
		return 0;
	}
	
	public int getScore(Player p) {
		for (Entry<Player, Integer> entry : score.entrySet()) {
			if(entry.getKey().getUsername().equals(p.getUsername())) {
				return entry.getValue();
			}
	    }
		return 0;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Player swapTurn(){
		if (this.p1.isTurn){
			this.p1.notTurn();
			this.p2.isTurn = true;
			this.currentPlayer = this.p2;
		}
		else{
			this.p1.isTurn = true;
			this.p2.notTurn();
			this.currentPlayer = this.p1;
		}
		for (Entry<Player, Integer> entry : score.entrySet()) {
			System.out.println(entry.getKey().getUsername() + ":" + entry.getValue());
	    }
		System.out.println("--");

		return this.currentPlayer;
	}



}
