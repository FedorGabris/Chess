package Model;

import Controller.TurnController;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Pawn extends Piece{

    public Pawn(boolean isWhite, boolean hasMoved, int row, int col, String location, GridPane board) {
        super(isWhite, hasMoved, row, col, location, board);
    }

    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves) {
        int x = this.getRow();
        int y = this.getCol();

        if (this.getIsWhite()) {
            if (!getHasMoved()) {
                if (grid.getPiece(x - 1, y) == null) {
                    super.possibleMoveAction(possibleMoves, x - 1, y);
                }
                if (grid.getPiece(x - 2, y) == null) {
                    super.possibleMoveAction(possibleMoves, x - 2, y);
                }
            }
            else {
                if (grid.getPiece(x - 1, y) == null) {
                    super.possibleMoveAction(possibleMoves, x - 1, y);
                }
            }
            if (y - 1 >= 0) {
                Piece target = grid.getPiece(x - 1, y - 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.possibleMoveAction(possibleMoves, x - 1, y - 1);
                }
            }
            if (y + 1 <= 7) {
                Piece target = grid.getPiece(x - 1, y + 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.possibleMoveAction(possibleMoves, x - 1, y + 1);
                }
            }
        }
        else {
            if (!getHasMoved()) {
                if (grid.getPiece(x + 1, y) == null) {
                    super.possibleMoveAction(possibleMoves, x + 1, y);
                }
                if (grid.getPiece(x + 2, y) == null) {
                    super.possibleMoveAction(possibleMoves, x + 2, y);
                }
            }
            else {
                if (grid.getPiece(x + 1, y) == null) {
                    super.possibleMoveAction(possibleMoves, x + 1, y);
                }
            }
            if (y - 1 >= 0) {
                Piece target = grid.getPiece(x + 1, y - 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.possibleMoveAction(possibleMoves, x + 1, y - 1);
                }
            }
            if (y + 1 <= 7) {
                Piece target = grid.getPiece(x + 1, y + 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.possibleMoveAction(possibleMoves, x + 1, y + 1);
                }
            }
        }
    }
}
