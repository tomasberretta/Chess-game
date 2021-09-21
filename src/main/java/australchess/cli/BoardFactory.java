package australchess.cli;

public interface BoardFactory {
    Board makeBoard(Player[] players);
}
