package australchess.board;

import australchess.movevalidator.MoveValidator;
import australchess.movevalidator.ValidateResult;

import java.util.List;
import java.util.Objects;

public class DefaultCheckDetector implements CheckDetector {
    MoveValidator moveValidator;

    public DefaultCheckDetector(MoveValidator moveValidator) {
        this.moveValidator = moveValidator;
    }

    @Override
    public boolean validate(Board board, String movingColor, String oppositeColor) {
        BoardPosition ownKingPosition = board.getKingPositionByColor(movingColor);
        List<BoardPosition> oppositePositions = board.getPositionsByColor(oppositeColor);
        for (BoardPosition oppositePosition : oppositePositions) {
            ValidateResult result = moveValidator.validate(new ParsedPosition(oppositePosition.getLetter(), oppositePosition.getNumber()), new ParsedPosition(ownKingPosition.getLetter(), ownKingPosition.getNumber()), board, oppositeColor);
            if(result.isValid()) return true;
        }
        return false;
    }

    @Override
    public boolean checkForCheck(Board board, String movingColor) {
        String oppositeColor;
        if(Objects.equals(movingColor, "White")) oppositeColor = "Black";
        else oppositeColor = "White";
        return validate(board, movingColor, oppositeColor);
    }
}
