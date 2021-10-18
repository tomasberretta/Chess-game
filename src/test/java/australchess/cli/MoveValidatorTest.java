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

public class MoveValidatorTest {

    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static MoveValidator moveValidator = new DefaultMoveValidator();

    public void setUp(){
        board = boardFactory.makeEmptyBoard();
    }

    @Test
    public void cannot_move_piece_to_unknown_board_position() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('z', 10), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Invalid move position or no piece to move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void cannot_move_position_with_no_piece_to_known_board_position() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('a', 5),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Invalid move position or no piece to move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void cannot_move_unknown_position_with_no_piece_to_known_board_position() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('z', 10),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Invalid move position or no piece to move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void cannot_move_opponents_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 6), board, "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Only your own pieces can be moved");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void rook_can_move_straight() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);
        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5), new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5), new ParsedPosition('e', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 4));
        }
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('d',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 4),new ParsedPosition('d', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 4),new ParsedPosition('d', 5));
        }
        assertThat(board.getPosition('d',4).getPiece()).isNull();
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 5),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 5),new ParsedPosition('e', 5));
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 6), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 6), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',6).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',4).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',4).getPiece()).isNull();
    }

    @Test
    public void rook_cannot_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.ROOK,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal ROOK move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',3).getPiece()).isNull();
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 5),new ParsedPosition('f', 3), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 5),new ParsedPosition('f', 3));
        }
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        assertThat(board.getPosition('f',3).getPiece()).isNotNull();
    }

    @Test
    public void pawn_cannot_move_double_straight_if_moved() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('e', 2), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('e', 2));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();
        assertThat(board.getPosition('e',2).getPiece()).isNull();
    }

    @Test
    public void pawn_can_move_diagonally_if_opponent_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 5,3);
        boardFactory.addPieceToBoard(board, pieces[2], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING, Type.BISHOP,Type.BISHOP});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);
        boardFactory.addPieceToBoard(board, pieces[1], 3,4);
        boardFactory.addPieceToBoard(board, pieces[2], 6,4);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('d',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 5),new ParsedPosition('g', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 5),new ParsedPosition('g', 4));
        }
        assertThat(board.getPosition('f',5).getPiece()).isNull();
        assertThat(board.getPosition('f',4).getPiece()).isNull();
        assertThat(board.getPosition('g',4).getPiece()).isNotNull();
    }

    @Test
    public void pawn_can_queen_when_reached_end_of_board() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,6);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 2),new ParsedPosition('e', 1), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 2),new ParsedPosition('e', 1));
        }
        assertThat(board.getPosition('e',2).getPiece()).isNull();
        assertThat(board.getPosition('e',1).getPiece()).isNotNull();
        assertThat(board.getPosition('e',1).getPiece().getType()).isEqualTo(Type.QUEEN);
    }

    @Test
    public void pawn_cannot_move_diagonally_if_no_piece() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 5,3);
        boardFactory.addPieceToBoard(board, pieces[2], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('d',4).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 5),new ParsedPosition('g', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 5),new ParsedPosition('g', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('f',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',4).getPiece()).isNull();
        assertThat(board.getPosition('g',4).getPiece()).isNull();
    }

    @Test
    public void pawn_cannot_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.PAWN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal PAWN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',3).getPiece()).isNull();
    }

    @Test
    public void bishop_can_move_diagonally() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.BISHOP,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 6), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',6).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 6),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 6),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('f',6).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 4),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 4),new ParsedPosition('e', 5));
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 6), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',6).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();
    }

    @Test
    public void bishop_cannot_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.BISHOP,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal BISHOP move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',3).getPiece()).isNull();
    }

    @Test
    public void knight_can_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KNIGHT,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',7).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 7),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 7),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('f',7).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 7), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('d',7).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 7),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 7),new ParsedPosition('e', 5));
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 6), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',6).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',4).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 6), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 6), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 6));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',6).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',4).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 4), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KNIGHT move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',4).getPiece()).isNull();
    }

    @Test
    public void queen_can_move_straight_and_diagonally() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.QUEEN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5), new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5), new ParsedPosition('e', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 4));
        }
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('d',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 4),new ParsedPosition('d', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 4),new ParsedPosition('d', 5));
        }
        assertThat(board.getPosition('d',4).getPiece()).isNull();
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 5),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 5),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 6), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',6).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 6),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 6),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('f',6).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 4),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 4),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('f',4).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void queen_cannot_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.QUEEN,Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);
        boardFactory.addPieceToBoard(board, pieces[1], 4,0);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal QUEEN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal QUEEN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal QUEEN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal QUEEN move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',3).getPiece()).isNull();
    }

    @Test
    public void king_can_move_straight_and_diagonally_1() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5), new ParsedPosition('e', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5), new ParsedPosition('e', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('d', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 4));
        }
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('d',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 4),new ParsedPosition('d', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 4),new ParsedPosition('d', 5));
        }
        assertThat(board.getPosition('d',4).getPiece()).isNull();
        assertThat(board.getPosition('d',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('d', 5),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('d', 5),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('d',5).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 6), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 6));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',6).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 6),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 6),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('f',6).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 4), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 4));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('f',4).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('f', 4),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('f', 4),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('f',4).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
    }

    @Test
    public void king_cannot_move_in_L() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('f', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('f', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('f',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('d', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('d', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('d',3).getPiece()).isNull();
    }

    @Test
    public void king_cannot_move_more_than_1() {
        setUp();
        Piece[] pieces = pieceSetFactory.makeSpecificPieceSet("Black", true, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 4,3);

        pieces = pieceSetFactory.makeSpecificPieceSet("White", false, new Type[]{Type.KING});
        boardFactory.addPieceToBoard(board, pieces[0], 3,7);

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 6), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 6));
        }
        assertThat(board.getPosition('e',5).getPiece()).isNull();
        assertThat(board.getPosition('e',6).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 6),new ParsedPosition('e', 5), board, "Black");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 6),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('e',6).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('g', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('g', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('g',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('c', 7), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('c', 7));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('c',7).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('c', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('c', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('c',5).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('c', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('c', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('c',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('e', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('e', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('g', 3), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('g', 3));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('g',3).getPiece()).isNull();

        validateResult = moveValidator.validate(new ParsedPosition('e', 5),new ParsedPosition('g', 5), board, "Black");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 5),new ParsedPosition('g', 5));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Illegal KING move");
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('g',5).getPiece()).isNull();
    }

}
