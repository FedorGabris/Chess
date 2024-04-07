package Model;

import Controller.TurnController;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class King extends Piece{

    public King(boolean isWhite, boolean hasMoved, int row, int col, String location, GridPane board) {
        super(isWhite, hasMoved, row, col, location, board);
    }

    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves) {
        int[] dx = {1, -1, 1, -1, 1, -1, 0, 0};
        int[] dy = {1, -1, -1, 1, 0, 0, 1, -1};

        int x = this.getRow();
        int y = this.getCol();

        for (int i = 0; i < 8; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];
            if (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7 && grid.getPiece(tempX, tempY) == null) {
                super.possibleMoveAction(possibleMoves, tempX, tempY);
            }
            else if (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7) {
                Piece target = grid.getPiece(tempX, tempY);
                if (this.getIsWhite() != target.getIsWhite()) {
                    super.possibleMoveAction(possibleMoves, tempX, tempY);
                }
            }
        }
    }
}
