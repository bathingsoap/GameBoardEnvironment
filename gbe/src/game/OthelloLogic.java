package game;
import engine.GameLogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class OthelloLogic extends GameLogic {
    OthelloState gamestate;

    public OthelloLogic(OthelloState state){
        super(state);
        this.gamestate = state;
    }

    @Override
    public boolean checkWinningState() {
        if (gamestate.movesLeft.isEmpty()){
            if (gamestate.pm.score.get(gamestate.p1) > gamestate.pm.score.get(gamestate.p2)) {
                JOptionPane.showMessageDialog(null, "Player 1 has won with a score of: " + gamestate.pm.score.get(gamestate.p1) + " . While " +
                        "Player 2 has a score of: " + gamestate.pm.score.get(gamestate.p2));
                return true;
            } else if (gamestate.pm.score.get(gamestate.p2) > gamestate.pm.score.get(gamestate.p1)) {
                JOptionPane.showMessageDialog(null, "Player 2 has won with a score of: " + gamestate.pm.score.get(gamestate.p2) + " . While " +
                        "Player 1 has a score of: " + gamestate.pm.score.get(gamestate.p1));
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Nobody won, it was a tie!");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkMove(int col, int row) {
        //return true if move is valid
        Object player = gamestate.getCurrentTurn().getPlayerPiece();
        boolean valid = false;
        System.out.println("row[" + row + "] col[" + col + "]");

        //invalid if move is not on an empty space
        if(!gamestate.pieces[row][col].equals("")) {
            return false;
        }

        //check surrounding if false return false
        if(!checkSurrounding(row, col)){
            return false;
        };

        //check row
        if(0 <= col && col < 8) {
            for (int i = col; i >= 0; i--) {
                if(gamestate.pieces[row][i].equals(player)){
                    ArrayList<Integer> left_row = new ArrayList<>();
                    left_row.add(row);
                    left_row.add(i);
                    gamestate.row_moves.add(left_row);
                    valid = true;
                    break;
                }
            }
            for (int i = col; i < 8; i++) {
                if(gamestate.pieces[row][i].equals(player)){
                    ArrayList<Integer> right_row = new ArrayList<>();
                    right_row.add(row);
                    right_row.add(i);
                    gamestate.row_moves.add(right_row);
                    valid = true;
                    break;
                }
            }
        }

        //check column
        if(0 <= row && row < 8){
            for(int i = row; i >=0; i--){
                if(gamestate.pieces[i][col].equals(player)){
                    ArrayList<Integer> up_col = new ArrayList<>();
                    up_col.add(i);
                    up_col.add(col);
                    gamestate.col_moves.add(up_col);
                    valid = true;
                    break;
                }
            }

            for(int i = row; i < 8; i++){
                if(gamestate.pieces[i][col].equals(player)){
                    ArrayList<Integer> down_col = new ArrayList<>();
                    down_col.add(i);
                    down_col.add(col);
                    gamestate.col_moves.add(down_col);
                    valid = true;
                    break;
                }
            }
        }

        //diagonal
        int top_left_row = row - 1;
        int top_left_column = col - 1;
        int top_right_row = row - 1;
        int top_right_column = col + 1;
        int bottom_left_row = row + 1;
        int bottom_left_column = col - 1;
        int bottom_right_row = row + 1;
        int bottom_right_column = col + 1;

        //check top left
        ArrayList<Integer> top_left = new ArrayList<>();
        if ((row - 1) >=0 && (col - 1) >=0){
            while(top_left_row >=0 && top_left_column >=0){
                if(gamestate.pieces[top_left_row][top_left_column].equals(player)){
                    top_left.add(top_left_row);
                    top_left.add(top_left_column);
                    gamestate.diagonal_moves.add(top_left);
                    valid = true;
                    break;
                }
                top_left_row -= 1;
                top_left_column -= 1;
            }
        }

        //check top right
        ArrayList<Integer> top_right = new ArrayList<>();
        if ((row - 1) >=0 && (col + 1) <8){
            while(top_right_row >=0 && top_right_column <8){
                if(gamestate.pieces[top_right_row][top_right_column].equals(player)){
                    top_right.add(top_right_row);
                    top_right.add(top_right_column);
                    gamestate.diagonal_moves.add(top_right);
                    valid = true;
                    break;
                }
                top_right_row -= 1;
                top_right_column += 1;
            }
        }

        //bottom left
        ArrayList<Integer> bottom_left = new ArrayList<>();
        if ((row + 1) < 8 && (col - 1) >= 0){
            while(bottom_left_row < 8 && bottom_left_column >= 0){
                if(gamestate.pieces[bottom_left_row][bottom_left_column].equals(player)){
                    bottom_left.add(bottom_left_row);
                    bottom_left.add(bottom_left_column);
                    gamestate.diagonal_moves.add(bottom_left);
                    valid = true;
                    break;
                }
                bottom_left_row += 1;
                bottom_left_column -= 1;
            }
        }

        //bottom right
        ArrayList<Integer> bottom_right = new ArrayList<>();
        if ((row + 1) < 8 && (col + 1) < 8){
            while(bottom_right_row < 8 && bottom_right_column <8){
                if(gamestate.pieces[bottom_right_row][bottom_right_column].equals(player)){
                    bottom_right.add(bottom_right_row);
                    bottom_right.add(bottom_right_column);
                    gamestate.diagonal_moves.add(bottom_right);
                    valid = true;
                    break;
                }
                bottom_right_row += 1;
                bottom_right_column += 1;
            }
        }
        return valid;
    }

    private boolean checkSurrounding(int row, int col){
        boolean surrounded = false;
        //check left
        if((col - 1) > -1){
            System.out.println("checking for left neighbor row[" + row + "] col[" + (col-1) + "]");
            if(gamestate.pieces[row][col-1].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("left " + gamestate.pieces[row][col-1]);
                return true;
            }
        }
        //check right
        if((col + 1) < 8){
            System.out.println("checking for right neighbor row[" + row + "] col[" + (col+1) + "]");
            if(gamestate.pieces[row][col+1].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("right " + gamestate.pieces[row][col+1]);
                return true;
            }
        }
        //check up
        if((row - 1) > -1){
            System.out.println("checking for up neighbor row[" +(row-1) + "] col[" + col + "]");
            if(gamestate.pieces[row-1][col].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("up " + gamestate.pieces[row-1][col]);
                return true;
            }
        }
        //check down
        if((row + 1) < 8){
            System.out.println("checking for down neighbor row[" +(row+1) + "] col[" + col + "]");
            if(gamestate.pieces[row+1][col].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("down " + gamestate.pieces[row+1][col]);
                return true;
            }
        }
        //check up left
        if((row - 1) > -1 && (col - 1) > -1){
            System.out.println("checking for up left neighbor row[" +(row-1) + "] col[" + (col-1) + "]");
            if(gamestate.pieces[row-1][col-1].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("up left " + gamestate.pieces[row-1][col-1]);
                return true;
            }
        }
        //check up right
        if((row - 1) > -1 && (col + 1) < 8){
            System.out.println("checking for up right neighbor row[" +(row-1) + "] col[" + (col+1) + "]");
            if(gamestate.pieces[row-1][col+1].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("up right " + gamestate.pieces[row-1][col+1]);
                return true;
            }
        }
        //check bottom left
        if((row + 1) < 8 && (col - 1) > -1){
            System.out.println("checking for bottom left neighbor row[" +(row+1) + "] col[" + (col-1) + "]");
            if(gamestate.pieces[row+1][col-1].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("bottom left " + gamestate.pieces[row+1][col-1]);
                return true;
            }
        }
        //check bottom right
        if((row + 1) < 8 && (col + 1) < 8){
            System.out.println("checking for bottom right neighbor row[" +(row+1) + "] col[" + (col+1) + "]");
            if(gamestate.pieces[row+1][col+1].equals("")){
                System.out.println("no neighbor");
            }
            else{
                System.out.println("bottom right " + gamestate.pieces[row+1][col+1]);
                return true;
            }
        }
        return surrounded;
    }
}
