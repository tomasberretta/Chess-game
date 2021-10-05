package australchess.piece;

public class DefaultPieceSetFactory implements PieceSetFactory{
    @Override
    public Piece[] makePieceSet(String color, boolean lowerCase) {
        Piece[] pieces = new Piece[16];
        for (int i = 0; i < pieces.length; i++) {
            if (i < 8) pieces[i] = new Pawn(color, lowerCase);
            if (i == 8 || i == 15) pieces[i] = new Rook(color, lowerCase);
            if (i == 9 || i == 14) pieces[i] = new Knight(color, lowerCase);
            if (i == 10 || i == 13) pieces[i] = new Bishop(color, lowerCase);
            if (i == 11) pieces[i] = new Queen(color, lowerCase);
            if (i == 12) pieces[i] = new King(color, lowerCase);
        }
        return pieces;
    }

    @Override
    public Piece[] makeSpecificPieceSet(String color, boolean lowerCase, Type[] types) {
        Piece[] pieces = new Piece[types.length];
        for (int i = 0; i < types.length; i++) {
            switch (types[i]){
                case KING -> pieces[i] = new King(color, lowerCase);
                case QUEEN -> pieces[i] = new Queen(color, lowerCase);
                case BISHOP -> pieces[i] = new Bishop(color, lowerCase);
                case KNIGHT -> pieces[i] = new Knight(color, lowerCase);
                case ROOK -> pieces[i] = new Rook(color, lowerCase);
                case PAWN -> pieces[i] = new Pawn(color, lowerCase);
            }
        }
        return pieces;
    }
}
