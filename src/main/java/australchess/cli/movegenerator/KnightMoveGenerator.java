package australchess.cli.movegenerator;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;
import australchess.cli.Status;
import australchess.cli.movevalidator.CheckRule;
import australchess.cli.movevalidator.ValidCapture;

import java.io.IOException;

public class KnightMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    CheckRule checkRule = new CheckRule();

    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece());
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!((Math.abs(to.getLetter() - from.getLetter()) * Math.abs(to.getNumber() - from.getNumber())) == 2)) throw new IOException("Illegal Knight move");
        if(!checkRule.validate(move,board)) throw new IOException("This move would put player on check");
        return moveResult;
    }
}
