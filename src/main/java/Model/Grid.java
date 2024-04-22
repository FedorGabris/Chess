package Model;

import Controller.TurnController;

public class Grid {
    private final Piece[][] grid;
    private final PossibleCheck possibleCheck;
    WhitePieceFactory newWhitePiece;
    BlackPieceFactory newBlackPiece;

    public Grid() {
        grid = new Piece[8][8];
        possibleCheck = new PossibleCheck();
    }

    public void initializeGrid(TurnController turnController) {
        this.newBlackPiece = new BlackPieceFactory();
        this.newWhitePiece = new WhitePieceFactory();

        for (int i = 0; i < 8; i++) {
            grid[1][i] = newBlackPiece.createPawn(1, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[1][i]);
            grid[6][i] = newWhitePiece.createPawn(6, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[6][i]);
        }
        for (int i = 0; i < 8; i += 7) {
            grid[0][i] = newBlackPiece.createRook(0, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[0][i]);
            grid[7][i] = newWhitePiece.createRook(7, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[7][i]);
        }
        for (int i = 1; i < 7; i += 5) {
            grid[0][i] = newBlackPiece.createKnight(0, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[0][i]);
            grid[7][i] = newWhitePiece.createKnight(7, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[7][i]);
        }
        for (int i = 2; i < 6; i += 3) {
            grid[0][i] = newBlackPiece.createBishop(0, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[0][i]);
            grid[7][i] = newWhitePiece.createBishop(7, i, turnController, possibleCheck);
            possibleCheck.addPiece(grid[7][i]);
        }
        grid[0][4] = newBlackPiece.createKing(0, 4, turnController, possibleCheck);
        possibleCheck.addPiece(grid[0][4]);
        grid[0][3] = newBlackPiece.createQueen(0, 3, turnController, possibleCheck);
        possibleCheck.addPiece(grid[0][3]);
        grid[7][4] = newWhitePiece.createKing(7, 4, turnController, possibleCheck);
        possibleCheck.addPiece(grid[7][4]);
        grid[7][3] = newWhitePiece.createQueen(7, 3, turnController, possibleCheck);
        possibleCheck.addPiece(grid[7][3]);

        turnController.setPossibleCheck(possibleCheck);
    }

    public Piece getPiece(int row, int col) {
        return grid[row][col];
    }

    public void movePiece(int currentRow, int currentCol, int newRow, int newCol) {
        grid[newRow][newCol] = grid[currentRow][currentCol];
        grid[currentRow][currentCol] = null;
    }

    public void setNull(int row, int col) {
        grid[row][col] = null;
    }

    public void setPiece(Piece piece, int row, int col) {
        grid[row][col] = piece;
    }

    public boolean isNull(int row, int col) {
        return grid[row][col] == null;
    }

    public Piece createPiece(int chosenPiece, boolean white, int row, int col, TurnController turnController) {
        setPiece(null, row, col);
        Piece newPiece;
        if(chosenPiece == 0) {
            if (white) {
                newPiece = newWhitePiece.createQueen(row, col, turnController, possibleCheck);
            }
            else {
                newPiece = newBlackPiece.createQueen(row, col, turnController, possibleCheck);
            }
        }
        else if(chosenPiece == 1) {
            if (white) {
                newPiece = newWhitePiece.createRook(row, col, turnController, possibleCheck);
            }
            else {
                newPiece = newBlackPiece.createRook(row, col, turnController, possibleCheck);
            }
        }
        else if(chosenPiece == 2) {
            if (white) {
                newPiece = newWhitePiece.createBishop(row, col, turnController, possibleCheck);
            }
            else {
                newPiece = newBlackPiece.createBishop(row, col, turnController, possibleCheck);
            }
        }
        else {
            if (white) {
                newPiece = newWhitePiece.createKnight(row, col, turnController, possibleCheck);
            }
            else {
                newPiece = newBlackPiece.createKnight(row, col, turnController, possibleCheck);
            }
        }
        possibleCheck.addPiece(newPiece);
        return newPiece;
    }
}
