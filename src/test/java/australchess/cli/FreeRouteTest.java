package australchess.cli;

import australchess.board.Board;
import australchess.board.BoardFactory;
import australchess.board.DefaultBoardFactory;
import australchess.board.ParsedPosition;
import australchess.piece.DefaultPieceSetFactory;
import australchess.piece.Piece;
import australchess.piece.PieceSetFactory;
import australchess.piece.Type;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeRouteTest {
    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static IO io = new IO();

    public void setUp(){
        board = boardFactory.makeEmptyBoard();
    }

    @Test
    public void cannot_move_over_diagonally() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN, Type.PAWN});
        int i = 3;
        int j = 3;
        for (Piece piece : pieces) {
            boardFactory.addPieceToBoard(board, piece, i,j);
            i++;
            if(i > 5){
                i = 3;
                j++;
            }
        }
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.QUEEN});
        for (Piece piece : pieces) {
            boardFactory.addPieceToBoard(board, piece, 4,4);
        }

        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('g', 6), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('g',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 6), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('c',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('g', 2), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('g',2).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 2), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('c',2).getPiece()).isNull();
    }

    @Test
    public void cannot_move_over_straight() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN,Type.PAWN, Type.PAWN});
        int i = 3;
        int j = 3;
        for (Piece piece : pieces) {
            boardFactory.addPieceToBoard(board, piece, i,j);
            i++;
            if(i > 5){
                i = 3;
                j++;
            }
        }
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.QUEEN});
        for (Piece piece : pieces) {
            boardFactory.addPieceToBoard(board, piece, 4,4);
        }
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('e', 6), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('e',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('e', 2), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('e',2).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 4), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('c',4).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('g', 4), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Route is not clear");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('g',4).getPiece()).isNull();
    }
}
