package australchess.piece;

import australchess.movegenerator.KnightMoveGenerator;

public class Knight extends Piece {
    public Knight(String color, boolean lowerCase) {
        super(color);
        this.id = 'N';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.KNIGHT;
        moveGenerator = new KnightMoveGenerator();
    }
}
