package australchess.cli.movegenerator;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;
import australchess.cli.Status;
import australchess.cli.movevalidator.BishopFreeRoute;
import australchess.cli.movevalidator.FreeRoute;
import australchess.cli.movevalidator.ValidCapture;

import java.io.IOException;

public class BishopMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    FreeRoute freeRoute = new BishopFreeRoute();

    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece(), Status.PLAYING);
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!((Math.abs(to.getLetter() - from.getLetter()) == Math.abs(to.getNumber() - from.getNumber())))) throw new IOException("Illegal Bishop move");
        if(!freeRoute.validate(move, board)) throw new IOException("Route is not clear");
        return moveResult;
    }
}
