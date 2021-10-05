package australchess.cli;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPosition;
import australchess.cli.board.ParsedPosition;
import australchess.cli.movegenerator.MoveResult;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class DefaultCheckDetector implements CheckDetector {
    @Override
    public boolean validate(Board board, String movingColor, String oppositeColor) {
        BoardPosition ownKingPosition = board.getKingPositionByColor(movingColor);
        List<BoardPosition> oppositePositions = board.getPositionsByColor(oppositeColor);
        for (BoardPosition oppositePosition : oppositePositions) {
            try {
                MoveResult result = board.validateMovePiece(new ParsedPosition(oppositePosition.getLetter(), oppositePosition.getNumber()), new ParsedPosition(ownKingPosition.getLetter(), ownKingPosition.getNumber()), oppositeColor);
                if (result != null) return true;
            } catch (IOException ignored) {
            }
        }
        return false;
    }

    @Override
    public boolean checkForCheck(Board board, String movingColor) {
        String oppositeColor;
        if(Objects.equals(movingColor, "White")) oppositeColor = "Black";
        else oppositeColor = "White";
        BoardPosition ownKingPosition = board.getKingPositionByColor(movingColor);
        List<BoardPosition> oppositePositions = board.getPositionsByColor(oppositeColor);
        for (BoardPosition oppositePosition : oppositePositions) {
            try {
                MoveResult result = board.validateMovePiece(new ParsedPosition(oppositePosition.getLetter(), oppositePosition.getNumber()), new ParsedPosition(ownKingPosition.getLetter(), ownKingPosition.getNumber()), oppositeColor);
                if (result != null) return true;
            } catch (IOException ignored) {
            }
        }
        return false;
    }
}
