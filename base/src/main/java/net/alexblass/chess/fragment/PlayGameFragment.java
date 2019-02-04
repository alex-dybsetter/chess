package net.alexblass.chess.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.alexblass.chess.base.R;

/**
 * This fragment displays the game data for the current active game.
 * Users interact with this fragment to play the game.
 */
public class PlayGameFragment extends Fragment {

    public PlayGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_play_game, container, false);
    }
}
