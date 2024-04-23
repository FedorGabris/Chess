package Controller;

import Model.*;
import View.Board;
import View.EndScreen;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static View.Board.*;

public class TurnController {

    private Grid grid;
    private final GridPane board;
    private PossibleCheck possibleCheck;
    private final String whitePlayerName;
    private final String blackPlayerName;
    private final HashMap<Button, Integer> buttonsMap;
    private ReplacePawnController replacePawnController;
    private final Board boardScreen;
    private boolean gameNotOver;
    private final ArrayList<Integer> turnPossibleMoves;

    public TurnController(String whitePlayerName, String blackPlayerName, GridPane board, HashMap<Button, Integer> buttonsMap, Board boardScreen) {
        this.whitePlayerName = whitePlayerName;
        this.blackPlayerName = blackPlayerName;
        this.board = board;
        this.buttonsMap = buttonsMap;
        this.replacePawnController = null;
        this.boardScreen = boardScreen;
        this.gameNotOver = true;
        turnPossibleMoves = new ArrayList<>();
        setButtons();
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public void setPossibleCheck(PossibleCheck possibleCheck) {
        this.possibleCheck = possibleCheck;
    }

    public int getWhiteKingPos() {
        return TurnData.getInstance().getWhiteKingPos();
    }

    public int getBlackKingPos() {
        return TurnData.getInstance().getBlackKingPos();
    }

    public boolean getWhiteMove() {
        return TurnData.getInstance().isWhiteMove();
    }

    public void setWhiteKingPos(int address) {
        TurnData.getInstance().setWhiteKingPos(address);
    }

    public void setBlackKing(int address) {
        TurnData.getInstance().setBlackKingPos(address);
    }

    public String getWhitePlayerName() {
        return whitePlayerName;
    }

    public String getBlackPlayerName() {
        return blackPlayerName;
    }

    private void setButtons() {
        for (Map.Entry<Button, Integer> entry : buttonsMap.entrySet()) {
            Button button = entry.getKey();
            Integer value = entry.getValue();
            int col = value % 10;
            int row = (value - col) / 10;
            button.setOnAction(actionEvent -> chooseFigure(row, col));
        }
    }

    public void chooseFigure(int col, int row) {
        if (!gameNotOver) {
            return;
        }
        if (this.replacePawnController != null) {
            replacePawnController.closeWindow();
            this.replacePawnController = null;
        }
        TurnData turnData = TurnData.getInstance();
        boolean whiteMove = turnData.isWhiteMove();
        int currentPosition = turnData.getCurrentFigure();
        ArrayList<Integer> possibleMoves = turnData.getPossibleMoves();
        Piece piece = grid.getPiece(row, col);
        int lastWhiteMove = turnData.getLastWhiteMove();
        int lastBlackMove = turnData.getLastBlackMove();
        int buttonValue = (row * 10) + col;

        if (currentPosition == 100) {
            if (piece != null) {
                if (whiteMove == piece.getIsWhite()) {
                    pointOutCurrentPiece(board, row, col);
                    piece.possibleMove(grid, possibleMoves, true);
                    if (piece instanceof King) {
                        if (piece.getHasMoved()/* && !turnData.isCheck()*/) {
                            King king = (King) piece;
                            king.possibleCastle(grid, possibleMoves);
                        }
                    }
                    else if (piece instanceof Pawn pawn) {
                        if (whiteMove && row == 3) {
                            pawn.possibleEnPassant(possibleMoves, lastWhiteMove, lastBlackMove, whiteMove, buttonValue);
                        }
                        else if (!whiteMove && row == 4) {
                            pawn.possibleEnPassant(possibleMoves, lastWhiteMove, lastBlackMove, whiteMove, buttonValue);
                        }
                    }
                    possibleMoveDisplayCall();
                    turnData.setCurrentFigure(buttonValue);
                }

            }
        }
        else if (currentPosition == buttonValue) {
            resetBoardDisplay(board);
            turnData.setCurrentFigure(100);
            possibleMoves.clear();
        }
        else if (possibleMoves.contains(buttonValue)) {
            int currentCol = currentPosition % 10;
            int currentRow = (currentPosition - currentCol) / 10;
            Piece deletingPiece = grid.getPiece(row, col);
            if (deletingPiece != null) {
                possibleCheck.removePiece(deletingPiece);
            }
            Piece movingPiece = grid.getPiece(currentRow, currentCol);
            if (movingPiece instanceof Pawn && (row == 7 || row == 0)) {
                possibleCheck.removePiece(movingPiece);
                this.replacePawnController = new ReplacePawnController(this, whiteMove, grid);
                replacePawnController.replacePawn(currentRow, currentCol, row, col);
            }
            else {
                finishMovement(row, col);
            }
        }
    }

    public void finishMovement(int newRow, int newCol) {
        TurnData turnData = TurnData.getInstance();
        boolean whiteMove = turnData.isWhiteMove();
        int current = turnData.getCurrentFigure();
        int currentCol = current % 10;
        int currentRow = (current - currentCol) / 10;
        int buttonValue = (newRow * 10) + newCol;
        ArrayList<Integer> possibleMoves = turnData.getPossibleMoves();
        Piece movingPiece = grid.getPiece(currentRow, currentCol);
        movingPiece.setNewRow(newRow);
        movingPiece.setNewCol(newCol);
        movingPiece.firstMove(true);
        removeImage(board, currentRow, currentCol);
        printImage(board, newRow, newCol, movingPiece.getLocation());
        if (movingPiece instanceof King) {
            int kingPos;
            if (whiteMove) {
                kingPos = getWhiteKingPos();
                setWhiteKingPos(buttonValue);
            }
            else {
                kingPos = getBlackKingPos();
                setBlackKing(buttonValue);
            }
            if (buttonValue == kingPos + 2) {
                grid.movePiece(newRow, 7, newRow, 5);
                Piece rook = grid.getPiece(newRow, 5);
                rook.setNewCol(5);
                rook.firstMove(true);
                removeImage(board, newRow, 7);
                printImage(board, newRow, 5, rook.getLocation());
            }
            else if (buttonValue == kingPos - 2) {
                grid.movePiece(newRow, 0, newRow, 3);
                Piece rook = grid.getPiece(newRow, 3);
                rook.setNewCol(3);
                rook.firstMove(true);
                removeImage(board, newRow, 0);
                printImage(board, newRow, 3, rook.getLocation());
            }
        }
        resetBoardDisplay(board);
        turnData.setCurrentFigure(100);
        int currentMove;
        if (movingPiece instanceof Pawn) {
            if (currentCol != newCol) {
                if (grid.getPiece(newRow, newCol) == null) {
                    Piece deleter = grid.getPiece(currentRow, newCol);
                    grid.setNull(currentRow, newCol);
                    possibleCheck.removePiece(deleter);
                    removeImage(board, currentRow, newCol);
                }
            }
            int rowDiff = newRow - currentRow;
            if (rowDiff == 2 || rowDiff == -2) {
                currentMove = buttonValue * 10;
            }
            else {
                currentMove = buttonValue;
            }
        }
        else {
            currentMove = buttonValue;
        }
        if (whiteMove) {
            turnData.setLastWhiteMove(currentMove);
        }
        else {
            turnData.setLastBlackMove(currentMove);
        }
        grid.movePiece(currentRow, currentCol, newRow, newCol);
        turnData.setWhiteMove(!turnData.isWhiteMove());
        possibleMoves.clear();
        if (turnData.isWhiteMove()) {
            ArrayList <Piece> allPieces = possibleCheck.getWhitePieces();
            for (Piece allyPiece : allPieces) {
                allyPiece.possibleMove(grid, turnPossibleMoves, true);
            }
        }
        else {
            ArrayList <Piece> allPieces = possibleCheck.getBlackPieces();
            for (Piece allyPiece : allPieces) {
                allyPiece.possibleMove(grid, turnPossibleMoves, true);
            }
        }
        if (turnPossibleMoves.isEmpty()) {
            this.gameNotOver = false;
            ArrayList <Integer> possibleStaleMate = new ArrayList<>();
            int kingPos;
            if (turnData.isWhiteMove()) {
                kingPos = turnData.getWhiteKingPos();
                ArrayList <Piece> allPieces = possibleCheck.getBlackPieces();
                for (Piece enemyPiece : allPieces) {
                    enemyPiece.possibleMove(grid, possibleStaleMate, true);
                }
            }
            else {
                kingPos = turnData.getBlackKingPos();
                ArrayList <Piece> allPieces = possibleCheck.getWhitePieces();
                for (Piece enemyPiece : allPieces) {
                    enemyPiece.possibleMove(grid, possibleStaleMate, true);
                }
            }
            EndScreen<Object> endScreen = new EndScreen<>(this);
            if (!possibleStaleMate.contains(kingPos)) {
                endScreen.endGameScreen(0);
            }
            else if (turnData.isWhiteMove()) {
                endScreen.endGameScreen(false);
            }
            else {
                endScreen.endGameScreen(true);
            }
            Stage board = boardScreen.getPrimaryStage();
            Stage endStage = endScreen.getEndStage();
            Button finishButton = endScreen.getFinishButton();
            finishButton.setOnAction(actionEvent -> {
                board.close();
                endStage.close();
            });
        }
        turnPossibleMoves.clear();
        if (grid.insufficientMaterial()) {
            this.gameNotOver = false;
            EndScreen<Object> endScreen = new EndScreen<>(this);
            endScreen.endGameScreen(1);
            Stage board = boardScreen.getPrimaryStage();
            Stage endStage = endScreen.getEndStage();
            Button finishButton = endScreen.getFinishButton();
            finishButton.setOnAction(actionEvent -> {
                board.close();
                endStage.close();
            });
        }
    }

    public void possibleMoveDisplayCall() {
        possibleMoveDisplay(board, TurnData.getInstance().getPossibleMoves());
    }

    public void displayPiece(int row, int col, String location) {
        printImage(board, row, col, location);
    }
}
