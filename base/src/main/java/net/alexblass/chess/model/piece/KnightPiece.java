package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.GameBoard;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized knight piece class.
 */
public class KnightPiece extends AbstractPiece {

    public KnightPiece(PieceColor color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Knights can move in an L shape in any direction.
     * Knights are the only piece that can jump over other pieces.
     * The end position may be occupied by an enemy piece for a capture.
     * Up/Down: <= |2|
     * Left/Right: <= |2|
     * Where if |Up/Down| == 2 then Left/Right == 1 or |Left/Right| == 2 then Up/Down == 1
     **/
    @Override
    public boolean isValidMove(GameBoard gameBoard, int newRow, int newCol) {
        return true;
    }

    @Override
    public int getImageResId() {
        return getColor().equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_knight_black : R.drawable.ic_piece_modern_knight_white;
    }
}
