package australchess.cli.movegenerator;

import australchess.cli.Status;
import australchess.cli.piece.Piece;
import lombok.Data;

@Data
public class MoveResult {
    Move move;
    Piece pMoved;
    Piece pTook;

    public MoveResult(Move move, Piece pMoved, Piece pTook) {
        this.move = move;
        this.pMoved = pMoved;
        this.pTook = pTook;
    }
}
