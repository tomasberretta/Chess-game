package australchess.cli;

import australchess.cli.board.Board;

public interface CheckDetector {
    boolean validate(Board boar, String movingColor);
}
