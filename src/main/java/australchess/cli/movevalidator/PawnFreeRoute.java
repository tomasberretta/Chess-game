package australchess.cli.movevalidator;

import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;

public class PawnFreeRoute implements FreeRoute {
    @Override
    public boolean validate(Move move, Board board) {
        int dirY = move.getTo().getNumber() > move.getFrom().getNumber() ? 1 : -1;
        if(Math.abs(move.getFrom().getNumber() - move.getTo().getNumber()) == 2) {
            return board.getPosition( move.getFrom().getLetter() , move.getFrom().getNumber() + dirY).getPiece() == null;
        }
        return true;
    }
}
