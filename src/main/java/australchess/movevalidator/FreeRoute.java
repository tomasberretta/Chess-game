package australchess.movevalidator;

import australchess.board.Board;
import australchess.movegenerator.Move;

public interface FreeRoute extends MoveValidator {

    boolean validate(Move move, Board board);
}
