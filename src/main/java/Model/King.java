package Model;

import Controller.TurnController;
import java.util.ArrayList;

public class King extends Piece implements PieceFollower{

    public King(boolean isWhite, int row, int col, String location, TurnController turnController, PossibleCheck possibleCheck) {
        super(isWhite, row, col, location, turnController, possibleCheck);
    }

    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves, boolean conditionalMoves) {
        int[] dx = {1, -1, 1, -1, 1, -1, 0, 0};
        int[] dy = {1, -1, -1, 1, 0, 0, 1, -1};

        int x = this.getRow();
        int y = this.getCol();

        for (int i = 0; i < 8; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7 && grid.getPiece(tempX, tempY) == null) {
                super.gridMovementPart(conditionalMoves, grid, x, y, tempX, tempY, possibleMoves);
            }
            else if (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7) {
                Piece target = grid.getPiece(tempX, tempY);
                if (this.getIsWhite() != target.getIsWhite()) {
                    super.gridMovementPart(conditionalMoves, grid, x, y, tempX, tempY, possibleMoves);
                }
            }
        }
    }

    public void possibleCastle(Grid grid, ArrayList<Integer> possibleMoves) {
        int row = getRow();
        int col = getCol();
        Piece rightRook = grid.getPiece(row, 7);
        Piece leftRook = grid.getPiece(row, 0);
        if (rightRook != null && rightRook.getHasMoved() && grid.isNull(row, 5) && grid.isNull(row, 6)) {
            boolean check1 = super.CheckExists(grid, row, col, row, col);
            boolean check2 = super.CheckExists(grid, row, col, row, 5);
            boolean check3 = super.CheckExists(grid, row, col, row, 6);
            if (check1 && check2 && check3) {
                super.possibleMoveAction(possibleMoves, row, 6);
            }
        }
        if (leftRook != null && leftRook.getHasMoved() && grid.isNull(row, 3) && grid.isNull(row, 2) && grid.isNull(row, 1)) {
            boolean check1 = super.CheckExists(grid, row, col, row, col);
            boolean check2 = super.CheckExists(grid, row, col, row, 3);
            boolean check3 = super.CheckExists(grid, row, col, row, 2);
            if (check1 && check2 && check3) {
                super.possibleMoveAction(possibleMoves, row, 2);
            }
        }
    }

    @Override
    public void allChecks(Grid grid, ArrayList<Integer> possibleChecks) {
        super.checkForChecks(grid, possibleChecks);
    }
}
