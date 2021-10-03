package australchess.cli.board;

import australchess.cli.movegenerator.MoveResult;
import australchess.cli.piece.Piece;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Board {
    @Getter List<Character> files;
    @Getter List<Integer> ranks;
    @Getter List<BoardPosition> positions;

    Board(int nFiles, int nRanks){

        files = new ArrayList<>(nFiles);
        ranks = new ArrayList<>(nRanks);
        positions = new ArrayList<>(nFiles*nRanks);

        for (int i = nRanks ; i >= 1 ; i--) {
            ranks.add(i);
        }

        for (char i = 'a'; i < (nFiles + 'a'); i++) {
            files.add(i);
        }

        for (Character file : files) {
            for (Integer rank : ranks) {
                positions.add(new BoardPosition(null, rank, file));
            }
        }
    }

    void putPieceIn (Piece piece, int i, int j){
        for (BoardPosition position : positions) {
            if (position.getLetter() == files.get(i) && Objects.equals(position.getNumber(), ranks.get(j))) {
                position.setPiece(piece);
            }
        }
    }

    void putPieceInBoardPosition (Piece piece, BoardPosition boardPosition){
        boardPosition.setPiece(piece);
    }

    BoardPosition getBoardPositionFromParsed(ParsedPosition parsedPosition){
        for (BoardPosition position : positions) {
            if (Objects.equals(position.getNumber(), parsedPosition.getNumber()) && Objects.equals(position.getLetter(), parsedPosition.getLetter())) return position;
        }
        return null;
    }

    public BoardPosition getPosition(char i, int j){
        for (BoardPosition position : positions) {
            if (position.getLetter() == i && Objects.equals(position.getNumber(), j)) {
                return position;
            }
        }
        return null;
    }

    public MoveResult movePiece(ParsedPosition from, ParsedPosition to, String movingColor) throws IOException {
        BoardPosition fromP = getBoardPositionFromParsed(from);
        BoardPosition toP = getBoardPositionFromParsed(to);
        if(fromP == null) throw new IOException("Invalid piece position");
        if(fromP.getPiece() == null) throw new IOException("No piece in position");
        if(toP == null) throw new IOException("Target position out of limits");
        MoveResult moveResult = fromP.getPiece().getMoveGenerator().genMove(fromP, toP, this, movingColor);
        putPieceInBoardPosition(moveResult.getPMoved(), moveResult.getMove().getTo());
        putPieceInBoardPosition(null, moveResult.getMove().getFrom());
        return moveResult;
    }

}
