package australchess.cli.board;

import java.util.Optional;

public class ParsedPositionParser {

    public static Optional<ParsedPosition> parse(String rawPosition) {
        var letterAndNumber = rawPosition.replaceAll("\\(|\\)", "").split(",");
        try {
            return Optional.of(new ParsedPosition(letterAndNumber[0].charAt(0),Integer.parseInt(letterAndNumber[1])));
        } catch (Exception e) {
            // TODO: do something with the exception!
            return Optional.empty();
        }
    }
}
