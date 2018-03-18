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
        Object player = gamestate.getCurrentTurn().getPlayerPiece();
        boolean valid = false;
        //check if space is empty
        if(!gamestate.pieces[row][col].equals("")) {
            return false;
        }
        //check row
        if(0 <= col && col < 8) {
            boolean existingNeighbors = false;
            for (int i = col; i >= 0; i--) {
                boolean validMoveCondition = gamestate.pieces[row][i - 1].equals(gamestate.getOtherColor(player)) ||
                        gamestate.pieces[row][i].equals(player);
                ArrayList<Integer> left_row = new ArrayList<>();
                if (validMoveCondition) {
                    existingNeighbors = true;
                    if (existingNeighbors && gamestate.pieces[row][i - 1].equals(gamestate.getOtherColor(player))) {
                        valid = true;
                        left_row.add(row);
                        left_row.add(i - 1);
                        gamestate.row_moves.add(left_row);
//                        break;
                    }
                    else { // if (existingNeighbors && gamestate.pieces[row][i].equals(player))
                        valid = true;
                        left_row.add(row);
                        left_row.add(i);
                        gamestate.row_moves.add(left_row);
                        break;
                    }
                }
                else {
                    break; // this break is to indicate that a move is invalid if the space has no neighbors.
                }
            }

            existingNeighbors = false;
            for (int i = col; i < 8; i++) {
                boolean validMoveCondition = gamestate.pieces[row][i + 1].equals(gamestate.getOtherColor(player)) ||
                        gamestate.pieces[row][i].equals(player);
                ArrayList<Integer> right_row = new ArrayList<>();
                if (validMoveCondition) {
                    existingNeighbors = true;
                    if (existingNeighbors && gamestate.pieces[row][i + 1].equals(gamestate.getOtherColor(player))) {
                        valid = true;
                        right_row.add(row);
                        right_row.add(i + 1);
                        gamestate.row_moves.add(right_row);
                        System.out.println(gamestate.row_moves);
//                        break;
                    }

                    else if (gamestate.pieces[row][i].equals(player)) { // never runs into this condition
                        System.out.println(i + 1 + ": i???");
                        valid = true;
                        right_row.add(row);
                        right_row.add(i);
                        gamestate.row_moves.add(right_row);
                        break;
                    }
                }
                else {
                    break; // this break is to indicate that a move is invalid if the space has no neighbors.
                }
            }
        }

        //check column
        if(0 <= row && row < 8){
            int piecesBetween = 0;
            for(int i = row; i >=0; i--){
                ArrayList<Integer> up_col = new ArrayList<>();
                if(gamestate.pieces[i][col].equals(player) && piecesBetween >= 1){
                    up_col.add(i);
                    up_col.add(col);
                    piecesBetween++;
                    gamestate.col_moves.add(up_col);
                    valid = true;
                    break;
                }
                piecesBetween++;
            }

            piecesBetween = 0;
            for(int i = row; i < 8; i++){
                ArrayList<Integer> down_col = new ArrayList<>();
                if(gamestate.pieces[i][col].equals(player) && piecesBetween >= 1){
                    down_col.add(i);
                    down_col.add(col);
                    piecesBetween++;
                    gamestate.col_moves.add(down_col);
                    valid = true;
                    break;
                }
                piecesBetween++;
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

}
