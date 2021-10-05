package australchess.movevalidator;

import australchess.board.Board;
import australchess.movegenerator.Move;

public class ValidCapture implements MoveValidator {
    @Override
    public boolean validate(Move move, Board board) {
        if(move.getTo().getPiece() == null) return true;
        return (!move.getFrom().getPiece().getColor().equals(move.getTo().getPiece().getColor()));
    }
}
