package game;

import engine.State;
import players.*;
import pieces.*;

public class CLState extends State {
    private static final String GAMETYPE = "Chutes and Ladders";
    private CLBoard board;
    private Player playerTurn;
    //private HashMap<Player,Integer> score;
    private CLLogic logic;
    private PlayerManager pm;
    private int turnInt;
    private SixSidedDice dice = new SixSidedDice();
    
    public CLState(CLBoard board){
        this.board = board;
        pm = PlayerManager.getInstance();
        turnInt = 0;
        logic  = new CLLogic(this);
        
        pm.newGame(GAMETYPE);
    }
    
    @Override
    public Player getCurrentTurn() {
        return playerTurn;
    }

    @Override
    public void makeMove() {
        String name = pm.getCurrentPlayer().username;
        Integer currentPosition = pm.getScore(pm.getCurrentPlayer());
        dice.rollDie();
//        Integer die = logic.rollDie();

        board.updateMessage1(name + " rolled a " + dice.getRecentRoll() + "!");
        Integer newPosition = currentPosition + dice.getRecentRoll();
        
        if((newPosition) > 100){
            newPosition = currentPosition;
            board.updateMessage2("Your roll puts you above 100.");
            board.updateMessage3("You are still on space " + newPosition + ".");
            board.updateMessage4("---");
        }else if(newPosition.equals(100)){
            board.updateMessage2("You are now on space 100." );
            board.updateMessage3("Congratulations, you win!");
            board.updateMessage4("---");
            board.updateWinner(true);
            pm.setScore(pm.getCurrentPlayer(), newPosition);
            pm.scoreWin(pm.getCurrentPlayer());
        }else{
            board.updateMessage2("You are now on space " + newPosition + ".");
            board.updateMessage3("---");
            board.updateMessage4("---");
            if(logic.isChute(newPosition)){
                board.updateMessage3("Oh no, you landed on a chute!");
                newPosition = logic.moveDown(newPosition);
                board.updateMessage4("You are now on space " + newPosition+".");
            }else if(logic.isLatter(newPosition)){
                board.updateMessage3("Yay, you landed on a ladder!");
                newPosition = logic.moveUp(newPosition);
                board.updateMessage4("You are now on space " + newPosition+".");
                if(newPosition.equals(100)){
                    board.updateMessage4("You are now on space 100." );
                    board.updateMessage5("Congratulations, you win!");
                    //board.updateMessage4("---");
                    board.updateWinner(true);
                    pm.setScore(pm.getCurrentPlayer(), newPosition);
                    pm.scoreWin(pm.getCurrentPlayer());
                }
            }
        }
        pm.setScore(pm.getCurrentPlayer(), newPosition);
        
       
        /*
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
        */
        
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
        
   
 
        
        boolean sameScore = false;
        if(pm.getScore(pm.p1)==pm.getScore(pm.p2)) {
        	sameScore = true;
        }
        
        board.movePlayer(turnInt, a, b, oldA, oldB, sameScore);
        
        if(turnInt == 0) {
        	board.updateP1Position(Integer.toString(newPosition));
        	turnInt=1;board.updateTurnLabel(pm.p2.getUsername()+":");
        }
        else {
        	board.updateP2Position(Integer.toString(newPosition));
        	turnInt=0;board.updateTurnLabel(pm.p1.getUsername()+":");
        }
        pm.swapTurn();
        
                  
    }

    public boolean checkWinState(){
        if(board.p1Position.equals("100") || board.p2Position.equals("100"))
            return true;
        return false;
    }

    @Override
    public void makeMove(int x, int y) {
        return;
    }

    @Override
    public void update(int x, int y) {
        return;
    }

    public void restart() {
        // restart Player Managers
        pm.newGame(GAMETYPE);

        turnInt = 0;

        // restart board
        board.updateMessage1("---");
        board.updateMessage2("---");
        board.updateMessage3("---");
        board.updateMessage4("---");
        board.updateMessage5("---");
        board.removePlayer();
        board.updateP1Position("0");
        board.updateP2Position("0");

        // restart logic by creating new instance of logic
        logic = new CLLogic(this);
        
    }

    public void restart(CLBoard board) {
        this.board = board;
        this.restart();
    }

	@Override
	public void exit() {
		// TODO Auto-generated method stub
		
	}
    
    
}
