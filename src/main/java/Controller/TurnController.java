package Controller;

import Model.Grid;
import Model.King;
import Model.Piece;
import Model.PossibleCheck;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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

    public TurnController(String whitePlayerName, String blackPlayerName, GridPane board, HashMap<Button, Integer> buttonsMap) {
        this.whitePlayerName = whitePlayerName;
        this.blackPlayerName = blackPlayerName;
        this.board = board;
        this.buttonsMap = buttonsMap;
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
        TurnData turnData = TurnData.getInstance();
        boolean whiteMove = turnData.isWhiteMove();
        int current = turnData.getCurrentFigure();
        ArrayList<Integer> possibleMoves = turnData.getPossibleMoves();
        Piece piece = grid.getPiece(row, col);
        int buttonValue = (row * 10) + col;

        if (current == 100) {
            if (piece != null) {
                if (whiteMove == piece.getIsWhite()) {
                    pointOutCurrentPiece(board, row, col);
                    piece.possibleMove(grid, possibleMoves, true);
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
            Piece deletingPiece = grid.getPiece(row, col);
            if (deletingPiece != null) {
                possibleCheck.removePiece(deletingPiece);
            }
            Piece movingPiece = grid.getPiece(currentRow, currentCol);
            movingPiece.setNewRow(row);
            movingPiece.setNewCol(col);
            movingPiece.firstMove(true);
            printImage(board, row, col, movingPiece.getLocation());
            if (movingPiece instanceof King) {
                if (whiteMove) {
                    setWhiteKingPos(buttonValue);
                }
                else {
                    setBlackKing(buttonValue);
                }
            }
            resetBoardDisplay(board);
            turnData.setCurrentFigure(100);
            grid.movePiece(currentRow, currentCol, row, col);
            grid.setNull(currentRow, currentCol);
            turnData.setWhiteMove(!turnData.isWhiteMove());
            possibleMoves.clear();
            ArrayList <Integer> turnPossibleMoves = new ArrayList<>();
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
                System.out.println("Check Mate!");
                if (turnData.isWhiteMove()) {
                    System.out.println(blackPlayerName + " won!");
                }
                else {
                    System.out.println(whitePlayerName + " won!");
                }
            }
        }
    }

    public void possibleMoveDisplayCall() {
        possibleMoveDisplay(board, TurnData.getInstance().getPossibleMoves());
    }

    public void displayPiece(int row, int col, String location) {
        printImage(board, row, col, location);
    }
}
