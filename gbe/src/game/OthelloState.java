package game;
import engine.State;
import players.*;

import java.awt.*;
import java.util.ArrayList;

public class OthelloState extends State{
    String[][] pieces; //[row][col]
    ArrayList<ArrayList<Integer>> row_moves = new ArrayList<>();
    ArrayList<ArrayList<Integer>> col_moves = new ArrayList<>();
    ArrayList<ArrayList<Integer>> diagonal_moves = new ArrayList<>();
    ArrayList<ArrayList<Integer>> flip = new ArrayList<>();
    ArrayList<ArrayList<Integer>> movesLeft = new ArrayList<>();
    int currentX; //row
    int currentY; //col
    PlayerManager pm;

    public OthelloState(){
        pm = PlayerManager.getInstance();
        pm.newGame();
        pm.setScore(pm.p1, 2);
        pm.setScore(pm.p2, 2);
        pm.p1.setPlayerPiece("black");
        pm.p2.setPlayerPiece("white");
        pieces = new String[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                pieces[i][j] = "";
            }
        }
        pieces[3][3] = "white";
        pieces[4][4] = "white";
        pieces[3][4] = "black";
        pieces[4][3] = "black";
        setMovesLeft();
    }

    @Override
    public void makeMove() {

    }

    @Override
    public void update(int y, int x) {
        currentX = x;
        currentY = y;
        makeMove(x,y);
        for(int i=0; i < flip.size(); i++){
            if(movesLeft.contains(flip.get(i))){
                movesLeft.remove(movesLeft.indexOf(flip.get(i)));
            }
            pieces[flip.get(i).get(0)][flip.get(i).get(1)] = (String) getCurrentTurn().getPlayerPiece();
        }
        updateScore(pm.p1, pm.p2);

        //clear row,col,diagonal moves
        row_moves.clear();
        col_moves.clear();
        diagonal_moves.clear();
        return;
    }

    @Override
    public void restart() {
    	pm.newGame();
    	 pm.setScore(pm.p1, 2);
         pm.setScore(pm.p2, 2);
         pieces = new String[8][8];
         for(int i = 0; i < 8; i++){
             for(int j = 0; j < 8; j++){
                 pieces[i][j] = "";
                 System.out.print(pieces[i][j]);
             }
             System.out.println();
         }
         
         pieces[3][3] = "white";
         pieces[4][4] = "white";
         pieces[3][4] = "black";
         pieces[4][3] = "black";
         System.out.println(pieces[3][3]);
         movesLeft = new ArrayList<>();
         setMovesLeft();
         
         
    }

    @Override
    public void exit() {
    }

    @Override
    public void makeMove(int col, int row) { // col, row
        if(!row_moves.isEmpty()){
            for (int i = 0; i < row_moves.size(); i++){
                row_move(row_moves.get(i).get(0),row_moves.get(i).get(1));
            }
        }
        if(!col_moves.isEmpty()){
            for (int i = 0; i < col_moves.size(); i++){
                col_move(col_moves.get(i).get(0), col_moves.get(i).get(1));
            }
        }
        if(!diagonal_moves.isEmpty()){
            for (int i = 0; i < diagonal_moves.size(); i++){
                diagonal_move(diagonal_moves.get(i).get(0), diagonal_moves.get(i).get(1));
            }
        }
        return;
    }

    public Color getColor(String color){
        if(color.equals("black")){
            return Color.BLACK;
        }
        return Color.WHITE;
    }

    public String getOtherColor(Object objectColor) {
        if (objectColor.equals("white")) {
            return "black";
        }
        else { return "white"; }
    }

    public void updateScore(Player player1, Player player2) {
        int player1_score = 0; int player2_score = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (player1.getPlayerPiece().equals(pieces[row][col])) {
                    player1_score++;
                } else if (player2.getPlayerPiece().equals(pieces[row][col])) {
                    player2_score++;
                }
            }
        }
        pm.setScore(player1, player1_score);
        pm.setScore(player2, player2_score);
    }

    private void setMovesLeft(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                ArrayList<Integer> move = new ArrayList<>();
                move.add(i);
                move.add(j);
                movesLeft.add(move);
            }
        }
        ArrayList<Integer> left_up = new ArrayList<>();
        left_up.add(3);
        left_up.add(3);
        movesLeft.remove(left_up);
        ArrayList<Integer> left_down = new ArrayList<>();
        left_down.add(4);
        left_down.add(3);
        movesLeft.remove(left_down);
        ArrayList<Integer> right_up = new ArrayList<>();
        right_up.add(3);
        right_up.add(4);
        movesLeft.remove(right_up);
        ArrayList<Integer> right_down = new ArrayList<>();
        right_down.add(4);
        right_down.add(4);
        movesLeft.remove(right_down);
    }

    private void row_move(int row, int col){
        //right
        if(currentY < col){
            for(int i = currentY; i <= col; i++){
                ArrayList<Integer> right_row = new ArrayList<>();
                right_row.add(row);
                right_row.add(i);
                flip.add(right_row);
            }
        }
        //left
        if(currentY > col){
            for(int i = currentY; i >= col; i--){
                ArrayList<Integer> left_row = new ArrayList<>();
                left_row.add(row);
                left_row.add(i);
                flip.add(left_row);
            }
        }
        return;
    };

    private void col_move(int row, int col){
        //up
        if(currentX > row){
            for(int i = currentX; i >= row; i--){
                ArrayList<Integer> up_col = new ArrayList<>();
                up_col.add(i);
                up_col.add(col);
                flip.add(up_col);
            }
        }
        //down
        if(currentX < row){
            for(int i = currentX; i <= row; i++){
                ArrayList<Integer> down_col = new ArrayList<>();
                down_col.add(i);
                down_col.add(col);
                flip.add(down_col);
            }
        }
        return ;
    };

    private void diagonal_move(int row, int col){
        //diagonal
        //top left
        if(row < currentX && col < currentY){
            int top_left_row = row;
            int top_left_column = col;
            while(top_left_row <= currentX && top_left_column <= currentY){
                ArrayList<Integer> top_left = new ArrayList<>();
                top_left.add(top_left_row);
                top_left.add(top_left_column);
                flip.add(top_left);
                top_left_row += 1;
                top_left_column += 1;
            }
        }

        //top right
        if(row < currentX && col > currentY){
            int top_right_row = row;
            int top_right_column = col;
            while(top_right_row <= currentX && top_right_column >= currentY){
                ArrayList<Integer> top_right = new ArrayList<>();
                top_right.add(top_right_row);
                top_right.add(top_right_column);
                flip.add(top_right);
                top_right_row += 1;
                top_right_column -= 1;
            }
        }
        //bottom left
        if(row > currentX && col < currentY){
            int bottom_left_row = row;
            int bottom_left_column = col;
            while(bottom_left_row >= currentX && bottom_left_column <= currentY){
                ArrayList<Integer> bottom_left = new ArrayList<>();
                bottom_left.add(bottom_left_row);
                bottom_left.add(bottom_left_column);
                flip.add(bottom_left);
                bottom_left_row -= 1;
                bottom_left_column += 1;
            }
        }

        //bottom right
        if(row > currentX && col > currentY){
            int bottom_right_row = row;
            int bottom_right_column = col;
            while(bottom_right_row >= currentX && bottom_right_column >= currentY){
                ArrayList<Integer> bottom_right = new ArrayList<>();
                bottom_right.add(bottom_right_row);
                bottom_right.add(bottom_right_column);
                flip.add(bottom_right);
                bottom_right_row -= 1;
                bottom_right_column -= 1;
            }
        }
        return;
    }

	@Override
	public Player getCurrentTurn() {
		return pm.getCurrentPlayer();
	};
}
