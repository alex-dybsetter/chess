package net.alexblass.chess.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import net.alexblass.chess.base.R;
import net.alexblass.chess.constant.Constants;
import net.alexblass.chess.model.GameBoard;
import net.alexblass.chess.model.piece.AbstractPiece;

import static net.alexblass.chess.constant.Constants.BOARD_LENGTH;

/**
 * An adapter to correctly display the game tiles on the chess board.
 */
public class ChessBoardAdapter extends ArrayAdapter {

    private Context mContext;
    private GameBoard mGameBoard;
    private DisplayMetrics mMetrics;
    private int mChessSquareSizeInDp;
    private int mSelectedPieceIndex;

    public ChessBoardAdapter(Context context, GameBoard gameBoard, DisplayMetrics metrics) {
        super(context, R.layout.item_chess_board_square, gameBoard.getPiecePlacementArray());
        mContext = context;
        mGameBoard = gameBoard;
        mMetrics = metrics;
        mChessSquareSizeInDp = -1;
        mSelectedPieceIndex = Constants.NO_PIECE_SELECTED;
    }

    public ChessBoardAdapter(Context context, GameBoard gameBoard, int chessSquareSizeInDp) {
        super(context, R.layout.item_chess_board_square, gameBoard.getPiecePlacementArray());
        mContext = context;
        mGameBoard = gameBoard;
        mMetrics = null;
        mChessSquareSizeInDp = chessSquareSizeInDp;
    }

    public AbstractPiece getItem(int row, int col) {
        return mGameBoard.getPieceAtCoordinates(row, col);
    }

    @Override
    public AbstractPiece getItem(int position) {
        return mGameBoard.getPieceAtPosition(position);
    }

    @Override
    public int getCount() {
        return mGameBoard.getSize();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View gameBoardSquare = convertView;
        ChessSquareViewHolder viewHolder;

        AbstractPiece piece = getItem(position);
        if (gameBoardSquare == null) {
            gameBoardSquare = LayoutInflater.from(mContext)
                    .inflate(R.layout.item_chess_board_square, parent, false);
            viewHolder = new ChessSquareViewHolder();
            viewHolder.chessSquareImageView = gameBoardSquare.findViewById(R.id.chessBoardSquareImageView);
            gameBoardSquare.setTag(viewHolder);
        } else {
            viewHolder = (ChessSquareViewHolder) gameBoardSquare.getTag();
        }

        viewHolder.setSquareColor(mContext, position, mSelectedPieceIndex);

        if (mChessSquareSizeInDp == -1) {
            viewHolder.setSquareDimens(mMetrics);
        } else {
            viewHolder.setSquareDimens(mChessSquareSizeInDp);
        }

        int resId = (piece != null) ? piece.getImageResId() : 0;
        viewHolder.chessSquareImageView.setImageResource(resId);

        return gameBoardSquare;
    }

    public void toggleSelectPiece(int position) {
        mSelectedPieceIndex = mSelectedPieceIndex == Constants.NO_PIECE_SELECTED ?
                position : Constants.NO_PIECE_SELECTED;
        notifyDataSetChanged();
    }

    public void movePiece(AbstractPiece piece, int row, int col) {
        mGameBoard.movePiece(piece, row, col);
        notifyDataSetChanged();
    }

    public GameBoard getGameBoard() {
        return mGameBoard;
    }

    public int getGridViewSizeFromChessSquareSize() {
        return (getScreenShortSide(mMetrics) / Constants.BOARD_LENGTH) * BOARD_LENGTH;
    }

    // public static methods //////////////////////////////////////////////////////////////////////
    public static int convertPositionToRow(int position) {
        return position / Constants.BOARD_LENGTH;
    }

    public static int convertPositionToCol(int position) {
        return position % Constants.BOARD_LENGTH;
    }

    public static int convertRowColToPosition(int row, int col) {
        return row * Constants.BOARD_LENGTH + col;
    }

    // private static helper methods //////////////////////////////////////////////////////////////
    private static int getScreenShortSide(DisplayMetrics metrics) {
        int screenHeight = metrics.heightPixels;
        int screenWidth = metrics.widthPixels;
        return (screenHeight < screenWidth) ? screenHeight : screenWidth;
    }

    // Viewholder /////////////////////////////////////////////////////////////////////////////////
    static class ChessSquareViewHolder {
        ImageView chessSquareImageView;

        private void setSquareColor(Context context, int position, int selectedPosition){
            int row = convertPositionToRow(position);
            int col = convertPositionToCol(position);
            int colorId;

            /*
             * Determine which row color pattern is starting:
             * 0 and even rows start with white
             * Odd rows start with black
             * */
            boolean rowStartsWithWhite = row % 2 == 0;
            if (rowStartsWithWhite) {
                colorId = col % 2 == 0 ? R.color.color_white : R.color.color_black;
            } else {
                colorId = col % 2 == 0 ? R.color.color_black : R.color.color_white;
            }
            if (selectedPosition == position) {
                colorId = R.color.color_selected;
            }
            chessSquareImageView.setBackgroundColor(ContextCompat.getColor(context, colorId));
        }

        /**
        * Set the chess square dimensions based off of the device size.
        **/
        private void setSquareDimens(DisplayMetrics metrics) {
            int chessSquareSize = (getScreenShortSide(metrics) / Constants.BOARD_LENGTH);

            chessSquareImageView.getLayoutParams().width = chessSquareSize;
            chessSquareImageView.getLayoutParams().height = chessSquareSize;
        }

        /**
         * Set the chess square dimensions based off of a predetermined size.
         **/
        private void setSquareDimens(int chessSquareSizeInDp) {
            chessSquareImageView.getLayoutParams().width = chessSquareSizeInDp;
            chessSquareImageView.getLayoutParams().height = chessSquareSizeInDp;
        }
    }
}