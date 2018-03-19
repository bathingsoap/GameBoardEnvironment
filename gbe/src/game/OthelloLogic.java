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
        //return true if no valid moves left
        for(int i = 0; i < gamestate.movesLeft.size(); i++){
            if(checkMove(gamestate.movesLeft.get(i).get(1), gamestate.movesLeft.get(i).get(0))){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkMove(int col, int row) {
        //return true if move is valid
        String player = (String) gamestate.getCurrentTurn().getPlayerPiece();
        String opponent = gamestate.getOtherColor(gamestate.getCurrentTurn().getPlayerPiece());
        boolean valid = false;

        //invalid if move is not on an empty space
        if(!gamestate.pieces[row][col].equals("")) {
            return false;
        }

        //check surrounding if false return false
        if(!checkSurrounding(row, col, opponent)){
            return false;
        };

        //check row
        if(0 <= (col-2) || (col+2) < 8) {
            if((col - 1) > -1) {
                if(gamestate.pieces[row][col-1].equals(opponent)) {
                    for (int i = col - 2; i >= 0; i--) {
                        if (gamestate.pieces[row][i].equals(player)) {
                            ArrayList<Integer> left_row = new ArrayList<>();
                            left_row.add(row);
                            left_row.add(i);
                            gamestate.row_moves.add(left_row);
                            valid = true;
                            break;
                        }
                        if (gamestate.pieces[row][i].equals("")){
                            break;
                        }
                    }
                }
            }
            if((col + 1) < 8) {
                if(gamestate.pieces[row][col+1].equals(opponent)){
                    for (int i = col+2; i < 8; i++) {
                        if(gamestate.pieces[row][i].equals(player)){
                            ArrayList<Integer> right_row = new ArrayList<>();
                            right_row.add(row);
                            right_row.add(i);
                            gamestate.row_moves.add(right_row);
                            valid = true;
                            break;
                        }
                        if (gamestate.pieces[row][i].equals("")){
                            break;
                        }
                    }
                }
            }
        }

        //check column
        if(0 <= row-2 || row+2 < 8){
            if((row - 1) > -1){
                if(gamestate.pieces[row-1][col].equals(opponent)) {
                    for (int i = row - 2; i >= 0; i--) {
                        if (gamestate.pieces[i][col].equals(player)) {
                            ArrayList<Integer> up_col = new ArrayList<>();
                            up_col.add(i);
                            up_col.add(col);
                            gamestate.col_moves.add(up_col);
                            valid = true;
                            break;
                        }
                        if (gamestate.pieces[i][col].equals("")){
                            break;
                        }
                    }
                }
            }
            if((row + 1) < 8){
                if(gamestate.pieces[row+1][col].equals(opponent)) {
                    for(int i = row+2; i < 8; i++){
                        if(gamestate.pieces[i][col].equals(player)){
                            ArrayList<Integer> down_col = new ArrayList<>();
                            down_col.add(i);
                            down_col.add(col);
                            gamestate.col_moves.add(down_col);
                            valid = true;
                            break;
                        }
                        if (gamestate.pieces[i][col].equals("")){
                            break;
                        }
                    }
                }
            }
        }

        //diagonal
        int top_left_row = row - 2;
        int top_left_column = col - 2;
        int top_right_row = row - 2;
        int top_right_column = col + 2;
        int bottom_left_row = row + 2;
        int bottom_left_column = col - 2;
        int bottom_right_row = row + 2;
        int bottom_right_column = col + 2;

        //check top left
        ArrayList<Integer> top_left = new ArrayList<>();
        if ((row - 2) >=0 && (col - 2) >=0){
            if((row - 1) > -1 && (col - 1) > -1){
                if(gamestate.pieces[row-1][col-1].equals(opponent)) {
                    while(top_left_row >=0 && top_left_column >=0){
                        if (gamestate.pieces[top_left_row][top_left_column].equals("")){
                            break;
                        }
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
            }
        }

        //check top right
        ArrayList<Integer> top_right = new ArrayList<>();
        if ((row - 2) >=0 && (col + 2) <8){
            if((row - 1) > -1 && (col + 1) < 8){
                if(gamestate.pieces[row-1][col+1].equals(opponent)) {
                    while(top_right_row >=0 && top_right_column <8){
                        if (gamestate.pieces[top_right_row][top_right_column].equals("")){
                            break;
                        }
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
            }
        }

        //bottom left
        ArrayList<Integer> bottom_left = new ArrayList<>();
        if ((row + 2) < 8 && (col - 2) >= 0){
            if((row + 1) < 8 && (col - 1) > -1){
                if(gamestate.pieces[row+1][col-1].equals(opponent)) {
                    while(bottom_left_row < 8 && bottom_left_column >= 0){
                        if (gamestate.pieces[bottom_left_row][bottom_left_column].equals("")){
                            break;
                        }
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
            }
        }

        //bottom right
        ArrayList<Integer> bottom_right = new ArrayList<>();
        if ((row + 2) < 8 && (col + 2) < 8){
            if((row + 1) < 8 && (col + 1) < 8){
                if(gamestate.pieces[row+1][col+1].equals(opponent)) {
                    while(bottom_right_row < 8 && bottom_right_column <8){
                        if (gamestate.pieces[bottom_right_row][bottom_right_column].equals("")){
                            break;
                        }
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
            }
        }
        return valid;
    }

    private boolean checkSurrounding(int row, int col, String opponent){
        //check left
        if((col - 1) > -1){
            if(gamestate.pieces[row][col-1].equals(opponent)){
                return true;
            }
        }
        //check right
        if((col + 1) < 8){
            if(gamestate.pieces[row][col+1].equals(opponent)){
                return true;
            }
        }
        //check up
        if((row - 1) > -1){
            if(gamestate.pieces[row-1][col].equals(opponent)){
                return true;            }
        }
        //check down
        if((row + 1) < 8){
            if(gamestate.pieces[row+1][col].equals(opponent)){
                return true;            }
        }
        //check up left
        if((row - 1) > -1 && (col - 1) > -1){
            if(gamestate.pieces[row-1][col-1].equals(opponent)){
                return true;            }
        }
        //check up right
        if((row - 1) > -1 && (col + 1) < 8){
            if(gamestate.pieces[row-1][col+1].equals(opponent)){
                return true;            }
        }
        //check bottom left
        if((row + 1) < 8 && (col - 1) > -1){
            if(gamestate.pieces[row+1][col-1].equals(opponent)){
                return true;            }
        }
        //check bottom right
        if((row + 1) < 8 && (col + 1) < 8){
            if(gamestate.pieces[row+1][col+1].equals(opponent)){
                return true;
            }
        }
        return false;
    }
}
