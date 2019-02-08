package net.alexblass.chess.model;

import net.alexblass.chess.base.R;

public enum PieceColor {
    BLACK(R.string.string_color_black),
    WHITE(R.string.string_color_white);

    private int mColorStringId;

    PieceColor(int stringId) {
        mColorStringId = stringId;
    }
}
