package australchess.movegenerator;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.BishopFreeRoute;
import australchess.movevalidator.CheckRule;
import australchess.movevalidator.FreeRoute;
import australchess.movevalidator.ValidCapture;

import java.io.IOException;

public class BishopMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    FreeRoute freeRoute = new BishopFreeRoute();
    CheckRule checkRule = new CheckRule();

    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece());
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!((Math.abs(to.getLetter() - from.getLetter()) == Math.abs(to.getNumber() - from.getNumber())))) throw new IOException("Illegal Bishop move");
        if(!freeRoute.validate(move, board)) throw new IOException("Route is not clear");
        if(!checkRule.validate(move,board)) throw new IOException("This move would put player on check");
        return moveResult;
    }
}
