package australchess.cli.piece;

import australchess.cli.Type;

public abstract class Piece {
    char id;
    String color;
    Type type;

    Piece (String color){
        this.color = color;
    }
}
