package australchess.cli;

import lombok.Data;
import lombok.Getter;

public @Data class ParsedPosition {
    private final @Getter Integer number;
    private final @Getter Character letter;
}
