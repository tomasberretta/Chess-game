package australchess.moverule;

import australchess.board.CheckDetector;
import australchess.board.DefaultCheckDetector;
import australchess.board.Board;
import australchess.movevalidator.DefaultMoveValidator;
import australchess.movevalidator.Move;
import australchess.movevalidator.MoveValidator;
import australchess.piece.King;
import australchess.piece.Piece;
import australchess.piece.Rook;
import australchess.piece.Type;

import java.util.Objects;

public class CheckRule implements MoveRule {
    CheckDetector checkDetector;

    public CheckRule(MoveValidator moveValidator) {
        checkDetector = new DefaultCheckDetector(moveValidator);
    }

    @Override
    public boolean validate(Move move, Board board) {
        boolean result = true;
        String ownColor = move.getFrom().getPiece().getColor();
        String oppositeColor;
        if(Objects.equals(ownColor, "White")) oppositeColor = "Black";
        else oppositeColor = "White";
        Piece temp = move.getTo().getPiece();
        if (temp != null){
            if(temp.getType() == Type.KING && !Objects.equals(temp.getColor(), ownColor)) return result;
        }
        board.putPieceInBoardPosition(move.getFrom().getPiece(), move.getTo());
        board.putPieceInBoardPosition(null, move.getFrom());
        result = !checkDetector.validate(board, ownColor, oppositeColor);
        board.putPieceInBoardPosition(move.getTo().getPiece(), move.getFrom());
        board.putPieceInBoardPosition(temp, move.getTo());
        return result;
    }

    public boolean validateForCastle(Move move, Board board) {
        boolean result = true;
        char letTo = move.getTo().getLetter();
        int numFrom = move.getFrom().getNumber();
        int dirX = move.getTo().getLetter() > move.getFrom().getLetter() ? 1 : -1;
        King king = (King) move.getFrom().getPiece();
        String ownColor = king.getColor();
        String oppositeColor;
        if(Objects.equals(ownColor, "White")) oppositeColor = "Black";
        else oppositeColor = "White";
        Piece temp = move.getTo().getPiece();
        if (temp != null){
            if(temp.getType() == Type.KING && !Objects.equals(temp.getColor(), ownColor)) return result;
        }
        board.putPieceInBoardPosition(king, move.getTo());
        board.putPieceInBoardPosition(null, move.getFrom());
        Rook rookToMove;
        if(dirX > 0){
            rookToMove = (Rook) board.getPosition((char) (letTo+1), numFrom).getPiece();
            board.putPieceInBoardPosition(rookToMove, board.getPosition((char) (letTo-1), numFrom));
            board.putPieceInBoardPosition(null, board.getPosition((char) (letTo+1), numFrom));
        }else{
            rookToMove = (Rook) board.getPosition((char) (letTo - 2), numFrom).getPiece();
            board.putPieceInBoardPosition(rookToMove, board.getPosition((char) (letTo+1), numFrom));
            board.putPieceInBoardPosition(null, board.getPosition((char) (letTo-2), numFrom));
        }
        result = !checkDetector.validate(board, ownColor, oppositeColor);
        board.putPieceInBoardPosition(move.getTo().getPiece(), move.getFrom());
        board.putPieceInBoardPosition(temp, move.getTo());
        if(dirX > 0){
            rookToMove = (Rook) board.getPosition((char) (letTo-1), numFrom).getPiece();
            board.putPieceInBoardPosition(null, board.getPosition((char) (letTo-1), numFrom));
            board.putPieceInBoardPosition(rookToMove, board.getPosition((char) (letTo+1), numFrom));
        }else{
            rookToMove = (Rook) board.getPosition((char) (letTo+1), numFrom).getPiece();
            board.putPieceInBoardPosition(null, board.getPosition((char) (letTo+1), numFrom));
            board.putPieceInBoardPosition(rookToMove, board.getPosition((char) (letTo-2), numFrom));
        }
        return result;
    }
}
