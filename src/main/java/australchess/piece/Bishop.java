package australchess.piece;

import australchess.movegenerator.BishopMoveGenerator;

public class Bishop extends Piece {
    public Bishop(String color, boolean lowerCase) {
        super(color);
        this.id = 'B';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.BISHOP;
        moveGenerator = new BishopMoveGenerator();
    }
}
