package australchess.board;

import australchess.piece.PieceSetFactory;
import australchess.cli.Player;
import australchess.piece.*;

public class DefaultBoardFactory implements BoardFactory {

    @Override
    public Board makeBoard(PieceSetFactory pieceSetFactory, Player[] players) {
        Board board = new Board(8,8);
        boolean lowerCase = false;
        for (Player player : players) {
            Piece[] pieces = pieceSetFactory.makePieceSet(player.getPlayingColor(), lowerCase);
            addPiecesToBoard(board, pieces, player.getPlayingColor());
            lowerCase = true;
        }
        return board;
    }

    @Override
    public Board makeEmptyBoard() {
        return new Board(8,8);
    }

    @Override
    public void addPiecesToBoard(Board board, Piece[] pieces, String color) {
        int index = 0;
        if(color.equals("White")){
            for (int j = 6; j < 8; j++) {
                for (int i = 0; i < board.getFiles().size(); i++) {
                    board.putPieceIn(pieces[index], i, j);
                    index++;
                }
            }
        }else{
            index = pieces.length -1;
            for (int j = 0; j < 2; j++) {
                for (int i = board.getFiles().size()-1; i >= 0; i--) {
                    board.putPieceIn(pieces[index], i, j);
                    index--;
                }
            }
        }
    }

    @Override
    public void addPieceToBoard(Board board, Piece piece, int i, int j) {
        board.putPieceIn(piece, i, j);
    }
}
