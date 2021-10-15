package australchess.moverule.piecemoverule;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.Move;
import australchess.piece.Type;

import java.util.Objects;

public class RookMoveRule implements PieceMoveRule {
    Type type = Type.ROOK;
    @Override
    public boolean validate(Move move, Board board){
        BoardPosition from = move.getFrom();
        BoardPosition to = move.getTo();
        return (Objects.equals(from.getNumber(), to.getNumber()) && !Objects.equals(from.getLetter(), to.getLetter())) ||
                (Objects.equals(from.getLetter(), to.getLetter()) && !Objects.equals(from.getNumber(), to.getNumber()));
    }

    @Override
    public Type getType() {
        return type;
    }
}
