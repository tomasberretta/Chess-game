package australchess.movevalidator;

import australchess.board.Board;
import australchess.board.BoardPosition;
import australchess.board.ParsedPosition;
import australchess.moverule.*;
import australchess.moverule.freeroute.*;
import australchess.moverule.piecemoverule.*;
import australchess.piece.Type;

import java.util.List;
import java.util.Objects;

public class DefaultMoveValidator implements MoveValidator {
    BoardLimitRule boardLimitRule = new BoardLimitRule();
    ValidCapture validCapture = new ValidCapture();
    List<FreeRoute> freeRoutes = List.of(new PawnFreeRoute(), new BishopFreeRoute(), new RookFreeRoute());
    List<PieceMoveRule> pieceMoveRules = List.of(new PawnMoveRule(), new BishopMoveRule(), new KnightMoveRule(), new RookMoveRule(), new KingMoveRule(), new QueenMoveRule());
    CheckRule checkRule = new CheckRule(this);
    CastleRule castleRule = new CastleRule();


    @Override
    public ValidateResult validate(BoardPosition from, BoardPosition to, Board board, String movingColor) {
        Move move = new Move(from, to);
        ValidateResult result = new ValidateResult(true, "");
        if(!boardLimitRule.validate(move,board)){
            result.setValid(false);
            result.setMessage("Invalid move position or no piece to move");
            return result;
        }
        if(!from.getPiece().getColor().equals(movingColor)){
            result.setValid(false);
            result.setMessage("Only your own pieces can be moved");
            return result;
        }
        if (!validCapture.validate(move,board)){
            result.setValid(false);
            result.setMessage("Own pieces cannot be taken");
            return result;
        }
        Type pieceType = from.getPiece().getType();
        for (PieceMoveRule pieceMoveRule : pieceMoveRules) {
            if(pieceMoveRule.getType() == pieceType) {
                if (!pieceMoveRule.validate(move,board)) {
                    result.setValid(false);
                    result.setMessage("Illegal "+ pieceType.toString() +" move");
                    return result;
                }
                break;
            }
        }
        if(pieceType == Type.QUEEN){
            if ((Objects.equals(from.getNumber(), to.getNumber()) && !Objects.equals(from.getLetter(), to.getLetter())) ||
                    (Objects.equals(from.getLetter(), to.getLetter()) && !Objects.equals(from.getNumber(), to.getNumber()))){
                for (FreeRoute freeRoute : freeRoutes) {
                    if(freeRoute.getType() == Type.ROOK) {
                        if (!freeRoute.validate(move,board)){
                            result.setValid(false);
                            result.setMessage("Route is not clear");
                            return result;
                        }
                        break;
                    }
                }
            }else{
                for (FreeRoute freeRoute : freeRoutes) {
                    if(freeRoute.getType() == Type.BISHOP) {
                        if (!freeRoute.validate(move,board)){
                            result.setValid(false);
                            result.setMessage("Route is not clear");
                            return result;
                        }
                        break;
                    }
                }
            }
        }else{
            for (FreeRoute freeRoute : freeRoutes) {
                if(freeRoute.getType() == pieceType) {
                    if (!freeRoute.validate(move,board)){
                        result.setValid(false);
                        result.setMessage("Route is not clear");
                        return result;
                    }
                    break;
                }
            }
        }
        if(pieceType == Type.KING && Math.abs(to.getLetter() - from.getLetter()) == 2){
            if(!castleRule.validate(move,board)){
                result.setValid(false);
                result.setMessage("Illegal Castling");
                return result;
            }else{
                if(!checkRule.validateForCastle(move,board)){
                    result.setValid(false);
                    result.setMessage("This move would put player on check");
                    return result;
                }
            }
        }else{
            if(!checkRule.validate(move,board)){
                result.setValid(false);
                result.setMessage("This move would put player on check");
                return result;
            }
        }
        return result;
    }

    @Override
    public ValidateResult validate(ParsedPosition from, ParsedPosition to, Board board, String movingColor) {
        return validate(board.getBoardPositionFromParsed(from), board.getBoardPositionFromParsed(to), board, movingColor);
    }
}
