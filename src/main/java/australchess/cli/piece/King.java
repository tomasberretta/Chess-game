package australchess.cli.piece;

import australchess.cli.Type;

public class King extends Piece {
    public King(String color) {
        super(color);
        this.id = 'K';
        this.type = Type.KING;
    }
}
