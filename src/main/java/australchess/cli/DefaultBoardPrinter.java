package australchess.cli;

import java.util.List;
import java.util.Optional;

// Improve or create a new implementation if you like, this is a naive implementation
public class DefaultBoardPrinter implements BoardPrinter {
    @Override
    public String print(List<BoardPosition> positions) {
        var files = List.of('h', 'g', 'f', 'e', 'd', 'c', 'b', 'a');
        var ranks = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        var builder = new StringBuilder();

        for(var file : files) {
            builder.append(file).append("|");
            for(var rank : ranks) {
                var toPrint = findPiece(file, rank, positions).orElse(' ');
                builder.append(toPrint).append("|");
            }
            builder.append("\n");
        }
        builder.append("  ");
        ranks.forEach(r -> builder.append(r).append(" "));
        builder.append("\n");
        return builder.toString();
    }

    private static Optional<Character> findPiece(Character file, Integer rank, List<BoardPosition> positions) {
        return positions.stream().filter(p -> p.getLetter() == file && p.getNumber().equals(rank)).findFirst().map(BoardPosition::getPieceId);
    }
}
