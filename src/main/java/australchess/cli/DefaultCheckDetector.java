package australchess.cli;

import australchess.cli.board.Board;

public class DefaultCheckDetector implements CheckDetector {
    @Override
    public boolean validate(Board boar, String movingColor) {
        return false;
    }
}
