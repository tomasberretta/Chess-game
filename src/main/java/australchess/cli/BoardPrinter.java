package australchess.cli;

import java.util.List;

public interface BoardPrinter {
    String print(List<BoardPosition> positions);
}
