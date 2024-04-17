package Model;

import Controller.TurnController;

public class BlackPieceFactory implements PieceFactory{
    @Override
    public Bishop createBishop(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Bishop(false, row, col, "/pieceImages/blackBishop.png", turnController, possibleCheck);
    }
    public King createKing(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new King(false, row, col, "/pieceImages/blackKing.png", turnController, possibleCheck);
    }
    public Knight createKnight(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Knight(false, row, col, "/pieceImages/blackKnight.png", turnController, possibleCheck);
    }
    public Pawn createPawn(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Pawn(false, row, col, "/pieceImages/blackPawn.png", turnController, possibleCheck);
    }
    public Queen createQueen(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Queen(false, row, col, "/pieceImages/blackQueen.png", turnController, possibleCheck);
    }
    public Rook createRook(int row, int col, TurnController turnController, PossibleCheck possibleCheck) {
        return new Rook(false, row, col, "/pieceImages/blackRook.png", turnController, possibleCheck);
    }
}
