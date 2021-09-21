package australchess.cli.piece;

import australchess.cli.Type;

public class Bishop extends Piece {
    public Bishop(String color) {
        super(color);
        this.id = 'B';
        this.type = Type.BISHOP;
    }
}
