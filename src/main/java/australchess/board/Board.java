package australchess.board;

import australchess.movevalidator.Move;
import australchess.movevalidator.MoveResult;
import australchess.piece.*;
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
        Piece movedPiece = from.getPiece();
        Piece tookPiece = to.getPiece();
        if(movedPiece.getType() == Type.PAWN && Objects.equals(movedPiece.getColor(), "White") && (Objects.equals(to.getNumber(), ranks.get(0)))) {
            putPieceInBoardPosition(new Queen("White", false), to);
        }
        else if (movedPiece.getType() == Type.PAWN && Objects.equals(movedPiece.getColor(), "Black") && (Objects.equals(to.getNumber(), ranks.get(ranks.size() - 1)))){
            putPieceInBoardPosition(new Queen("Black", true), to);
        }
        else if(movedPiece.getType() == Type.KING && Math.abs(to.getLetter() - from.getLetter()) == 2){
            int dirX = to.getLetter() > from.getLetter() ? 1 : -1;
            char letTo = to.getLetter();
            int numFrom = from.getNumber();
            Rook rookToMove;
            if(dirX > 0){
                rookToMove = (Rook) getPosition((char) (letTo+1), numFrom).getPiece();
                putPieceInBoardPosition(rookToMove, getPosition((char) (letTo-1), numFrom));
                putPieceInBoardPosition(null, getPosition((char) (letTo+1), numFrom));
            }else{
                rookToMove = (Rook) getPosition((char) (letTo - 2), numFrom).getPiece();
                putPieceInBoardPosition(rookToMove, getPosition((char) (letTo+1), numFrom));
                putPieceInBoardPosition(null, getPosition((char) (letTo-2), numFrom));
            }
            setMoved(rookToMove);
            putPieceInBoardPosition(movedPiece, to);
        } else {
            putPieceInBoardPosition(movedPiece, to);
        }
        putPieceInBoardPosition(null, from);
        setMoved(movedPiece);
        return new MoveResult(new Move(from, to), movedPiece, tookPiece);
    }

    void setMoved(Piece piece){
        switch (piece.getType()){
            case PAWN -> ((Pawn) piece).setMoved(true);
            case ROOK -> ((Rook) piece).setMoved(true);
            case KING -> ((King) piece).setMoved(true);
        }
    }


}
