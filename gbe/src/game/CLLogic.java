package game;

import engine.GameLogic;
import engine.State;
import java.util.HashMap;
import java.util.Random;
import players.Player;

public class CLLogic extends GameLogic{
    
    private HashMap<Integer,String> numberIndeces;
    private HashMap<Integer,Integer> latterMoves;
    private HashMap<Integer,Integer> chuteMoves;
    CLState state;
    
    public CLLogic(CLState state){
        super(state);
        this.state = state;
        numberIndeces = new HashMap<>();
        latterMoves = new HashMap<>();
        chuteMoves = new HashMap<>();
        fillNumberIndeces();
        fillLatterMoves();
        fillChuteMoves();
    }

    @Override
    public void update(State state){
        
    }

    @Override
    public boolean checkWinningState() {
        return state.checkWinState();
    }

    public boolean checkMove(int x, int y){
        return false;
        
    }

    public int rollDie(){           // abstracted to SixSidedDice in Pieces for other future games that use dice
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }
    
    public void fillNumberIndeces(){
        Integer key = 100;
        for(int i = 0; i < 10; i++){
            if(i%2 == 0){
                for(int j = 0; j < 10; j++){
                    String value = Integer.toString(i) + j;
                    numberIndeces.put(key, value);
                    key--;
                }
            }
            else{
                for(int j = 9; j >= 0; j--){
                    String value = Integer.toString(i) + j;
                    numberIndeces.put(key, value);
                    key--;
                }
            }  
        } 
    }
    
    public void fillLatterMoves(){
        latterMoves.put(1,38);
        latterMoves.put(4,14);
        latterMoves.put(9,31);
        latterMoves.put(21,42);
        latterMoves.put(28,84);
        latterMoves.put(51,67);
        latterMoves.put(71,91);
        latterMoves.put(80,100); 
    }
    
    public void fillChuteMoves(){  
        chuteMoves.put(98,79);
        chuteMoves.put(95,75);
        chuteMoves.put(93,73);
        chuteMoves.put(87,24);
        chuteMoves.put(62,19);
        chuteMoves.put(64,60);
        chuteMoves.put(54,34);
        chuteMoves.put(17,7);
    }
    
    public boolean isLatter(Integer space){
        return latterMoves.containsKey(space);
    }
    
    public boolean isChute(Integer space){
        return chuteMoves.containsKey(space);
    }
    
    public Integer moveUp(Integer space){
        return latterMoves.get(space);
    }
    
    public Integer moveDown(Integer space){
        return chuteMoves.get(space);
    }
    
    public String getBoardIndices(Integer position){
        return numberIndeces.get(position);
    }

}
