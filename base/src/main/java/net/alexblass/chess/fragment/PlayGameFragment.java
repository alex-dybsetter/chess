package net.alexblass.chess.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import net.alexblass.chess.adapter.ChessBoardAdapter;
import net.alexblass.chess.base.R;
import net.alexblass.chess.model.GameBoard;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This fragment displays the game data for the current active game.
 * Users interact with this fragment to play the game.
 */
public class PlayGameFragment extends Fragment {

    private GridView mGridView;

    private ChessBoardAdapter mChessBoardAdapter;

    public PlayGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_play_game, container, false);
        ButterKnife.bind(this, view);

        mGridView = view.findViewById(R.id.chessBoardGridView);
        initializeChessBoard();

        return view;
    }

    private void initializeChessBoard() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

        mChessBoardAdapter = new ChessBoardAdapter(getContext(), new GameBoard(), metrics);

        ViewGroup.LayoutParams layoutParams = mGridView.getLayoutParams();
        layoutParams.width = mChessBoardAdapter.getGridViewSizeFromChessSquareSize();
        mGridView.setLayoutParams(layoutParams);
        mGridView.setAdapter(mChessBoardAdapter);

        setChessBoardClickListener();
    }

    private void setChessBoardClickListener() {

    }
}
