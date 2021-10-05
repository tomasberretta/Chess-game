package australchess.cli.movegenerator;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;
import australchess.cli.Status;
import australchess.cli.movevalidator.*;

import java.io.IOException;
import java.util.Objects;

public class QueenMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    FreeRoute freeRoute1 = new RookFreeRoute();
    FreeRoute freeRoute2 = new BishopFreeRoute();
    CheckRule checkRule = new CheckRule();



    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece());
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(!(((Math.abs(to.getLetter() - from.getLetter()) == Math.abs(to.getNumber() - from.getNumber()))) || ((Objects.equals(from.getNumber(), to.getNumber()) && !Objects.equals(from.getLetter(), to.getLetter())) ||
                (Objects.equals(from.getLetter(), to.getLetter()) && !Objects.equals(from.getNumber(), to.getNumber()))))) throw new IOException("Illegal Queen move");
        if ((Objects.equals(from.getNumber(), to.getNumber()) && !Objects.equals(from.getLetter(), to.getLetter())) ||
                (Objects.equals(from.getLetter(), to.getLetter()) && !Objects.equals(from.getNumber(), to.getNumber()))){
            if(!freeRoute1.validate(move, board)) throw new IOException("Route is not clear");
        }else{
            if (!freeRoute2.validate(move, board)) throw new IOException("Route is not clear");
        }
//        if((!freeRoute1.validate(move, board)) || (!freeRoute2.validate(move, board)) ) throw new IOException("Route is not clear");
        if(!checkRule.validate(move,board)) throw new IOException("This move would put player on check");

        return moveResult;
    }
}
