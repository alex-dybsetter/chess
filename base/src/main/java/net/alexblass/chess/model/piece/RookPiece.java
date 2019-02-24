package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.model.GameBoard;
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
    public boolean isValidMove(GameBoard gameBoard, int newRow, int newCol) {
        int rowDelta = newRow - getRow();
        int colDelta = newCol - getCol();

        if ((rowDelta != 0 && colDelta != 0) || areThereObstructions(gameBoard, newRow, newCol)) {
            return false;
        }
        return true;
    }

    @Override
    public int getImageResId() {
        return getColor().equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_rook_black : R.drawable.ic_piece_modern_rook_white;
    }

    private boolean areThereObstructions(GameBoard gameBoard, int newRow, int newCol) {
        AbstractPiece piece;

        if (getRow() < newRow) { // Rook moves down the board
            for (int i = getRow() + 1; i <= newRow - 1; i++) {
                piece = gameBoard.getPieceAtCoordinates(i, newCol);
                if (piece != null) {
                    return true;
                }
            }
        } else if (getRow() > newRow) { // Rook moves up the board
            for (int i = getRow() - 1; i >= newRow + 1; i--) {
                piece = gameBoard.getPieceAtCoordinates(i, newCol);
                if (piece != null) {
                    return true;
                }
            }
        } else if (getCol() < newCol) { // Rook moves right on the board
            for (int i = getCol() + 1; i <= newCol - 1; i++) {
                piece = gameBoard.getPieceAtCoordinates(newRow, i);
                if (piece != null) {
                    return true;
                }
            }
        } else if (getCol() > newCol) { // Rook moves left on the board
            for (int i = getCol() - 1; i >= newCol + 1; i--) {
                piece = gameBoard.getPieceAtCoordinates(newRow, i);
                if (piece != null) {
                    return true;
                }
            }
        }

        piece = gameBoard.getPieceAtCoordinates(newRow, newCol);
        return piece != null && !canCapturePiece(piece);
    }
}
