package Model;

import Controller.TurnController;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Rook extends Piece{

    public Rook(boolean isWhite, boolean hasMoved, int row, int col, String location, GridPane board) {
        super(isWhite, hasMoved, row, col, location, board);
    }

    @Override
    public void possibleMove(Grid grid, ArrayList<Integer> possibleMoves) {
        int x = this.getRow();
        int y = this.getCol();

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < 4; i++) {
            int tempX = x + dx[i];
            int tempY = y + dy[i];

            while (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7 && grid.getPiece(tempX, tempY) == null) {
                super.possibleMoveAction(possibleMoves, tempX, tempY);
                tempX += dx[i];
                tempY += dy[i];
            }
            if (tempX >= 0 && tempX <= 7 && tempY >= 0 && tempY <= 7) {
                Piece target = grid.getPiece(tempX, tempY);
                if (this.getIsWhite() != target.getIsWhite()) {
                    super.possibleMoveAction(possibleMoves, tempX, tempY);
                }
            }
        }
    }
}
