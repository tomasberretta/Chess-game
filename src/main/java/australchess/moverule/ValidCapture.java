package australchess.moverule;

import australchess.board.Board;
import australchess.movevalidator.Move;

public class ValidCapture implements MoveRule {
    @Override
    public boolean validate(Move move, Board board) {
        if(move.getTo().getPiece() == null) return true;
        return (!move.getFrom().getPiece().getColor().equals(move.getTo().getPiece().getColor()));
    }
}
