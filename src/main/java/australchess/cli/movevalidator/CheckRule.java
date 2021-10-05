package australchess.cli.movevalidator;

import australchess.cli.CheckDetector;
import australchess.cli.DefaultCheckDetector;
import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;
import australchess.cli.piece.Piece;
import australchess.cli.piece.Type;

import java.util.Objects;

public class CheckRule implements MoveValidator {
    CheckDetector checkDetector = new DefaultCheckDetector();
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
}
