package australchess.cli;

public class DefaultBoardFactory implements BoardFactory {

    @Override
    public Board makeBoard(Player[] players) {
        return new Board();
    }
}
