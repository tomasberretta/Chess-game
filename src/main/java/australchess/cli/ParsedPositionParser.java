package australchess.cli;

import java.util.Optional;

public class ParsedPositionParser {

    public static Optional<ParsedPosition> parse(String rawPosition) {
        var numberAndLetter = rawPosition.replaceAll("\\(|\\)", "").split(",");
        try {
            return Optional.of(new ParsedPosition(Integer.parseInt(numberAndLetter[0]), numberAndLetter[1].charAt(0)));
        } catch (Exception e) {
            // TODO: do something with the exception!
            return Optional.empty();
        }
    }
}
