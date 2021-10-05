package australchess.movevalidator;

import australchess.board.Board;
import australchess.movegenerator.Move;

import java.io.IOException;

public interface MoveValidator {

    boolean validate (Move move, Board board) throws IOException;
}
