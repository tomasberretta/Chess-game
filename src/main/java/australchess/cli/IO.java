package australchess.cli;

import australchess.cli.board.Board;
import australchess.cli.board.BoardPrinter;
import australchess.cli.board.ParsedPosition;
import australchess.cli.board.ParsedPositionParser;
import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.util.Scanner;

public class IO {

    public ParsedPosition askForPosition(String question) {
        System.out.println(question);
        System.out.print("Enter in format -> (letter,number): ");
        var scanner = new Scanner(System.in);
        var positionAsString = scanner.nextLine();
        return ParsedPositionParser.parse(positionAsString)
                .orElseGet(() -> askForPosition("The position " + positionAsString + " is invalid. Please enter a new one"));
    }

    public void printCurrentPlayerTurn(String name, String color) {
        System.out.println("It's " + name + " turn, playing as: "+ color);
    }

    public String askForString(String question) {
        System.out.println(question);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void printHeader() throws IOException {
        String header = FigletFont.convertOneLine("AustralChess");
        System.out.println(header);
    }

    public void printSpaces (int n){
        for (int i = 0; i < n; i++) {
            System.out.println();
        }
    }

    public void printBoard(BoardPrinter boardPrinter, Board board) {
        var boardAsString = boardPrinter.print(board);
        System.out.println(boardAsString);
    }
}
