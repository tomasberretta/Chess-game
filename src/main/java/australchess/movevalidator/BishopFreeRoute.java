package australchess.movevalidator;

import australchess.board.Board;
import australchess.movegenerator.Move;

public class BishopFreeRoute implements FreeRoute {
    @Override
    public boolean validate(Move move, Board board) {
        int srcY = move.getFrom().getNumber();
        int srcX = move.getFrom().getLetter();
        int destY = move.getTo().getNumber();
        int destX = move.getTo().getLetter();

        int dirX = destX>srcX ? 1 : -1;
        int dirY = destY>srcY ? 1 : -1;

        for (int i=1;i<Math.abs(destX-srcX);++i) {
            if (board.getPosition((char) (srcX+i*dirX), srcY+i*dirY).getPiece() != null) return false;
        }
        return true;
    }
}
