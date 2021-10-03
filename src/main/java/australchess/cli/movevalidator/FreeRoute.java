package australchess.cli.movevalidator;

import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;

public interface FreeRoute extends MoveValidator {

    boolean validate(Move move, Board board);
}
