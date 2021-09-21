package australchess.cli.piece;

import australchess.cli.Type;

public class Pawn extends Piece{
    public Pawn(String color) {
        super(color);
        this.id = 'P';
        this.type = Type.PAWN;
    }
}
