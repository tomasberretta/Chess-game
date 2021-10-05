package australchess.piece;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.movegenerator.MoveGenerator;
import australchess.movegenerator.MoveResult;
import lombok.Data;

import java.io.IOException;

@Data
public abstract class Piece {
    char id;
    String color;
    Type type;
    MoveGenerator moveGenerator;

    Piece (String color){
        this.color = color;
    }

    public MoveResult move (BoardPosition from, BoardPosition to, Board board, String movingColor) throws IOException {
        return moveGenerator.genMove(from, to, board, movingColor);
    }

}
