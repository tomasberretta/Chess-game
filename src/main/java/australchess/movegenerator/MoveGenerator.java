package australchess.movegenerator;

import australchess.board.Board;
import australchess.board.BoardPosition;

import java.io.IOException;

public interface MoveGenerator {
    MoveResult genMove(BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException;
}
