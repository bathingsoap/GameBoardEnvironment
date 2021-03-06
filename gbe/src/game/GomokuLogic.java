package game;

import java.awt.*;
import java.util.ArrayList;

import pieces.GomokuPiece;
import pieces.Piece;

class GomokuLogic {
    // Color.getHSBColor(72, 77, 81); 黑色
    // Color.getHSBColor(246, 246, 246); 白色
    private int currentX;
    private int currentY;
    private int rows;
    private int cols;
    private Color currentColor;
    private ArrayList<ArrayList<GomokuPiece>> pieces;

    GomokuLogic() {}

    private boolean boolPiece(int currentX, int currentY, Color currentColor) {
        return pieces.get(currentX).get(currentY).getColor().getAlpha() != new Color(0, 0, 0, 0).getAlpha() && pieces.get(currentX).get(currentY).getColor().getRGB() == currentColor.getRGB();
    }

    void setCurrentX(int currentX) {
        this.currentX = currentX;
    }

    void setCurrentY(int currentY) {
        this.currentY = currentY;
    }

    void setRows(int rows) {
        this.rows = rows;
    }

    void setCols(int cols) {
        this.cols = cols;
    }

    void setCurrentColor(Color currentColor) {
        this.currentColor = currentColor;
    }

    void setPieces(ArrayList<ArrayList<GomokuPiece>> pieces2) {
        this.pieces = pieces2;
    }

    boolean boolWin() {
        int piecesInARow = 1;
        for (int x = currentX - 1; x >= 0; x--) {
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
