package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SinglePlayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings_screen_singleplayer);

        int orientation = getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load the landscape layout
            setContentView(R.layout.fragment_settings_screen_singleplayer_land);
        } else {
            // Load the portrait layout
            setContentView(R.layout.fragment_settings_screen_singleplayer);
        }

        // Find the 3x3 button by its ID
        Button three = findViewById(R.id.board_3x3_button);
        Button four = findViewById(R.id.board_4x4_button);
        Button five = findViewById(R.id.board_5x5_button);

        // OnClickListener button
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the new activity
                Intent intent = new Intent(SinglePlayerActivity.this, ThreeByThree.class);
                startActivity(intent);
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the new activity
                Intent intent = new Intent(SinglePlayerActivity.this, FourByFour.class);
                startActivity(intent);
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // When the button is clicked, start the new activity
                Intent intent = new Intent(SinglePlayerActivity.this, FiveByFive.class);
                startActivity(intent);
            }
        });
    }

}