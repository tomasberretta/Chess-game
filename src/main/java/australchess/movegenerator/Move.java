package australchess.movegenerator;

import australchess.board.BoardPosition;
import lombok.Data;

@Data
public class Move {

    BoardPosition from;
    BoardPosition to;

    public Move(BoardPosition from, BoardPosition to) {
        this.from = from;
        this.to = to;
    }
}
