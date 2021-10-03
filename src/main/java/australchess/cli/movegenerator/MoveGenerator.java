package australchess.cli.movegenerator;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;

import java.io.IOException;

public interface MoveGenerator {
    MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException;
}
