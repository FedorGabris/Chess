package Model;

import Controller.TurnController;
import View.Board;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;

public abstract class Piece {
    private final boolean isWhite;
    private boolean hasMoved;
    private int row;
    private int col;
    private final String location;
    private final GridPane board;

    public Piece(boolean isWhite, boolean hasMoved, int row, int col, String location, GridPane board) {
        this.isWhite = isWhite;
        this.hasMoved = hasMoved;
        this.row = row;
        this.col = col;
        this.location = location;
        this.board = board;
        Board.printImage(board, row, col, location);
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getLocation() {
        return location;
    }

    public GridPane getBoard() {
        return board;
    }

    public abstract void possibleMove(Grid grid, ArrayList<Integer> possibleMoves);

    public void setNewRow(int newRow) {
        this.row = newRow;
    }

    public void setNewCol(int newCol) {
        this.col = newCol;
    }

    public void firstMove(boolean firstMove) {
        this.hasMoved = firstMove;
    }

    protected void possibleMoveAction(ArrayList<Integer> possibleMoves, int row, int col) {
        int possibleMove = (row * 10) + col;
        possibleMoves.add(possibleMove);
    }
}
