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
    abstract public int getImageResIdByColor(PieceColor color);

    public AbstractPiece(PieceColor color, int row, int col) {
        mColor = color;
        mRow = row;
        mCol = col;
    }
}
