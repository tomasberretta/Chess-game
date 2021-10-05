package australchess.cli.board;

import australchess.cli.movegenerator.MoveResult;
import australchess.cli.piece.Piece;
import australchess.cli.piece.Queen;
import australchess.cli.piece.Type;
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

    public void putPieceInBoardPosition (Piece piece, BoardPosition boardPosition){
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

    public BoardPosition getKingPositionByColor (String color){
        for (BoardPosition position : positions) {
            if (position.getPiece() != null) {
                if(Objects.equals(position.getPiece().getColor(), color) && position.getPiece().getType() == Type.KING) return position;
            }
        }
        return null;
    }

    public List<BoardPosition> getPositionsByColor (String color){
        List<BoardPosition> colorPositions = new ArrayList<>();
        for (BoardPosition position : positions) {
            if (position.getPiece() != null) {
                if(Objects.equals(position.getPiece().getColor(), color))colorPositions.add(position);
            }
        }
        return colorPositions;
    }

    public MoveResult movePiece(ParsedPosition from, ParsedPosition to, String movingColor) throws IOException {
        MoveResult moveResult = validateMovePiece(from, to,movingColor);
        if(moveResult.getPMoved().getType() == Type.PAWN && Objects.equals(moveResult.getPMoved().getColor(), "White") && (moveResult.getMove().getTo().getNumber() == 8)) {
            putPieceInBoardPosition(new Queen("White", false), moveResult.getMove().getTo());
        }
        else if (moveResult.getPMoved().getType() == Type.PAWN && Objects.equals(moveResult.getPMoved().getColor(), "Black") && (moveResult.getMove().getTo().getNumber() == 1)){
            putPieceInBoardPosition(new Queen("Black", true), moveResult.getMove().getTo());
        }
        else{
            putPieceInBoardPosition(moveResult.getPMoved(), moveResult.getMove().getTo());
        }
        putPieceInBoardPosition(null, moveResult.getMove().getFrom());
        return moveResult;
    }

    public MoveResult validateMovePiece(ParsedPosition from, ParsedPosition to, String movingColor) throws IOException {
        BoardPosition fromP = getBoardPositionFromParsed(from);
        BoardPosition toP = getBoardPositionFromParsed(to);
        return validateMovePiece(fromP,toP, movingColor);
    }

    public MoveResult validateMovePiece(BoardPosition from, BoardPosition to, String movingColor) throws IOException {
        if(from == null) throw new IOException("Invalid piece position");
        if(from.getPiece() == null) throw new IOException("No piece in position");
        if(to == null) throw new IOException("Target position out of limits");
        return from.getPiece().getMoveGenerator().genMove(from, to, this, movingColor);
    }

}
