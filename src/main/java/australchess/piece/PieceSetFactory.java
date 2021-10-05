package australchess.piece;

public interface PieceSetFactory {
    Piece[] makePieceSet(String color, boolean lowerCase);
    Piece[] makeSpecificPieceSet(String color, boolean lowerCase, Type[] types);
}
