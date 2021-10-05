package australchess.cli;

import australchess.cli.board.*;
import australchess.cli.movegenerator.MoveResult;
import australchess.cli.piece.DefaultPieceSetFactory;
import australchess.cli.piece.Piece;
import australchess.cli.piece.PieceSetFactory;
import australchess.cli.piece.Type;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckRuleTest {
    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static IO io = new IO();

    public void setUp(){
        board = boardFactory.makeEmptyBoard();
    }

    @Test
    public void cannot_move_pawn_away_from_protecting_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',8).getPiece()).isNotNull();
    }

    @Test
    public void cannot_move_rook_away_from_protecting_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 5), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',8).getPiece()).isNotNull();

    }

    @Test
    public void cannot_move_bishop_away_from_protecting_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.BISHOP,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',8).getPiece()).isNotNull();

    }

    @Test
    public void cannot_move_knight_away_from_protecting_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KNIGHT,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        try {
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',8).getPiece()).isNotNull();

    }

    @Test
    public void cannot_move_king_to_check() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,7);
        boardFactory.addPieceToBoard(board, pieces[1], 3,7);
        try {
            board.movePiece(new ParsedPosition('d', 8),new ParsedPosition('e', 8), "Black");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");

        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('d',8).getPiece()).isNotNull();
    }

    @Test
    public void move_pawn_away_and_check_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.PAWN,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        MoveResult moveResult = null;
        try {
            moveResult = board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 5), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");
        }
        io.printBoard(new DefaultBoardPrinter(), board);

        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(moveResult).isNotNull();
    }

    @Test
    public void move_bishop_away_and_check_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.BISHOP,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        MoveResult moveResult = null;
        try {
            moveResult = board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('f', 5), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");
        }
        io.printBoard(new DefaultBoardPrinter(), board);
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(moveResult).isNotNull();
    }

    @Test
    public void move_rook_away_and_check_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        MoveResult moveResult = null;
        try {
            moveResult = board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('f', 4), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");
        }
        io.printBoard(new DefaultBoardPrinter(), board);
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(moveResult).isNotNull();
    }

    @Test
    public void move_knight_away_and_check_king() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KNIGHT,Type.QUEEN, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,4);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        MoveResult moveResult = null;
        try {
            moveResult = board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 3), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");
        }
        io.printBoard(new DefaultBoardPrinter(), board);
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(moveResult).isNotNull();
    }

    @Test
    public void move_normally_check_king_exists() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KNIGHT, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,4);
        boardFactory.addPieceToBoard(board, pieces[1], 3,7);

        io.printBoard(new DefaultBoardPrinter(), board);

        MoveResult moveResult = null;
        try {
            moveResult = board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 3), "White");
        } catch (IOException e) {
            assertThat(e.getMessage()).isEqualTo("This move would put player on check");
        }
        io.printBoard(new DefaultBoardPrinter(), board);
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(moveResult).isNotNull();
        assertThat(board.getPosition('e',8).getPiece()).isNotNull();

    }
}
