package australchess.cli.movevalidator;

import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;
import australchess.cli.piece.Type;

public class ValidCapture implements MoveValidator {
    @Override
    public boolean validate(Move move, Board board) {
        if(move.getTo().getPiece() == null) return true;
        return (!move.getFrom().getPiece().getColor().equals(move.getTo().getPiece().getColor()));
    }
}
