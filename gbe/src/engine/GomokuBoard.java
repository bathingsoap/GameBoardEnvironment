import java.awt.*;
import java.util.ArrayList;

public class RenjuLogic {
    //    Color.getHSBColor(72, 77, 81); 黑色
//    Color.getHSBColor(246, 246, 246); 白色
    private int currentX;
    private int currentY;
    private int rows;
    private int cols;
    private Color currentColor;
    private ArrayList<ArrayList<GomokuPiece>> pieces;

    public RenjuLogic() {}

    private boolean boolPiece(int currentX, int currentY, Color currentColor) {
        return pieces.get(currentX).get(currentY) == currentColor;
    }

    public void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    public void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setCols(int cols) {
        this.rows = rows;
    }

    public void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    public void setPieces(ArrayList<ArrayList<Color>> pieces) {
        this.pieces = pieces;
    }

    public boolean boolWin() {
        int piecesInARow = 1;
        for (int x = currentX - 1; x > 0; x--) {
            if(boolPiece(x, currentY, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        for (int x = currentX + 1; x <= rows; x++) {
            if (boolPiece(x, currentY, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        if (piecesInARow >= 5) {
            return true;
        } else {
            piecesInARow = 1;
        }

        for (int y = currentY - 1; y >= 0; y--) {
            if (boolPiece(currentX, y, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        for (int y = currentY + 1; y <= rows; y++) {
            if (boolPiece(currentX, y, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        if (piecesInARow >= 5) {
            return true;
        } else {
            piecesInARow = 1;
        }

        for (int x = currentX + 1, y = currentY - 1; x <= cols && y >= 0; x++, y--) {
            if (boolPiece(x, y, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        for (int x = currentX - 1, y = currentY + 1; x >= 0 && y <= rows; x--, y++) {
            if (boolPiece(x, y, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        if (piecesInARow >= 5) {
            return true;
        } else {
            piecesInARow = 1;
        }

        for (int x = currentX - 1, y = currentY - 1; x >= 0 && y >= 0; x--, y--) {
            if (boolPiece(x, y, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        for (int x = currentX + 1, y = currentY + 1; x <= cols && y <= rows; x++, y++) {
            if (boolPiece(x, y, currentColor)) {
                piecesInARow++;
            } else {
                break;
            }
        }

        return piecesInARow >= 5;
    }


}
