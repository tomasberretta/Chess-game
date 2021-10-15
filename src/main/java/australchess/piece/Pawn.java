package australchess.piece;

import lombok.Getter;
import lombok.Setter;


public class Pawn extends Piece{
    @Getter @Setter
    boolean moved;

    public Pawn(String color, boolean lowerCase) {
        super(color);
        this.id = 'P';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.PAWN;
        moved = false;
    }
}
