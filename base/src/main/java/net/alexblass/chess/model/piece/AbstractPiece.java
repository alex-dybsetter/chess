package net.alexblass.chess.model.piece;

import net.alexblass.chess.model.PieceColor;

/**
 * An abstract class to derive different piece types to store data related to the game pieces.
 */
public abstract class AbstractPiece {

    private String mName;
    private PieceColor mColor;
    private int mRow;
    private int mCol;
    private int mPointsValue;
    private int mImageResourceId;
    private boolean mHasMovedFromStart;
    private boolean mIsActive;

    abstract public boolean isValidMove();
    abstract public int getImageResId();

    public AbstractPiece(PieceColor color, int row, int col) {
        mColor = color;
        mRow = row;
        mCol = col;
    }

    public int getRow() {
        return mRow;
    }

    public int getCol() {
        return mCol;
    }

    public PieceColor getColor() {
        return mColor;
    }

    public void setCoordinates(int row, int col) {
        mRow = row;
        mCol = col;
    }

    public void setHasMovedFromStart(boolean hasMovedFromStart) {
        mHasMovedFromStart = hasMovedFromStart;
    }
}
