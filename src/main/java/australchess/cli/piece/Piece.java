package australchess.cli.piece;

import australchess.cli.movegenerator.MoveGenerator;
import lombok.Data;

@Data
public abstract class Piece {
    char id;
    String color;
    Type type;
    MoveGenerator moveGenerator;

    Piece (String color){
        this.color = color;
    }

}
