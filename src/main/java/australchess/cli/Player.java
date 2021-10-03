package australchess.cli;

import lombok.Getter;

public class Player {
    @Getter String playingColor;
    @Getter String name;

    Player (String playingColor, String name) {
        this.playingColor = playingColor;
        this.name = name;
    }
}
