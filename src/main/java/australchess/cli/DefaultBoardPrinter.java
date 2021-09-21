package australchess.cli;

import java.util.List;
import java.util.Optional;

// Improve or create a new implementation if you like, this is a naive implementation
public class DefaultBoardPrinter implements BoardPrinter {
    @Override
    public String print(Board board) {
        var builder = new StringBuilder();

        for(var file : board.getFiles()) {
            builder.append(file).append("|");
            for(var rank : board.getRanks()) {
                var toPrint = findPiece(file, rank, board).orElse(' ');
                builder.append(toPrint).append("|");
            }
            builder.append("\n");
        }
        builder.append("  ");
        board.getRanks().forEach(r -> builder.append(r).append(" "));
        builder.append("\n");
        return builder.toString();
    }

    private static Optional<Character> findPiece(Character file, Integer rank, Board board) {
        return board.getPositions().stream().filter(p -> p.getLetter() == file && p.getNumber().equals(rank)).findFirst().map(BoardPosition::getPieceId);
    }
}
