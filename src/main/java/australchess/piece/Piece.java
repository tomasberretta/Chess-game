package australchess.piece;

import lombok.Data;


@Data
public abstract class Piece {
    char id;
    String color;
    Type type;

    Piece (String color){
        this.color = color;
    }

}
