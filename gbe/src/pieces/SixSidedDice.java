package pieces;

import java.awt.*;
import java.util.Random;

public class SixSidedDice implements Piece{

    int recentRoll;
    Random rand;

    int x, y;
    Color color;

    public SixSidedDice() {
        this.rand = new Random();
        this.recentRoll = rand.nextInt(6) + 1;
    }

    public int rollDie() {
        this.recentRoll = rand.nextInt(6) + 1;
        return this.recentRoll;
    }

    @Override
    public void createPiece() {
        this.rand = new Random();
        this.recentRoll = rand.nextInt(6) + 1;
    }

    public int getRecentRoll() {
        return this.recentRoll;
    }



    @Override
    public void createPiece(int x, int y, Color color) {
        this.rand = new Random();
        this.recentRoll = rand.nextInt(6) + 1;
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
