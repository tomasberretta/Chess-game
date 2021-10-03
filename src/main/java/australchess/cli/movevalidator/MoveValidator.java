package australchess.cli.movevalidator;

import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;

import java.io.IOException;

public interface MoveValidator {

    boolean validate (Move move, Board board) throws IOException;
}
