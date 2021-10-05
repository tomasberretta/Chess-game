package australchess.cli;

import australchess.board.*;
import australchess.piece.DefaultPieceSetFactory;
import australchess.piece.Piece;
import australchess.piece.PieceSetFactory;
import australchess.piece.Type;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ValidCaptureTest {
    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static IO io = new IO();

    public void setUp(){
        board = boardFactory.makeEmptyBoard();
    }

    @Test
    public void cannot_take_own_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,4);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 4), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Own pieces cannot be taken");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('d',4).getPiece()).isNotNull();
    }

    @Test
    public void can_take_opponents_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,4);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('e', 5), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',5).getPiece().getType()).isEqualTo(Type.QUEEN);
    }

}
