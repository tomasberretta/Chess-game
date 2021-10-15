package australchess.moverule.piecemoverule;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.Move;
import australchess.piece.Type;

public class KingMoveRule implements PieceMoveRule {
    Type type = Type.KING;

    @Override
    public boolean validate(Move move, Board board){
        BoardPosition from = move.getFrom();
        BoardPosition to = move.getTo();
        if((Math.abs(to.getLetter() - from.getLetter()) == 0 && Math.abs(to.getNumber() - from.getNumber()) == 0)) return false;
        return Math.abs(to.getLetter() - from.getLetter()) <= 1 && Math.abs(to.getNumber() - from.getNumber()) <= 1;
    }

    @Override
    public Type getType() {
        return type;
    }
}
