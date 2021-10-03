package australchess.cli.movevalidator;

import australchess.cli.board.Board;
import australchess.cli.movegenerator.Move;

public class RookFreeRoute implements FreeRoute {
    @Override
    public boolean validate(Move move, Board board) {
        int numFrom = move.getFrom().getNumber();
        int numTo = move.getTo().getNumber();
        char letFrom = move.getFrom().getLetter();
        char letTo = move.getTo().getLetter();

        if(letFrom == letTo) {
            if(numTo - numFrom > 0){
                for (int i = numFrom + 1; i < numTo; i++) {
                    if(board.getPosition(letFrom, i).getPiece() != null) return false;
                }
            }else{
                for (int i = numTo + 1; i < numFrom; i++) {
                    if(board.getPosition(letFrom, i).getPiece() != null) return false;
                }
            }
        } else {
            if(letTo - letFrom > 0){
                for (int i = letFrom + 1; i < letTo; i++) {
                    if(board.getPosition((char) i, numFrom).getPiece() != null) return false;
                }
            }else{
                for (int i = letTo + 1; i < letFrom; i++) {
                    if(board.getPosition((char) i, numFrom).getPiece() != null) return false;
                }
            }
        }
        return true;
    }
}
