package australchess.cli.board;

import australchess.cli.piece.Piece;

import java.util.Optional;

// Improve or create a new implementation if you like, this is a naive implementation
public class DefaultBoardPrinter implements BoardPrinter {
    @Override
    public String print(Board board) {
        var builder = new StringBuilder();
        var ranks = board.getRanks();
        var files = board.getFiles();
        for(var rank : ranks) {
            builder.append(rank).append("|");
            for(var file : files) {
                var toPrint = findPiece( file, rank, board).orElse(' ');
                builder.append(toPrint).append("|");
            }
            builder.append("\n");
        }
        builder.append("  ");
        files.forEach(f -> builder.append(f).append(" "));
        builder.append("\n");
        return builder.toString();
    }

    private static Optional<Character> findPiece(Character file, Integer rank, Board board) {
        return board.getPositions().stream().filter(p -> p.getLetter() == file && p.getNumber().equals(rank)).findFirst().map(BoardPosition::getPiece).map(Piece::getId);
    }
}
