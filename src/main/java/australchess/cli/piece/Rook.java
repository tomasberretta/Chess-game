package australchess.cli.piece;

import australchess.cli.Type;

public class Rook extends Piece{
    public Rook(String color) {
        super(color);
        this.id = 'R';
        this.type = Type.ROOK;
    }
}
