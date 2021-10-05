package australchess.board;


public interface CheckDetector {
    boolean validate(Board board, String movingColor, String oppositeColor);

    boolean checkForCheck(Board board, String movingColor);
}
