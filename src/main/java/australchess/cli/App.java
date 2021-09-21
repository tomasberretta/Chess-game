package australchess.cli;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

// Starter CLI interface for the chess game, modify as you wish.
// TODO: Fill in!
public class App {
    static Player[] players;
    static Board board;
    static int playerTurn;
    static Status status;
    static BoardFactory boardFactory;
    static PieceSetFactory pieceSetFactory;

    public static void main(String[] args) throws IOException {


        final BoardPrinter boardPrinter = new DefaultBoardPrinter();

        printHeader();
//        final var firstPlayerId = askForString("Name of player that moves white: ");
//        final var secondPlayerId = askForString("Name of player that moves black: ");

        setUpGame(2, new String[]{"White", "Black"}, "Default");

        System.out.println();
        System.out.println();

        while(shouldContinue()) {
            printCurrentPlayerTurn();
            System.out.println();
            printBoard(boardPrinter, board);
            final var positionFrom = askForPosition("Enter position of the piece you want to move");
            final var positionTo = askForPosition("Enter position of cell you want to move it to");
            move(positionFrom, positionTo);
            System.out.println();
            System.out.println();
        }
    }

    private static void setUpGame(int numberOfPlayers, String[] playerColors, String variation){
        pieceSetFactory = new DefaultPieceSetFactory();
        boardFactory = new DefaultBoardFactory();
        players = new Player[numberOfPlayers];
        for (int i = 0; i < numberOfPlayers; i++) {
            players[i] = new Player(playerColors[i], pieceSetFactory.makePieceSet(playerColors[i]));
        }
        board = boardFactory.makeBoard(players);
        playerTurn = 0;
        status = Status.PLAYING;
    }

    private static void printBoard(BoardPrinter boardPrinter, Board board) {
        var boardAsString = boardPrinter.print(board);
        System.out.println(boardAsString);
    }

    private static void move(ParsedPosition from, ParsedPosition to) {
        if (playerTurn >= players.length-1) playerTurn = 0;
        else ++playerTurn;
    }

    private static String playerToMove() {
        return players[playerTurn].playingColor;
    }

    private static boolean shouldContinue() {
        return (status.equals(Status.PLAYING) || status.equals(Status.PLAYER_CHECKED));
    }

    private static ParsedPosition askForPosition(String question) {
        System.out.println(question);
        System.out.print("Enter in format -> (number,letter): ");
        var scanner = new Scanner(System.in);
        var positionAsString = scanner.nextLine();
        return ParsedPositionParser.parse(positionAsString)
                .orElseGet(() -> askForPosition("The position " + positionAsString + " is invalid. Please enter a new one"));
    }

    private static void printCurrentPlayerTurn() {
        System.out.println("It's " + playerToMove() + " turn!");
    }

    private static String askForString(String question) {
        System.out.println(question);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void printHeader() throws IOException {
        String header = FigletFont.convertOneLine("AustralChess");
        System.out.println(header);
    }
}
