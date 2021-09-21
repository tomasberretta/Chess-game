package australchess.cli;

import australchess.cli.piece.*;

public class DefaultPieceSetFactory implements PieceSetFactory{
    @Override
    public Piece[] makePieceSet(String color) {
        Piece[] pieces = new Piece[16];
        for (int i = 0; i < pieces.length; i++) {
            if (i < 8) pieces[i] = new Pawn(color);
            if (i == 8 || i == 15) pieces[i] = new Rook(color);
            if (i == 9 || i == 14) pieces[i] = new Knight(color);
            if (i == 10 || i == 13) pieces[i] = new Bishop(color);
            if (i == 11) pieces[i] = new Queen(color);
            if (i == 12) pieces[i] = new King(color);
        }
        return pieces;
    }
}
