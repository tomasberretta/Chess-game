package australchess.board;

import australchess.movevalidator.Move;
import australchess.movevalidator.MoveResult;
import australchess.piece.Piece;
import australchess.piece.Queen;
import australchess.piece.Type;
import lombok.Getter;

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
                positions.add(new BoardPosition(null,file, rank));
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

    public BoardPosition getBoardPositionFromParsed(ParsedPosition parsedPosition){
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

    public MoveResult movePiece(ParsedPosition fromP, ParsedPosition toP) {
        BoardPosition from = getBoardPositionFromParsed(fromP);
        BoardPosition to = getBoardPositionFromParsed(toP);
        Move move = new Move(from, to);
        MoveResult moveResult = new MoveResult(move, from.getPiece(), to.getPiece());
        if(moveResult.getPMoved().getType() == Type.PAWN && Objects.equals(moveResult.getPMoved().getColor(), "White") && (Objects.equals(moveResult.getMove().getTo().getNumber(), ranks.get(0)))) {
            putPieceInBoardPosition(new Queen("White", false), moveResult.getMove().getTo());
        }
        else if (moveResult.getPMoved().getType() == Type.PAWN && Objects.equals(moveResult.getPMoved().getColor(), "Black") && (Objects.equals(moveResult.getMove().getTo().getNumber(), ranks.get(ranks.size() - 1)))){
            putPieceInBoardPosition(new Queen("Black", true), moveResult.getMove().getTo());
        }
        else{
            putPieceInBoardPosition(moveResult.getPMoved(), moveResult.getMove().getTo());
        }
        putPieceInBoardPosition(null, moveResult.getMove().getFrom());
        return moveResult;
    }


}
