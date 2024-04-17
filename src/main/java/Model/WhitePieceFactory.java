package Model;

import Controller.TurnController;

public class WhitePieceFactory implements PieceFactory{
    @Override
    public Bishop createBishop(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Bishop(true, row, col, "/pieceImages/whiteBishop.png", turnController, possibleCheck);
    }
    public King createKing(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new King(true, row, col, "/pieceImages/whiteKing.png", turnController, possibleCheck);
    }
    public Knight createKnight(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Knight(true, row, col, "/pieceImages/whiteKnight.png", turnController, possibleCheck);
    }
    public Pawn createPawn(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Pawn(true, row, col, "/pieceImages/whitePawn.png", turnController, possibleCheck);
    }
    public Queen createQueen(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Queen(true, row, col, "/pieceImages/whiteQueen.png", turnController, possibleCheck);
    }
    public Rook createRook(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Rook(true, row, col, "/pieceImages/whiteRook.png", turnController, possibleCheck);
    }
}
