package net.alexblass.chess.constant;

import net.alexblass.chess.base.R;

/**
 * A class to keep track of the constants that will be accessed by the game logic.
 */
public class Constants {

    public static final String QUEEN = "queen";
    public static final String KING = "king";
    public static final String ROOK = "rook";
    public static final String BISHOP = "bishop";
    public static final String KNIGHT = "knight";
    public static final String PAWN = "pawn";

    public static final int COLOR_RESOURCE_WHITE = R.color.color_white; // White goes first in the game
    public static final int COLOR_RESOURCE_BLACK = R.color.color_black;

    public static final int BOARD_LENGTH = 8;
    public static final int HOME_ROW_WHITE = 7;
    public static final int HOME_ROW_BLACK = 0;

    /* Board should look like:
                 0 1 2 3 4 5 6 7
                 _ _ _ _ _ _ _ _
              0 |_|_|_|_|_|_|_|_| // Black home row
              1 |_|_|_|_|_|_|_|_| // Black home row
              2 |_|_|_|_|_|_|_|_|
              3 |_|_|_|_|_|_|_|_|
              4 |_|_|_|_|_|_|_|_|
              5 |_|_|_|_|_|_|_|_|
              6 |_|_|_|_|_|_|_|_| // White home row
              7 |_|_|_|_|_|_|_|_| // White home row
         */
    // There are 64 tiles for each space on the board
    // We're holding the list of tiles as an Array of Pieces
    // so we can populate it into a GridView with our Adapter
    // To determine the position in the list with an x and y
    // coordinate, use (x * BOARD_LENGTH) + y. This formula
    // is already saved in our Piece class in mListPosition

    public static final int CHESS_SQUARE_SIZE_UNSPECIFIED = -1;
    public static final int NO_PIECE_SELECTED = -1;
}
