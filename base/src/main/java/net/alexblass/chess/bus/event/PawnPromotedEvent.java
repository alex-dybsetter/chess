package net.alexblass.chess.bus.event;

import net.alexblass.chess.model.piece.AbstractPiece;

/**
 * An event that fires when the user decides what type of piece to promote their Pawn to.
 * Used to update the GameBoard with the new piece.
 */
public class PawnPromotedEvent {
    private AbstractPiece mPawnToPromote;

    public PawnPromotedEvent(AbstractPiece pawnToPromote) {
        mPawnToPromote = pawnToPromote;
    }

    public AbstractPiece getPawnToPromote() {
        return mPawnToPromote;
    }
}
