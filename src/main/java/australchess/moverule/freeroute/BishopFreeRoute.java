package australchess.moverule.freeroute;

import australchess.board.Board;
import australchess.movevalidator.Move;
import australchess.piece.Type;

public class BishopFreeRoute implements FreeRoute {
    Type type = Type.BISHOP;

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

    @Override
    public Type getType() {
        return type;
    }
}
