package Model;

import java.util.ArrayList;

public class PossibleCheck {
    private final ArrayList<Piece> whitePieces;
    private final ArrayList<Piece> blackPieces;
    private final ArrayList<Integer> possibleChecks;

    public PossibleCheck() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        possibleChecks = new ArrayList<>();
    }

    public void addWhitePiece(Piece piece) {
        whitePieces.add(piece);
    }

    public void addBlackPiece(Piece piece) {
        blackPieces.add(piece);
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return whitePieces;
    }

    public void removePiece(Piece piece) {
        if (piece.getIsWhite()) {
            whitePieces.remove(piece);
        } else {
            blackPieces.remove(piece);
        }
    }

    public ArrayList<Integer> getPossibleChecks() {
        return possibleChecks;
    }

    public void checkTest(boolean whiteMove, Grid grid) {
        if (whiteMove) {
            for (Piece piece : blackPieces) {
                ((PieceFollower)piece).allChecks(grid, possibleChecks);
            }
        }
        else {
            for (Piece piece : whitePieces) {
                ((PieceFollower)piece).allChecks(grid, possibleChecks);
            }
        }
    }
}
