package australchess.movegenerator;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.CheckRule;
import australchess.movevalidator.FreeRoute;
import australchess.movevalidator.PawnFreeRoute;
import australchess.movevalidator.ValidCapture;
import australchess.piece.Pawn;

import java.io.IOException;

public class PawnMoveGenerator implements MoveGenerator {
    ValidCapture validCapture = new ValidCapture();
    FreeRoute freeRoute = new PawnFreeRoute();
    CheckRule checkRule = new CheckRule();

    @Override
    public MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        Move move = new Move(from, to);
        if(!from.getPiece().getColor().equals(movingColor)) throw new IOException("Only your own pieces can be moved");
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece());
        boolean moved = ((Pawn) from.getPiece()).isMoved();
        if (!validCapture.validate(move,board)) throw new IOException("Own pieces cannot be taken");
        if(movingColor.equals("White")){
            if(!((!moved && (to.getNumber() == from.getNumber()+1 || to.getNumber() == from.getNumber()+2) && to.getLetter() == from.getLetter())
                    ||(!moved && (to.getNumber() == from.getNumber()+1) && (to.getLetter() == from.getLetter()+1 || to.getLetter() == from.getLetter()-1) && to.getPiece() != null)
                    || (moved && to.getNumber() == from.getNumber()+1 && to.getLetter() == from.getLetter())
                    || (moved && to.getNumber() == from.getNumber()+1 && (to.getLetter() == from.getLetter()+1 || to.getLetter() == from.getLetter()-1) && to.getPiece() != null)
            ))throw new IOException("Illegal Pawn move");
        }else{
            if(!((!moved && (to.getNumber() == from.getNumber()-1 || to.getNumber() == from.getNumber()-2) && to.getLetter() == from.getLetter())
                    ||(!moved && (to.getNumber() == from.getNumber()-1) && (to.getLetter() == from.getLetter()+1 || to.getLetter() == from.getLetter()-1) && to.getPiece() != null)
                    || (moved && to.getNumber() == from.getNumber()-1 && to.getLetter() == from.getLetter())
                    || (moved && to.getNumber() == from.getNumber()-1 && (to.getLetter() == from.getLetter()+1 || to.getLetter() == from.getLetter()-1) && to.getPiece() != null)
            ))throw new IOException("Illegal Pawn move");
        }
        if(!freeRoute.validate(move, board)) throw new IOException("Route is not clear");
        if(!checkRule.validate(move,board)) throw new IOException("This move would put player on check");
        ((Pawn) from.getPiece()).setMoved(true);
        return moveResult;
    }
}
