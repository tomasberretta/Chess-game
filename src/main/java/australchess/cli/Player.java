package australchess.cli;

import australchess.cli.piece.Piece;

public class Player {
    String playingColor;
    Piece[] pieceSet;
    boolean isChecked;

    Player (String playingColor, Piece[] pieceSet) {
        this.playingColor = playingColor;
        this.pieceSet = pieceSet;
        isChecked = false;
    }
}
