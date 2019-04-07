package net.alexblass.chess.bus.event;

import net.alexblass.chess.model.piece.AbstractPiece;

/**
 * An event that fires when a Pawn reaches the enemy's home row.
 * Used to trigger the pawn promotion dialog.
 */
public class PawnEligibleForPromotionEvent {
    private AbstractPiece mPawnToPromote;

    public PawnEligibleForPromotionEvent(AbstractPiece pawnToPromote) {
        mPawnToPromote = pawnToPromote;
    }

    public AbstractPiece getPawnToPromote() {
        return mPawnToPromote;
    }
}
