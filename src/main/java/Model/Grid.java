package Model;

import Controller.TurnController;
import javafx.scene.layout.GridPane;

public class Grid {
    private final Piece[][] grid;
    private PossibleCheck possibleCheck;

    public Grid() {
        grid = new Piece[8][8];
        possibleCheck = new PossibleCheck();
    }

    public void initializeGrid(TurnController turnController) {
        for (int i = 0; i < 8; i++) {
            Pawn blackPawn = new Pawn(false, false, 1, i, "/pieceImages/blackPawn.png", turnController, possibleCheck);
            grid[1][i] = blackPawn;
            possibleCheck.addBlackPiece(blackPawn);
            Pawn whitePawn = new Pawn(true, false, 6, i, "/pieceImages/whitePawn.png", turnController, possibleCheck);
            grid[6][i] = whitePawn;
            possibleCheck.addWhitePiece(whitePawn);
        }
        for (int i = 0; i < 8; i += 7) {
            Rook blackRook = new Rook(false, false, 0, i, "/pieceImages/blackRook.png", turnController, possibleCheck);
            grid[0][i] = blackRook;
            possibleCheck.addBlackPiece(blackRook);
            Rook whiteRook = new Rook(true, false, 7, i, "/pieceImages/whiteRook.png", turnController, possibleCheck);
            grid[7][i] = whiteRook;
            possibleCheck.addWhitePiece(whiteRook);
        }
        for (int i = 1; i < 7; i += 5) {
            Knight blackKnight= new Knight(false, false,  0, i, "/pieceImages/blackKnight.png", turnController, possibleCheck);
            grid[0][i] = blackKnight;
            possibleCheck.addBlackPiece(blackKnight);
            Knight whiteKnight = new Knight(true, false, 7, i, "/pieceImages/whiteKnight.png", turnController, possibleCheck);
            grid[7][i] = whiteKnight;
            possibleCheck.addWhitePiece(whiteKnight);
        }
        for (int i = 2; i < 6; i += 3) {
            Bishop blackBishop = new Bishop(false, false, 0, i, "/pieceImages/blackBishop.png", turnController, possibleCheck);
            grid[0][i] = blackBishop;
            possibleCheck.addBlackPiece(blackBishop);
            Bishop whiteBishop = new Bishop(true, false, 7, i, "/pieceImages/whiteBishop.png", turnController, possibleCheck);
            grid[7][i] = whiteBishop;
            possibleCheck.addWhitePiece(whiteBishop);
        }
        King blackKing = new King(false, false, 0, 4, "/pieceImages/blackKing.png", turnController, possibleCheck);
        grid[0][4] = blackKing;
        possibleCheck.addBlackPiece(blackKing);
        Queen blackQueen = new Queen(false, false, 0, 3, "/pieceImages/blackQueen.png", turnController, possibleCheck);
        grid[0][3] = blackQueen;
        possibleCheck.addBlackPiece(blackQueen);
        King whiteKing = new King(true, false, 7, 4, "/pieceImages/whiteKing.png", turnController, possibleCheck);
        grid[7][4] = whiteKing;
        possibleCheck.addWhitePiece(whiteKing);
        Queen whiteQueen = new Queen(true, false, 7, 3, "/pieceImages/whiteQueen.png", turnController, possibleCheck);
        grid[7][3] = whiteQueen;
        possibleCheck.addWhitePiece(whiteQueen);

        turnController.setPossibleCheck(possibleCheck);
    }

    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    public void movePiece(int currentRow, int currentCol, int newRow, int newCol) {
        grid[newRow][newCol] = grid[currentRow][currentCol];
    }

    public void setNull(int row, int col) {
        grid[row][col] = null;
    }

    public void setPiece(Piece piece, int row, int col) {
        grid[row][col] = piece;
    }
}
