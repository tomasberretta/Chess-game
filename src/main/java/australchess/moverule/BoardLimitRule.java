package australchess.moverule;

import australchess.board.Board;
import australchess.movevalidator.Move;

import java.io.IOException;

public class BoardLimitRule implements MoveRule {
    @Override
    public boolean validate(Move move, Board board) {
        if(move.getFrom() == null) return false;
        if(move.getFrom().getPiece() == null) return false;
        return move.getTo() != null;
    }
}
