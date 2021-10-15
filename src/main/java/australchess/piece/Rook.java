package australchess.piece;

import lombok.Getter;
import lombok.Setter;

public class Rook extends Piece{
    @Getter
    @Setter
    boolean moved = false;

    public Rook(String color, boolean lowerCase) {
        super(color);
        this.id = 'R';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.ROOK;
    }
}
