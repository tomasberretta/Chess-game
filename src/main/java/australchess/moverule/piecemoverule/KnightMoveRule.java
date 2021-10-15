package australchess.moverule.piecemoverule;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.Move;
import australchess.piece.Type;

public class KnightMoveRule implements PieceMoveRule {
    Type type = Type.KNIGHT;

    @Override
    public boolean validate(Move move, Board board){
        BoardPosition from = move.getFrom();
        BoardPosition to = move.getTo();
        return (Math.abs(to.getLetter() - from.getLetter()) * Math.abs(to.getNumber() - from.getNumber())) == 2;
    }

    @Override
    public Type getType() {
        return type;
    }
}
