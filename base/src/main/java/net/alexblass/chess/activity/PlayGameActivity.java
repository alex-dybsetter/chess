package net.alexblass.chess.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import net.alexblass.chess.base.R;
import net.alexblass.chess.fragment.PlayGameFragment;

/***
* This activity is for playing active games.
**/
public class PlayGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        if (savedInstanceState != null) {
            return;
        }

        PlayGameFragment playGameFragment = new PlayGameFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.play_game_container, playGameFragment, playGameFragment.getTag())
                .commit();
    }
}
