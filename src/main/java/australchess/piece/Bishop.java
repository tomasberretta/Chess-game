package australchess.piece;


public class Bishop extends Piece {
    public Bishop(String color, boolean lowerCase) {
        super(color);
        this.id = 'B';
        if (lowerCase) id = Character.toLowerCase(id);
        this.type = Type.BISHOP;
    }
}
