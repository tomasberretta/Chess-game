package australchess.cli.movegenerator;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;
import australchess.cli.Status;
import australchess.cli.movevalidator.ValidCapture;
import australchess.cli.piece.King;

import java.io.IOException;

public class KingMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();


    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece(), Status.PLAYING);
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!(((Math.abs(to.getLetter() - from.getLetter()) * Math.abs(to.getNumber() - from.getNumber())) == 1) ||
                ((Math.abs(to.getLetter() - from.getLetter()) * Math.abs(to.getNumber() - from.getNumber())) == 0))) throw new IOException("Illegal King move");

        ((King) from.getPiece()).setMoved(true);
        return moveResult;
    }
}
