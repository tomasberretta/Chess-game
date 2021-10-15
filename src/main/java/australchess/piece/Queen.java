package australchess.piece;


public class Queen extends Piece {
    public Queen(String color, boolean lowerCase) {
        super(color);
        this.id = 'Q';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.QUEEN;

    }
}
