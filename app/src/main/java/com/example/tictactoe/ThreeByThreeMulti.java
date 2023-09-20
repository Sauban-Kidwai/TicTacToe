
package com.example.tictactoe;

    import androidx.appcompat.app.AppCompatActivity;
    import android.content.Intent;
    import android.content.res.Configuration;
    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;

public class ThreeByThreeMulti extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_game_board_3x3_lands);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_game_board_3x3);
        }

        // Add more buttons and logic for the rest of the grid cells...
    }
}
