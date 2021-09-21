package australchess.cli;

import australchess.cli.piece.Piece;

public interface PieceSetFactory {
    Piece[] makePieceSet(String color);
}
