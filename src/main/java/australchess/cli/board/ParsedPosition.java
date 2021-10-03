package australchess.cli.board;

import lombok.Data;
import lombok.Getter;

public @Data class ParsedPosition {
    private final @Getter Character letter;
    private final @Getter Integer number;
}
