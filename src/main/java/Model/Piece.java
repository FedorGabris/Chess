package Model;

import Controller.TurnController;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public abstract class Piece {
    private final boolean isWhite;
    private boolean hasMoved;
    private int row;
    private int col;
    private final String location;
    private final PossibleCheck possibleCheck;
    private final TurnController turnController;

    public Piece(boolean isWhite, int row, int col, String location, TurnController turnController, PossibleCheck possibleCheck) {
        this.isWhite = isWhite;
        this.hasMoved = false;
        this.row = row;
        this.col = col;
        this.location = location;
        this.possibleCheck = possibleCheck;
        this.turnController = turnController;
        turnController.displayPiece(row, col, location);
    }

    public boolean getIsWhite() {
        return isWhite;
    }

    public boolean getHasMoved() {
        return !hasMoved;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getLocation() {
        return location;
    }

    public abstract void possibleMove(Grid grid, ArrayList<Integer> possibleMoves, boolean conditionalMoves);

    public void setNewRow(int newRow) {
        this.row = newRow;
    }

    public void setNewCol(int newCol) {
        this.col = newCol;
    }

    public void firstMove(boolean firstMove) {
        this.hasMoved = firstMove;
    }

    public TurnController getTurnController() {
        return turnController;
    }

    protected void possibleMoveAction(ArrayList<Integer> possibleMoves, int row, int col) {
        int possibleMove = (row * 10) + col;
        possibleMoves.add(possibleMove);
    }

    protected boolean CheckExists(Grid grid, int currentX, int currentY, int targetX, int targetY) {
        int newKingAddress = 100;
        int oldKingAddress = 100;
        boolean kingIsWhite = true;
        Piece holder = grid.getPiece(targetX, targetY);
        if (grid.getPiece(currentX, currentY) instanceof King) {
            newKingAddress = (targetX * 10) + targetY;
            kingIsWhite = grid.getPiece(currentX, currentY).isWhite;
            if (kingIsWhite) {
                oldKingAddress = turnController.getWhiteKingPos();
                turnController.setWhiteKingPos(newKingAddress);
            }
            else {
                oldKingAddress = turnController.getBlackKingPos();
                turnController.setBlackKing(newKingAddress);
            }
        }
        grid.movePiece(currentX, currentY, targetX, targetY);
        possibleCheck.checkTest(turnController.getWhiteMove(), grid);
        grid.movePiece(targetX, targetY, currentX, currentY);
        grid.setPiece(holder, targetX, targetY);
        ArrayList<Integer> checks = possibleCheck.getPossibleChecks();
        boolean whiteMove = turnController.getWhiteMove();
        int kingPos;
        if (whiteMove) {
            kingPos = turnController.getWhiteKingPos();
        }
        else {
            kingPos = turnController.getBlackKingPos();
        }
        boolean isCheck = checks.contains(kingPos);
        checks.clear();
        if (newKingAddress != 100) {
            if (kingIsWhite) {
                turnController.setWhiteKingPos(oldKingAddress);
            }
            else {
                turnController.setBlackKing(oldKingAddress);
            }
        }
        return !isCheck;
    }

    protected void checkForChecks (Grid grid, ArrayList<Integer> possibleChecks) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Future<ArrayList<Integer>> future = executor.submit(() -> {
            ArrayList<Integer> possibleMovesForPiece = new ArrayList<>();
            possibleMove(grid, possibleMovesForPiece, false);
            return possibleMovesForPiece;
        });

        try {
            ArrayList<Integer> possibleMovesForPiece = future.get();

            synchronized (possibleChecks) {
                possibleChecks.addAll(possibleMovesForPiece);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
