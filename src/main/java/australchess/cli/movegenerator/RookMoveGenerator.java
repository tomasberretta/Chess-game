package australchess.cli.movegenerator;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;
import australchess.cli.Status;
import australchess.cli.movevalidator.FreeRoute;
import australchess.cli.movevalidator.RookFreeRoute;
import australchess.cli.movevalidator.ValidCapture;
import australchess.cli.piece.Rook;

import java.io.IOException;
import java.util.Objects;

public class RookMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    FreeRoute freeRoute = new RookFreeRoute();


    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece(), Status.PLAYING);
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!((Objects.equals(from.getNumber(), to.getNumber()) && !Objects.equals(from.getLetter(), to.getLetter())) ||
                (Objects.equals(from.getLetter(), to.getLetter()) && !Objects.equals(from.getNumber(), to.getNumber())))) throw new IOException("Illegal Rook move");
        if(!freeRoute.validate(move,board)) throw new IOException("Route is not clear");
        ((Rook) from.getPiece()).setMoved(true);
        return moveResult;
    }
}
