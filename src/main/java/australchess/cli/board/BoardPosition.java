package australchess.cli.board;

import australchess.cli.piece.Piece;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public @Data class BoardPosition {
    private @Getter @Setter Piece piece;
    private @Getter @Setter Integer number;
    private @Getter @Setter Character letter;

    public BoardPosition(Piece piece, Integer number, Character letter) {
        this.piece = piece;
        this.number = number;
        this.letter = letter;
    }
}
