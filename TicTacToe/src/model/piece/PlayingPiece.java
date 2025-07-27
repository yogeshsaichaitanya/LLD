package model.piece;

public abstract class PlayingPiece {
    private PieceType piece;

    public PlayingPiece(PieceType piece) {
        this.piece = piece;
    }

    public PieceType getPiece() {
        return piece;
    }

    @Override
    public String toString() {
        return piece.name();
    }
}
