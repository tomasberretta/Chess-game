package australchess.cli;

import australchess.board.*;
import australchess.movevalidator.DefaultMoveValidator;
import australchess.movevalidator.MoveValidator;
import australchess.movevalidator.ValidateResult;
import australchess.piece.DefaultPieceSetFactory;
import australchess.piece.Piece;
import australchess.piece.PieceSetFactory;
import australchess.piece.Type;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class CheckRuleTest {
    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static MoveValidator moveValidator = new DefaultMoveValidator();
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 4), board,  "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 5), board,  "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 6), board,  "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board,  "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('d', 8),new ParsedPosition('e', 8), board,  "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 8),new ParsedPosition('e', 8));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('d', 5), board, "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 5));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('f', 5), board, "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('f', 5));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('f', 4), board, "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('f', 4));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('c', 3), board, "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 3));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('c', 3), board, "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('c', 3));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('e',8).getPiece()).isNotNull();

    }
}
