package australchess.cli;

import australchess.cli.board.*;
import australchess.cli.movegenerator.MoveResult;
import australchess.cli.piece.DefaultPieceSetFactory;
import australchess.cli.piece.PieceSetFactory;

import java.io.IOException;

// Starter CLI interface for the chess game, modify as you wish.
public class App {
    static Player[] players;
    static Board board;
    static int playerTurn;
    static Status status;
    static BoardFactory boardFactory;
    static PieceSetFactory pieceSetFactory;
    static IO io;

    public static void main(String[] args) throws IOException {

        io = new IO();

        final BoardPrinter boardPrinter = new DefaultBoardPrinter();

        printHeader();

        setUpGame(2, new String[]{"White", "Black"}, "Default");

        printSpaces(2);

        while(shouldContinue()) {
            printCurrentPlayerTurn();
            printSpaces(1);
            printBoard(boardPrinter, board);
            final var positionFrom = askForPosition("Enter position of the piece you want to move");
            final var positionTo = askForPosition("Enter position of cell you want to move it to");
            try {
                move(positionFrom, positionTo);
            }catch (Exception e){
                printSpaces(2);
                System.out.println(e.getMessage());
            }
            printSpaces(2);
        }
    }

    private static void setUpGame(int numberOfPlayers, String[] playerColors, String variation){
        boardFactory = new DefaultBoardFactory();
        pieceSetFactory = new DefaultPieceSetFactory();
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            final var playerID = askForString("Name of player that moves "+playerColors[i]+": ");
            players[i] = new Player(playerColors[i], playerID);
        }
        board = boardFactory.makeBoard(pieceSetFactory, players);
        playerTurn = 0;
        status = Status.PLAYING;
    }

    private static void printBoard(BoardPrinter boardPrinter, Board board) {
        io.printBoard(boardPrinter, board);
    }

    private static void move(ParsedPosition from, ParsedPosition to) throws IOException {
        MoveResult moveResult = board.movePiece(from, to, players[playerTurn].getPlayingColor());
        status = moveResult.getStatus();
        if (playerTurn >= players.length-1) playerTurn = 0;
        else ++playerTurn;
    }

    private static String playerToMove() {
        return players[playerTurn].name;
    }
    private static String playerToColorMove() {
        return players[playerTurn].playingColor;
    }

    private static boolean shouldContinue() {
        return (status.equals(Status.PLAYING) || status.equals(Status.PLAYER_CHECKED));
    }

    private static ParsedPosition askForPosition(String question) {
        return io.askForPosition(question);
    }

    private static void printCurrentPlayerTurn() {
        io.printCurrentPlayerTurn(playerToMove(), playerToColorMove());
    }

    private static void printSpaces (int n){
        io.printSpaces(n);
    }

    private static String askForString(String question) {
        return io.askForString(question);
    }

    private static void printHeader() throws IOException {
        io.printHeader();
    }
}
