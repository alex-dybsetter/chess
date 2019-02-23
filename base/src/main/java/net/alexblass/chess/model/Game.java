package net.alexblass.chess.model;

public class Game {
    private int mId;
    private PieceColor mActiveTurn;

    public Game(int id) {
        mId = id;
        mActiveTurn = PieceColor.WHITE;
    }

    public PieceColor getActiveTurn() {
        return mActiveTurn;
    }

    public void nextTurn() {
        mActiveTurn = mActiveTurn.equals(PieceColor.WHITE) ? PieceColor.BLACK : PieceColor.WHITE;
    }
}
