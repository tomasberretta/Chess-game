package australchess.moverule;

import australchess.board.Board;
import australchess.movevalidator.Move;

import java.io.IOException;

public interface MoveRule {

    boolean validate (Move move, Board board) throws IOException;
}
