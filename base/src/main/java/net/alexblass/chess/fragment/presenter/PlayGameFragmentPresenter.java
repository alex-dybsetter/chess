package net.alexblass.chess.fragment.presenter;

import android.content.Context;
import android.util.Pair;

import net.alexblass.chess.adapter.ChessBoardAdapter;
import net.alexblass.chess.base.R;
import net.alexblass.chess.fragment.PlayGameFragment;
import net.alexblass.chess.model.Game;
import net.alexblass.chess.model.GameBoard;
import net.alexblass.chess.model.piece.AbstractPiece;

public class PlayGameFragmentPresenter {
    private Context mContext;
    private PlayGameFragment mView;

    private Game mGame;
    private Pair<Integer, Integer> mFirstClickCoordinates;
    private Pair<Integer, Integer> mSecondClickCoordinates;
    private AbstractPiece mSelectedPiece;

    public PlayGameFragmentPresenter(PlayGameFragment view, Context context) {
        mContext = context;
        mView = view;
        mGame = new Game(1);
    }

    public void handleClick(GameBoard gameBoard, int position) {
        int row = ChessBoardAdapter.convertPositionToRow(position);
        int col = ChessBoardAdapter.convertPositionToCol(position);

        if (mFirstClickCoordinates == null) {
            mFirstClickCoordinates = new Pair<>(row, col);
            handleFirstClick(gameBoard, position);
        } else {
            mSecondClickCoordinates = new Pair<>(row, col);
            handleSecondClick(gameBoard, position, row, col);
        }
    }

    private void handleFirstClick(GameBoard gameBoard, int position) {
        mSelectedPiece = gameBoard.getPieceAtPosition(position);
        if (mSelectedPiece == null) {
            mFirstClickCoordinates = null;
            mView.showErrorToast(R.string.invalid_move_invalid_piece);
        } else if (!mSelectedPiece.getColor().equals(mGame.getActiveTurn())) {
            mFirstClickCoordinates = null;
            mView.showErrorToast(R.string.invalid_move_invalid_color);
        } else {
            mView.toggleSelectPiece(position);
        }
    }

    private void handleSecondClick(GameBoard gameBoard, int position, int row, int col) {
        if (mFirstClickCoordinates.equals(mSecondClickCoordinates)) {
            mView.toggleSelectPiece(position);
            resetClicks();
            return;
        }

        if (mSelectedPiece != null) {
            mView.movePiece(mSelectedPiece, row, col);
            mView.toggleSelectPiece(position);
            mGame.nextTurn();
            resetClicks();
        }
    }

    private void resetClicks() {
        mFirstClickCoordinates = null;
        mSecondClickCoordinates = null;
    }
}
