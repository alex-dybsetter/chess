package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized king piece class.
 */
public class KingPiece extends AbstractPiece {

    public KingPiece(PieceColor color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Kings can move to an adjacent tile in any direction.
     * The end position must not put the king in a position where it may be captured by an enemy piece.
     * The end position may be occupied by an enemy piece for a capture.
     * Up/Down: = |1|
     * Left/Right: = |1|
     **/
    @Override
    public boolean isValidMove() {
        return true;
    }

    @Override
    public int getImageResId() {
        return getColor().equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_king_black : R.drawable.ic_piece_modern_king_white;
    }
}
