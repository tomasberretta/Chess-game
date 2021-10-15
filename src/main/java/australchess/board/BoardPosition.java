package australchess.board;

import australchess.piece.Piece;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public @Data class BoardPosition {
    private @Getter @Setter Piece piece;
    private @Getter @Setter Integer number;
    private @Getter @Setter Character letter;

    public BoardPosition(Piece piece, Character letter, Integer number) {
        this.piece = piece;
        this.number = number;
        this.letter = letter;
    }

    public String getCoordinates(){
        return "("+ letter + "," + number + ")";
    }
}
