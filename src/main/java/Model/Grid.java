package Model;

import Controller.TurnController;
import javafx.scene.layout.GridPane;

public class Grid {
    private final Piece[][] grid;

    public Grid() {
        grid = new Piece[8][8];
    }

    public void initializeGrid(GridPane board, TurnController turnController) {
        for (int i = 0; i < 8; i++) {
            grid[1][i] = new Pawn(false, false, 1, i, "/pieceImages/blackPawn.png", board);
            grid[6][i] = new Pawn(true, false, 6, i, "/pieceImages/whitePawn.png", board);
        }
        for (int i = 0; i < 8; i += 7) {
            grid[0][i] = new Rook(false, false, 0, i, "/pieceImages/blackRook.png", board);
            grid[7][i] = new Rook(true, false, 7, i, "/pieceImages/whiteRook.png", board);
        }
        for (int i = 1; i < 7; i += 5) {
            grid[0][i] = new Knight(false, false,  0, i, "/pieceImages/blackKnight.png", board);
            grid[7][i] = new Knight(true, false, 7, i, "/pieceImages/whiteKnight.png", board);
        }
        for (int i = 2; i < 6; i += 3) {
            grid[0][i] = new Bishop(false, false, 0, i, "/pieceImages/blackBishop.png", board);
            grid[7][i] = new Bishop(true, false, 7, i, "/pieceImages/whiteBishop.png", board);
        }
        grid[0][4] = new King(false, false, 0, 4, "/pieceImages/blackKing.png", board);
        grid[0][3] = new Queen(false, false, 0, 3, "/pieceImages/blackQueen.png", board);
        grid[7][4] = new King(true, false, 7, 4, "/pieceImages/whiteKing.png", board);
        grid[7][3] = new Queen(true, false, 7, 3, "/pieceImages/whiteQueen.png", board);
    }

    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    public void movePiece(int currentRow, int currentCol, int newRow, int newCol) {
        grid[newRow][newCol] = grid[currentRow][currentCol];
        grid[currentRow][currentCol] = null;
    }
}
