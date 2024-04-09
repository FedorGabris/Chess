package Model;

import Controller.TurnController;

import java.util.ArrayList;

public class Bishop extends Piece implements PieceFollower{
    public Bishop(boolean isWhite, boolean hasMoved, int row, int col, String location, TurnController turnController, PossibleCheck possibleCheck) {
        super(isWhite, hasMoved, row, col, location, turnController, possibleCheck);
    }

    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves, boolean conditionalMoves) {
        int[] dx = {1, 1, -1, -1};
        int[] dy = {1, -1, 1, -1};

        int x = this.getRow();
        int y = this.getCol();

        for (int i = 0; i < 4; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            while (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7 && grid.getPiece(tempX, tempY) == null) {
                if (conditionalMoves) {
                    if (super.CheckExists(grid, x, y, tempX, tempY)) {
                        super.possibleMoveAction(possibleMoves, tempX, tempY);
                    }
                }
                else {
                    super.possibleMoveAction(possibleMoves, tempX, tempY);
                }
                tempX += dx[i];
                tempY += dy[i];
            }
            if (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7) {
                Piece target = grid.getPiece(tempX, tempY);
                if (this.getIsWhite() != target.getIsWhite()) {
                    if (conditionalMoves) {
                        if (super.CheckExists(grid, x, y, tempX, tempY)) {
                            super.possibleMoveAction(possibleMoves, tempX, tempY);
                        }
                    }
                    else {
                        super.possibleMoveAction(possibleMoves, tempX, tempY);
                    }
                }
            }
        }
    }

    @Override
    public void allChecks(Grid grid, ArrayList<Integer> possibleChecks) {
        possibleMove(grid, possibleChecks, false);
    }
}
