package australchess.cli;

import australchess.cli.board.*;
import australchess.cli.piece.DefaultPieceSetFactory;
import australchess.cli.piece.Piece;
import australchess.cli.piece.PieceSetFactory;
import australchess.cli.piece.Type;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveGeneratorTest {

    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static IO io = new IO();

    public void setUp(){
        board = boardFactory.makeEmptyBoard();
    }

    @Test
    public void rook_can_move_straight() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        io.printBoard(new DefaultBoardPrinter(), board);
        try {
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('d',4).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('d', 4),new ParsedPosition('d', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('d',4).getPiece()).isNull();
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('d', 5),new ParsedPosition('e', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('d',5).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void rook_cannot_move_diagonally() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Rook move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        io.printBoard(new DefaultBoardPrinter(), board);
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Rook move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Rook move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',4).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Rook move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',4).getPiece()).isNull();
    }

    @Test
    public void pawn_can_move_straight() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 5,3);
        boardFactory.addPieceToBoard(board, pieces[2], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        io.printBoard(new DefaultBoardPrinter(), board);
        try {
            board.movePiece(new ParsedPosition('f', 5),new ParsedPosition('f', 3), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        assertThat(board.getPosition('f',3).getPiece()).isNotNull();
    }

    @Test
    public void bishop_can_move_diagonally() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.BISHOP,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',6).getPiece()).isNotNull();
        io.printBoard(new DefaultBoardPrinter(), board);
        try {
            board.movePiece(new ParsedPosition('f', 6),new ParsedPosition('e', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('f',6).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',4).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('f', 4),new ParsedPosition('e', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('f',4).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void bishop_cannot_move_straight() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.BISHOP,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Bishop move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Bishop move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',5).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Bishop move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Bishop move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
    }

    @Test
    public void knight_can_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KNIGHT,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        io.printBoard(new DefaultBoardPrinter(), board);
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',7).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('f', 7),new ParsedPosition('e', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('f',7).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('d',7).getPiece()).isNotNull();
        try {
            board.movePiece(new ParsedPosition('d', 7),new ParsedPosition('e', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("");
        }
        assertThat(board.getPosition('d',7).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void knight_cannot_move_straight_or_diagonally() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KNIGHT,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',5).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',6).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',4).getPiece()).isNull();
        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("Illegal Knight move");
        }
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',4).getPiece()).isNull();
    }

}
