package Controller;

import Model.Grid;
import View.ReplacePawnScreen;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * This class handles replacing pawn, when it enters its final row.
 */
public class ReplacePawnController {
    private final TurnController turnController;
    private final ReplacePawnScreen replacePawnScreen;
    private final boolean whiteMove;
    private final Grid grid;

    public ReplacePawnController(TurnController turnController, boolean whiteMove, Grid grid) {
        this.turnController = turnController;
        this.whiteMove = whiteMove;
        this.replacePawnScreen = new ReplacePawnScreen(whiteMove);
        this.grid = grid;
    }

    /**
     *In this method, there are event handlers that are set up in gui class, when, pressed, they create a given piece.
     */
    public void replacePawn(int currentRow, int currentCol, int row, int col) {
        EventHandler<ActionEvent> queenHandler = event -> {
            replacePawnScreen.closeStage();
            grid.setPiece(grid.createPiece(0, whiteMove, currentRow, currentCol, turnController), currentRow, currentCol);
            turnController.finishMovement(row, col);
        };

        EventHandler<ActionEvent> rookHandler = event -> {
            replacePawnScreen.closeStage();
            grid.setPiece(grid.createPiece(1, whiteMove, currentRow, currentCol, turnController), currentRow, currentCol);
            turnController.finishMovement(row, col);
        };

        EventHandler<ActionEvent> bishopHandler = event -> {
            replacePawnScreen.closeStage();
            grid.setPiece(grid.createPiece(2, whiteMove, currentRow, currentCol, turnController), currentRow, currentCol);
            turnController.finishMovement(row, col);
        };

        EventHandler<ActionEvent> knightHandler = event -> {
            replacePawnScreen.closeStage();
            grid.setPiece(grid.createPiece(3, whiteMove, currentRow, currentCol, turnController), currentRow, currentCol);
            turnController.finishMovement(row, col);
        };

        replacePawnScreen.pickPieceScreen(queenHandler, rookHandler, bishopHandler, knightHandler);
    }

    public void closeWindow() {
        replacePawnScreen.closeStage();
    }
}
