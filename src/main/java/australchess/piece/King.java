package australchess.piece;

import australchess.movegenerator.KingMoveGenerator;
import lombok.Getter;
import lombok.Setter;

public class King extends Piece {
    @Getter
    @Setter
    boolean moved = false;

    public King(String color, boolean lowerCase) {
        super(color);
        this.id = 'K';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.KING;
        moveGenerator = new KingMoveGenerator();
    }
}
