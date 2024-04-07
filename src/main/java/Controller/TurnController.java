package Controller;

import Model.Grid;
import Model.Piece;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

import static View.Board.*;

public class TurnController {

    private Grid grid;
    private final GridPane board;

    public TurnController(GridPane board) {
        this.board = board;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void chooseFigure(int col, int row) {
        TurnData turnData = TurnData.getInstance();
        boolean whiteMove = turnData.isWhiteMove();
        int current = turnData.getCurrentFigure();
        ArrayList<Integer> possibleMoves = turnData.getPossibleMoves();
        Piece piece = grid.getPiece(row, col);
        int buttonValue = (row * 10) + col;

        if (current == 100) {
            if (piece != null) {
                if (whiteMove == piece.getIsWhite()) {
                    pointCurrentPiece(board, row, col);
                    piece.possibleMove(grid, possibleMoves);
                    possibleMoveDisplayCall();
                    turnData.setCurrentFigure(buttonValue);
                }

            }
        }
        else if (current == buttonValue) {
            resetBoardDisplay(board);
            turnData.setCurrentFigure(100);
            possibleMoves.clear();
        }
        else if (possibleMoves.contains(buttonValue)) {
            int currentCol = current % 10;
            int currentRow = (current - currentCol) / 10;
            removeImage(board, currentRow, currentCol);
            Piece movingPiece = grid.getPiece(currentRow, currentCol);
            movingPiece.setNewRow(row);
            movingPiece.setNewCol(col);
            movingPiece.firstMove(true);
            printImage(board, row, col, movingPiece.getLocation());
            resetBoardDisplay(board);
            turnData.setCurrentFigure(100);
            grid.movePiece(currentRow, currentCol, row, col);
            turnData.setWhiteMove(!turnData.isWhiteMove());
            possibleMoves.clear();
        }
    }

    public void possibleMoveDisplayCall() {
        possibleMoveDisplay(board, TurnData.getInstance().getPossibleMoves());
    }
}
