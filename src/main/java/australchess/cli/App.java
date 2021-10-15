package australchess.cli;

import australchess.board.*;
import australchess.movevalidator.*;
import australchess.piece.DefaultPieceSetFactory;
import australchess.piece.Piece;
import australchess.piece.PieceSetFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Starter CLI interface for the chess game, modify as you wish.
public class App {
    static Player[] players;
    static Board board;
    static int playerTurn;
    static Status status;
    static BoardFactory boardFactory;
    static PieceSetFactory pieceSetFactory;
    static CheckDetector checkDetector;
    static MoveValidator moveValidator;
    static List<MoveResult> moves;
    static IO io;

    public static void main(String[] args) throws IOException {

        io = new IO();

        final BoardPrinter boardPrinter = new DefaultBoardPrinter();

        printHeader();

        setUpGame(2, new String[]{"White", "Black"}, "Default");

        printSpaces(2);

        while(shouldContinue()) {
            printCurrentPlayerTurn();
            printCurrentStatus();
            printSpaces(1);
            printBoard(boardPrinter, board);
            final var positionFrom = askForPosition("Enter position of the piece you want to move");
            final var positionTo = askForPosition("Enter position of cell you want to move it to");
            ValidateResult validateResult = validateMove(positionFrom,positionTo);
            try {
                if(!validateResult.isValid()) throw new IOException(validateResult.getMessage());
                else move(positionFrom, positionTo);
            }catch (IOException e){
                printSpaces(2);
                System.out.println(e.getMessage());
            }
            printSpaces(2);
        }
        printSpaces(2);
        printCurrentPlayerTurn();
        printCurrentStatus();
        printBoard(boardPrinter, board);
        printMoves();

    }

    private static void printMoves() {
        for (int i = 0; i < moves.size(); i++) {
            MoveResult moveResult= moves.get(i);
            Piece pTook = moveResult.getPTook();
            if(pTook != null){
                System.out.println( i + 1 +") "+ moveResult.getPMoved().getColor() + " " +moveResult.getPMoved().getType().toString() +" moved from: "+ moveResult.getMove().getFrom().getCoordinates() + " to: "+moveResult.getMove().getTo().getCoordinates() + " and took "+ moveResult.getPTook().getColor()+" "+ moveResult.getPTook().getType().toString());
            }
            else {
                System.out.println( i + 1 +") "+ moveResult.getPMoved().getColor() + " " + moveResult.getPMoved().getType().toString() +" moved from: "+ moveResult.getMove().getFrom().getCoordinates() + " to: "+moveResult.getMove().getTo().getCoordinates());
            }
        }
    }

    private static void setUpGame(int numberOfPlayers, String[] playerColors, String variation){
        boardFactory = new DefaultBoardFactory();
        pieceSetFactory = new DefaultPieceSetFactory();
        moveValidator = new DefaultMoveValidator();
        checkDetector = new DefaultCheckDetector(moveValidator);
        players = new Player[numberOfPlayers];
        moves = new ArrayList<>();
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
        MoveResult moveResult = board.movePiece(from, to);
        moves.add(moveResult);
        if (playerTurn >= players.length-1) playerTurn = 0;
        else ++playerTurn;
    }

    public static ValidateResult validateMove(ParsedPosition from, ParsedPosition to){
        return moveValidator.validate(board.getBoardPositionFromParsed(from), board.getBoardPositionFromParsed(to), board, playerToColorMove());
    }

    private static void printCurrentStatus(){
        io.printCurrentStatus(status);
    }
    private static String playerToColorMove() {
        return players[playerTurn].playingColor;
    }
    private static String playerToMove() {
        return players[playerTurn].name;
    }

    private static boolean shouldContinue() {
        checkStatus();
        return (status.equals(Status.PLAYING) || status.equals(Status.PLAYER_CHECKED));
    }

    private static void checkStatus() {
        if(checkDetector.checkForCheck(board,playerToColorMove())) status = Status.PLAYER_CHECKED;
        else status = Status.PLAYING;
        List<Move> moves = generatePossibleMoves();
        if (status.equals(Status.PLAYER_CHECKED)){
            if (moves.isEmpty()) status = Status.PLAYER_CHECKMATED;
        } else {
            if (moves.isEmpty()) status = Status.STALEMATE;
        }
    }

    private static List<Move> generatePossibleMoves() {
        List<Move> moves = new ArrayList<>();
        for (BoardPosition boardPosition : board.getPositionsByColor(playerToColorMove())) {
            for (BoardPosition position : board.getPositions()) {
                ValidateResult validateResult = moveValidator.validate(boardPosition, position, board, playerToColorMove());
                if(validateResult.isValid()){
                    moves.add(new Move(boardPosition, position));
                    return moves;
                }

            }
        }
        return moves;
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
