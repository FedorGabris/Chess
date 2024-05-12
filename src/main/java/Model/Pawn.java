package Model;

import Controller.TurnController;
import java.util.ArrayList;

public class Pawn extends Piece implements PieceFollower{

    public Pawn(boolean isWhite, int row, int col, String location, TurnController turnController, PossibleCheck possibleCheck) {
        super(isWhite, row, col, location, turnController, possibleCheck);
    }

    /**
     * This method checks finds all moves this Pawn can make and adds them to the possibleMoves arrayList.
     */
    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves, boolean conditionalMoves) {
        int x = this.getRow();
        int y = this.getCol();

        if (this.getIsWhite()) {
            if (getHasMoved()) {
                if (grid.getPiece(x - 1, y) == null) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x - 1, y, possibleMoves);
                }
                if (grid.getPiece(x - 1, y) == null && grid.getPiece(x - 2, y) == null) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x - 2, y, possibleMoves);
                }
            }
            else {
                if (grid.getPiece(x - 1, y) == null) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x - 1, y, possibleMoves);
                }
            }
            if (y - 1 >= 0) {
                Piece target = grid.getPiece(x - 1, y - 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x - 1, y - 1, possibleMoves);
                }
            }
            if (y + 1 <= 7) {
                Piece target = grid.getPiece(x - 1, y + 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x - 1, y + 1, possibleMoves);
                }
            }
        }
        else {
            if (getHasMoved()) {
                if (grid.getPiece(x + 1, y) == null) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x + 1, y, possibleMoves);
                }
                if (grid.getPiece(x + 1, y) == null && grid.getPiece(x + 2, y) == null) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x + 2, y, possibleMoves);
                }
            }
            else {
                if (grid.getPiece(x + 1, y) == null) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x + 1, y, possibleMoves);
                }
            }
            if (y - 1 >= 0) {
                Piece target = grid.getPiece(x + 1, y - 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x + 1, y - 1, possibleMoves);
                }
            }
            if (y + 1 <= 7) {
                Piece target = grid.getPiece(x + 1, y + 1);
                if (target != null && this.getIsWhite() != target.getIsWhite()) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, x + 1, y + 1, possibleMoves);
                }
            }
        }
    }

    /**
     * This method handles possible EnPassant, if the move is possible, it adds it to the possibleMoves arrayList.
     */
    public void possibleEnPassant(ArrayList<Integer> possibleMoves, int lastWhiteMove, int lastBlackMove, boolean whiteMove, int buttonValue) {
        if (whiteMove) {
            if (buttonValue == lastWhiteMove) {
                if ((buttonValue - 1) * 10 == lastBlackMove) {
                    possibleMoves.add(buttonValue - 11);
                }
                if ((buttonValue + 1) * 10 == lastBlackMove) {
                    possibleMoves.add(buttonValue - 9);
                }
            }
        }
        else {
            if (buttonValue == lastBlackMove) {
                if ((buttonValue - 1) * 10 == lastWhiteMove) {
                    possibleMoves.add(buttonValue + 9);
                }
                if ((buttonValue + 1) * 10 == lastWhiteMove) {
                    possibleMoves.add(buttonValue + 11);
                }
            }
        }
    }

    @Override
    public void allChecks(Grid grid, ArrayList<Integer> possibleChecks) {
        super.checkForChecks(grid, possibleChecks);
    }
}
