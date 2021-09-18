package australchess.cli;

import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.util.Scanner;

// Starter CLI interface for the chess game, modify as you wish.
// TODO: Fill in!
public class App {
    public static void main(String[] args) throws IOException {
        final BoardPrinter boardPrinter = new DefaultBoardPrinter();

        printHeader();
        final var firstPlayerId = askForString("Name of player that moves white: ");
        final var secondPlayerId = askForString("Name of player that moves black: ");
        System.out.println();
        System.out.println();

        while(shouldContinue()) {
            printCurrentPlayerTurn();
            System.out.println();
            printBoard(boardPrinter);
            final var positionFrom = askForPosition("Enter position of the piece you want to move");
            final var positionTo = askForPosition("Enter position of cell you want to move it to");
            move(positionFrom, positionTo);
            System.out.println();
            System.out.println();
        }
    }

    private static void printBoard(BoardPrinter boardPrinter) {
        // TODO: provide the board somehow.
        var positions = new DefaultSomethingClass().getPiecePositions();
        var boardAsString = boardPrinter.print(positions);
        System.out.println(boardAsString);
    }

    private static void move(ParsedPosition from, ParsedPosition to) {
        // TODO implement!
    }

    private static String playerToMove() {
        return "Someone"; //TODO Implement!
    }

    private static boolean shouldContinue() {
        return true; //TODO Implement!
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
