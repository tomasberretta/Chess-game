package australchess.moverule.piecemoverule;

import australchess.board.Board;
import australchess.moverule.MoveRule;
import australchess.movevalidator.Move;
import australchess.piece.Type;

public interface PieceMoveRule extends MoveRule {
    boolean validate(Move move, Board board);
    Type getType();
}
