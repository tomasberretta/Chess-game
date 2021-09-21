package australchess.cli.piece;

import australchess.cli.Type;

public class Queen extends Piece {
    public Queen(String color) {
        super(color);
        this.id = 'Q';
        this.type = Type.QUEEN;
    }
}
