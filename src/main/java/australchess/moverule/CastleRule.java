package australchess.moverule;

import australchess.board.Board;
import australchess.movevalidator.Move;
import australchess.piece.King;
import australchess.piece.Rook;
import australchess.piece.Type;

import java.util.Objects;


public class CastleRule implements MoveRule {
    @Override
    public boolean validate(Move move, Board board) {
        int dirX = move.getTo().getLetter() > move.getFrom().getLetter() ? 1 : -1;
        char letFrom = move.getFrom().getLetter();
        char letTo = move.getTo().getLetter();
        int numFrom = move.getFrom().getNumber();
        King king = (King) move.getFrom().getPiece();
        Rook rookToMove;

        if(dirX > 0){
            for (int i = letFrom + 1; i < letTo; i++) {
                if(board.getPosition((char) i, numFrom).getPiece() != null) return false;
            }
            if(board.getPosition((char) (letTo+1), numFrom).getPiece().getType() != Type.ROOK || !Objects.equals(board.getPosition((char) (letTo + 1), numFrom).getPiece().getColor(), king.getColor())) return false;
            else rookToMove = (Rook) board.getPosition((char) (letTo+1), numFrom).getPiece();
        }else{
            for (int i = letTo - 1; i < letFrom ; i++) {
                if(board.getPosition((char) i, numFrom).getPiece() != null) return false;
            }
            if(board.getPosition((char) (letTo - 2), numFrom).getPiece().getType() != Type.ROOK || !Objects.equals(board.getPosition((char) (letTo - 2), numFrom).getPiece().getColor(), king.getColor())) return false;
            else rookToMove = (Rook) board.getPosition((char) (letTo - 2), numFrom).getPiece();
        }
        return !king.isMoved() && !rookToMove.isMoved();
    }
}
