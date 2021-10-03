package australchess.cli.board;


import australchess.cli.piece.PieceSetFactory;
import australchess.cli.Player;
import australchess.cli.piece.Piece;

public interface BoardFactory {
    Board makeBoard(PieceSetFactory pieceSetFactory, Player[] players);
    Board makeEmptyBoard();
    void addPiecesToBoard(Board board, Piece[] pieces, String color);
    void addPieceToBoard(Board board, Piece piece, int i, int j);

}
