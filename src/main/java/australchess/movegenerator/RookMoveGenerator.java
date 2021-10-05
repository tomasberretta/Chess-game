package australchess.movegenerator;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.CheckRule;
import australchess.movevalidator.FreeRoute;
import australchess.movevalidator.RookFreeRoute;
import australchess.movevalidator.ValidCapture;
import australchess.piece.Rook;

import java.io.IOException;
import java.util.Objects;

public class RookMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    FreeRoute freeRoute = new RookFreeRoute();
    CheckRule checkRule = new CheckRule();

    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece());
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!((Objects.equals(from.getNumber(), to.getNumber()) && !Objects.equals(from.getLetter(), to.getLetter())) ||
                (Objects.equals(from.getLetter(), to.getLetter()) && !Objects.equals(from.getNumber(), to.getNumber())))) throw new IOException("Illegal Rook move");
        if(!freeRoute.validate(move,board)) throw new IOException("Route is not clear");
        if(!checkRule.validate(move,board)) throw new IOException("This move would put player on check");

        ((Rook) from.getPiece()).setMoved(true);
        return moveResult;
    }
}
