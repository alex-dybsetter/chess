package net.alexblass.chess.fragment.presenter;

import android.content.Context;
import android.util.Pair;

import net.alexblass.chess.adapter.ChessBoardAdapter;
import net.alexblass.chess.base.R;
import net.alexblass.chess.constant.Constants;
import net.alexblass.chess.fragment.PlayGameFragment;
import net.alexblass.chess.model.Game;
import net.alexblass.chess.model.GameBoard;
import net.alexblass.chess.model.piece.AbstractPiece;
import net.alexblass.chess.model.piece.KingPiece;
import net.alexblass.chess.model.piece.RookPiece;

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
            handleSecondClick(gameBoard, position);
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

    private void handleSecondClick(GameBoard gameBoard, int position) {
        if (mFirstClickCoordinates.equals(mSecondClickCoordinates)) {
            mView.toggleSelectPiece(position);
            resetClicks();
            return;
        }

        if (!mSelectedPiece.isValidMove(gameBoard, mSecondClickCoordinates.first, mSecondClickCoordinates.second)) {
            mView.showErrorToast(R.string.invalid_move_invalid_move);
            return;
        }

        if (mSelectedPiece != null) {
            mView.movePiece(mSelectedPiece, mSecondClickCoordinates.first, mSecondClickCoordinates.second);
            mView.toggleSelectPiece(position);
            mGame.nextTurn();
            resetClicks();
        }
    }

    public void castlingMoveRookPiece(GameBoard gameBoard) {
        int castlingKingColDelta = mSecondClickCoordinates.second - mFirstClickCoordinates.second;
        int rookCol = mSecondClickCoordinates.second;
        rookCol += castlingKingColDelta > 0 ? Constants.CASTLING_ROOK_MOVE_LEFT : Constants.CASTLING_ROOK_MOVE_RIGHT;

        RookPiece castlingRook = ((KingPiece) mSelectedPiece)
                .getCastlingRook(gameBoard, mSecondClickCoordinates.first, castlingKingColDelta);
        mView.movePiece(castlingRook, mSecondClickCoordinates.first, rookCol);
    }

    private void resetClicks() {
        mFirstClickCoordinates = null;
        mSecondClickCoordinates = null;
    }
}
