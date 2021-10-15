package australchess.moverule.freeroute;

import australchess.board.Board;
import australchess.movevalidator.Move;
import australchess.piece.Type;

import java.util.Objects;

public class PawnFreeRoute implements FreeRoute {
    Type type = Type.PAWN;

    @Override
    public boolean validate(Move move, Board board) {
        int dirY = move.getTo().getNumber() > move.getFrom().getNumber() ? 1 : -1;
        int dirYx2 = move.getTo().getNumber() > move.getFrom().getNumber() ? 2 : -2;
        if(Math.abs(move.getFrom().getNumber() - move.getTo().getNumber()) == 2) {
            return board.getPosition( move.getFrom().getLetter() , move.getFrom().getNumber() + dirY).getPiece() == null && board.getPosition( move.getFrom().getLetter() , move.getFrom().getNumber() + dirYx2).getPiece() == null;
        }
        if(Math.abs(move.getFrom().getNumber() - move.getTo().getNumber()) == 1 && (move.getFrom().getLetter() == move.getTo().getLetter())) {
            return board.getPosition( move.getFrom().getLetter() , move.getFrom().getNumber() + dirY).getPiece() == null;
        }
        if (Math.abs(move.getFrom().getNumber() - move.getTo().getNumber()) == 1 && (move.getFrom().getLetter() != move.getTo().getLetter())){
            return !Objects.equals(move.getTo().getPiece().getColor(), move.getFrom().getPiece().getColor());
        }
        return true;
    }

    @Override
    public Type getType() {
        return type;
    }
}
