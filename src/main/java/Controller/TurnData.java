package Controller;

import java.util.ArrayList;

/**
 * This class holds all the info about current turn, it is build as singleton.
 */
public class TurnData {
    private static TurnData instance;
    private boolean whiteMove;
    private int whiteKingPos;
    private int blackKingPos;
    private int currentFigure;
    private final ArrayList<Integer> possibleMoves;
    private int lastWhiteMove;
    private int lastBlackMove;

    private TurnData() {
        whiteMove = true;
        blackKingPos = 4;
        whiteKingPos = 74;
        currentFigure = 100;
        possibleMoves = new ArrayList<>();
        lastWhiteMove = 99;
        lastBlackMove = 99;
    }

    public static TurnData getInstance() {
        if (instance == null) {
            instance = new TurnData();
        }
        return instance;
    }

    public boolean isWhiteMove() {
        return whiteMove;
    }

    public void setWhiteMove(boolean whiteMove) {
        this.whiteMove = whiteMove;
    }

    public int getWhiteKingPos() {
        return whiteKingPos;
    }

    public void setWhiteKingPos(int whiteKingPos) {
        this.whiteKingPos = whiteKingPos;
    }

    public int getBlackKingPos() {
        return blackKingPos;
    }

    public void setBlackKingPos(int blackKingPos) {
        this.blackKingPos = blackKingPos;
    }

    public int getCurrentFigure() {
        return currentFigure;
    }

    public void setCurrentFigure(int currentFigure) {
        this.currentFigure = currentFigure;
    }

    public int getLastWhiteMove() {
        return lastWhiteMove;
    }

    public int getLastBlackMove() {
        return lastBlackMove;
    }

    public void setLastWhiteMove(int lastWhiteMove) {
        this.lastWhiteMove = lastWhiteMove;
    }

    public void setLastBlackMove(int lastBlackMove) {
        this.lastBlackMove = lastBlackMove;
    }

    public ArrayList<Integer> getPossibleMoves() {
        return possibleMoves;
    }
}

