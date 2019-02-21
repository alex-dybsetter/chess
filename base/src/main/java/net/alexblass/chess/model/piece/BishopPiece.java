package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized bishop piece class.
 */
public class BishopPiece extends AbstractPiece {

    public BishopPiece(PieceColor color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Bishops can move in a diagonal line in any direction where there are
     * no obstructions between the start position and the end position.
     * The end position may be occupied by an enemy piece for a capture.
     * Up/Down: <= |7|
     * Left/Right: <= |7|
     * Where |Up/Down| == |Left/Right|
     **/
    @Override
    public boolean isValidMove() {
        return true;
    }

    @Override
    public int getImageResId() {
        return mColor.equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_bishop_black : R.drawable.ic_piece_modern_bishop_white;
    }
}
