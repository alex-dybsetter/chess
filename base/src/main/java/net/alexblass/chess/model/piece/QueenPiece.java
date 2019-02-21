package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized queen piece class.
 */
public class QueenPiece extends AbstractPiece {

    public QueenPiece(PieceColor color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Queens can move in a straight line either horizontally or vertically where there are
     * no obstructions between the start position and the end position.
     * Up/Down: <= |7|
     * Left/Right: <= |7|
     **/
    @Override
    public boolean isValidMove() {
        return true;
    }

    @Override
    public int getImageResId() {
        return mColor.equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_queen_black : R.drawable.ic_piece_modern_queen_white;
    }
}
