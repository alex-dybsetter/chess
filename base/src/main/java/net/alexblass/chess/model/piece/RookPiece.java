package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized rook piece class.
 */
public class RookPiece extends AbstractPiece {

    public RookPiece(PieceColor color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Rooks can move in a straight line either horizontally or vertically where there are
     * no obstructions between the start position and the end position.
     * The end position may be occupied by an enemy piece for a capture.
     * Up/Down: <= |7|
     * Left/Right: <= |7|
     * Where if |Up/Down| > 0 then Left/Right == 0 or |Left/Right| > 0 then Up/Down == 0
     **/
    @Override
    public boolean isValidMove() {
        return true;
    }

    @Override
    public int getImageResId() {
        return getColor().equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_rook_black : R.drawable.ic_piece_modern_rook_white;
    }
}
