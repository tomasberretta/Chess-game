package australchess.piece;


public class Knight extends Piece {
    public Knight(String color, boolean lowerCase) {
        super(color);
        this.id = 'N';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.KNIGHT;
    }
}
