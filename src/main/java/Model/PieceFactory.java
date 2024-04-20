package Model;

import Controller.TurnController;

public interface PieceFactory { //abstract factory design patter
    Bishop createBishop(int row, int col, TurnController turnController, PossibleCheck possibleCheck);
    King createKing(int row, int col, TurnController turnController, PossibleCheck possibleCheck);
    Knight createKnight(int row, int col, TurnController turnController, PossibleCheck possibleCheck);
    Pawn createPawn(int row, int col, TurnController turnController, PossibleCheck possibleCheck);
    Queen createQueen(int row, int col, TurnController turnController, PossibleCheck possibleCheck);
    Rook createRook(int row, int col, TurnController turnController, PossibleCheck possibleCheck);
}
