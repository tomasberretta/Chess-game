package australchess.cli.movevalidator;

import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;

public class CheckRule implements MoveValidator {
    @Override
    public boolean validate(Move move, Board board) {
        return false;
    }
}
