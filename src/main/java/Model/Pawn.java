package Model;

import Controller.TurnController;
import java.util.ArrayList;

public class Pawn extends Piece implements PieceFollower{

    public Pawn(boolean isWhite, int row, int col, String location, TurnController turnController, PossibleCheck possibleCheck) {
        super(isWhite, row, col, location, turnController, possibleCheck);
    }

    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves, boolean conditionalMoves) {
        int x = this.getRow();
        int y = this.getCol();

        if (this.getIsWhite()) {
            if (getHasMoved()) {
                if (grid.getPiece(x - 1, y) == null) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x - 1, y)) {
                            super.possibleMoveAction(possibleMoves, x - 1, y);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x - 1, y);
                    }
                }
                if (grid.getPiece(x - 1, y) == null && grid.getPiece(x - 2, y) == null) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x - 2, y)) {
                            super.possibleMoveAction(possibleMoves, x - 2, y);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x - 2, y);
                    }
                }
            }
            else {
                if (grid.getPiece(x - 1, y) == null) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x - 1, y)) {
                            super.possibleMoveAction(possibleMoves, x - 1, y);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x - 1, y);
                    }
                }
            }
            if (y - 1 >= 0) {
                Piece target = grid.getPiece(x - 1, y - 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x - 1, y - 1)) {
                            super.possibleMoveAction(possibleMoves, x - 1, y - 1);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x - 1, y - 1);
                    }
                }
            }
            if (y + 1 <= 7) {
                Piece target = grid.getPiece(x - 1, y + 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x - 1, y + 1)) {
                            super.possibleMoveAction(possibleMoves, x - 1, y + 1);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x - 1, y + 1);
                    }
                }
            }
        }
        else {
            if (getHasMoved()) {
                if (grid.getPiece(x + 1, y) == null) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x + 1, y)) {
                            super.possibleMoveAction(possibleMoves, x + 1, y);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x + 1, y);
                    }
                }
                if (grid.getPiece(x + 1, y) == null && grid.getPiece(x + 2, y) == null) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x + 2, y)) {
                            super.possibleMoveAction(possibleMoves, x + 2, y);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x + 2, y);
                    }
                }
            }
            else {
                if (grid.getPiece(x + 1, y) == null) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x + 1, y)) {
                            super.possibleMoveAction(possibleMoves, x + 1, y);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x + 1, y);
                    }
                }
            }
            if (y - 1 >= 0) {
                Piece target = grid.getPiece(x + 1, y - 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x + 1, y - 1)) {
                            super.possibleMoveAction(possibleMoves, x + 1, y - 1);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x + 1, y - 1);
                    }
                }
            }
            if (y + 1 <= 7) {
                Piece target = grid.getPiece(x + 1, y + 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, x + 1, y + 1)) {
                            super.possibleMoveAction(possibleMoves, x + 1, y + 1);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, x + 1, y + 1);
                    }
                }
            }
        }
    }

    @Override
    public void allChecks(Grid grid, ArrayList<Integer> possibleChecks) {
        super.checkForChecks(grid, possibleChecks);
    }
}
