package australchess.cli.movegenerator;

import australchess.cli.Status;
import australchess.cli.piece.Piece;
import lombok.Data;

@Data
public class MoveResult {
    Move move;
    Piece pMoved;
    Piece pTook;
    Status status;

    public MoveResult(Move move, Piece pMoved, Piece pTook, Status status) {
        this.move = move;
        this.pMoved = pMoved;
        this.pTook = pTook;
        this.status = status;
    }
}
