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

public class ValidCaptureTest {
    static Board board;
    static BoardFactory boardFactory = new DefaultBoardFactory();
    static PieceSetFactory pieceSetFactory = new DefaultPieceSetFactory();
    static MoveValidator moveValidator = new DefaultMoveValidator();

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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('d', 4), board, "White");
        assertThat(validateResult.isValid()).isFalse();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('d', 4));
        }
        assertThat(validateResult.getMessage()).isEqualTo("Own pieces cannot be taken");
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

        ValidateResult validateResult = moveValidator.validate(new ParsedPosition('e', 4),new ParsedPosition('e', 5), board, "White");
        assertThat(validateResult.isValid()).isTrue();
        if(validateResult.isValid()){
            board.movePiece(new ParsedPosition('e', 4),new ParsedPosition('e', 5));
        }
        assertThat(board.getPosition('e',4).getPiece()).isNull();
        assertThat(board.getPosition('e',5).getPiece()).isNotNull();
        assertThat(board.getPosition('e',5).getPiece().getType()).isEqualTo(Type.QUEEN);
    }

}
