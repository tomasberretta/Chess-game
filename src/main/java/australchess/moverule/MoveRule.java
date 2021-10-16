package australchess.moverule;

import australchess.board.Board;
import australchess.movevalidator.Move;


public interface MoveRule {

    boolean validate (Move move, Board board);
}
