package australchess.board;


import australchess.piece.PieceSetFactory;
import australchess.cli.Player;
import australchess.piece.Piece;

public interface BoardFactory {
    Board makeBoard(PieceSetFactory pieceSetFactory, Player[] players);
    Board makeEmptyBoard();
    void addPiecesToBoard(Board board, Piece[] pieces, String color);
    void addPieceToBoard(Board board, Piece piece, int i, int j);

}
