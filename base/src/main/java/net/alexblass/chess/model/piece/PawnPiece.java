package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.GameBoard;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized pawn piece class.
 */
public class PawnPiece extends AbstractPiece {

    public PawnPiece(PieceColor color, int row, int col) {
        super(color, row, col);
    }

    /**
     * Pawns can move can generally move one space in the direction of the enemy's home row.
     * Captures are not allowed so the end position must be unoccupied.
     * Up/Down: == +-1 (away from home, towards enemy home row)
     * Left/Right: 0
     *
     * Special case: On the first move, a pawn may move two spaces instead of one where there are
     * no obstructions between the start position and the end position.
     * Captures are not allowed so the end position must unoccupied.
     * Up/Down: == +-2 (away from home, towards enemy home row)
     * Left/Right: 0
     *
     * Special case: Pawns may capture pieces diagonal in either direction, left or right, in the
     * direction towards the enemy's home row.
     * This move is a capture so the end position must be occupied by an enemy piece.
     * Up/Down: == +-1 (away from home, towards enemy home row)
     * Left/Right: == |1|
     *
     * Special case: There is a special move called "en passant" that allows pawns to capture an
     * enemy piece as they move two spaces in the direction of their enemy's home.  This move is
     * only allowed on a pawn's first move, and only if the pawn would have otherwise been able to
     * capture the enemy piece by moving one space instead.
     * The end position must unoccupied.
     * Up/Down: == +-2 (away from home, towards enemy home row)
     * Left/Right: 0 (The diagonal enemy piece is captured as the pawn "passes" through.
     **/
    @Override
    public boolean isValidMove(GameBoard gameBoard, int newRow, int newCol) {
        return true;
    }

    @Override
    public int getImageResId() {
        return getColor().equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_pawn_black : R.drawable.ic_piece_modern_pawn_white;
    }
}
