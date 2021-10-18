package australchess.cli;

import australchess.board.*;
import australchess.movevalidator.DefaultMoveValidator;
import australchess.movevalidator.MoveValidator;
import australchess.movevalidator.ValidateResult;
import australchess.piece.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CastleTest {
    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static MoveValidator moveValidator = new DefaultMoveValidator();

    public void setUp(){
        board = boardFactory.makeEmptyBoard();
    }

    @Test
    public void can_short_castle() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 7,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('g', 1), board,  "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('g', 1));
        }
        assertThat(board.getPosition('f',1).getPiece()).isNotNull();
        assertThat(board.getPosition('h',1).getPiece()).isNull();
        assertThat(board.getPosition('g',1).getPiece()).isNotNull();
        assertThat(board.getPosition('e',1).getPiece()).isNull();
    }

    @Test
    public void cannot_short_castle_due_to_own_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING, Type.BISHOP});
        boardFactory.addPieceToBoard(board, pieces[0], 7,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 5,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('g', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('g', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('f',1).getPiece()).isNotNull();
        assertThat(board.getPosition('h',1).getPiece()).isNotNull();
        assertThat(board.getPosition('g',1).getPiece()).isNull();
    }

    @Test
    public void cannot_short_castle_due_to_opponent_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING,Type.BISHOP});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);
        boardFactory.addPieceToBoard(board, pieces[1], 5,7);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 7,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('g', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('g', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('f',1).getPiece()).isNotNull();
        assertThat(board.getPosition('h',1).getPiece()).isNotNull();
        assertThat(board.getPosition('g',1).getPiece()).isNull();
    }

    @Test
    public void cannot_short_castle_due_to_no_rook() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING,Type.BISHOP});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);
        boardFactory.addPieceToBoard(board, pieces[1], 5,7);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.BISHOP, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 7,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('g', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('g', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('f',1).getPiece()).isNotNull();
        assertThat(board.getPosition('h',1).getPiece()).isNotNull();
        assertThat(board.getPosition('g',1).getPiece()).isNull();
    }

    @Test
    public void cannot_short_castle_due_to_check() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING, Type.QUEEN});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);
        boardFactory.addPieceToBoard(board, pieces[1], 6,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 7,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('g', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('g', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('g',8).getPiece()).isNotNull();
        assertThat(board.getPosition('h',1).getPiece()).isNotNull();
        assertThat(board.getPosition('g',1).getPiece()).isNull();
    }

    @Test
    public void can_long_castle() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 0,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('c', 1), board,  "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('c', 1));
        }
        assertThat(board.getPosition('d',1).getPiece()).isNotNull();
        assertThat(board.getPosition('a',1).getPiece()).isNull();
        assertThat(board.getPosition('c',1).getPiece()).isNotNull();
        assertThat(board.getPosition('e',1).getPiece()).isNull();
    }

    @Test
    public void cannot_long_castle_due_to_own_piece1() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING, Type.BISHOP});
        boardFactory.addPieceToBoard(board, pieces[0], 0,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 1,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('c', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('c', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('b',1).getPiece()).isNotNull();
        assertThat(board.getPosition('a',1).getPiece()).isNotNull();
        assertThat(board.getPosition('c',1).getPiece()).isNull();
    }

    @Test
    public void cannot_long_castle_due_to_own_piece2() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING, Type.BISHOP});
        boardFactory.addPieceToBoard(board, pieces[0], 0,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);
        boardFactory.addPieceToBoard(board, pieces[2], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('c', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('c', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('a',1).getPiece()).isNotNull();
        assertThat(board.getPosition('d',1).getPiece()).isNotNull();
        assertThat(board.getPosition('c',1).getPiece()).isNull();
    }

    @Test
    public void cannot_long_castle_due_to_no_rook() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.BISHOP, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 0,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('c', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('c', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('a',1).getPiece()).isNotNull();
        assertThat(board.getPosition('c',1).getPiece()).isNull();
    }

    @Test
    public void cannot_long_castle_due_to_opponent_rook() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING, Type.ROOK});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);
        boardFactory.addPieceToBoard(board, pieces[1], 0,7);


        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('c', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('c', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal Castling");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('a',1).getPiece()).isNotNull();
        assertThat(board.getPosition('c',1).getPiece()).isNull();
    }

    @Test
    public void cannot_long_castle_due_to_check() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING, Type.QUEEN});
        boardFactory.addPieceToBoard(board, pieces[0], 4,0);
        boardFactory.addPieceToBoard(board, pieces[1], 2,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.ROOK, Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 0,7);
        boardFactory.addPieceToBoard(board, pieces[1], 4,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 1),new ParsedPosition('c', 1), board,  "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 1),new ParsedPosition('c', 1));
        }
        assertThat(validateResult.getMessage()).isEqualTo("This move would put player on check");
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('a',1).getPiece()).isNotNull();
        assertThat(board.getPosition('c',8).getPiece()).isNotNull();
        assertThat(board.getPosition('c',1).getPiece()).isNull();
    }

}
