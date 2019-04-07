package net.alexblass.chess.model.piece;

import net.alexblass.chess.model.GameBoard;
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

    abstract public int getImageResId();
    abstract public boolean isValidMove(GameBoard gameBoard, int newRow, int newCol);

    public AbstractPiece(PieceColor color, int row, int col) {
        mColor = color;
        mRow = row;
        mCol = col;
        mHasMovedFromStart = false;
    }

    static boolean isSquareEmpty(AbstractPiece pieceSpace) {
        return pieceSpace == null;
    }

    public String getName() {
        return mName;
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

    boolean hasMovedFromStart() {
        return mHasMovedFromStart;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setCoordinates(int row, int col) {
        mRow = row;
        mCol = col;
    }

    public void setHasMovedFromStart(boolean hasMovedFromStart) {
        mHasMovedFromStart = hasMovedFromStart;
    }

    boolean canCapturePiece(AbstractPiece pieceToCapture) {
        return !mColor.equals(pieceToCapture.getColor());
    }
}
