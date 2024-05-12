package Model;

import java.util.ArrayList;

/**
 * This class keeps track of pieces that are on the board, also handles checks, when an attempt of movement is made, it
 * goes also through this class, and it adds all checks opponent can make into arrayList.
 */
public class PossibleCheck {
    private final ArrayList<Piece> whitePieces;
    private final ArrayList<Piece> blackPieces;
    private final ArrayList<Integer> possibleChecks;

    public PossibleCheck() {
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        possibleChecks = new ArrayList<>();
    }

    public void addPiece(Piece piece) {
        if (piece.getIsWhite()) {
            whitePieces.add(piece);
        } else {
            blackPieces.add(piece);
        }
    }

    public ArrayList<Piece> getWhitePieces() {
        return whitePieces;
    }

    public ArrayList<Piece> getBlackPieces() {
        return blackPieces;
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
