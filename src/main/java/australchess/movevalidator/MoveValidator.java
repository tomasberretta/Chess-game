package australchess.movevalidator;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.board.ParsedPosition;

public interface MoveValidator {
    ValidateResult validate(BoardPosition from, BoardPosition to, Board board, String movingColor);
    ValidateResult validate(ParsedPosition from, ParsedPosition to, Board board, String movingColor);
}
