package australchess.cli.piece;

import australchess.cli.Type;

public class Knight extends Piece {
    public Knight(String color) {
        super(color);
        this.id = 'N';
        this.type = Type.KNIGHT;
    }
}
