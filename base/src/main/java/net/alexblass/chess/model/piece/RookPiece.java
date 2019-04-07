package net.alexblass.chess.model.piece;

import net.alexblass.chess.base.R;
import net.alexblass.chess.constant.Constants;
import net.alexblass.chess.model.GameBoard;
import net.alexblass.chess.model.PieceColor;

/**
 * A specialized rook piece class.
 */
public class RookPiece extends AbstractPiece {

    public RookPiece(PieceColor color, int row, int col) {
        super(color, row, col);
        setName(Constants.ROOK);
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
        return isValidRookMove(gameBoard, this, newRow, newCol);
    }

    @Override
    public int getImageResId() {
        return getColor().equals(PieceColor.BLACK) ? R.drawable.ic_piece_modern_rook_black : R.drawable.ic_piece_modern_rook_white;
    }

    static boolean isValidRookMove(GameBoard gameBoard, AbstractPiece pieceToMove, int newRow, int newCol) {
        int rowDelta = newRow - pieceToMove.getRow();
        int colDelta = newCol - pieceToMove.getCol();

        if ((rowDelta != 0 && colDelta != 0) || areThereObstructions(gameBoard, pieceToMove, newRow, newCol)) {
            return false;
        }
        return true;
    }

    private static boolean areThereObstructions(GameBoard gameBoard, AbstractPiece pieceToMove, int newRow, int newCol) {
        AbstractPiece piece;

        if (pieceToMove.getRow() < newRow) { // Rook moves down the board
            for (int i = pieceToMove.getRow() + 1; i <= newRow - 1; i++) {
                piece = gameBoard.getPieceAtCoordinates(i, newCol);
                if (!isSquareEmpty(piece)) {
                    return true;
                }
            }
        } else if (pieceToMove.getRow() > newRow) { // Rook moves up the board
            for (int i = pieceToMove.getRow() - 1; i >= newRow + 1; i--) {
                piece = gameBoard.getPieceAtCoordinates(i, newCol);
                if (!isSquareEmpty(piece)) {
                    return true;
                }
            }
        } else if (pieceToMove.getCol() < newCol) { // Rook moves right on the board
            for (int i = pieceToMove.getCol() + 1; i <= newCol - 1; i++) {
                piece = gameBoard.getPieceAtCoordinates(newRow, i);
                if (!isSquareEmpty(piece)) {
                    return true;
                }
            }
        } else if (pieceToMove.getCol() > newCol) { // Rook moves left on the board
            for (int i = pieceToMove.getCol() - 1; i >= newCol + 1; i--) {
                piece = gameBoard.getPieceAtCoordinates(newRow, i);
                if (!isSquareEmpty(piece)) {
                    return true;
                }
            }
        }

        piece = gameBoard.getPieceAtCoordinates(newRow, newCol);
        return !isSquareEmpty(piece) && !pieceToMove.canCapturePiece(piece);
    }
}
