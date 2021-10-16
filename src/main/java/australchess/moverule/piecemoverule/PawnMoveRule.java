package australchess.moverule.piecemoverule;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movevalidator.Move;
import australchess.piece.Pawn;
import australchess.piece.Piece;
import australchess.piece.Type;

public class PawnMoveRule implements PieceMoveRule {
    Type type = Type.PAWN;

    @Override
    public boolean validate(Move move, Board board){
        Piece piece = move.getFrom().getPiece();
        String movingColor = piece.getColor();
        BoardPosition from = move.getFrom();
        BoardPosition to = move.getTo();
        boolean moved = false;
        if(piece.getType() == Type.PAWN){
            moved = ((Pawn)piece).isMoved();
        }
        if(movingColor.equals("White")){
            return (!moved && (to.getNumber() == from.getNumber() + 1 || to.getNumber() == from.getNumber() + 2) && to.getLetter() == from.getLetter())
                    || (!moved && (to.getNumber() == from.getNumber() + 1) && (to.getLetter() == from.getLetter() + 1 || to.getLetter() == from.getLetter() - 1) && to.getPiece() != null)
                    || (moved && to.getNumber() == from.getNumber() + 1 && to.getLetter() == from.getLetter())
                    || (moved && to.getNumber() == from.getNumber() + 1 && (to.getLetter() == from.getLetter() + 1 || to.getLetter() == from.getLetter() - 1) && to.getPiece() != null);
        }else{
            return (!moved && (to.getNumber() == from.getNumber() - 1 || to.getNumber() == from.getNumber() - 2) && to.getLetter() == from.getLetter())
                    || (!moved && (to.getNumber() == from.getNumber() - 1) && (to.getLetter() == from.getLetter() + 1 || to.getLetter() == from.getLetter() - 1) && to.getPiece() != null)
                    || (moved && to.getNumber() == from.getNumber() - 1 && to.getLetter() == from.getLetter())
                    || (moved && to.getNumber() == from.getNumber() - 1 && (to.getLetter() == from.getLetter() + 1 || to.getLetter() == from.getLetter() - 1) && to.getPiece() != null);
        }
    }

    @Override
    public Type getType() {
        return type;
    }
}
