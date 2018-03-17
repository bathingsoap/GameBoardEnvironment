package game;

import engine.State;
import players.*;
import engine.Board;
import engine.GameLogic;
import java.util.HashMap;


public class CLState extends State {
    private CLBoard board;
    private Player playerTurn;
    private Player[] players;
    private HashMap<Player,Integer> score;
    private CLLogic logic;
    
    
    public CLState(CLBoard board, PlayerManager playerManager){
        this.board = board;
        this.players = playerManager.getPlayers();
        this.playerTurn = players[0];
        score = new HashMap<>();
        for(Player p: players){
            score.put(p,0);
        }
        logic  = new CLLogic();
    }
    
    @Override
    public Player getCurrentTurn() {
        return playerTurn;
    }

    @Override
    public void makeMove() {
        boolean winner = false;
        Integer currentPosition = score.get(getCurrentTurn());
        Integer die = logic.rollDie();
        board.updateMessage1("You rolled a " + die + "!");
        Integer newPosition = currentPosition + die;
        
        if((newPosition) > 100){
            newPosition = currentPosition;
            board.updateMessage2("Your roll puts you above 100.");
            board.updateMessage3("You are still on space " + newPosition + ".");
            board.updateMessage4("");
        }else if(newPosition.equals(100)){
            board.updateMessage2("You are now on space 100." );
            board.updateMessage3("Congratulations, you win!");
            board.updateMessage4("");
            board.updateWinner(true);
            score.put(playerTurn, newPosition);
            winner = true;
        }else{
            board.updateMessage2("You are now on space " + newPosition + ".");
            board.updateMessage3("");
            board.updateMessage4("");
            if(logic.isChute(newPosition)){
                board.updateMessage3("Oh no, you landed on a chute!");
                newPosition = logic.moveDown(newPosition);
                board.updateMessage4("You are now on space " + newPosition+".");
            }else if(logic.isLatter(newPosition)){
                board.updateMessage3("Yay, you landed on a latter!");
                newPosition = logic.moveUp(newPosition);
                board.updateMessage4("You are now on space " + newPosition+".");
                if(newPosition.equals(100)){
                    board.updateMessage2("You are now on space 100." );
                    board.updateMessage3("Congratulations, you win!");
                    board.updateMessage4("");
                    board.updateWinner(true);
                    score.put(playerTurn, newPosition);
                    winner = true;
                }
            }
        }
        score.put(playerTurn, newPosition);
        
        if(players[0].equals(getCurrentTurn())){
            board.updateP1Position(Integer.toString(newPosition));
            playerTurn = players[1];
            if(!winner)
                board.updateTurnLabel("Player 2:");

        }else{
            board.updateP2Position(Integer.toString(newPosition));
            playerTurn = players[0];
            if(!winner)
                board.updateTurnLabel("Player 1:");
        }
        
        
        int oldA = 0;
        int oldB = 0;
        
        if(!currentPosition.equals(0)){
            String[] oldIndeces = logic.getBoardIndices(currentPosition).split("");
            oldA = Integer.parseInt(oldIndeces[0]);
            oldB = Integer.parseInt(oldIndeces[1]);
        }
           
        String[] indeces = logic.getBoardIndices(newPosition).split("");
        int a = Integer.parseInt(indeces[0]);
        int b = Integer.parseInt(indeces[1]);
        
   
        
        int p =1;
        if(players[1].equals(getCurrentTurn()))
            p = 2;    
        
        Integer s1 = -1;
        boolean sameScore = false;
        for(Integer s2: score.values()){
            if(s2.equals(s1))
                sameScore = true;
            s1 = s2;
        }
        board.movePlayer(p, a, b, oldA, oldB, sameScore);      
    }
    
    public HashMap<Player,Integer> getScore(){
        return score;
    }
    
    
}
