package australchess.cli;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public @Data class BoardPosition {
    private final @Getter Character pieceId;
    private final @Getter Integer number;
    private final @Getter Character letter;
}
